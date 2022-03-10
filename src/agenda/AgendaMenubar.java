package agenda;

import data.Festival;
import guis.HelpWindowGUI;
import guis.createGUIs.CreateGenreGUI;
import guis.editGUIs.EditArtistGUI;
import guis.editGUIs.EditEventGUI;
import guis.createGUIs.CreateArtistGUI;
import guis.createGUIs.CreateEventGUI;
import guis.editGUIs.EditGenreGUI;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

        loadFile.setOnAction(event -> {
            io.FileHandler.readFromFile();
        });

        savePlanning.setOnAction(event -> {
            io.FileHandler.saveToFile(Festival.getInstance());
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

        // all menu bars together
        MenuBar fileMenuBar = new MenuBar();
        MenuBar editMenuBar = new MenuBar();
        MenuBar saveMenuBar = new MenuBar();
        MenuBar deleteMenuBar = new MenuBar();
        MenuBar helpMenuBar = new MenuBar();

        fileMenuBar.getMenus().add(fileMenu);
        editMenuBar.getMenus().add(editMenu);
        saveMenuBar.getMenus().add(saveMenu);
        helpMenuBar.getMenus().add(help);

        HBox menus = new HBox(fileMenuBar, editMenuBar, saveMenuBar, helpMenuBar);
        agendaMenuBarScene = new HBox(menus);
    }

    public static HBox getAgendaMenuBarScene() {
        return agendaMenuBarScene;
    }


}
