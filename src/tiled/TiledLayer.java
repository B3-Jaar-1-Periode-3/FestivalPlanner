package tiled;

import org.jfree.fx.FXGraphics2D;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

public class TiledLayer {
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;
    private int[][] tileValues;
    private ArrayList<Integer> collision;

    public TiledLayer(JsonObject objectlayer) {
        this.collision = new ArrayList<>(Arrays.asList(3294, 3295, 3296));
        System.out.println(objectlayer.getString("name"));
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

    public void draw(FXGraphics2D graphics2D) {
        for (int i = 0; i < tileValues.length; i++) {
            for (int j = 0; j < tileValues[i].length; j++) {
                int data = tileValues[i][j];
                if (data == 0) {
                    continue;
                }
                BufferedImage image = TileSetManager.getInstance().getTile(data);
                AffineTransform transform = graphics2D.getTransform();
                transform.translate(i * 32 + offsetX, j * 32 + offsetY);
                graphics2D.drawImage(image, transform, null);
            }
        }
    }

    public int[][] getTileValues() {
        return tileValues;
    }

    public ArrayList<Integer> getCollision() {
        return collision;
    }
}
