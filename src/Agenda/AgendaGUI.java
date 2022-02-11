package Agenda;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class AgendaGUI extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage)  throws Exception{

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());


        Menu menu = new Menu("Menu");

        // load file (always first on the list)
        MenuItem loadFile = new MenuItem("Load file");
        menu.getItems().add(loadFile);

        // save planning menu
        MenuItem saveEdit = new MenuItem("Save planning");
        menu.getItems().add(saveEdit);

        // edit menu & sub menu
        Menu editMenu = new Menu("Edit");
        MenuItem editEvent = new MenuItem("Edit event");
        MenuItem editArtist = new MenuItem("Edit artist");
        MenuItem editGenre = new MenuItem("Edit genre");
        editMenu.getItems().add(editEvent);
        editMenu.getItems().add(editArtist);
        editMenu.getItems().add(editGenre);
        menu.getItems().add(editMenu);

        // save menu & sub menu
        Menu saveMenu = new Menu("Save");
        MenuItem saveEvent = new MenuItem("Save event");
        MenuItem saveArtist = new MenuItem("Save artist");
        MenuItem saveGenre = new MenuItem("Save genre");
        saveMenu.getItems().add(saveEvent);
        saveMenu.getItems().add(saveArtist);
        saveMenu.getItems().add(saveGenre);
        menu.getItems().add(saveMenu);

        // delete menu & sub menu
        Menu deleteMenu = new Menu("Delete");
        MenuItem deleteEvent = new MenuItem("Delete event");
        MenuItem deleteArtist = new MenuItem("Delete artist");
        MenuItem deleteGenre = new MenuItem("Delete genre");
        deleteMenu.getItems().add(deleteEvent);
        deleteMenu.getItems().add(deleteArtist);
        deleteMenu.getItems().add(deleteGenre);
        menu.getItems().add(deleteMenu);


        // help menu (always last on the list)
        MenuItem helpMenu = new MenuItem("Help");
        menu.getItems().add(helpMenu);

        // all menus together
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(menu);


        mainPane.setTop(menuBar);
        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Agenda");
        stage.show();
        draw(g2d);

    }

    public void init() {
    }

    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);
        // graphics.scale(1, -1);

        drawGrit(graphics);

        drawText(graphics,-920,290,"main stage");
        drawText(graphics,-920,90,"substage 1");
        drawText(graphics,-920,-110,"substage 2");
        drawText(graphics,-920,-310,"Substage 3");

    }

    public Stroke drawLine(float width){
        Stroke s = new BasicStroke(width,
                BasicStroke.JOIN_ROUND,
                BasicStroke.CAP_ROUND);

        return s;
    }

    //maakt de lijnen die op het canvas staan.
    public void drawGrit(FXGraphics2D graphics){
        graphics.setStroke(drawLine(10));
        graphics.drawLine(-760,1080,-760,-1080);
        graphics.drawLine(-1800,380,1800,380);
        graphics.drawLine(-1800,180,1800,180);
        graphics.drawLine(-1800,-20,1800,-20);
        graphics.drawLine(-1800,-220,1800,-220);
        graphics.drawLine(-1800,-420,1800,-420);
    }

    public void drawText(FXGraphics2D graphics2D,int x, int y, String name){
        graphics2D.drawString(name,x, -y);
    }

    public static void main(String[] args) {
        launch(AgendaGUI.class);
    }


}