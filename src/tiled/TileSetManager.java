package tiled;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public class TileSetManager {
    private static TileSetManager tileSetManager;
    ArrayList<TileSet> tileSets;

    public TileSetManager() {
        tileSets = new ArrayList<>();
    }

    public static TileSetManager getInstance() {
        if (tileSetManager == null) {
            tileSetManager = new TileSetManager();
        }
        return tileSetManager;
    }

    public void addTileSet(TileSet tileSet) {
        this.tileSets.add(tileSet);
    }

    public BufferedImage getTile(int id) {
        for (TileSet tileSet : tileSets) {
            if (tileSet.contains(id)) {
                return tileSet.getTile(id);
            }
        }
        return null;
    }
}
