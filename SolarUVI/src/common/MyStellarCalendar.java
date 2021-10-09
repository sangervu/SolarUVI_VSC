package common;

import java.util.Calendar;
import java.util.TimeZone;

public class MyStellarCalendar {

    public static int year;
	public static int month;
	public static int day;
	public static int hour;
	public static int minute;
	public static double timeZone;
    public static double T;
    public static double currentT;
    public static double stellarTimeNoonDeg;
    public static double stellarTimeLocalDeg;

	public static void MyDate() {

		TimeZone tz = TimeZone.getDefault();
        int timeZoneInt = tz.getRawOffset(); // in milliseconds
        timeZone = (double) timeZoneInt / 1000. / 60. / 60.; // in hours
       
        /*Calendar and time*/
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
    }
	
	public static void Julian() {

        double julian = 367 * year - (7 * (year + (month + 9) / 12)) / 4 - (3 * ((year + (month - 9) / 7) / 100 + 1)) / 4 + 275 * month / 9 + day + 1721029;
        
        T = (julian - 2451545.) * 0.000027378507871321;
        currentT = (julian - 0.5 + hour / 24. + minute / 1440. - 2451545.) * 0.000027378507871321;
    }
	
    public static void StellarTime() {
    	
    	double longitudeDeg = Location.longitude;
        double stellarTimeDeg = TrueDegree.minDegree((24110.54841 + 8640184.812866 * T + 0.093104 * (T * T) - 0.0000062 * (T * T * T)) / 3600. * 15.);
        
        stellarTimeNoonDeg = TrueDegree.minDegree(stellarTimeDeg + 1.002737908 * (-timeZone ) * 15. + longitudeDeg);
        stellarTimeLocalDeg = TrueDegree.minDegree(stellarTimeNoonDeg + (1.002737908 * (hour + minute / 60.) * 15.));
    }

}
