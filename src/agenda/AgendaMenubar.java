package agenda;

import data.Festival;
import guis.HelpWindowGUI;
import guis.createguis.CreateGenreGUI;
import guis.editguis.EditArtistGUI;
import guis.editguis.EditEventGUI;
import guis.createguis.CreateArtistGUI;
import guis.createguis.CreateEventGUI;
import guis.editguis.EditGenreGUI;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class AgendaMenubar {
    private static HBox agendaMenuBarScene;

    public static void build(Stage stage) {
        // Single buttons
        Menu help = new Menu("Help");
        MenuItem helpButton = new MenuItem("Help");
        help.getItems().add(helpButton);

        helpButton.setOnAction(event -> {
            new HelpWindowGUI().show();
        });

        // File menu & submenu's
        Menu fileMenu = new Menu("File");
        MenuItem loadFile = new MenuItem("Load file");
        MenuItem savePlanning = new MenuItem("Save planning");
        fileMenu.getItems().addAll(loadFile, savePlanning);

        FileChooser fileChooser = new FileChooser();

        loadFile.setOnAction(event -> {
            fileChooser.setTitle("Load from");
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                io.FileHandler.readFromFile(file);
            }
        });

        savePlanning.setOnAction(event -> {
            fileChooser.setTitle("Save as");
            File file = fileChooser.showSaveDialog(stage);
            if (file != null){
                io.FileHandler.saveToFile(file, Festival.getInstance());
            }
        });

        // edit menu & sub menu's
        Menu editMenu = new Menu("Edit");
        MenuItem editEvent = new MenuItem("Event");
        MenuItem editArtist = new MenuItem("Artist");
        MenuItem editGenre = new MenuItem("Genre");
        editMenu.getItems().addAll(editEvent, editArtist, editGenre);

        editEvent.setOnAction(event -> {
            new EditEventGUI().show();
        });

        editArtist.setOnAction(event -> {
            new EditArtistGUI().show();
        });

        editGenre.setOnAction(event -> {
            new EditGenreGUI().show();
        });

        // save menu & sub menu's
        Menu saveMenu = new Menu("Create");
        MenuItem saveEvent = new MenuItem("Event");
        MenuItem saveArtist = new MenuItem("Artist");
        MenuItem saveGenre = new MenuItem("Genre");
        saveMenu.getItems().addAll(saveEvent, saveArtist, saveGenre);

        saveEvent.setOnAction(event -> {
            new CreateEventGUI().show();
        });

        saveArtist.setOnAction(event -> {
            new CreateArtistGUI().show();
        });

        saveGenre.setOnAction(event -> {
            new CreateGenreGUI().show();
        });

        //Views
        Menu views = new Menu("View");
        MenuItem simulator = new MenuItem("Simulation");
        views.getItems().addAll(simulator);

        simulator.setOnAction(event -> {
            new SimulatorGUI().show();
        });

        // all menu bars together
        MenuBar fileMenuBar = new MenuBar();
        MenuBar editMenuBar = new MenuBar();
        MenuBar saveMenuBar = new MenuBar();
        MenuBar helpMenuBar = new MenuBar();
        MenuBar viewsMenuBar = new MenuBar();

        fileMenuBar.getMenus().add(fileMenu);
        editMenuBar.getMenus().add(editMenu);
        saveMenuBar.getMenus().add(saveMenu);
        helpMenuBar.getMenus().add(help);
        viewsMenuBar.getMenus().add(views);

        HBox menus = new HBox(fileMenuBar, editMenuBar, saveMenuBar, viewsMenuBar, helpMenuBar);
        agendaMenuBarScene = new HBox(menus);
    }

    public static HBox getAgendaMenuBarScene() {
        return agendaMenuBarScene;
    }


}
