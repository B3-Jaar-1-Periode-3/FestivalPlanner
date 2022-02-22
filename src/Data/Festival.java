package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {
    private static Festival festival;

    private ArrayList<Genre> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;
    private ArrayList<Event> eventList;


    public Festival(){
        this.genreList = new ArrayList<>();
        genreList.add(new Genre("Rock"));
        this.artistList = new ArrayList<>();
        artistList.add(new Artist("Merijn"));
        artistList.add(new Artist("Wouter"));
        this.podiumList = new ArrayList<>();
        podiumList.add(new Podium(1));
        this.eventList = new ArrayList<>();
    }

    public Festival(ArrayList<Genre> genreList, ArrayList<Artist> artistList, ArrayList<Podium> podiumList, ArrayList<Event> eventList) {
        this.genreList = genreList;
        this.artistList = artistList;
        this.podiumList = podiumList;
        this.eventList = eventList;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public ArrayList<Artist> getArtistList() {
        return artistList;
    }

    public ArrayList<Podium> getPodiumList() {
        return podiumList;
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }


    public void addGenre(Genre genre) {
        this.genreList.add(genre);
    }

    public void addArtist(Artist artist) {
        this.artistList.add(artist);
    }

    public void addPodium(Podium podium) {
        this.podiumList.add(podium);
    }

    public void addEvent(Event event) {
        this.eventList.add(event);
    }

    public static Festival getInstance() {
        if (festival == null) {
            festival = new Festival();
        }
        return festival;
    }
}
