import java.util.ArrayList;
import java.util.Arrays;

/*
 *Emmanuel Salcedo
 *COMPSCI 76
 *program to test the method with three different arrays using generic
 */

public class Emmanuel_Salcedo_Generics {
	public static void main(String[] args) {
		// Create a list of Integers with 2,4,3
		Integer[] intArray = { new Integer(2), new Integer(4), new Integer(3) };
		// make list into an Arraylist
		ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(intArray));

		// Create a list of Doubles with 3.4, 1.2, -12.3
		Double[] doubleArray = { new Double(3.4), new Double(1.2), new Double(-12.3) };
		// convert to an array list of Doubles
		ArrayList<Double> doubleList = new ArrayList<>(Arrays.asList(doubleArray));

		// Create a list of Strings with "Bob", "Alice", "Ted", and "Carol"
		String[] stringArray = { "Bob", "Alice", "Ted", "Carol" };
		// convert into an arraylist of strings
		ArrayList<String> stringList = new ArrayList<>(Arrays.asList(stringArray));

		// Display original list
		System.out.println("Integer List: " + intList);
		System.out.println("Double List: " + doubleList);
		System.out.println("String List: " + stringList);
		System.out.println("\n=================================\n");

		// Sort the lists using the generic sort
		sort(intList);
		sort(doubleList);
		sort(stringList);

		// Display the sorted lists
		System.out.println("Sorted Integer List: " + intList);
		System.out.println("Sorted Double List: " + doubleList);
		System.out.println("Sorted String List: " + stringList);
	}

	public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
		// creates an instance of the variable type being used and a counter
		E minValue;
		int minIndex;

		// loops through the list
		for (int i = 0; i < list.size() - 1; i++) {
			// Find the minimum in the ArrayList
			minValue = list.get(i);
			minIndex = i;
			// loops though the list from the current minimum to the end of the list to find
			// the next value
			for (int j = i + 1; j < list.size(); j++) {
				// compares the current min to the next value in the list
				if (minValue.compareTo(list.get(j)) > 0) {
					// if the value being compared to is a value less that the current value
					// set that value to be the current minimum if not continue the loop
					minValue = list.get(j);
					minIndex = j;
				}
			}
			// if they are different values then switch the the two
			if (minIndex != i) {
				list.set(minIndex, list.get(i));
				list.set(i, minValue);
			}
		}
	}

}
