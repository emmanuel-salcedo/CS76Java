
/**
 * Emmanuel Salcedo
 * COMSC 76
 */

import java.util.Date;
import java.util.Scanner;

public class Emmanuel_Salcedo_AbstractClassesandInterfaces {
	public static void main(String[] args) {
		Scanner Scan = new Scanner(System.in); // Initializes Scan as new scanner
		System.out.print("Enter the First Side of the triangle: "); // Prompts User to Enter the length of the first side
		double side1 = Scan.nextDouble();

		System.out.print("Enter the Second Side of the triangle: "); // Prompts User to Enter the length of the second
																	// side
		double side2 = Scan.nextDouble();

		System.out.print("Enter the Third Side of the triangle: "); // Prompts User to Enter the length of the third side
		double side3 = Scan.nextDouble();

		// checks to see if the 3 sides inputed by the user make up a real triangle
		if (side1 + side2 < side3 || side1 + side3 < side2 || side2 + side3 < side1)
			System.out.print("improper measurements, not a real triangle. ");

		else {

			System.out.print("Enter the color: "); // Prompts User for Color of the triangle as a String
			String color = Scan.next();

			System.out.print("is the triangle filled? Enter 'true' or 'false': "); // Prompts user to state if the
																					// triangle is filled
			boolean isFilled = Scan.nextBoolean();

			triangle newTriangle = new triangle(side1, side2, side3, color, isFilled); // creates new triangle with the
																						// info provided
			System.out.print(newTriangle.toString()); // prints triangle info
		}
	}
}

class GeometricObject {
	private String color = " ";
	private boolean isFilled;
	private java.util.Date dateCreated;
	// public double p;

	public GeometricObject() {
		dateCreated = new java.util.Date();
	}

	public GeometricObject(String color, boolean filled) {
		dateCreated = new java.util.Date();
		this.color = color;
		this.isFilled = filled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean filled) {
		this.isFilled = filled;
	}

	public java.util.Date getDateCreated() {
		return dateCreated;
	}

	public String toString() {
		return "created on " + dateCreated + "\ncolor: " + color + "\nfilled: " + isFilled;
	}
}

class triangle extends GeometricObject {
	private double s1; // side one
	private double s2; // side two
	private double s3; // side three
	private double p; // the perimeter
	private double b; // half of the perimeter

	public triangle() {
	}

	public triangle(double s1, double s2, double s3, String color, boolean filled) {
		super(color, filled);
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}

	// public double gethalfPerimeter () {
	// b = (s1 + s2 + s3) / 2;
	// return b;
	// }

	public double getPerimeter() {
		double p;
		p = (s1 + s2 + s3);
		return p;

	}

	public double getArea() {
		double s;
		b = (s1 + s2 + s3) / 2;
		s = Math.pow((b * (b - s1) * (b - s2) * (b - s3)), 0.5);
		return s;
	}

	// @Override
	public boolean isFilled() {
		return super.isFilled();
	}

	public String getColor() {
		return super.getColor();
	}

	public String toString() {
		return super.toString() + "\nperimeter is " + getPerimeter() + "\narea is: " + getArea();
	}
}
