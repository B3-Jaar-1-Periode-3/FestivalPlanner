package agenda;

import data.Festival;
import data.Visitor;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import tiled.Camera;
import tiled.TiledMap;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SimulatorGUI extends Stage implements Resizable{
    private Canvas backgroundCanvas;
    private Canvas canvas;
    private TiledMap tiledMap;
    private Camera camera;
    private FXGraphics2D graphics2D;
    private FXGraphics2D backgroundGraphics;
    private Pane pane;
    private boolean toUpdateBackground;
    private long lastFPSCheck = 0;
    private int currentFPS = 0;
    private int totalFrames = 0;
    private double timer = 1;

    public SimulatorGUI() {
        BorderPane mainPane = new BorderPane();
        this.toUpdateBackground = true;
        canvas = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        backgroundCanvas = new Canvas(Toolkit.getDefaultToolkit().getScreenSize().getWidth(), Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        this.backgroundGraphics = new FXGraphics2D(backgroundCanvas.getGraphicsContext2D());
        this.graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        this.pane = new Pane();
        pane.getChildren().addAll(backgroundCanvas, canvas);
        canvas.toFront();
        camera = new Camera(this);

        mainPane.setTop(pane);

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
                draw(graphics2D);
            }
        }.start();
        draw(graphics2D);
    }

    public void draw(FXGraphics2D graphics) {
        totalFrames++;
        if(System.nanoTime() > lastFPSCheck + 1000000000) {
            lastFPSCheck = System.nanoTime();
            currentFPS = totalFrames;
            totalFrames = 0;
        }

        graphics.setBackground(new Color(16, 16, 48));

        if (toUpdateBackground) {
            drawBackground(backgroundGraphics);
        }

        canvas = createNewCanvas();
        graphics = graphics2D;
        graphics.setTransform(camera.getTransform());

        for (Visitor visitor : Festival.getInstance().getVisitors()) {
            if (visitor.isSpawned()) {
                visitor.draw(graphics);
            }
        }

        graphics.setTransform(new AffineTransform());
        graphics.setColor(Color.GREEN);
        graphics.setFont(new Font("Arial", Font.PLAIN, 25));
        graphics.drawString(currentFPS + "",(int) backgroundCanvas.getWidth()-30, 25);
    }

    public void drawBackground(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.clearRect(0, 0, (int) backgroundCanvas.getWidth(), (int) backgroundCanvas.getHeight());
        graphics.setTransform(camera.getTransform());
        graphics.setBackground(new Color(16, 16, 48));
        this.toUpdateBackground = false;
        tiledMap.draw(graphics);
//        Festival.getInstance().getVisitors().get(0).getTarget().draw(graphics);
    }

    private Canvas createNewCanvas() {
        pane.getChildren().remove(canvas);
        this.canvas = new Canvas(canvas.getWidth(), canvas.getHeight());
        pane.getChildren().add(this.canvas);
        this.canvas.toFront();
        graphics2D = new FXGraphics2D(canvas.getGraphicsContext2D());
        return this.canvas;
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

    public Canvas getBackgroundCanvas() {
        return backgroundCanvas;
    }

    public void setBackgroundCanvas(Canvas backgroundCanvas) {
        this.backgroundCanvas = backgroundCanvas;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public FXGraphics2D getGraphics2D() {
        return graphics2D;
    }

    public void setGraphics2D(FXGraphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    public FXGraphics2D getBackgroundGraphics() {
        return backgroundGraphics;
    }

    public void setBackgroundGraphics(FXGraphics2D backgroundGraphics) {
        this.backgroundGraphics = backgroundGraphics;
    }

    public boolean isToUpdateBackground() {
        return toUpdateBackground;
    }

    public void setToUpdateBackground(boolean toUpdateBackground) {
        this.toUpdateBackground = toUpdateBackground;
    }

    public long getLastFPSCheck() {
        return lastFPSCheck;
    }

    public void setLastFPSCheck(long lastFPSCheck) {
        this.lastFPSCheck = lastFPSCheck;
    }

    public int getCurrentFPS() {
        return currentFPS;
    }

    public void setCurrentFPS(int currentFPS) {
        this.currentFPS = currentFPS;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public void setTotalFrames(int totalFrames) {
        this.totalFrames = totalFrames;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}
