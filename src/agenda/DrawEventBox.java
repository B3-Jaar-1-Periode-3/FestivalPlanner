package agenda;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.Duration;
import java.time.LocalTime;

public class DrawEventBox {

    public static void addBox(FXGraphics2D graphics, int podiumID, LocalTime beginTime, LocalTime endTime) {

        double podiumWidth = (double)((1920 - 150)/4); //Screen width - time column / amount of podiums
        double x = 150 + (podiumWidth * (podiumID - 1));
        double y = 80 + (100 * beginTime.getHour());
        long timeLength = Duration.between(beginTime, endTime).toMinutes();
        double height = (double)(100 * (timeLength / 60));

        Rectangle2D box = new Rectangle2D.Double(x,y,podiumWidth,height);
        graphics.setColor(Color.black);
        graphics.draw(box);
        graphics.fill(box);
    }
}
