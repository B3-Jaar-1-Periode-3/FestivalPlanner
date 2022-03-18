package tiled;

import javax.json.JsonObject;

public class TiledObject {
    private String name;
    private double width;
    private double height;
    private double x;
    private double y;

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

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
