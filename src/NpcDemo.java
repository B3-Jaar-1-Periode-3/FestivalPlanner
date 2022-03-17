import com.sun.glass.ui.Screen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class NpcDemo extends Application {

    private ResizableCanvas canvas;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane, 1500, 800));
        stage.setTitle("NPCs");

        stage.setX(Screen.getScreens().get(0).getX());
        stage.setY(Screen.getScreens().get(0).getY());
        stage.show();
        draw(g2d);

        canvas.setOnMouseMoved(event -> {
            for (Npc visitor : this.visitors) {
                visitor.setTarget(new Point2D.Double(event.getX(), event.getY()));
            }
        });
    }


    ArrayList<Npc> visitors;
    double timer;

    public void init() {
        this.visitors = new ArrayList<>();
        while (this.visitors.size() < 40) {
            Npc visitor = new Npc(new Point2D.Double(Math.random() * 1000, Math.random() * 1000), 0); // starting position
            if (!visitor.checkCollision(this.visitors)) {
                this.visitors.add(visitor);
            }
        }

        timer = 0;
    }


    public void draw(FXGraphics2D g2) {
        g2.setTransform(new AffineTransform());
        g2.setBackground(new Color(155, 205, 175));
        g2.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());

        for (Npc visitor : this.visitors) {
            visitor.draw(g2);
        }
    }

    public void update(double frameTime) {
        timer += frameTime;
        if (timer > 10) {
            timer -= 10;
            for (Npc visitor : this.visitors) {
                visitor.setTarget(new Point2D.Double(Math.random() * 1000, Math.random() * 1000));
            }
        }


        for (Npc visitor : this.visitors) {
            visitor.update(this.visitors);
        }
    }
}

