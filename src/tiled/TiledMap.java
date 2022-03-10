package tiled;

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
                layer.addValueToArray(i%layer.getHeight(), i/layer.getWidth(), data);
            }
            layers.add(layer);
            System.out.println(layer);
        });
        
    }
}
