package editGUIs;

import Data.Artist;
import Data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import saveGUIs.SaveArtistGUI;

public class EditArtistGUI extends Stage {
    private ListView<Artist> artists;
    private BorderPane mainPane;
    private HBox hBox;
    private Button editArtist;

    public EditArtistGUI() {
        mainPane = new BorderPane();
        hBox = new HBox();
        artists = new ListView<>();

        for (Artist artist : Festival.getInstance().getArtistList()) {
            artists.getItems().add(artist);
        }

        editArtist = new Button("Edit Artist");
        editArtist.setPrefSize(500, 50);

        hBox.getChildren().addAll(editArtist);

        editArtist.setOnAction(event -> {
            new EditArtistPopUp(artists.getSelectionModel().getSelectedItem()).show();
            close();
        });

        mainPane.setCenter(artists);
        mainPane.setBottom(hBox);

        Scene scene = new Scene(mainPane);

        setTitle("Edit Artist");
        setScene(scene);
    }
}
