package Agenda;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class AgendaGUI extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage)  {

        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());


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
