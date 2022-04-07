package data;
import javafx.scene.control.Alert;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Event implements Serializable {

    private ArrayList<Artist> artists;
    private ArrayList<Visitor> visitors;
    private Genre genre;
    private LocalTime startTime;
    private LocalTime endTime;
    private Podium podium;
    private double popularity;

    private boolean eventVisitorsSpawned;

    public Event(LocalTime startTime, LocalTime endTime, Genre genre, Podium podium, ArrayList<Artist> artists, double popularity) {
        this.artists = artists;
        this.genre = genre;
        this.startTime = startTime;
        this.endTime = endTime;
        this.podium = podium;
        this.popularity = popularity;
        this.eventVisitorsSpawned = false;
        this.visitors = new ArrayList<>();
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

    public void setArtists(ArrayList<Artist> artistss) {
        this.artists = artistss;
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

    public boolean isFree(Event event) {
        for (Event eventFromAll : Festival.getInstance().getEventList()) {
            if (event.getStartTime().isBefore(eventFromAll.getEndTime()) && event.getEndTime().isAfter(eventFromAll.getStartTime()) && event.getPodium().getID() == eventFromAll.getPodium().getID() && !event.equals(eventFromAll)) {
                new Alert(Alert.AlertType.ERROR, "Event is overlapping with another event").show();
                return false;
            }
        }
        for (Artist artist : event.getArtists()) {
            if (!artist.isFree(event)) {
                new Alert(Alert.AlertType.ERROR, "Artist has another event planned at this time.");
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return this.artists + " " + this.genre + " " + this.podium + " " + this.popularity + " " + this.startTime + " " + this.endTime;
    }

    public boolean isEventVisitorsSpawned() {
        return eventVisitorsSpawned;
    }

    public void setEventVisitorsSpawned(boolean eventVisitorsSpawned) {
        this.eventVisitorsSpawned = eventVisitorsSpawned;
    }

    public void addVisitor(Visitor visitor) {
        this.visitors.add(visitor);
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }
}
