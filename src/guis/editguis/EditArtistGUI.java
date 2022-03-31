package guis.editguis;

import agenda.DrawEventBox;
import data.Artist;
import data.Event;
import data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class EditArtistGUI extends Stage {
    private ListView<Artist> artists;
    private BorderPane mainPane;
    private HBox hBox;
    private Button editArtist;
    private Button deleteArtist;

    public EditArtistGUI() {
        mainPane = new BorderPane();
        artists = new ListView<>();
        hBox = new HBox();
        deleteArtist = new Button("Delete Artist");

        for (Artist artist : Festival.getInstance().getArtistList()) {
            artists.getItems().add(artist);
        }

        editArtist = new Button("Edit Artist");
        editArtist.setPrefSize(250, 50);
        deleteArtist.setPrefSize(250,50);

        editArtist.setOnAction(event -> {
            new EditArtistPopUp(artists.getSelectionModel().getSelectedItem()).show();
            DrawEventBox.drawAllBoxes();
            close();
        });

        deleteArtist.setOnAction(event -> {
            Artist selectedArtist = artists.getSelectionModel().getSelectedItem();
            Festival.getInstance().getArtistList().remove(selectedArtist);
            artists.getItems().remove(selectedArtist);

            for (Event eventItem : Festival.getInstance().getEventList()){
                if (eventItem.getArtists().contains(selectedArtist)){
                    Festival.getInstance().getEventList().remove(eventItem);
                    DrawEventBox.clearALlBoxes(eventItem);
                }
            }

            DrawEventBox.drawAllBoxes();
        });

        hBox.getChildren().addAll(editArtist, deleteArtist);
        mainPane.setCenter(artists);
        mainPane.setBottom(hBox);

        Scene scene = new Scene(mainPane);

        setTitle("Edit Artist");
        setScene(scene);
    }
}
