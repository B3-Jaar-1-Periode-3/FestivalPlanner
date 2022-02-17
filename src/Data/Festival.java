package Data;
import java.util.ArrayList;

public class Festival {

    private ArrayList<String> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;

    public void festival() {
        this.genreList = new ArrayList<>();
        this.artistList = new ArrayList<>();
        this.podiumList = new ArrayList<>();
    }

    private void addGenre(String genre) {
        this.genreList.add(genre);
    }

    public void addArtist(Artist artist) {
        this.artistList.add(artist);
    }

    public void addPodium(Podium podium) {
        this.podiumList.add(podium);
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
}
