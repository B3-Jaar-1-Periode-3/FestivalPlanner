import agenda.AgendaGUI;
import data.Festival;
import tiled.TiledMap;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {
        TiledMap tiledMap = new TiledMap("Festival_Planner_B3.json");

        Festival festival = Festival.getInstance();
        launch(AgendaGUI.class);
    }

}
