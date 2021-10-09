package calculations;

import common.Location;
import common.MathNew;
import common.MyStellarCalendar;
import common.TrueDegree;

public class SunPosition {

    static double currentElevationDeg;
    static double currentAzimuthDeg;
    static String currentAzimuthString;
    static double maxElevationDeg;

    public static void sunPosition() {
    	
    	double latitudeDeg = Location.latitude;
    	double alfaDeg = ElementsSun.alfaSunDeg;
    	double deltaDeg = ElementsSun.deltaSunDeg;
    	double timeStellarLocalDeg = MyStellarCalendar.stellarTimeLocalDeg;

        maxElevationDeg = MathNew.roundDesimal_1(TrueDegree.trueElevation(90.0 + deltaDeg - latitudeDeg));
        double hourRad = Math.toRadians(timeStellarLocalDeg - alfaDeg);

        double deltaRad = Math.toRadians(deltaDeg);
        double latitudeRad = Math.toRadians(latitudeDeg);

        /*atsimuutti x ja y komponentit*/
        double Ay = Math.sin(hourRad) * Math.cos(deltaRad);
        double Ax = Math.cos(hourRad) * Math.cos(deltaRad) * Math.sin(latitudeRad) - Math.sin(deltaRad) * Math.cos(latitudeRad);

        double azimuth = MathNew.roundDesimal_1(TrueDegree.minDegree(TrueDegree.trueTan(Ay, Ax) + 180.));
        currentAzimuthDeg = azimuth;

        currentElevationDeg = MathNew.roundDesimal_1(Math.toDegrees(Math.asin(Math.sin(deltaRad) * Math.sin(latitudeRad) + Math.cos(hourRad) * Math.cos(deltaRad) * Math.cos(latitudeRad))));
        currentAzimuthString = TrueDegree.NorthSouthEastWest(azimuth);
    }
}
