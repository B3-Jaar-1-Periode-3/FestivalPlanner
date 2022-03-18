import agenda.AgendaGUI;
import data.Festival;
import tiled.TiledMap;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {
        Festival festival = Festival.getInstance();
        launch(AgendaGUI.class);
    }
}
