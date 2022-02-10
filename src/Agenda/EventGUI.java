package Agenda;

import Data.Artist;
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
    private VBox vBox;
    private HBox hBox;
    private ComboBox<String> artists;
    private ArrayList<Artist> artistArrayList;

    @Override
    public void start(Stage stage) throws Exception {
        artistArrayList = new ArrayList<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        vBox = new VBox();
        artists = new ComboBox<>();

        for (Artist artist : artistArrayList) {
            artists.getItems().add(artist.getName());
        }

        artists.setPrefSize(100,30);
        Label labelArtist = new Label("Artiest:");
        labelArtist.setFont(Font.font(20));

        mainPane.setPrefSize(1000,800);
        mainPane.setLeft(hBox);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(labelArtist, vBox);
        vBox.getChildren().addAll(artists);

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.show();
    }

    public void setArtistArrayList(ArrayList<Artist> artists) {
        this.artistArrayList = artists;
    }

    public static void main(String[] args) {
        launch(EventGUI.class);
    }
}
