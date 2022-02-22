package Agenda;

import editGUIs.EditArtistGUI;
import saveGUIs.SaveArtistGUI;
import saveGUIs.SaveEventGUI;
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
        MenuItem editEvent = new MenuItem("Edit event");
        MenuItem editArtist = new MenuItem("Edit artist");
        MenuItem editGenre = new MenuItem("Edit genre");
        editMenu.getItems().addAll(editEvent, editArtist, editGenre);

        editEvent.setOnAction(event -> {

        });

        editArtist.setOnAction(event -> {
            new EditArtistGUI().show();
        });

        editGenre.setOnAction(event -> {

        });

        // save menu & sub menu's
        Menu saveMenu = new Menu("Save");
        MenuItem saveEvent = new MenuItem("Save event");
        MenuItem saveArtist = new MenuItem("Save artist");
        MenuItem saveGenre = new MenuItem("Save genre");
        saveMenu.getItems().addAll(saveEvent, saveArtist, saveGenre);

        saveEvent.setOnAction(event -> {
            new SaveEventGUI().show();
        });

        saveArtist.setOnAction(event -> {
            new SaveArtistGUI().show();
        });

        saveGenre.setOnAction(event -> {

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
