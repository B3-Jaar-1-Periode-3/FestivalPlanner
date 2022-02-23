package guis.createGUIs;

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


public class CreateEventGUI extends Stage {

    public CreateEventGUI() {
        ArrayList<Artist> artistList = Festival.getInstance().getArtistList();
        ArrayList<Podium> podiumList = Festival.getInstance().getPodiumList();
        ArrayList<Genre> genreList = Festival.getInstance().getGenreList();

        //Creates window content
        BorderPane mainPane = new BorderPane();
        HBox hBox = new HBox();
        VBox vBoxLabels = new VBox();
        VBox vBoxBoxes = new VBox();

        //Creates ComboBoxes
        ComboBox<Genre> genreBox = new ComboBox<>();
        ComboBox<Artist> artists = new ComboBox<>();
        ComboBox<Podium> podiaBox = new ComboBox<>();

        //Creates Labels
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
        Label labelOutput = new Label("");
        labelOutput.setFont(Font.font(15));

        //Creates Input buttons and fields
        Button save = new Button("Save");
        Button add = new Button("Add");
        ListView<Artist> artistsListView = new ListView<>();
        TextField enterBegin = new TextField();
        TextField enterEnd = new TextField();
        Slider popularity = new Slider(1, 10, 1);

        popularity.setShowTickLabels(true);
        popularity.setShowTickMarks(true);
        popularity.setMajorTickUnit(1);
        popularity.setMinorTickCount(0);
        popularity.setSnapToTicks(true);

        save.setMinWidth(100);

        //Input saved data into lists
        for (Artist artist : artistList) {
            artists.getItems().add(artist);
        }

        for (Podium podium : podiumList) {
            podiaBox.getItems().add(podium);
        }

        for (Genre genre : genreList) {
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

            if (!beginTime.toString().isEmpty() &&
                !endTime.toString().isEmpty() &&
                !genreBox.getValue().toString().isEmpty() &&
                !artistsListView.getItems().isEmpty()) { //Checks if any input is empty
                if (endTime.isAfter(beginTime) && !beginTime.equals(endTime)) {
                    ArrayList<Artist> artistsToAdd = new ArrayList<>(artistsListView.getItems());
                    Event newEvent = new Event(beginTime, endTime, genreBox.getValue(), podiaBox.getValue(), artistsToAdd, popularity.getValue());
                    if (newEvent.isFree(newEvent)) {
                        Festival.getInstance().addEvent(newEvent);
                        DrawEventBox.drawAllBoxes();
                        labelOutput.setText("Event saved!");
                        close();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Can not add event, podium/artist is unavailable").show();
                    }
                } else {
                    labelOutput.setText("End time is before begin time");
                }
            } else {
                labelOutput.setText("Please fill in all fields");
            }
        });

        mainPane.setPrefSize(500, 400);
        mainPane.setLeft(hBox);
        mainPane.setRight(artistsListView);
        vBoxBoxes.setSpacing(10);
        vBoxLabels.setSpacing(15);
        hBox.getChildren().addAll(vBoxLabels, vBoxBoxes, add);
        vBoxLabels.getChildren().addAll(labelArtist, labelPodium, labelBegin, labelEnd, labelGenre, labelPopularity, labelOutput, save);
        vBoxBoxes.getChildren().addAll(artists, podiaBox, enterBegin, enterEnd, genreBox, popularity);

        Scene scene = new Scene(mainPane);
        setTitle("Event");
        setScene(scene);
    }
}
