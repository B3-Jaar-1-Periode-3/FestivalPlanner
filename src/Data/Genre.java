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
}
