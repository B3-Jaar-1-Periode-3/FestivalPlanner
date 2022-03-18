package agenda;

import data.Festival;
import data.Visitor;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.Affine;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import tiled.Camera;
import tiled.TiledMap;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SimulatorGUI extends Stage implements Resizable{
    private Canvas canvas;
    private TiledMap tiledMap;
    private Camera camera;
    private long lastFPSCheck = 0;
    private int currentFPS = 0;
    private int totalFrames = 0;
    private double timer = 1;

    public SimulatorGUI() {
        BorderPane mainPane = new BorderPane();
        mainPane.setMinSize(1010, 1080);
        canvas = new Canvas(1010, 1080);
        FXGraphics2D g = new FXGraphics2D(canvas.getGraphicsContext2D());
        camera = new Camera(canvas, this, g);

        mainPane.setTop(canvas);

        tiledMap = Festival.getInstance().getTiledMap();
        Scene scene = new Scene(mainPane);


        setTitle("Simulation");
        setScene(scene);

        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g);
            }
        }.start();
        draw(g);
    }

    public void draw(FXGraphics2D graphics) {
        totalFrames++;
        if(System.nanoTime() > lastFPSCheck + 1000000000) {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }

        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        graphics.setTransform(camera.getTransform((int)canvas.getWidth(), (int)canvas.getHeight()));
        tiledMap.draw(graphics);

        for (Visitor visitor : Festival.getInstance().getVisitors()) {
            if (visitor.isSpawned()) {
                visitor.draw(graphics);
            }
        }

        graphics.setTransform(new AffineTransform());
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString(currentFPS + "",(int) canvas.getWidth()-30, 25);
    }

    public void update(double deltaTime) {
        if (timer > -0.1) {
            timer -= deltaTime;
        }
        for (Visitor visitor : Festival.getInstance().getVisitors()) {
            if (!visitor.isSpawned()) {
                if (timer <= 0) {
                    visitor.spawn(tiledMap.getSpawn());
                    timer = 1;
                }
            } else {
                visitor.update(deltaTime);
            }
        }
    }
}
