package agenda;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import tiled.TiledMap;

import java.awt.geom.AffineTransform;

public class SimulatorGUI extends Stage {
    private Canvas canvas;
    private TiledMap tiledMap;

    public SimulatorGUI() {
        BorderPane mainPane = new BorderPane();
        mainPane.setMinSize(1010, 1080);
        canvas = new Canvas(1010, 1080);

        mainPane.setTop(canvas);

        tiledMap = new TiledMap("Map.json");
        Scene scene = new Scene(mainPane);

        draw(new FXGraphics2D(canvas.getGraphicsContext2D()));

        setTitle("Simulation");
        setScene(scene);
    }

    public void draw (FXGraphics2D graphics) {
        AffineTransform transform = graphics.getTransform();
        graphics.clearRect(0,0, (int) canvas.getWidth(), (int) canvas.getHeight());
        transform.scale(0.5,0.5);
        graphics.setTransform(transform);
        tiledMap.draw(graphics);
    }
}
