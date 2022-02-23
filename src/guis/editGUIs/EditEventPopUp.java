package guis.editGUIs;

import agenda.DrawEventBox;
import data.*;
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

public class EditEventPopUp extends Stage {
    private BorderPane mainPane;
    private VBox vBoxLabels;
    private VBox vBoxBoxes;
    private HBox hBox;
    private ComboBox<Artist> artists;
    private ComboBox<Podium> podiaBox;
    private ComboBox<Genre> genreBox;
    private ArrayList<Artist> artistArrayList;
    private Podium podium;
    private Genre genre;
    private Button add;
    private Button save;
    private ListView<Artist> artistsListView;
    private DateTimeFormatter formatter;


    public EditEventPopUp(Event event) {
        this.artistArrayList = new ArrayList<>(event.getArtists());
        this.podium = event.getPodium();
        this.genre = event.getGenre();
        this.formatter = DateTimeFormatter.ofPattern("H:mm");

        artistsListView = new ListView<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxLabels = new VBox();
        vBoxBoxes = new VBox();
        genreBox = new ComboBox<>();
        artists = new ComboBox<>();
        podiaBox = new ComboBox<>();
        save = new Button("Save");
        add = new Button("Add");

        TextField enterBegin = new TextField();
        TextField enterEnd = new TextField();
        Slider popularitySlider = new Slider(1, 10, 1);

        popularitySlider.setShowTickLabels(true);
        popularitySlider.setShowTickMarks(true);
        popularitySlider.setMajorTickUnit(1);
        popularitySlider.setMinorTickCount(0);
        popularitySlider.setSnapToTicks(true);

        save.setMinWidth(100);

        for (Artist artistFromList : Festival.getInstance().getArtistList()) {
            artists.getItems().add(artistFromList);
        }

        for (Podium podiumFromList : Festival.getInstance().getPodiumList()) {
            podiaBox.getItems().add(podiumFromList);
        }

        for (Genre genreFromList : Festival.getInstance().getGenreList()) {
            genreBox.getItems().add(genreFromList);
        }

        add.setOnAction(e -> {
            if (!artists.getSelectionModel().isEmpty() && !artistsListView.getItems().contains(artists.getValue())) {
                artistsListView.getItems().add(artists.getValue());
            }
        });

        genreBox.setValue(event.getGenre());
        podiaBox.setValue(event.getPodium());

        enterBegin.setText(this.formatter.format(event.getStartTime()));
        enterEnd.setText(this.formatter.format(event.getEndTime()));
        popularitySlider.setValue(event.getPopularity());

        artistsListView.getItems().addAll(artistArrayList);

        save.setOnAction(e -> {
            LocalTime beginTime = event.getStartTime();
            LocalTime endTime = event.getEndTime();

            try {
                event.setStartTime(LocalTime.parse(enterBegin.getText(), formatter));
                event.setEndTime(LocalTime.parse(enterEnd.getText(), formatter));
            } catch (DateTimeParseException exception) {
                new Alert(Alert.AlertType.ERROR, "Please use format: 01:59").show();
                return;
            }
            event.setGenre(genreBox.getValue());
            event.setPodium(podiaBox.getValue());
            event.setPopularity(popularitySlider.getValue());
            event.setArtists(new ArrayList<>(artistsListView.getItems()));
            DrawEventBox.drawAllBoxes();
            close();
            new EditEventGUI().show();
        });

        artists.setPrefSize(100, 30);
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
        mainPane.setRight(artistsListView);
        vBoxBoxes.setSpacing(10);
        vBoxLabels.setSpacing(15);
        hBox.getChildren().addAll(vBoxLabels, vBoxBoxes, add);
        vBoxLabels.getChildren().addAll(labelArtist, labelPodium, labelBegin, labelEnd, labelGenre, labelPopularity, save);
        vBoxBoxes.getChildren().addAll(artists, podiaBox, enterBegin, enterEnd, genreBox, popularitySlider);

        Scene scene = new Scene(mainPane);
        setTitle("Event");
        setScene(scene);
    }
}
