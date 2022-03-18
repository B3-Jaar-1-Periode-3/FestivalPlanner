package data;
import java.io.Serializable;

public class Podium implements Serializable {

    private int id;
    private String name;

    public Podium(int ID, String name) {
        this.id = ID;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
