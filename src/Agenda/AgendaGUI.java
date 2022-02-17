package Agenda;


import javafx.application.Application;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class AgendaGUI extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        //making a scrol pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(canvas);
        scrollPane.setPannable(true);

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
        MenuBar fileMenuBar = new MenuBar();
        MenuBar editMenuBar = new MenuBar();
        MenuBar saveMenuBar = new MenuBar();
        MenuBar deleteMenuBar = new MenuBar();

        fileMenuBar.getMenus().add(fileMenu);
        editMenuBar.getMenus().add(editMenu);
        saveMenuBar.getMenus().add(saveMenu);
        deleteMenuBar.getMenus().add(deleteMenu);

        mainPane.setCenter(scrollPane);
        HBox menus = new HBox(fileMenuBar, editMenuBar, saveMenuBar, deleteMenuBar, help);
        mainPane.setTop(menus);
        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Agenda");
        stage.show();
        draw(g2d);

    }

    public void init() {
    }

    public void draw(FXGraphics2D graphics) {
        canvas.setHeight(3000.0);
        canvas.setWidth(2000.0);

        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        drawGrit(graphics);
        drawText(graphics);
        drawTime(graphics);
        System.out.println("with :" + canvas.getWidth());
        System.out.println("hitht: " + canvas.getHeight());


    }

    public Stroke drawLine(float width) {
        Stroke s = new BasicStroke(width,
                BasicStroke.JOIN_ROUND,
                BasicStroke.CAP_ROUND);

        return s;
    }

    // this makes the grid of the planner
    public void drawGrit(FXGraphics2D graphics) {

        graphics.setStroke(drawLine(5));
        int x = 250;
        int y1 = 0;
        int y2 = 3000;
        for (int lines = 0; lines < 4; lines++) {
            graphics.drawLine(x, y1, x, y2);
            x += 455;
        }

        graphics.drawLine(0, 80, 1920, 80);
    }

    /**
     * deze zet de naam van het podium
     *
     * @param graphics2D
     */
    public void drawText(FXGraphics2D graphics2D) {
        graphics2D.setFont(new Font("Purisa", Font.PLAIN, 32));
        graphics2D.drawString("Main Stage", 400, 50);
        graphics2D.drawString("substage 1", 855, 50);
        graphics2D.drawString("substage 2", 1310, 50);
        graphics2D.drawString("substage 3", 1765, 50);

    }

    /**
     * tekend de uuren.
     *
     * @param graphics2D
     */
    public void drawTime(FXGraphics2D graphics2D) {
        int x = 50;
        int y = 110;
        for (int time = 0; time < 25; time++) {
            if (time < 10) {
                graphics2D.drawString("0" + time + ".00", x, y);

            } else if (time == 24) {
                graphics2D.drawString("00.00", x, y);
            } else {
                graphics2D.drawString(time + ".00", x, y);
            }
            y += 200;

            graphics2D.setStroke(drawLine(5));
            graphics2D.drawLine(-1800, -420, 1800, -420);
        }
    }


    public void drawText(FXGraphics2D graphics2D, int x, int y, String name) {
        graphics2D.drawString(name, x, -y);
    }

    public static void main(String[] args) {
        launch(AgendaGUI.class);
    }


}