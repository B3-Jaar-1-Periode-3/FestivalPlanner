package guis.editGUIs;

import agenda.AgendaGUI;
import agenda.DrawEventBox;
import data.Event;
import data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class EditEventGUI extends Stage {
    private ListView<Event> events;
    private BorderPane mainPane;
    private Button editEvent;
    private HBox hBox;
    private Button deleteEvent;

    public EditEventGUI() {
        events = new ListView<>();
        mainPane = new BorderPane();
        hBox = new HBox();
        editEvent = new Button("Edit Event");
        deleteEvent = new Button("Delete Event");

        editEvent.setPrefSize(250,50);
        deleteEvent.setPrefSize(250,50);

        hBox.getChildren().addAll(editEvent,deleteEvent);

        mainPane.setCenter(events);
        mainPane.setBottom(hBox);

        for (Event event : Festival.getInstance().getEventList()) {
            events.getItems().add(event);
        }

        editEvent.setOnAction(event -> {
            new EditEventPopUp(events.getSelectionModel().getSelectedItem()).show();
            close();
        });

        deleteEvent.setOnAction(event -> {
            if (!events.getSelectionModel().isEmpty()) {
                Festival.getInstance().getEventList().remove(events.getSelectionModel().getSelectedItem());
                DrawEventBox.clearALlBoxes(events.getSelectionModel().getSelectedItem());

                events.getItems().remove(events.getSelectionModel().getSelectedItem());
            }
        });

        Scene scene = new Scene(mainPane);

        setTitle("Edit event");
        setScene(scene);
    }
}
