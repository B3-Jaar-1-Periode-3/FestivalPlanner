package tiled;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonArray;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TiledMap {
    private ArrayList<TiledLayer> layers;
    private ArrayList<TiledObjectLayer> objectLayers;
    private ArrayList<Tile> tiles;
    private TiledLayer collisionLayer;
    private Point2D spawn;

    public TiledMap(String fileName) {
        layers = new ArrayList<>();
        objectLayers = new ArrayList<>();
        JsonReader reader = Json.createReader(getClass().getClassLoader().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        root.getJsonArray("layers").forEach(jsonValue -> {
            System.out.println(jsonValue.asJsonObject().getString("type"));
            if (jsonValue.asJsonObject().getString("type").equals("objectgroup")) {
                System.out.println("Added object layer");
                objectLayers.add(new TiledObjectLayer(jsonValue.asJsonObject()));
            } else if (!jsonValue.asJsonObject().getString("name").equals("Collision")) {
                JsonObject objectLayer = jsonValue.asJsonObject();
                TiledLayer layer = new TiledLayer(objectLayer);

                JsonArray layerData = jsonValue.asJsonObject().getJsonArray("data");
                for (int i = 0; i < layerData.size(); i++) {
                    int data = layerData.getInt(i);
                    layer.addValueToArray(i / layer.getWidth(), i % layer.getWidth(), data);
                }
                layers.add(layer);
            } else {
                collisionLayer = new TiledLayer(jsonValue.asJsonObject());

                JsonArray collisionData = jsonValue.asJsonObject().getJsonArray("data");
                for (int i = 0; i < collisionData.size(); i++) {
                    int data = collisionData.getInt(i);
                    collisionLayer.addValueToArray(i / collisionLayer.getWidth(), i % collisionLayer.getWidth(), data);
                }
            }
        });

        init();

        root.getJsonArray("tilesets").forEach(tileSet -> {
            TileSetManager.getInstance().addTileSet(new TileSet(tileSet.asJsonObject()));
        });
    }

    public void draw(FXGraphics2D graphics2D) {
        for (TiledLayer layer : layers) {
            layer.draw(graphics2D);
        }
    }

    public void init() {
        TiledObject objectSpawn = getObject("Male Spawn");
        spawn = getCenter(objectSpawn);
    }

    public Point2D getCenter(TiledObject object) {
        return new Point2D.Double(object.getX() + (object.getWidth()/2), object.getY());
    }

    public TiledObject getObject(String object) {
        for (TiledObjectLayer objectLayer : objectLayers) {
            for (TiledObject objectLayerObject : objectLayer.getObjects()) {
                if (objectLayerObject.getName().equals(object)) {
                    return objectLayerObject;
                }
            }
        }
        return null;
    }

    public TiledLayer getCollisionLayer() {
        return collisionLayer;
    }

    public ArrayList<TiledLayer> getLayers() {
        return layers;
    }

    public ArrayList<TiledObjectLayer> getObjectLayers() {
        return objectLayers;
    }

    public Point2D getSpawn() {
        return spawn;
    }

    public void setSpawn(Point2D spawn) {
        this.spawn = spawn;
    }
}
