package Data;

import java.util.ArrayList;

public class Genre {

   private ArrayList<String> genre;

    public Genre() {
        this.genre = new ArrayList<>();
    }

    public void addGenre() {
        genre.add("Pop");
        genre.add("Rock");
        genre.add("Rap");
        genre.add("Hip-Hop");
        genre.add("Country");
        genre.add("R&B");
        genre.add("Folk");
        genre.add("Jazz");
        genre.add("Heavy metal");
        genre.add("EDM");
        genre.add("Soul");
    }
}
