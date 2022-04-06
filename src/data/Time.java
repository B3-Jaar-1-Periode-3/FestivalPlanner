package data;

import java.time.LocalTime;

public class Time {
    public static LocalTime time = LocalTime.of(0,0);
    private static int speedup = 1;
    private static double timer = 0;

    public Time(){
    }

    public static void update(double deltaTime){
        time = time.plusSeconds(speedup * (long)(deltaTime * 100));
    }

    public static LocalTime getTime(){
        return time;
    }

/*    public static String timeToString(){
        String timeString = Integer.toString(time);
        String minute = timeString.substring(timeString.length() - 2);
        String hour = timeString.substring(0, timeString.length() - 2);
        return hour + ":" + minute;
    }*/

    public static void setSpeed(int speed){
        speedup = speed;
    }

    public static int getSpeed(){
        return speedup;
    }
}
