package Agenda;

import createGUIs.CreateGenreGUI;
import editGUIs.EditArtistGUI;
import editGUIs.EditEventGUI;
import createGUIs.CreateArtistGUI;
import createGUIs.CreateEventGUI;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class AgendaMenubar {
    private static HBox agendaMenuBarScene;

    public static void build(Stage stage){
        // Single buttons
        Menu help = new Menu("Help");
        MenuItem helpButton = new MenuItem("Help");
        help.getItems().add(helpButton);

        helpButton.setOnAction(event -> {

        });

        // File menu & submenu's
        Menu fileMenu = new Menu("File");
        MenuItem loadFile = new MenuItem("Load file");
        MenuItem savePlanning = new MenuItem("Save planning");
        fileMenu.getItems().addAll(loadFile, savePlanning);

        loadFile.setOnAction(event -> {

        });

        savePlanning.setOnAction(event -> {

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

        // delete menu & sub menu's
        Menu deleteMenu = new Menu("Delete");
        MenuItem deleteEvent = new MenuItem("Delete event");
        MenuItem deleteArtist = new MenuItem("Delete artist");
        MenuItem deleteGenre = new MenuItem("Delete genre");
        deleteMenu.getItems().addAll(deleteEvent, deleteArtist, deleteGenre);

        deleteEvent.setOnAction(event -> {

        });

        deleteArtist.setOnAction(event -> {

        });

        deleteGenre.setOnAction(event -> {

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
        deleteMenuBar.getMenus().add(deleteMenu);
        helpMenuBar.getMenus().add(help);

        HBox menus = new HBox(fileMenuBar, editMenuBar, saveMenuBar, deleteMenuBar, helpMenuBar);
        agendaMenuBarScene = new HBox(menus);
    }

    public static HBox getAgendaMenuBarScene(){return agendaMenuBarScene;}

}
