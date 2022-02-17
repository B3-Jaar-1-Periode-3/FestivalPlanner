package Agenda;

import Data.Artist;
import Data.Genre;
import Data.Podium;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class EventGUI extends Application {
    private BorderPane mainPane;
    private VBox vBoxLabels;
    private VBox vBoxBoxes;
    private HBox hBox;
    private ComboBox<String> artists;
    private ComboBox<String> podiaBox;
    private ComboBox<String> genreBox;
    private ArrayList<Artist> artistArrayList;
    private ArrayList<Podium> podiumArrayList;
    private ArrayList<Genre> genreArrayList;


    @Override
    public void start(Stage stage) throws Exception {
        artistArrayList = new ArrayList<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxLabels = new VBox();
        vBoxBoxes = new VBox();
        genreBox = new ComboBox<>();
        artists = new ComboBox<>();
        podiaBox = new ComboBox<>();
        podiumArrayList = new ArrayList<>();
        genreArrayList = new ArrayList<>();


        podiumArrayList.add(new Podium(1));
        artistArrayList.add(new Artist("Agt"));
        genreArrayList.add(new Genre("Pop"));

        for (Artist artist : artistArrayList) {
            artists.getItems().add(artist.getName());
        }

        for (Podium podium : podiumArrayList) {
            String id = String.valueOf(podium.getID());
            podiaBox.getItems().add(id);
        }

        for (Genre genre : genreArrayList) {
            genreBox.getItems().add(genre.getGenre());
        }

        artists.setPrefSize(100,30);
        Label labelArtist = new Label("Artist:");
        labelArtist.setFont(Font.font(15));
        Label labelPodium = new Label("Podium:");
        labelPodium.setFont(Font.font(15));
        Label labelBegin = new Label("Begin Time:");
        labelBegin.setFont(Font.font(15));
        Label labelEnd = new Label("End Time:");
        labelEnd.setFont(Font.font(15));
        Label labelGenre = new Label("Genre:");
        labelGenre.setFont(Font.font(15));

        TextField enterBegin = new TextField();
        TextField enterEnd = new TextField();

        mainPane.setPrefSize(1000,800);
        mainPane.setLeft(hBox);
        hBox.setSpacing(20);
        vBoxBoxes.setSpacing(10);
        vBoxLabels.setSpacing(15);
        hBox.getChildren().addAll(vBoxLabels, vBoxBoxes);
        vBoxLabels.getChildren().addAll(labelArtist, labelPodium, labelBegin, labelEnd, labelGenre);
        vBoxBoxes.getChildren().addAll(artists, podiaBox, enterBegin, enterEnd, genreBox);

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.setTitle("Add an Event");
        stage.show();
    }

    public void setArtistArrayList(ArrayList<Artist> artists) {
        this.artistArrayList = artists;
    }

    public void setPodiumArrayList(ArrayList<Podium> podiums) {
        this.podiumArrayList = podiums;
    }

    public void setGenreList(ArrayList<Genre> genres) {
        this.genreArrayList = genres;
    }

    public static void main(String[] args) {
        launch(EventGUI.class);
    }
}
