package data;
import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements Serializable {

    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean isFree(Event event) {
        for (Event eventForArtist : Festival.getInstance().getEventsForArtist(this)) {
            if (event.getStartTime().isBefore(eventForArtist.getEndTime()) && event.getEndTime().isAfter(eventForArtist.getStartTime()) && !event.equals(eventForArtist)) {
               return false;
            }
        }
        return true;
    }
}
