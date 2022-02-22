package Data;
import java.io.Serializable;

public class Podium implements Serializable {

    private int id;

    public Podium(int ID) {
        this.id = ID;
    }

    public int getID() {
        return id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
