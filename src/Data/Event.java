package Data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Event implements Serializable {

    private ArrayList<Artist> artists;
    private Genre genre;
    private int startTime;
    private int endTime;
    private Podium podium;
    private int ID;
    private double popularity;

    public Event(int startTime, int endTime, Genre genre, Podium podium, Artist artist, double popularity) {
        this.artists = new ArrayList<>(Arrays.asList(artist));
        this.genre = genre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.podium = podium;
        this.popularity = popularity;
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

    public  double getPopularity() {
        return popularity;
    }
}
