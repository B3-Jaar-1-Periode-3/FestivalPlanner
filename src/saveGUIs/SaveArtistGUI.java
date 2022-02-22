package saveGUIs;

import Data.Artist;
import Data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SaveArtistGUI extends Stage {
    private BorderPane mainPane;
    private HBox hBox;
    private VBox vBoxLabels;
    private VBox vBoxFields;

    public SaveArtistGUI() {
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxFields = new VBox();
        vBoxLabels = new VBox();

        Label artistName = new Label("Name:");
        TextField artistNameField = new TextField();
        Button save = new Button("Save");
        save.setPrefSize(50,50);

        save.setOnAction(event -> {
            Festival.getInstance().addArtist(new Artist(artistNameField.getText()));
        });

        vBoxLabels.getChildren().addAll(artistName);
        vBoxFields.getChildren().addAll(artistNameField);

        hBox.getChildren().addAll(vBoxLabels,vBoxFields);

        mainPane.setTop(hBox);
        mainPane.setBottom(save);

        Scene scene = new Scene(mainPane);

        setTitle("Edit Artist");
        setScene(scene);
    }
}
