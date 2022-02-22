package editGUIs;

import Data.Artist;
import Data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class EditArtistGUI extends Stage {
    private ListView<Artist> artists;
    private BorderPane mainPane;
    private Button editArtist;

    public EditArtistGUI() {
        mainPane = new BorderPane();
        artists = new ListView<>();

        for (Artist artist : Festival.getInstance().getArtistList()) {
            artists.getItems().add(artist);
        }

        editArtist = new Button("Edit Artist");
        editArtist.setPrefSize(500, 50);

        editArtist.setOnAction(event -> {
            new EditArtistPopUp(artists.getSelectionModel().getSelectedItem()).show();
            close();
        });

        mainPane.setCenter(artists);
        mainPane.setBottom(editArtist);

        Scene scene = new Scene(mainPane);

        setTitle("Edit Artist");
        setScene(scene);
    }
}
