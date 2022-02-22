package saveGUIs;

import Data.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class SaveEventGUI extends Stage {
    private BorderPane mainPane;
    private VBox vBoxLabels;
    private VBox vBoxBoxes;
    private HBox hBox;
    private ComboBox<Artist> artists;
    private ComboBox<Podium> podiaBox;
    private ComboBox<Genre> genreBox;
    private ArrayList<Artist> artistArrayList;
    private ArrayList<Podium> podiumArrayList;
    private ArrayList<Genre> genreArrayList;
    private Button save;
    private ListView<Artist> artistsListView;
    private Button add;

    public SaveEventGUI() {
        this.artistArrayList = Festival.getInstance().getArtistList();
        this.podiumArrayList = Festival.getInstance().getPodiumList();
        this.genreArrayList = Festival.getInstance().getGenreList();

        artistArrayList = new ArrayList<>();
        artistsListView = new ListView<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxLabels = new VBox();
        vBoxBoxes = new VBox();
        genreBox = new ComboBox<>();
        artists = new ComboBox<>();
        podiaBox = new ComboBox<>();
        podiumArrayList = new ArrayList<>();
        genreArrayList = new ArrayList<>();
        save = new Button("Save");
        add = new Button("Add");

        TextField enterBegin = new TextField();
        TextField enterEnd = new TextField();
        Slider popularity = new Slider(1, 10, 1);

        popularity.setShowTickLabels(true);
        popularity.setShowTickMarks(true);
        popularity.setMajorTickUnit(1);
        popularity.setMinorTickCount(0);
        popularity.setSnapToTicks(true);

        save.setPrefSize(75, 75);

        for (Artist artist : Festival.getInstance().getArtistList()) {
            artists.getItems().add(artist);
        }

        for (Podium podium : Festival.getInstance().getPodiumList()) {
            podiaBox.getItems().add(podium);
        }

        for (Genre genre : Festival.getInstance().getGenreList()) {
            genreBox.getItems().add(genre);
        }

        add.setOnAction(event -> {
            if (!artists.getSelectionModel().isEmpty() && !artistsListView.getItems().contains(artists.getValue())) {
                artistsListView.getItems().add(artists.getValue());
            }
        });

        save.setOnAction(event -> {
            LocalTime beginTime;
            LocalTime endTime;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            try {
                beginTime = LocalTime.parse(enterBegin.getText(), formatter);
                endTime = LocalTime.parse(enterEnd.getText(), formatter);
            } catch (DateTimeParseException e) {
                new Alert(Alert.AlertType.ERROR, "Please use format: 1:59").show();
                return;
            }
            Festival.getInstance().addEvent(new Event(beginTime, endTime, genreBox.getValue(), podiaBox.getValue(), artists.getValue(), popularity.getValue()));
            close();
        });

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
        Label labelPopularity = new Label("Popularity: ");
        labelPopularity.setFont(Font.font(15));

        mainPane.setPrefSize(500, 400);
        mainPane.setLeft(hBox);
        mainPane.setBottom(save);
        mainPane.setRight(artistsListView);
        vBoxBoxes.setSpacing(10);
        vBoxLabels.setSpacing(15);
        hBox.getChildren().addAll(vBoxLabels, vBoxBoxes, add);
        vBoxLabels.getChildren().addAll(labelArtist, labelPodium, labelBegin, labelEnd, labelGenre, labelPopularity);
        vBoxBoxes.getChildren().addAll(artists, podiaBox, enterBegin, enterEnd, genreBox, popularity);

        Scene scene = new Scene(mainPane);
        setTitle("Event");
        setScene(scene);
    }
}
