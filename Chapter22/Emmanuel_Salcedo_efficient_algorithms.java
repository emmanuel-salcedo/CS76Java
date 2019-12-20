/**
 * Emmanuel Salcedo
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Emmanuel_Salcedo_efficient_algorithms {
	public static void main(String[] args) {
		/*
		 * Create 500 points in an array
		 */
		Point[] points = new Point[100];
		/*
		 * Loop Through each point and
		 */
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point((int) (Math.random() * 100000), (int) (Math.random() * 100000));
		}

		double dis = Pair.distance(points[0], points[1]);

		Point a = points[0];
		Point b = points[1];
		for (int i = 2; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				double currentdis = Pair.distance(points[i], points[j]);
				if (currentdis < dis) {
					dis = currentdis;
					a = points[i];
					b = points[j];
				}
			}
		}
		Pair p = new Pair(a, b);

		Pair pair = Pair.getClosestPair(points);
		System.out.println("Pair by Divide and Conqure");
		System.out.println(pair.getp1());
		System.out.println(pair.getp2());
		System.out.println(pair.getDistance());
		System.out.println("brute force");
		System.out.println(p.getp1());
		System.out.println(p.getp2());
		System.out.println(p.getDistance());
	}

}

class Point implements Comparable<Point> {
	private double x;
	private double y;

	public Point() {
		this(0, 0);
	}

	public Point(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if (x > o.x) {
			return 1;
		} else if (x < o.x) {
			return -1;
		} else {
			if (y > o.y) {
				return 1;
			} else if (y < o.y) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	@Override
	public String toString() {
		return "x: " + x + ",\ty: " + y;
	}
}

class CompareY implements Comparator<Point> {
	@Override
	public int compare(Point p1, Point p2) {
		if (p1.getY() < p2.getY()) {
			return -1;
		} else if (p1.getY() == p2.getY()) {
			if (p1.getX() < p2.getX()) {
				return -1;
			} else if (p1.getX() == p2.getX()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
	}
}

//Pair class 
class Pair {

	private Point point1;
	private Point point2;

	public Pair(Point p1, Point p2) {
		this.point1 = p1;
		this.point2 = p2;
	}

	public void setp1(Point p1) {
		this.point1 = p1;
	}

	public void setp2(Point p2) {
		this.point2 = p2;
	}

	public Point getp1() {
		return point1;
	}

	public Point getp2() {
		return point2;
	}

	// get distance helper method
	public double getDistance() {
		return distance(point1, point2);
	}

	// Compute the distance between two points p1 and p2
	public static double distance(Point p1, Point p2) {
		return distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}

	// Compute the distance between points though the coordinates
	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));

	}

	// Return the distance of the closest pair of points in case of the inputs are
	// 2d array
	public static Pair getClosestPair(double[][] points) {
		Point[] points2 = new Point[points.length];
		for (int i = 0; i < points.length; i++) {
			points2[i] = new Point(points[i][0], points[i][1]);
		}
		return getClosestPair(points2);
	}

	// Return the distance of the closest pair of points in case of the inputs are
	// array
	public static Pair getClosestPair(Point[] points) {

		// Step One Sort
		Arrays.sort(points); // pointsOrderedOnX
		Point[] pointsOrderedOnY = points.clone(); // pointsOrderedOnY
		Arrays.sort(pointsOrderedOnY, new CompareY());
		return distance(points, 0, points.length - 1, pointsOrderedOnY);
	}

	public static Pair distance(Point[] pointsX, int min, int max, Point[] pointsY) {

		if (min >= max) {
			return null;
		} else if (min + 1 == max) {
			return new Pair(pointsX[min], pointsX[max]);
		}

		/**
		 * Step 2
		 */

		// divide the sets in half, the right side and left side and recurse though each
		// side
		int halfway = (min + max) / 2;
		Pair leftside = distance(pointsX, min, halfway, pointsY);
		Pair rightside = distance(pointsX, halfway + 1, max, pointsY);

		// initialize the distance and pair
		double distance = 0;
		Pair pair = null;
		// if there are not points then there is no distance, since no points
		// exist
		if (leftside == null && rightside == null) {
			distance = 0;
		} else if (leftside == null) {
			distance = rightside.getDistance();
			pair = rightside;
		} else if (rightside == null) {
			distance = leftside.getDistance();
			pair = leftside;
		} else {
			distance = Math.min(leftside.getDistance(), rightside.getDistance());
			if (leftside.getDistance() <= rightside.getDistance()) {
				pair = leftside;
			} else {
				pair = rightside;
			}

		}
		// divide the points into two different subsets, the left and the right
		ArrayList<Point> stripL = new ArrayList<Point>();
		ArrayList<Point> stripR = new ArrayList<Point>();

		// loops through sorted points by Ycoord and goes through them to see if they
		// are a possible minimum distance
		// if they are smaller than the current minimum distance.
		for (int i = 0; i < pointsY.length; i++) {
			if ((pointsY[i].getX() <= pointsX[halfway].getX())
					&& (pointsY[i].getX() >= pointsX[halfway].getX() - distance)) {
				stripL.add(pointsY[i]);
			} else {
				stripR.add(pointsY[i]);
			}
		}

		/**
		 * STEP 3
		 */
		// loop through the strip to see if the shortest distance exists in the strips
		double d3 = distance;// current shortest distance
		int r = 0; // current index
		// loop through the points StripL
		for (int i = 0; i < stripL.size(); i++) {
			// Skip the points in stripR below the points y and the distance, out of the
			// range
			while (r < stripR.size() && stripL.get(i).getY() > stripR.get(r).getY() + distance) {
				r++;
			}

			int r1 = r;
			while (r1 < stripR.size() && stripR.get(r1).getY() <= stripL.get(i).getY() + distance) {
				// Check to see if the distance between the current point StripL and the
				// current point in StripR and shorter than the current shortest distance

				if (d3 > distance(stripL.get(i), stripR.get(r1))) {
					d3 = distance(stripL.get(i), stripR.get(r1));
					pair.point1 = stripL.get(i);
					pair.point2 = stripR.get(r1);
				}
				r1++;
			}
		}

		// returns the pair with the smallest distance
		return pair;
	}
}
