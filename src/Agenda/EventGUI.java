package Agenda;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.PrintWriter;

public class EventGUI extends Application {
    private BorderPane mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        mainPane = new BorderPane();

        Scene scene = new Scene(mainPane);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(EventGUI.class);
    }

}
