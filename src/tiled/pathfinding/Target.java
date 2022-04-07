package tiled.pathfinding;

import org.jfree.fx.FXGraphics2D;
import tiled.Tile;
import tiled.TiledLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Target implements Serializable {

    private TiledLayer collisionLayer;
    private int tileX;
    private int tileY;
    private Point2D tileCenter;
    private int[][] reached;

    public static final HashMap<RenderingHints.Key, Object> RenderingProperties = new HashMap<>();

    static {
        RenderingProperties.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        RenderingProperties.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        RenderingProperties.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    }

    public Target(TiledLayer collisionLayer, Tile tile) {
        this.collisionLayer = collisionLayer;
        this.tileX = tile.getX();
        this.tileY = tile.getY();
        this.tileCenter = tile.getCenter();
        System.out.println(tileX);
        System.out.println(tileY);
        this.reached = new int[collisionLayer.getWidth()+1][collisionLayer.getHeight()+1];
        for (int y = 0; y <= collisionLayer.getHeight(); y++) {
            for (int x = 0; x <= collisionLayer.getWidth(); x++) {
                reached[x][y] = Integer.MAX_VALUE;
            }
        }

        System.out.println("Created 2d map with [" + collisionLayer.getHeight() + "] [" + collisionLayer.getWidth() + "]");

        Queue<Tile> frontier = new LinkedList<>();
        frontier.offer(tile);
        reached[tileY][tileX] = 0;

        ArrayList<Tile> values = new ArrayList<>(Arrays.asList(new Tile(1,0, true), new Tile(-1,0, true), new Tile(0,1, true), new Tile(0,-1, true)));
        while (!frontier.isEmpty()) {
/*            System.out.println("Beginning");*/
            Tile current = frontier.poll();
            for (Tile value : values) {
                Tile tileToCheck = new Tile(current.getX() + value.getX(), current.getY() + value.getY(), true);
                if (tileToCheck.getX() < 0 || tileToCheck.getX() >= collisionLayer.getWidth() || tileToCheck.getY() < 0 || tileToCheck.getY() >= collisionLayer.getHeight()) {
                    System.out.println("Returning");
                    continue;
                }
/*                System.out.println("Checking tile");*/
                if (reached[tileToCheck.getY()][tileToCheck.getX()] == Integer.MAX_VALUE) {
                    if (collisionLayer.getTileValues()[tileToCheck.getX()][tileToCheck.getY()] == 0) {
                        reached[tileToCheck.getY()][tileToCheck.getX()] = reached[current.getY()][current.getX()] + 1;
                        frontier.offer(tileToCheck);
                    }
                }
            }
        }
        print();
    }

    public Point2D getDirection(int tileX, int tileY) {
        ArrayList<Point2D> directions = new ArrayList<>(Arrays.asList(new Point2D.Double(1,0), new Point2D.Double(-1,0), new Point2D.Double(0,1), new Point2D.Double(0,-1)));

        Point2D pointToGo = new Point2D.Double(0,0);
        if (tileY > 0 && reached.length > tileY && tileX > 0 && reached[tileY].length > tileX) {
            double currentPoint = reached[tileY][tileX];
            for (Point2D direction : directions) {
                Point2D newPoint = new Point2D.Double(tileX + direction.getX(), tileY + direction.getY());
                if (newPoint.getY() > 0 && reached.length > newPoint.getY() && newPoint.getX() > 0 && reached[(int) newPoint.getY()].length > newPoint.getX()) {
                    if (reached[(int) newPoint.getY()][(int) newPoint.getX()] < currentPoint) {
                        currentPoint = reached[(int) newPoint.getY()][(int) newPoint.getX()];
                        pointToGo = direction;
                    }
                }
            }
        }
        return pointToGo;
    }

    public HashMap<Integer, BufferedImage> images = new HashMap<>();
    public void draw(FXGraphics2D graphics) {
        for(int i = 0; i < reached.length; i++) {
            for (int j = 0; j < reached[i].length; j++) {
                int value = reached[i][j];
                if(value == Integer.MAX_VALUE) {
                    value = -1;
                }
                BufferedImage image;
                if(images.containsKey(value)) {
                    image = images.get(value);
                } else {
                    image = textToImage(value + "", new JLabel("").getFont(), 12);
                    images.put(value, image);
                }
                //graphics.drawString(value + "", j * 32 + 16, i * 32 + 16);

                AffineTransform transformImage = graphics.getTransform();
                transformImage.translate(j * 32 + 8, i * 32 + 8);
                graphics.drawImage(image,transformImage, null);

            }
        }

    }

    public static BufferedImage textToImage(String Text, Font f, float Size){
        //Derives font to new specified size, can be removed if not necessary.
        f = f.deriveFont(Size);

        FontRenderContext frc = new FontRenderContext(null, true, true);

        //Calculate size of buffered image.
        LineMetrics lm = f.getLineMetrics(Text, frc);

        Rectangle2D r2d = f.getStringBounds(Text, frc);

        BufferedImage img = new BufferedImage((int)Math.ceil(r2d.getWidth()), (int)Math.ceil(r2d.getHeight()), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = img.createGraphics();

        g2d.setRenderingHints(RenderingProperties);

        g2d.setBackground(Color.WHITE);
        g2d.setColor(Color.BLACK);

        g2d.clearRect(0, 0, img.getWidth(), img.getHeight());

        g2d.drawString(Text, 0, lm.getAscent());

        g2d.dispose();

        return img;
    }

    public void print() {
        for(int i = 19; i < 30; i++) {
            for(int j = 85; j < 105; j++) {
                int value = reached[i][j];
                if(value == Integer.MAX_VALUE) {
                    System.out.print("-1\t");
                } else {
                    System.out.print(value + "\t");
                }
            }
            System.out.println();
        }
    }

    public Point2D getTileCenter() {
        return tileCenter;
    }
}
