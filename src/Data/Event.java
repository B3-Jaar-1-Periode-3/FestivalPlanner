package Data;
import java.util.ArrayList;

public class Event {

    private ArrayList<Artist> artists;
    //TODO Genre

    public Event() {
    }

    public void addArtists(Artist artist) {
        this.artists.add(artist);
    }
}
