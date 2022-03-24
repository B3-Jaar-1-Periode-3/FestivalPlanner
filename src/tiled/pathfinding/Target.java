package tiled.pathfinding;

import tiled.TiledLayer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Target {

    private TiledLayer collisionLayer;
    private int tileX;
    private int tileY;
    private double[][] reached;

    public Target(TiledLayer collisionLayer, Point2D start) {
        this.collisionLayer = collisionLayer;
        this.tileX = (int) start.getX();
        this.tileY = (int) start.getY();
        this.reached = new double[collisionLayer.getWidth()+1][collisionLayer.getHeight()+1];

        Queue<Point2D> frontier = new LinkedList<>();
        frontier.offer(start);
        reached[tileY][tileX] = 0;

        ArrayList<Point2D> values = new ArrayList<>(Arrays.asList(new Point2D.Double(1,0), new Point2D.Double(-1,0), new Point2D.Double(0,1), new Point2D.Double(0,-1)));
        while (!frontier.isEmpty()) {
            Point2D current = frontier.poll();
            for (Point2D value : values) {
                Point2D tileToCheck = new Point2D.Double(current.getX() + value.getX(), current.getY() + value.getY());
                if (tileToCheck.getX() < 0 || tileToCheck.getX() >= collisionLayer.getWidth() || tileToCheck.getY() < 0 || tileToCheck.getY() >= collisionLayer.getHeight()) {
                    continue;
                }
                if (reached[(int) tileToCheck.getY()][(int) tileToCheck.getX()] == Integer.MAX_VALUE) {
                    if (!collisionLayer.getCollision().contains(collisionLayer.getTileValues()[(int) tileToCheck.getX()][(int) tileToCheck.getY()])) {
                        reached[(int) tileToCheck.getY()][(int) tileToCheck.getX()] = reached[(int) current.getY()][(int) current.getX()] + 1;
                        frontier.offer(tileToCheck);
                    }
                }
            }
        }
    }
}
