package guis.createguis;

import data.Festival;
import data.Genre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateGenreGUI extends Stage {

    public CreateGenreGUI() {
        //Creates window content
        BorderPane mainPane = new BorderPane();
        HBox hBox = new HBox();
        VBox vBoxFields = new VBox();
        VBox vBoxLabels = new VBox();

        //Creates input and labels
        mainPane.setPrefSize(400,100);
        Label genre = new Label("Genre: ");
        TextField genreField = new TextField();
        Button save = new Button("Save");
        save.setPrefSize(400,50);
        Label output = new Label("");

        genreField.setOnAction(actionEvent -> { //Saves and clears input when pressing enter
            String input = genreField.getText();
            if (!input.isEmpty()) { //Checks if input contains something before saving
                Festival.getInstance().addGenre(new Genre(input));
                genreField.clear();
                output.setText("Saved " + input);
            } else {
                output.setText("No input");
            }

        });

        save.setOnAction(event -> { //Saves and closes window
            String input = genreField.getText();
            if (!input.isEmpty()) { //Checks if input contains something before saving
                Festival.getInstance().addGenre(new Genre(input));
            }
            close();
        });

        hBox.getChildren().addAll(vBoxLabels, vBoxFields);
        vBoxLabels.getChildren().add(genre);
        vBoxFields.getChildren().add(genreField);
        vBoxFields.getChildren().add(output);

        mainPane.setTop(hBox);
        mainPane.setBottom(save);

        Scene scene = new Scene(mainPane);

        setTitle("Create Genre");
        setScene(scene);
    }
}
