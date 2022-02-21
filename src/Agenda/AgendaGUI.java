package Agenda;


import Data.Event;
import Data.Festival;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;
import javafx.scene.layout.BorderPane;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class AgendaGUI extends Application {

    private ResizableCanvas canvas;
    private final double screenWidth = 1920;
    private final double screenHeigth = 5000;

    @Override
    public void start(Stage stage){
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);

        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        //making a scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(canvas);
        scrollPane.setPannable(true);

        // Build & setup AgendaMenuBar scene
        AgendaMenubar.build(stage);
        mainPane.setTop(AgendaMenubar.getAgendaMenuBarScene());

        mainPane.setCenter(scrollPane);
        stage.setScene(new Scene(mainPane, 1920, 1080));
        stage.setTitle("Agenda");
        stage.show();
        draw(g2d);
    }

    public void init() {
    }

    public void draw(FXGraphics2D graphics) {

        canvas.setWidth(screenWidth);
        canvas.setHeight(screenHeigth);

        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        drawGrit(graphics);
        drawText(graphics);
        drawTime(graphics);
        drawHourLine(graphics);
    }

    public Stroke drawLine(float width) {
        Stroke s = new BasicStroke(width,
                BasicStroke.JOIN_ROUND,
                BasicStroke.CAP_ROUND);
        return s;
    }

    // this makes the grid of the planner
    public void drawGrit(FXGraphics2D graphics) {

        graphics.setStroke(drawLine(3));
        int x = 150;
        int y1 = 0;
        int y2 = (int)this.canvas.getHeight();
        for (int lines = 0; lines < 4; lines++) {
            graphics.drawLine(x, y1, x, y2);
            x += (screenWidth-150)/4;
        }
     //   graphics.drawLine(0, 80, 1920, 80);
    }

    /**
     * deze zet de naam van het podium
     *
     * @param graphics2D
     */
    public void drawText(FXGraphics2D graphics2D) {
        graphics2D.setFont(new Font("Purisa", Font.PLAIN, 32));
        graphics2D.drawString("Main Stage", (int)((screenWidth-150)/4) - 150, 50);
        graphics2D.drawString("Substage 1", (int)(((screenWidth-150)/4)*2) - 150, 50);
        graphics2D.drawString("Substage 2", (int)(((screenWidth-150)/4)*3) - 150, 50);
        graphics2D.drawString("Substage 3", (int)(((screenWidth-150)/4)*4) - 150, 50);
    }

    /**
     * tekent de uren.
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

    public void drawHourLine(FXGraphics2D graphics2D) {
        int y = 80;
        for (int time = 0; time < 25; time++) {
            graphics2D.setStroke(drawLine(1));
            graphics2D.drawLine(0, y, (int)screenWidth, y);
            y += 200;
        }
    }

    public void drawRectangle(FXGraphics2D graphics2D, int width, int height){
        graphics2D.drawRect(500,200,width,height);
    }

    public static void main(String[] args) {
        launch(AgendaGUI.class);
    }
}