package data;

import tiled.TiledMap;
import tiled.TiledObject;
import tiled.TiledObjectLayer;
import tiled.pathfinding.Target;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Festival implements Serializable {
    private static Festival festival;

    private TiledMap tiledMap;
    private ArrayList<Genre> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;
    private ArrayList<Event> eventList;
    private ArrayList<Visitor> visitors;

    public Festival() {
        this.genreList = new ArrayList<>();
        this.artistList = new ArrayList<>();
        this.podiumList = new ArrayList<>();
        this.eventList = new ArrayList<>();
        this.visitors = new ArrayList<>();
        this.tiledMap = new TiledMap("Map.json");
        ArrayList<TiledObjectLayer> objectLayers = tiledMap.getObjectLayers();

        for (TiledObjectLayer objectLayer : objectLayers) {
            for (int i = 0; i < 4; i++) {
                podiumList.add(new Podium(i, objectLayer.getObjects().get(i).getName(), objectLayer.getObjects().get(i)));
            }
        }

        System.out.println(podiumList.get(2).getName());
        // araylist of visitors
//        for (int i = 0; i < 1; i++) {
//            visitors.add(new Visitor( new Target(tiledMap.getCollisionLayer(), podiumList.get(0).getObject().getCenterTile())));
//            visitors.add(new Visitor( new Target(tiledMap.getCollisionLayer(),podiumList.get(1).getObject().getCenterTile())));
//        }
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


    public void addEvent(Event event) {
        this.eventList.add(event);
    }

    public void setFestival(Festival input) { //Set all info of Festival instance
        this.genreList.addAll(input.getGenreList());
        this.artistList.addAll(input.getArtistList());
        this.podiumList.addAll(input.getPodiumList());
        this.eventList.addAll(input.getEventList());
    }

    public void clearAll() { //Deletes all info of Festival instance
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

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }

    public static Festival getInstance() {
        if (festival == null) {
            festival = new Festival();
        }
        return festival;
    }

    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }


}
