package tiled;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import java.util.ArrayList;

public class TiledMap {
    private ArrayList<TiledLayer> layers;

    public TiledMap(String fileName) {
        layers = new ArrayList<>();
        JsonReader reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        root.getJsonArray("layers").forEach(jsonValue -> {
            JsonObject objectLayer = jsonValue.asJsonObject();
            TiledLayer layer = new TiledLayer(objectLayer);

            JsonArray layerData = jsonValue.asJsonObject().getJsonArray("data");
            for (int i = 0; i < layerData.size(); i++) {
                int data = layerData.getInt(i);
                layer.addValueToArray(i / layer.getWidth(), i % layer.getWidth(), data);
            }
            layers.add(layer);
        });

        root.getJsonArray("tilesets").forEach(tileSet -> {
            TileSetManager.getInstance().addTileSet(new TileSet(tileSet.asJsonObject()));
        });
    }

    public void draw(FXGraphics2D graphics2D) {
        System.out.println("Drawing Map");
        for (TiledLayer layer : layers) {
            layer.draw(graphics2D);
        }
    }
}
