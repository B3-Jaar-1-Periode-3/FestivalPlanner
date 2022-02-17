package Data;
import java.io.Serializable;
import java.util.ArrayList;

public class Event implements Serializable {

    private ArrayList<Artist> artists;
    private Genre genre;
    private int startTime;
    private int endTime;
    private Podium podium;
    private int ID;
    private int populariteit;

    public Event(int startTime, int endTime, int populariteit) {
        this.artists = new ArrayList<>();
        this.genre = new Genre();
        this.startTime = startTime;
        this.endTime = endTime;
        this.podium = new Podium(ID);
        this.populariteit = populariteit;
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

    public  int getPopulariteit() {
        return populariteit;
    }
}
