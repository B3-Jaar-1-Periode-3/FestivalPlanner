package Data;
import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {

    private ArrayList<String> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;
    private ArrayList<Event> events;

    public Festival(ArrayList<String> genreList, ArrayList<Artist> artistList, ArrayList<Podium> podiumList, ArrayList<Event> events) {
        this.genreList = genreList;
        this.artistList = artistList;
        this.podiumList = podiumList;
        this.events = events;
    }

    public ArrayList<String> getGenreList() {
        return genreList;
    }

    public ArrayList<Artist> getArtistList() {
        return artistList;
    }

    public ArrayList<Podium> getPodiumList() {
        return podiumList;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void addGenre(String genre) {
        this.genreList.add(genre);
    }

    public void addArtist(Artist artist) {
        this.artistList.add(artist);
    }

    public void addPodium(Podium podium) {
        this.podiumList.add(podium);
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }
}
