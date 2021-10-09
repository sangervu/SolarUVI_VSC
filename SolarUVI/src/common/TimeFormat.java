package common;

public class TimeFormat {

    public static double hourTimeDouble;
    public static String minuteTimeString;

    public static String timeHour(double timeValue) {
        
        int hourTime = (int)timeValue;
        String hourTimeString = String.valueOf(hourTime);
        
        hourTimeDouble = (double)hourTime;

        return hourTimeString;
    }

    public static String timeMinute(double timeValue) {

        int minuteTime = (int)((timeValue - hourTimeDouble) * 60.);

        if (minuteTime < 10) {
            minuteTimeString = "0" + String.valueOf(minuteTime);
        } else {
            minuteTimeString = String.valueOf(minuteTime);
        }
        return minuteTimeString;
    }

}

