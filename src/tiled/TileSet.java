package tiled;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class TileSet {
    private int columns;
    private int tileWidth;
    private int tileHeight;
    private int tileCount;
    private int firstGid;
    private String imageName;
    private String name;
    private HashMap<Integer, BufferedImage> tiles;

    public TileSet(JsonObject tiledSet) {
        columns = tiledSet.getInt("columns");
        tileWidth = tiledSet.getInt("tilewidth");
        tileHeight = tiledSet.getInt("tileheight");
        tileCount = tiledSet.getInt("tilecount");
        firstGid = tiledSet.getInt("firstgid");
        imageName = tiledSet.getString("image");
        name = tiledSet.getString("name");
        tiles = new HashMap<>();

        final BufferedImage image;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResource(imageName));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        for (int i = 0; i < tileCount; i++) {
            BufferedImage currentTile = image.getSubimage(i%columns * tileWidth, i/columns * tileHeight, tileWidth,tileHeight);
            tiles.put(i + firstGid, currentTile);
        }
    }

    public boolean contains(int id) {
        return tiles.containsKey(id);
    }

    public BufferedImage getTile(int id) {
        return tiles.get(id);
    }
}
