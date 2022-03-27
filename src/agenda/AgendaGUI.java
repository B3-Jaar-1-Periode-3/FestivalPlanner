package agenda;

import data.Festival;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class AgendaGUI extends Application {

    private final double screenWidth = 1920;
    private final double screenHeight = 2600;
    public static ResizableCanvas canvas;

    @Override
    public void start(Stage stage) {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        //Creates a scroll pane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(canvas);
        scrollPane.setPannable(true);

        //Build & setup AgendaMenuBar scene
        AgendaMenubar.build(stage);
        mainPane.setTop(AgendaMenubar.getAgendaMenuBarScene());

        mainPane.setCenter(scrollPane);
        stage.setScene(new Scene(mainPane, screenWidth, 1080));
        stage.setTitle("Agenda");
        stage.show();

    }

    public void draw(FXGraphics2D graphics) {
        canvas.setWidth(this.screenWidth);
        canvas.setHeight(this.screenHeight);

        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.white);
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        drawGrid(graphics);
        drawText(graphics);
        drawTime(graphics);
        drawHourLine(graphics);
        DrawEventBox.drawAllBoxes();
    }

    private Stroke drawLine(float width) {
        Stroke stroke = new BasicStroke(width,
                BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND);
        return stroke;
    }

    //Draws the grid
    private void drawGrid(FXGraphics2D graphics) {
        graphics.setStroke(drawLine(3));
        int x = 150;
        int y1 = 0;
        int y2 = (int) this.canvas.getHeight();
        for (int lines = 0; lines < 4; lines++) {
            graphics.drawLine(x, y1, x, y2);
            x += (screenWidth - 150) / 4;
        }
        //   graphics.drawLine(0, 80, 1920, 80);
    }

    //Draws names of Podiums
    private void drawText(FXGraphics2D graphics2D) {
        graphics2D.setFont(new Font("Purisa", Font.PLAIN, 20));
        graphics2D.drawString(Festival.getInstance().getPodiumList().get(0).getName(), (int) ((screenWidth - 150) / 4) - 150, 50);
        graphics2D.drawString(Festival.getInstance().getPodiumList().get(1).getName(), (int) (((screenWidth - 150) / 4) * 2) - 150, 50);
        graphics2D.drawString(Festival.getInstance().getPodiumList().get(2).getName(), (int) (((screenWidth - 150) / 4) * 3) - 150, 50);
        graphics2D.drawString(Festival.getInstance().getPodiumList().get(3).getName(), (int) (((screenWidth - 150) / 4) * 4) - 150, 50);
    }

    //Draws the time
    private void drawTime(FXGraphics2D graphics2D) {
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
            y += 100;

            graphics2D.setStroke(drawLine(5));
            graphics2D.drawLine(-1800, -420, 1800, -420);
        }
    }

    public void drawText(FXGraphics2D graphics2D, int x, int y, String name) {
        graphics2D.drawString(name, x, -y);
    }

    private void drawHourLine(FXGraphics2D graphics2D) {
        int y = 80;
        for (int time = 0; time < 25; time++) {
            graphics2D.setStroke(drawLine(1));
            graphics2D.drawLine(0, y, (int) screenWidth, y);
            y += 100;
        }
    }

    public static ResizableCanvas getCanvas(){
        return canvas;
    }
}