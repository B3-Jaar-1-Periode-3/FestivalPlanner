package agenda;

import data.Artist;
import data.Festival;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.Duration;
import java.time.LocalTime;

public class DrawEventBox {

    public static void drawAllBoxes(){
        Festival festival = Festival.getInstance();

        for (data.Event event : festival.getEventList()) {
            addBox(event);
        }
    }

    public static void addBox(data.Event event) {
        ResizableCanvas canvas = AgendaGUI.getCanvas();
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();
        int podiumID = event.getPodium().getID();
        double podiumWidth = (double)((1920 - 150)/4); //Screen width - time column / amount of podiums
        double x = 150 + (podiumWidth * (podiumID - 1));
        double y = 80 + (100 * startTime.getHour());
        long timeLength = Duration.between(startTime, endTime).toMinutes();
        double height = (100 * (timeLength / 60.0));

        Rectangle2D box = new Rectangle2D.Double(x,y,podiumWidth,height);
        graphics.setColor(Color.CYAN);
        graphics.fill(box);
        graphics.setColor(Color.black);
        graphics.draw(box);

        if (event.getArtists().size() == 1) {
            graphics.drawString("Artist: " + event.getArtists().get(0).getName(), (int)x, (int)y + 20);
        } else if (event.getArtists().size() == 2) {
            graphics.drawString("Artists: " + event.getArtists().get(0).getName() + ", " + event.getArtists().get(1).getName(), (int)x, (int)y + 20);
        } else {
            graphics.drawString("Artists: " + event.getArtists().get(0).getName() + ", " + event.getArtists().get(1).getName() + " and more", (int)x, (int)y + 20);
        }
        graphics.drawString("Popularity: " + event.getPopularity(), (int)x, (int)y + 40);
    }
}
