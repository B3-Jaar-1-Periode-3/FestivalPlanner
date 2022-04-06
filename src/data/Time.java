package data;

public class Time {
    public static int time = 100;
    private static int speedup = 1;
    private static double timer = 0;

    public Time(){
    }

    public static void update(double deltaTime){
        timer += deltaTime;
        if (speedup > 0 && timer >= (1.0 / speedup)){
            time++;
            timer = 0;
        }

        if ((time % 100) >= 60){
            time = time + 40;
        }
    }

    public static int getTime(){
        return time;
    }

    public static String timeToString(){
        String timeString = Integer.toString(time);
        String minute = timeString.substring(timeString.length() - 2);
        String hour = timeString.substring(0, timeString.length() - 2);
        return hour + ":" + minute;
    }

    public static void setSpeed(int speed){
        speedup = speed;
    }

    public static int getSpeed(){
        return speedup;
    }
}
