package Data;

import java.io.Serializable;

public class Genre implements Serializable {

    private String genre;

    public Genre(String genre) {
       this.genre = genre;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
