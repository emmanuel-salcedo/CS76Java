import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Emmanuel_Salcedo_Lists_Stacks__Queues {
	public static void main(String[] args) {
		//create 100 points in an array
		Point[] points = new Point[100];
		for (int i = 0; i < points.length; i++) {
			// create random X and Y values from 0-100 for each point
			points[i] = new Point((double) (Math.random() * 100), (double) (Math.random() * 100));
		}

		// Sort the Array of points
		Arrays.sort(points);
		// convert the array of points into a List
		List<Point> list1 = Arrays.asList(points);
		// display the list of points in increasing order by x values
		System.out.println("\nPoints in increasing order of their x-coordinates:");
		System.out.println(list1);

		// Display the points in increasing order of their y-coordinates
		Arrays.sort(points, new CompareY());
		List<Point> list2 = Arrays.asList(points);
		System.out.println("\nPoints in increasing order of their y-coordinates:");
		System.out.println(list2);
	}
}

class Point implements Comparable<Point> {
	// x and y variables are initialized
	private double x;
	private double y;

	// empty constructor
	Point() {
	}

	// Constructer for a Point with x, y
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// calls for the X value of the current point
	public double getX() {
		return x;
	}

	// asks for an x value and sets it as the the X value of the current point
	public void setX(double x) {
		this.x = x;
	}

	// calls for the Y value of the current point
	public double getY() {
		return y;
	}

//asks for an Y value and sets it as the the Y value of the current point
	public void setY(double y) {
		this.y = y;
	}

	public int compareTo(Point point) {
		// if the x values are the same sort by the Y value
		if (x == point.getX()) {
			if (y > point.getY())
				return 1;
			else if (y < point.getY())
				return -1;
			else
				return 0;
			// sort by x values
		} else if (x > point.getX())
			return 1;
		else
			return -1;
	}

	@Override
	// display the coordinates in "(x,y)" form
	public String toString() {
		return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ")";
	}

}

//class CompareY implements the Comparator interface the compares the Points by Y value instead of X
class CompareY implements Comparator<Point> {
	// compare method that takes 2 points to compare
	public int compare(Point p1, Point p2) {
		// get the x and y values for both points
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = p2.getX();
		double y2 = p2.getY();
		// if the y values are the same compare the x values
		if (y1 == y2) {

			if (x1 < x2)
				return -1;
			else if (x1 == x2)
				return 0;
			else
				return 1;

			// compare the y values
		} else if (y1 < y2)
			return -1;
		else
			return 1;

	}
}
