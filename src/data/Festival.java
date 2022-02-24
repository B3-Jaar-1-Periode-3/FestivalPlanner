package data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Festival implements Serializable {
    private static Festival festival;

    private ArrayList<Genre> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;
    private ArrayList<Event> eventList;

    public Festival(){
        this.genreList = new ArrayList<>();
        this.artistList = new ArrayList<>();
        this.podiumList = new ArrayList<>();
        podiumList.add(new Podium(1));
        podiumList.add(new Podium(2));
        podiumList.add(new Podium(3));
        podiumList.add(new Podium(4));
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

    public void setFestival(Festival input) { //Set all info of Festival instance
        this.genreList.addAll(input.getGenreList());
        this.artistList.addAll(input.getArtistList());
        this.podiumList.addAll(input.getPodiumList());
        this.eventList.addAll(input.getEventList());
    }

    public void clearAll(){ //Deletes all info of Festival instance
        this.genreList.clear();
        this.artistList.clear();
        this.podiumList.clear();
        this.eventList.clear();
    }

    public ArrayList<Event> getEventsForArtist(Artist artist) {
        ArrayList<Event> eventsForArtist = new ArrayList<>();

        for (Event event : eventList) {
            if (event.getArtists().contains(artist)) {
                eventsForArtist.add(event);
            }
        }
        return eventsForArtist;
    }

    public static Festival getInstance() {
        if (festival == null) {
            festival = new Festival();
        }
        return festival;
    }
}
