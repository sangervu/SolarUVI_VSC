package common;

public class Location {

	public static double longitude = 24.90;// asteina
	public static double latitude = 60.20;// asteina

	public static double getCurrentLatitude() {

		// oletussijainti
		return longitude;
	}

	public static double getCurrentLongitude() {

		// oletussijainti
		return longitude;
	}

	public static void setCurrentLatitude(double lat) {

		latitude = lat; // asteina

		// locaation voi jatkossa hakea esim. puhelimen API:sta tai tietokeen
		// lokaatiotiedosta

	}
	public static void setCurrentLongitude(double lon) {

		longitude = lon; // asteina

		// locaation voi jatkossa hakea esim. puhelimen API:sta tai tietokeen
		// lokaatiotiedosta

	}
}
