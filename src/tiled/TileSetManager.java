package tiled;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileSetManager {
    ArrayList<TileSet> tileSets;

    public TileSetManager() {
        tileSets = new ArrayList<>();
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
