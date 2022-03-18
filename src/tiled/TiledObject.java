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
}
