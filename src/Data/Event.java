package Data;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Event implements Serializable {

    private ArrayList<Artist> artists;
    private Genre genre;
    private LocalTime startTime;
    private LocalTime endTime;
    private Podium podium;
    private double popularity;

    public Event(LocalTime startTime, LocalTime endTime, Genre genre, Podium podium, ArrayList<Artist> artists, double popularity) {
        this.artists = artists;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Podium getPodium() {
        return podium;
    }

    public  double getPopularity() {
        return popularity;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setPodium(Podium podium) {
        this.podium = podium;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return this.artists + " " + this.genre + " " + this.podium + " " + this.popularity + " " + this.startTime + " " + this.endTime;
    }
}
