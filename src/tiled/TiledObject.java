package tiled;

import javax.json.JsonObject;
import java.awt.geom.Point2D;

public class TiledObject {

    private String name;
    private int width;
    private int height;
    private int x;
    private int y;

    public TiledObject(JsonObject object) {
        this.name = object.getString("name");
        System.out.println("Loaded podium " + name);
        this.width = object.getInt("width");
        this.height = object.getInt("height");
        this.x = object.getInt("x");
        this.y = object.getInt("y");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tile getCenterTile() {
        return new Tile(x + width/2, y + height/2, false);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
