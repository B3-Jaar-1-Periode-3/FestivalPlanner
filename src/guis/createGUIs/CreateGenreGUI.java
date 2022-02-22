package guis.createGUIs;

import Data.Festival;
import Data.Genre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateGenreGUI extends Stage {

    private BorderPane mainPane;
    private HBox hBox;
    private VBox vBoxLabels;
    private VBox vBoxFields;
    private TextField genreField;

    public CreateGenreGUI() {
        mainPane = new BorderPane();
        hBox = new HBox();
        vBoxFields = new VBox();
        vBoxLabels = new VBox();

        mainPane.setPrefSize(400,100);
        Label genre = new Label("Genre: ");
        genreField = new TextField();
        Button save = new Button("Save");
        save.setPrefSize(400,50);

        save.setOnAction(event -> {
            Festival.getInstance().addGenre(new Genre(genreField.getText()));
            close();
        });


        hBox.getChildren().addAll(vBoxLabels,vBoxFields);
        vBoxLabels.getChildren().add(genre);
        vBoxFields.getChildren().add(genreField);

        mainPane.setTop(hBox);
        mainPane.setBottom(save);

        Scene scene = new Scene(mainPane);

        setTitle("Create Genre");
        setScene(scene);
    }
}
