package Agenda;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;


public class AgendaMenubar {
    private static HBox agendaMenuBarScene;

    public static void build(){
        // Single buttons
        Button help = new Button("Help");

        // File menu and submenu's
        Menu file = new Menu("File");
        Menu fileMenu = new Menu("File");
        MenuItem loadFile = new MenuItem("Load file");
        MenuItem savePlanning = new MenuItem("Save planning");
        fileMenu.getItems().add(loadFile);
        fileMenu.getItems().add(savePlanning);
        file.getItems().add(fileMenu);

        // edit menu & sub menu's
        Menu edit = new Menu("Edit");
        Menu editMenu = new Menu("Edit");
        MenuItem editEvent = new MenuItem("Edit event");
        MenuItem editArtist = new MenuItem("Edit artist");
        MenuItem editGenre = new MenuItem("Edit genre");
        editMenu.getItems().add(editEvent);
        editMenu.getItems().add(editArtist);
        editMenu.getItems().add(editGenre);
        edit.getItems().add(editMenu);

        // save menu & sub menu's
        Menu save = new Menu("Save");
        Menu saveMenu = new Menu("Save");
        MenuItem saveEvent = new MenuItem("Save event");
        MenuItem saveArtist = new MenuItem("Save artist");
        MenuItem saveGenre = new MenuItem("Save genre");
        saveMenu.getItems().add(saveEvent);
        saveMenu.getItems().add(saveArtist);
        saveMenu.getItems().add(saveGenre);
        save.getItems().add(saveMenu);

        // delete menu & sub menu's
        Menu delete = new Menu("Delete");
        Menu deleteMenu = new Menu("Delete");
        MenuItem deleteEvent = new MenuItem("Delete event");
        MenuItem deleteArtist = new MenuItem("Delete artist");
        MenuItem deleteGenre = new MenuItem("Delete genre");
        deleteMenu.getItems().add(deleteEvent);
        deleteMenu.getItems().add(deleteArtist);
        deleteMenu.getItems().add(deleteGenre);
        delete.getItems().add(deleteMenu);

        // all menu bars together
        javafx.scene.control.MenuBar fileMenuBar = new javafx.scene.control.MenuBar();
        javafx.scene.control.MenuBar editMenuBar = new javafx.scene.control.MenuBar();
        javafx.scene.control.MenuBar saveMenuBar = new javafx.scene.control.MenuBar();
        javafx.scene.control.MenuBar deleteMenuBar = new javafx.scene.control.MenuBar();

        fileMenuBar.getMenus().add(fileMenu);
        editMenuBar.getMenus().add(editMenu);
        saveMenuBar.getMenus().add(saveMenu);
        deleteMenuBar.getMenus().add(deleteMenu);


        HBox menus = new HBox(fileMenuBar, editMenuBar, saveMenuBar, deleteMenuBar, help);
//        mainPane.setTop(menus);


        agendaMenuBarScene = new HBox(menus);

    }

    public static HBox getAgendaMenuBarScene(){return agendaMenuBarScene;}

}
