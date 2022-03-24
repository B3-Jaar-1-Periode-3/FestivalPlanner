package data;
import tiled.TiledObject;

import java.io.Serializable;

public class Podium implements Serializable {

    private int id;
    private String name;
    private TiledObject object;

    public Podium(int ID, String name, TiledObject object) {
        this.id = ID;
        this.name = name;
        this.object = object;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public TiledObject getObject() {
        return object;
    }

    @Override
    public String toString() {
        return name;
    }
}
