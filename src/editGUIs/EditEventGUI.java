package editGUIs;

import Data.Event;
import Data.Festival;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalTime;

public class EditEventGUI extends Stage {
    private ListView<Event> events;
    private BorderPane mainPane;
    private Button editEvent;

    public EditEventGUI() {
        events = new ListView<>();
        mainPane = new BorderPane();
        editEvent = new Button("Edit Event");

        editEvent.setPrefSize(500,50);

        mainPane.setCenter(events);
        mainPane.setBottom(editEvent);

        for (Event event : Festival.getInstance().getEventList()) {
            events.getItems().add(event);
        }

        editEvent.setOnAction(event -> {
            new EditEventPopUp(events.getSelectionModel().getSelectedItem()).show();
            System.out.println(events.getSelectionModel().getSelectedItem().toString());
            close();
        });

        Scene scene = new Scene(mainPane);

        setTitle("Edit event");
        setScene(scene);
    }
}
