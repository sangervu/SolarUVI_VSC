package calculations;

import common.Location;
import common.MyStellarCalendar;
import common.TimeFormat;
import common.TrueDegree;

public class UVICalculations {
	
	 public static void uviNow() {
	        
	     double longitudeDeg = Location.longitude;
	     double latitudeDeg = Location.latitude;
	     
		 double T = MyStellarCalendar.T;

	        // calculate sun elements
	        double deltaSunDeg = ElementsSun.deltaSunDeg;
	        double alfaSunDeg = ElementsSun.alfaSunDeg;

	        double timeStellarLocalDeg = MyStellarCalendar.stellarTimeLocalDeg;
	        double timeStellarNoon = MyStellarCalendar.stellarTimeNoonDeg;

	        // Sun position parameters
	        double currentSunElevationDeg = SunPosition.currentElevationDeg;
	        double sunAzimuthDeg = SunPosition.currentAzimuthDeg;
	        String sunAzimuthString = SunPosition.currentAzimuthString;
	        double maxSunElevationDeg = SunPosition.maxElevationDeg;

	        //SolarPower powerSolar = new SolarPower(latitudeDeg, T, currentSunElevationDeg, maxSunElevationDeg);


	        //double sunPowerNow = powerSolar.currentSunPower;
	        double sunUvi = SolarCalculations.UvIndex;

	        System.out.println("Sun current elevation: " + currentSunElevationDeg);
	        System.out.println("Sun current azimuth: " + sunAzimuthDeg);
	        System.out.println("Sun current azimuth: " + sunAzimuthString);
	        //System.out.println("Sun current Power [W/m2]: " + sunPowerNow);
	        System.out.println("UVI now: " + sunUvi);
	        
	        double sunUviMax = SolarCalculations.UvIndexMax;

	        System.out.println("UVI max today: " + sunUviMax);
	        System.out.println("Sun max elevation today: " + maxSunElevationDeg);
	    }
	
}
