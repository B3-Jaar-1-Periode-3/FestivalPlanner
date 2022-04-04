package tiled;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.Resizable;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private Point2D centerPoint = new Point2D.Double(0, 0);
    private float zoom = 0.75f;
    private double rotation = 0;
    private Point2D lastmousePos;
    private Canvas canvas;
    private Resizable resizable;
    private FXGraphics2D graphics;

    public Camera(Canvas canvas, Resizable resizable, FXGraphics2D graphics) {
        this.canvas = canvas;
        this.resizable = resizable;
        this.graphics = graphics;

        canvas.setOnMousePressed(e -> this.lastmousePos = new Point2D.Double(e.getX(), e.getY()));
        canvas.setOnMouseDragged(e -> mouseDragged(e));
        canvas.setOnScroll(e -> mouseScroll(e));
    }

    public AffineTransform getTransform(int windowWidth, int windowHeight) {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth / 2, windowHeight / 2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        tx.rotate(rotation);
        return tx;
    }

    public void mouseDragged(MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            centerPoint = new Point2D.Double(
                    centerPoint.getX() - (lastmousePos.getX() - e.getX()) / zoom,
                    centerPoint.getY() - (lastmousePos.getY() - e.getY()) / zoom
            );
            lastmousePos = new Point2D.Double(e.getX(), e.getY());
            resizable.draw(graphics);
        }
    }

    public void mouseScroll(ScrollEvent e) {
        float zoom = this.zoom * (float) (1 + e.getDeltaY() / 500.0f);

        this.zoom = zoom;
        resizable.draw(graphics);

    }
}
