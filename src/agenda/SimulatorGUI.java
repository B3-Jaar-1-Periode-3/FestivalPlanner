package agenda;

import data.*;
import data.Event;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;
import tiled.Camera;
import tiled.TiledMap;
import tiled.pathfinding.Target;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.time.LocalTime;
import java.util.ArrayList;

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
    private Label timeText = new Label();
    private ProgressBar timeBar;


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
        mainPane.setCenter(pane);
        javafx.scene.text.Font font = new javafx.scene.text.Font(16);

        //Settings bar
        HBox settingBar = new HBox();
        mainPane.setTop(settingBar);
        settingBar.setSpacing(4);

        //Adding Buttons to Setting bar
        Label timeLabel = new Label("Time: ");
        timeLabel.setFont(font);
        timeText.setFont(font);
        timeBar = new ProgressBar();
        javafx.scene.control.Button pause = new javafx.scene.control.Button("=");
        javafx.scene.control.Button fastForwardOne = new javafx.scene.control.Button(">");
        javafx.scene.control.Button fastForwardTwo = new javafx.scene.control.Button(">>");
        javafx.scene.control.Button fastForwardThree = new Button(">>>");
        settingBar.getChildren().addAll(timeLabel, timeText, timeBar, pause, fastForwardOne, fastForwardTwo, fastForwardThree);

        //Actions of Buttons in Setting bar
        pause.setOnAction(e -> Time.setSpeed(0));
        fastForwardOne.setOnAction(e -> Time.setSpeed(1));
        fastForwardTwo.setOnAction(e -> Time.setSpeed(2));
        fastForwardThree.setOnAction(e -> Time.setSpeed(4));

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

        for (Visitor v : Festival.getInstance().getVisitors()) {
            v.draw(graphics);
        }
/**
 * TODO hier kan je werken met je timer voor festivals. dan moet je door de evenementen gaan en dan kijken of de tijd van het evenement binnen de tijd van de timer valt en dan moet hij er visitorss naar sturen.
 */

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
        timeText.setText(Time.timeToString());
        Time.update(deltaTime);
        if (timer > -0.1) {
            timer -= deltaTime;
        }
        for (Event event : Festival.getInstance().getEventList()) {
            if (Time.getTime().isBefore(event.getEndTime()) && Time.getTime().isAfter(event.getStartTime())) {
                Podium selectedPodium = event.getPodium();
                if (!event.isEventVisitorsSpawned()) {
                    System.out.println("Event started :)");
                    event.setEventVisitorsSpawned(true);
                    for (int i = 0; i < event.getPopularity()*5; i++) {
                        Festival.getInstance().getVisitors().add(new Visitor(new Target(tiledMap.getCollisionLayer(), selectedPodium.getObject().getCenterTile())));
                        System.out.println("Added Visitor! + ( " + Festival.getInstance().getVisitors().size() + " )");
                    }
                }
            }
        }
        for (Visitor visitor : Festival.getInstance().getVisitors()) {
            if (!visitor.isSpawned()) {
                if (timer <= 0) {
                    visitor.spawn(tiledMap.getSpawn());
                    timer = (0.5 / Time.getSpeed());
                }
            } else {
                visitor.update(deltaTime, tiledMap.getExit());
            }
        }
        this.timeBar.setProgress((Time.getTime().getHour() * 60 + Time.getTime().getMinute()) / (double) 1440); //Updates Time bar in sync

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
