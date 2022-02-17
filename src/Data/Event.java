package Data;
import java.util.ArrayList;

public class Event {

    private ArrayList<Artist> artists;
    private Genre genre;
    private int startTime;
    private int endTime;
    private Podium podium;
    private int ID;

    public Event(int startTime, int endTime) {
        this.artists = new ArrayList<>();
        this.genre = new Genre();
        this.startTime = startTime;
        this.endTime = endTime;
        this.podium = new Podium(ID);
    }

    public void addArtists(Artist artist) {
        this.artists.add(artist);
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public Podium getPodium() {
        return podium;
    }
}
