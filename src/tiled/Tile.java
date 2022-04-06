package tiled;

import java.awt.geom.Point2D;
import java.io.Serializable;

public class Tile implements Serializable {

    private int x;
    private int y;
    private final double width = 32.0;
    private final double height = 32.0;

    public Tile(int x, int y, boolean isInTile) {
        if (isInTile) {
            this.x = x;
            this.y = y;
        } else {
            this.x = (int) Math.floor(x / width);
            this.y = (int) Math.floor(y / height);
        }
    }

    public Point2D getCenter() {
        return new Point2D.Double(x + width/2, y + height/2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
