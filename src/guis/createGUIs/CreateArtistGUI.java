package guis.createGUIs;

import data.Artist;
import data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CreateArtistGUI extends Stage {
    private BorderPane mainPane;
    private HBox hBox;
    private VBox vBoxLabels;
    private VBox vBoxFields;
    private TextField artistNameField;

    public CreateArtistGUI() {
        mainPane = new BorderPane();
        hBox = new HBox(20);
        vBoxFields = new VBox();
        vBoxLabels = new VBox();

        mainPane.setPrefSize(400,100);
        Label artistName = new Label("Name:");
        artistNameField = new TextField();
        Button save = new Button("Save");
        save.setPrefSize(400,50);

        save.setOnAction(event -> {
            Festival.getInstance().addArtist(new Artist(artistNameField.getText()));
            close();
        });

        vBoxLabels.getChildren().addAll(artistName);
        vBoxFields.getChildren().addAll(artistNameField);

        hBox.getChildren().addAll(vBoxLabels,vBoxFields);

        mainPane.setTop(hBox);
        mainPane.setBottom(save);

        Scene scene = new Scene(mainPane);

        setTitle("Save Artist");
        setScene(scene);
    }

    public void setTextField(String name) {
        artistNameField.setText(name);
    }
}
