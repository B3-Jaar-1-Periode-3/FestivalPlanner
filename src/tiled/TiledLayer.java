package tiled;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TiledLayer {
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;
    private int[][] tileValues;

    public TiledLayer(JsonObject objectlayer) {
        this.width = objectlayer.getInt("width");
        this.height = objectlayer.getInt("height");
        if (objectlayer.containsKey("offsetx")) {
            this.offsetX = objectlayer.getInt("offsetx");
            this.offsetY = objectlayer.getInt("offsety");
        } else {
            this.offsetX = 0;
            this.offsetY = 0;
        }
        tileValues = new int[width][height];
    }

    public void addValueToArray(int height, int width, int data) {
        if (tileValues.length > width && tileValues[width].length > height) {
            tileValues[width][height] = data;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        ArrayList<String> tileValuesAsString = new ArrayList<>();
        for(int i = 0; i < tileValues.length; i++) {
            for(int j = 0; j < tileValues[i].length; j++) {
                if(tileValues[i][j] != 0) {
                    tileValuesAsString.add("(" + i + "," + j + ") " + tileValues[i][j]);
                }
            }
        }
        
        return "TiledLayer" +
                "\nwidth= " + width +
                "\nheight= " + height +
                "\noffsetX= " + offsetX +
                "\noffsetY= " + offsetY +
                "\nValues= " + tileValuesAsString;
    }
}
