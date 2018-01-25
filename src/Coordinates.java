import java.math.*;

public class Coordinates {

	//NOTICE:
	//The structure of this class is not efficient but it's not imoprtant at this point 
	//This will be entirely changed once I have finished creating all the planets

	public double[] getVenusCoordinates(double radius){
		double[] coordinates = new double[2];
		coordinates[0] = (double) radius * Math.sin(Math.toRadians(90));
		coordinates[1] = (double) radius * Math.cos(Math.toRadians(90));

		return coordinates;
	}

	public double[] getMercuryCoordinates(double radius){
		double[] coordinates = new double[2];
		coordinates[0] = (double) radius * Math.sin(Math.toRadians(120));
		coordinates[1] = (double) radius * Math.cos(Math.toRadians(120));

		return coordinates;
	}

	public double[] getMarsCoordinates(double radius){
		double[] coordinates = new double[2];
		coordinates[0] = (double) radius * Math.sin(Math.toRadians(180));
		coordinates[1] = (double) radius * Math.cos(Math.toRadians(180));

		return coordinates;
	}

}