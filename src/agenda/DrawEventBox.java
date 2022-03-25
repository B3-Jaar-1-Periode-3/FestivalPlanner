package agenda;

import data.Artist;
import data.Event;
import data.Festival;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class DrawEventBox {

    public static void drawAllBoxes(){
        Festival festival = Festival.getInstance();

        for (data.Event event : festival.getEventList()) {
            addBox(event);
        }
    }

    public static void clearALlBoxes(Event event) {
        ResizableCanvas canvas = AgendaGUI.getCanvas();
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();
        int podiumID = event.getPodium().getID();
        double podiumWidth = ((double) (1920-150)/4); //Screen width - time column / amount of podiums
        double x = 150 + (podiumWidth * (podiumID - 1));
        double y = 80 + (100 * startTime.getHour());
        long timeLength = Duration.between(startTime, endTime).toMinutes();
        double height = (100 * (timeLength / 60.0));

        Rectangle2D box = new Rectangle2D.Double(x, y, podiumWidth,height);
        graphics.setColor(Color.white);
        graphics.fill(box);

        AgendaGUI agendaGUI = new AgendaGUI();

        graphics.setColor(Color.black);
        agendaGUI.draw(graphics);
    }

    public static void addBox(Event event) {
        ResizableCanvas canvas = AgendaGUI.getCanvas();
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        LocalTime startTime = event.getStartTime();
        LocalTime endTime = event.getEndTime();
        int podiumID = event.getPodium().getID();
        double podiumWidth = (double)((1920 - 150)/4); //Screen width - time column / amount of podiums
        double x = 150 + (podiumWidth * (podiumID - 1));
        double y = 80 + (100 * startTime.getHour()) + (int)(100 * (startTime.getMinute()) / 60);
        long timeLength = Duration.between(startTime, endTime).toMinutes();
        double height = (100 * (timeLength / 60.0));

        Rectangle2D box = new Rectangle2D.Double(x,y,podiumWidth,height);
        graphics.setColor(Color.CYAN);
        graphics.fill(box);
        graphics.setColor(Color.black);
        graphics.draw(box);

        ArrayList<Artist> artistList = event.getArtists();
        int i = 0;
        for (Artist artist : artistList){
            i++;
            if (artistList.size() == 1){
                graphics.drawString("Artist: ", (int)(x), (int)y + 20);
                graphics.drawString(artist.getName(), (int)(x), (int)y + 40);
            } else {
                graphics.drawString("Artists: ", (int)(x), (int)y + 20);
                if(i >= 4){
                    graphics.drawString("...", (int)(x), (int)y + 15 + (20 * i));
                    break;
                } else {
                    graphics.drawString(artist.getName(), (int) (x), (int) y + 20 + (20 * i));
                }
            }
        }

        graphics.drawString("Genre: " + event.getGenre(), (int)(x + podiumWidth / 2), (int)y + 20);
        graphics.drawString("Popularity: " + event.getPopularity(), (int)(x + podiumWidth / 2), (int)y + 40);
        graphics.drawString("Time: " + event.getStartTime() + " - " + event.getEndTime(), (int)(x + podiumWidth / 2), (int)y + 60);
    }
}
