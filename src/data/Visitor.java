package data;

import org.jfree.fx.FXGraphics2D;
import tiled.Tile;
import tiled.TiledLayer;
import tiled.TiledObject;
import tiled.pathfinding.Target;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor extends Npc {

    transient private ArrayList<BufferedImage> femaleSprites;
    transient private ArrayList<BufferedImage> maleSprites;
    private boolean targetIsSet;
    private Event event;

    public Visitor(Target target, Event event) {
        super(target);
        this.event = event;
        femaleSprites = new ArrayList<>();
        maleSprites = new ArrayList<>();

        try {
            System.out.println("Creating new visitor");
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("betty.png"));
            int spriteWidth = image.getWidth() / 4;
            int spriteHeight = image.getHeight() / 4;
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    femaleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = ImageIO.read(getClass().getClassLoader().getResource("george.png"));
            int spriteWidth = image.getWidth() / 4;
            int spriteHeight = image.getHeight() / 4;
            for (int x = 0; x < 4; x++) {
                for (int y = 0; y < 4; y++) {
                    maleSprites.add(image.getSubimage(x * spriteHeight, y * spriteHeight, spriteWidth, spriteHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        if (isSpawned()) {
            AffineTransform transform = graphics.getTransform();
            transform.translate(getPosition().getX(), getPosition().getY());
            graphics.drawImage(maleSprites.get(0), transform, null);
        }
    }

    @Override
    public void update(double deltaTime, TiledLayer collisionLayer, Tile tile) {
        if (isSpawned()) {
            int tileX = (int) Math.floor(getPosition().getX()/32);
            int tileY = (int) Math.floor(getPosition().getY()/32);
            Point2D direction = target.getDirection(tileX, tileY);
            setPosition(new Point2D.Double(position.getX() + (direction.getX() * (4 * Time.getSpeed())), position.getY() + (direction.getY() * (4 * Time.getSpeed()))));
            if (Time.getTime().isAfter(event.getEndTime())) {
                if (!targetIsSet) {
                    setTarget(new Target(collisionLayer, tile));
                    targetIsSet = true;
                }
                Point2D exit = new Point2D.Double(tile.getX(), tile.getY());
                System.out.println("Exit location is " + exit);
                if (this.position.getX() / 32 < 110 && this.position.getX() / 32 > 100 && this.position.getY() / 32 < 80 && this.position.getY() / 32 > 70) {
                    this.target = null;
                }
                if (target == null) {
                    this.spawned = false;
                    Festival.getInstance().getVisitors().remove(this);
                }
            }
        }
    }

    @Override
    public void exit(Tile tile, TiledLayer collisionLayer) {
        this.target = new Target(collisionLayer, tile);
        System.out.println("Exit location" + tile);
    }

    public void setTargetIsSet(boolean bool) {
        targetIsSet = bool;
    }

    public boolean getTargetIsSet() {
        return this.targetIsSet;
    }

    public ArrayList<BufferedImage> getFemaleSprites() {
        return femaleSprites;
    }

    public ArrayList<BufferedImage> getMaleSprites() {
        return maleSprites;
    }
}
