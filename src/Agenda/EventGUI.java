package Agenda;

import Data.Artist;
import Data.Genre;
import Data.Podium;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;


public class EventGUI extends Application {
    private BorderPane mainPane;
    private VBox vBoxLabels;
    private VBox vBoxComboboxes;
    private HBox hBox;
    private ComboBox<String> artists;
    private ComboBox<String> podiaBox;
    private ArrayList<Artist> artistArrayList;
    private ArrayList<Podium> podiumArrayList;
    private ArrayList<Genre> genreArrayList;


    @Override
    public void start(Stage stage) throws Exception {
        artistArrayList = new ArrayList<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxLabels = new VBox();
        vBoxComboboxes = new VBox();
        artists = new ComboBox<>();
        podiaBox = new ComboBox<>();
        podiumArrayList = new ArrayList<>();


        podiumArrayList.add(new Podium(1));
        artistArrayList.add(new Artist("Agt"));

        for (Artist artist : artistArrayList) {
            artists.getItems().add(artist.getName());
        }

        for (Podium podium : podiumArrayList) {
            podiaBox.getItems().add(podium.toString());
        }

        artists.setPrefSize(100,30);
        Label labelArtist = new Label("Artiest:");
        labelArtist.setFont(Font.font(20));
        Label labelPodium = new Label("Podium:");
        labelPodium.setFont(Font.font(20));

        mainPane.setPrefSize(1000,800);
        mainPane.setLeft(hBox);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(vBoxLabels, vBoxComboboxes);
        vBoxLabels.getChildren().addAll(labelArtist, labelPodium);
        vBoxComboboxes.getChildren().addAll(artists, podiaBox);

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
