package Agenda;

import Data.Festival;
import Data.Podium;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PodiumGUI extends Application {


    @Override
    public void start(Stage stage) {

        GridPane gridPane = new GridPane();
        TextField input = new TextField();
        input.setMaxWidth(100);
        Button addPodium = new Button("add podium");


        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Insert podium"), 1, 0);
        gridPane.add(new Label("ID: "), 1, 1);
        gridPane.add(input, 2, 1);
        gridPane.add(addPodium, 1, 2);


        gridPane.setPrefSize(500, 400);

        Scene scene = new Scene(gridPane);

        stage.setScene(scene);
        stage.show();

        //  addPodium.setOnAction(event ->
    }

        public static void main(String[] args) {
            launch(PodiumGUI.class);
        }


}