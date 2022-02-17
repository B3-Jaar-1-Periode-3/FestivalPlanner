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
import java.awt.geom.Line2D;

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
        canvas.setWidth(1920.0);
        canvas.setHeight(2023.0);

        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.translate(this.canvas.getWidth() / 2, this.canvas.getHeight() / 2);

        drawGrit(graphics);
        drawText(graphics);
        drawTime(graphics);
        System.out.println("with :"+canvas.getWidth());
        System.out.println("hitht: "+ canvas.getHeight());


    }

    public Stroke drawLine(float width){
        Stroke s = new BasicStroke(width,
                BasicStroke.JOIN_ROUND,
                BasicStroke.CAP_ROUND);

        return s;
    }

    //maakt de lijnen die op het canvas staan.
    public void drawGrit(FXGraphics2D graphics){

        graphics.setStroke(drawLine(5));
        graphics.drawLine(-820,1080,-820,-1080);
        graphics.drawLine(-365,-550,-365,500);
        graphics.drawLine(90,-550,90,500);
        graphics.drawLine(545,-550,545,500);
        graphics.drawLine(1000,-550,1000,500);



        graphics.drawLine(-1800,-420,1800,-420);
    }

    public void drawText(FXGraphics2D graphics2D){
        drawText(graphics2D,-800,480,"main stage");
        drawText(graphics2D,-345,480,"substage 1");
        drawText(graphics2D,110,480,"substage 2");
        drawText(graphics2D,565,480,"Substage 3");
    }

    public void drawTime(FXGraphics2D graphics2D){
        int y=400 ;
        for (int time = 0; time < 24; time++) {
            if (time<10){
                drawText(graphics2D,-900,y,"0"+time+".00");
            }
            else { drawText(graphics2D,-900,y,time+".00");}
            y-=35;
        }
    }


    public void drawText(FXGraphics2D graphics2D,int x, int y, String name){
        graphics2D.drawString(name,x, -y);
    }

    public static void main(String[] args) {
        launch(AgendaGUI.class);
    }


}