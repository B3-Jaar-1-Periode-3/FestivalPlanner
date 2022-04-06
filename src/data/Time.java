package data;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static LocalTime time = LocalTime.of(0,0);
    private static int speedup = 1;

    public Time(){
    }

    public static void update(double deltaTime){
        time = time.plusSeconds(speedup * (long)(deltaTime * 100));
    }

    public static LocalTime getTime(){
        return time;
    }

    public static String timeToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return formatter.format(getTime());
    }

    public static void setSpeed(int speed){
        speedup = speed;
    }

    public static int getSpeed(){
        return speedup;
    }
}
