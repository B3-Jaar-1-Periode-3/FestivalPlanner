package tiled;

import javax.json.JsonObject;
import java.util.ArrayList;

public class TiledObjectLayer {
    private ArrayList<TiledObject> objects;
    private int x;
    private int y;
    private String name;

    public TiledObjectLayer(JsonObject object) {
        this.x = object.getInt("x");
        this.y = object.getInt("y");

        this.name = object.getString("name");

        objects = new ArrayList<>();
        object.getJsonArray("objects").forEach(jsonValue -> {
            objects.add(new TiledObject(jsonValue.asJsonObject()));
        });
    }

    public ArrayList<TiledObject> getObjects() {
        return objects;
    }
}
