package Data;
import java.io.Serializable;
import java.util.ArrayList;

public class Festival implements Serializable {

    private ArrayList<String> genreList;
    private ArrayList<Artist> artistList;
    private ArrayList<Podium> podiumList;

    public Festival(ArrayList<String> genreList, ArrayList<Artist> artistList, ArrayList<Podium> podiumList) {
        this.genreList = genreList;
        this.artistList = artistList;
        this.podiumList = podiumList;
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
