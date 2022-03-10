package guis.editguis;

import agenda.DrawEventBox;
import data.Festival;
import data.Genre;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EditGenreGUI extends Stage {
    private BorderPane mainPane;
    private ListView<Genre> genreListView;
    private HBox hBox;
    private Button editGenre;
    private Button deleteGenre;

    public EditGenreGUI() {
        mainPane = new BorderPane();
        genreListView = new ListView<>();
        hBox = new HBox();
        editGenre = new Button("Edit Genre");
        deleteGenre = new Button("Delete Genre");

        genreListView.getItems().addAll(Festival.getInstance().getGenreList());

        editGenre.setPrefSize(250, 50);
        deleteGenre.setPrefSize(250, 50);

        editGenre.setOnAction(event -> {
            new EditGenrePopUp(genreListView.getSelectionModel().getSelectedItem()).show();
            DrawEventBox.drawAllBoxes();
            close();
        });

        deleteGenre.setOnAction(event -> {
            if (!genreListView.getSelectionModel().isEmpty()) {
                Festival.getInstance().getGenreList().remove(genreListView.getSelectionModel().getSelectedItem());
                genreListView.getItems().remove(genreListView.getSelectionModel().getSelectedItem());
                DrawEventBox.drawAllBoxes();
            }
        });

        hBox.getChildren().addAll(editGenre, deleteGenre);

        mainPane.setCenter(genreListView);
        mainPane.setBottom(hBox);

        Scene scene = new Scene(mainPane);

        setTitle("Edit Genre");
        setScene(scene);
    }
}
