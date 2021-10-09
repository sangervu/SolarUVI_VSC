package calculations;

import common.Location;
import common.MathNew;
import common.MyStellarCalendar;
import common.TimeFormat;
import common.TrueDegree;

public class SolarCalculations {

//	static double thermalActivity;
//	static double thermalActivityStrong;
//	static double thermalActivityEnd;
//	static String thermalActivityEndString;
	public static double UvIndex;
	public static double UvIndexMax;
	public static double UvIndexOverThree;
	public static double UvIndexEnd;
	public static String UvIndexEndString;
	public static double maxSunRadiationPowerAnnual;
	public static double maxSunRadiationPowerDiurnal;
	public static double currentSunRadiationPower;

	private static String timeSouthString;
	private static double timeSouth;

	public static void south() {

		double alfaDeg = ElementsSun.alfaSunDeg;
		double timeStellarNoonDeg = MyStellarCalendar.stellarTimeNoonDeg;

		/* korkeimmillaan, eli etelässä */
		double timeSouth = TrueDegree.minDegree(alfaDeg - timeStellarNoonDeg) * 24. / 360.;
		timeSouthString = (TimeFormat.timeHour(timeSouth) + ":" + TimeFormat.timeMinute(timeSouth));
	}

	public static void solarCalculations() {

		double deltaSunDeg = ElementsSun.alfaSunDeg;
		double latitudeDeg = Location.latitude;
		double currentSunElevationDeg = SunPosition.currentElevationDeg;
		double maxSunAltitudeDiurnalDeg = SunPosition.maxElevationDeg;

		// double weakThermalLimitRad = Math.toRadians(17.5); // degrees in Sun
		// elevation, empirical
		// double strongThermalLimitRad = Math.toRadians(35.0); // degrees in Sun
		// elevation, empirical
		// double uvOverTwoLimitRad = Math.toRadians(90.0 - 55.0); //degrees in Sun
		// elevation, UV
		double uvOverThreeLimitRad = Math.toRadians(90.0 - 48.0); // degrees in Sun elevation, UV

		double latitudeRad = Math.toRadians(latitudeDeg);
		double currentSunElevationRad = Math.toRadians(currentSunElevationDeg);

		double deltaSunRad = Math.toRadians(deltaSunDeg);

		// thermalActivity = 2
		// * Math.acos(-Math.tan(deltaSunRad) * Math.tan(latitudeRad)
		// + Math.sin(weakThermalLimitRad) / (Math.cos(deltaSunRad) *
		// Math.cos(latitudeRad)))
		// / (2 * Math.PI) * 24;
		// if (Double.isNaN(thermalActivity)) {
		// thermalActivity = 0.;
		// }
		//
		// thermalActivityStrong = 2
		// * Math.acos(-Math.tan(deltaSunRad) * Math.tan(latitudeRad)
		// + Math.sin(strongThermalLimitRad) / (Math.cos(deltaSunRad) *
		// Math.cos(latitudeRad)))
		// / (2 * Math.PI) * 24;
		// if (Double.isNaN(thermalActivityStrong)) {
		// thermalActivityStrong = 0.;
		// }
		//
		// if (Double.isNaN(thermalActivity) || thermalActivity == 0) {
		// thermalActivityEnd = 0.;
		// } else {
		// thermalActivityEnd = timeSouth + thermalActivity / 2;
		// }

		double a = 2.696056, b = 5.474571, c = -0.09888, d = 0.040392;
		double m = 1. / Math.cos(Math.asin(6371. / 6393. * Math.sin((Math.PI / 2 - currentSunElevationRad))));

		double uvIndex = MathNew.roundDesimal_1(MathNew.pow(Math.cos(Math.PI / 2 - currentSunElevationRad), a)
				* MathNew.exp(b + c * m + d * m * m) / 25.);

		if (Double.isNaN(uvIndex)) {
			UvIndex = 0.;
		} else {

			UvIndex = uvIndex;
		}

		double maxSunAltitudeDiurnalRad = Math.toRadians(maxSunAltitudeDiurnalDeg);

		double mMax = 1. / Math.cos(Math.asin(6371. / 6393. * Math.sin((Math.PI / 2 - maxSunAltitudeDiurnalRad))));

		UvIndexMax = MathNew.roundDesimal_1(MathNew.pow(Math.cos(Math.PI / 2 - maxSunAltitudeDiurnalRad), a)
				* MathNew.exp(b + c * mMax + d * mMax * mMax) / 25.);

		double uvIndexOverThree = 2
				* Math.acos(-Math.tan(deltaSunRad) * Math.tan(latitudeRad)
						+ Math.sin(uvOverThreeLimitRad) / (Math.cos(deltaSunRad) * Math.cos(latitudeRad)))
				/ (2 * Math.PI) * 24;

		if (Double.isNaN(uvIndexOverThree)) {
			UvIndexOverThree = 0.;
		}

		if (Double.isNaN(uvIndex) || uvIndexOverThree == 0) {
			UvIndexEnd = 0.;
		} else {
			UvIndexEnd = timeSouth + uvIndexOverThree / 2;
		}

		// TimeFormat timeUvi = new TimeFormat(uvIndexEnd);
		// this.UvIndexEndString = (TimeFormat.hourTimeString) + ":" +
		// (TimeFormat.minuteTimeString);

	}

	public static void SolarPower() {

		double latitudeDeg = Location.latitude;
		double T = MyStellarCalendar.T;
		double currentSunElevationDeg = SunPosition.currentElevationDeg;
		double maxSunElevationDeg = SunPosition.maxElevationDeg;

		double latitudeRad = Math.toRadians(latitudeDeg);
		double deltaSunRad = Math.toRadians(latitudeDeg);

		// calculate max elevation of the sun in degrees
		double elevationMax;
		if (latitudeDeg < 23.5 & latitudeDeg > -23.5) {
			elevationMax = 90.;
		} else if (latitudeDeg > 23.5) {
			elevationMax = 90 - latitudeDeg + 23.5;
		} else {
			elevationMax = 90 + latitudeDeg + 23.5;
		}

		double maxSunAltitudeAnnualRad = Math.toRadians(elevationMax); // max Sun elevation in Rad

		double maxSunAltitudeDiurnalRad = Math.toRadians(maxSunElevationDeg);

		maxSunRadiationPowerAnnual = MathNew.roundDesimal_1(1350.0 * Math.sin(maxSunAltitudeAnnualRad)
				* MathNew.pow(0.78, (1 / Math.sin(maxSunAltitudeAnnualRad))));

		// Maximun diurnal solar power
		double sunPower = MathNew.roundDesimal_1(1350.0 * Math.sin(maxSunAltitudeDiurnalRad)
				* MathNew.pow(0.78, (1 / Math.sin(maxSunAltitudeDiurnalRad))));
		if (sunPower < 0) {
			maxSunRadiationPowerDiurnal = 0;
		} else {
			maxSunRadiationPowerDiurnal = sunPower;
		}

		// Current solar power
		double currentSunElevationRad = Math.toRadians(currentSunElevationDeg);
		sunPower = MathNew.roundDesimal_1(
				1350.0 * Math.sin(currentSunElevationRad) * MathNew.pow(0.78, (1 / Math.sin(currentSunElevationRad))));
		if (sunPower < 0) {
			currentSunRadiationPower = 0;
		} else {
			currentSunRadiationPower = sunPower;
		}

	}

}
