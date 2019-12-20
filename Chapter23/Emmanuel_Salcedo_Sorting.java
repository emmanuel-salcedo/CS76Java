/*Emmanuel Salcedo
 * Sorting
 * 
 * */

import java.util.ArrayList;

public class Emmanuel_Salcedo_Sorting {

	public static void main(String[] args) {
		// displays the header with all the sort names
		System.out.print(
				"|Array size    |Selection Sort|Bubble Sort   |Merge Sort    |Quick Sort    |Heap Sort     |Radix Sort    |");
		// makes a loop from 50000 to 300000 with increments of 50000
		for (int i = 50000; i <= 300000; i += 50000) {
			printValue(i);
		}
	}

	public static void printValue(int arraySize) {
		// sets the text spacing to keep the columns aligned
		int strSpace = 14;

		// make a list with the current size and fills it with random values from 0 to
		// 1000000
		int[] list = new int[arraySize];
		for (int i = 0; i < list.length; i++) {
			list[i] = (int) (Math.random() * 1000000);
		}

		// create the dividers before running the sort
		System.out.print("\n|");
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < strSpace; j++) {
				System.out.print("-");
			}
			System.out.print("|");
		}
		System.out.printf("\n|%" + strSpace + "d|", arraySize);

		// run the list through selection sort
		// create a new array as a copy of the randomly generated array
		int[] arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// record the start time
		long startTime = System.nanoTime();
		// run selection sort
		selectionSort(arrayofnums);
		// record end time and display how long it took to run
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

		// reset array of nums to a new array and clone it
		arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// record start time
		startTime = System.nanoTime();
		// run bubble sort on the array
		bubbleSort(arrayofnums);
		// record end time and display how long it took to run
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

		// reset array
		arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// get start time
		startTime = System.nanoTime();
		// run mergeSort
		mergeSort(arrayofnums);
		// record end time and display how long it took to run
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

		// reset array
		arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// get start time
		startTime = System.nanoTime();
		// run quicksort
		quickSort(arrayofnums);
		// record end time and display how long it took to run
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

		// reset array
		arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// get start time
		startTime = System.nanoTime();
		// run heapsort
		heapSort(arrayofnums);
		// record end time and display how long it took to run
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

		// reset array
		arrayofnums = new int[arraySize];
		System.arraycopy(list, 0, arrayofnums, 0, list.length);
		// get start time
		startTime = System.nanoTime();
		// run radixsort
		radixSort(arrayofnums, 1000000);
		// record end time and display how long it took to run
		endTime = System.nanoTime();
		executionTime = endTime - startTime;
		System.out.printf("%" + strSpace + "d|", executionTime);

	}

	public static void selectionSort(int[] list) {
		// loop through list
		for (int i = 0; i < list.length - 1; i++) {
			// set the current value to be the min value and min index
			int currentMin = list[i];
			int currentMinIndex = i;
			// loop through the rest of the array
			for (int j = i + 1; j < list.length; j++) {
				// if the the current value of the array is less than the currentMin value
				if (currentMin > list[j]) {
					// the currentmin takes in the value of the new minimum value and min index
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			// check to see if index where the minimum number was found is the same
			if (currentMinIndex != i) {
				// if not then switch the values to make the next min number be the next number
				// in the list
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}

	public static void bubbleSort(int[] list) {
		boolean needNextPass = true;
		// loop through the list as long as we need another pass
		for (int k = 1; k < list.length && needNextPass; k++) {
			// Array may be sorted and next pass not needed
			needNextPass = false;
			for (int i = 0; i < list.length - k; i++) {
				if (list[i] > list[i + 1]) {
					// Swap list[i] with list[i + 1]
					int temp = list[i];
					list[i] = list[i + 1];
					list[i + 1] = temp;
					needNextPass = true; // Next pass still needed
				}
			}
		}
	}

	public static void mergeSort(int[] list) {
		if (list.length > 1) {
			// Merge sort the first half
			int[] firstHalf = new int[list.length / 2];
			System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
			mergeSort(firstHalf);

			// Merge sort the second half
			int secondHalfLength = list.length - list.length / 2;
			int[] secondHalf = new int[secondHalfLength];
			System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
			mergeSort(secondHalf);

			// Merge firstHalf with secondHalf into list
			merge(firstHalf, secondHalf, list);
		}
	}

	public static void merge(int[] list1, int[] list2, int[] temp) {
		int current1 = 0; // Current index in list1
		int current2 = 0; // Current index in list2
		int current3 = 0; // Current index in temp

		while (current1 < list1.length && current2 < list2.length) {
			if (list1[current1] < list2[current2])
				temp[current3++] = list1[current1++];
			else
				temp[current3++] = list2[current2++];
		}

		while (current1 < list1.length)
			temp[current3++] = list1[current1++];

		while (current2 < list2.length)
			temp[current3++] = list2[current2++];
	}

	// quicksort method
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	// quicksort helper method
	private static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
	private static int partition(int[] list, int first, int last) {
		int pivot = list[first]; // Choose the first element as the pivot
		int low = first + 1; // Index for forward search
		int high = last; // Index for backward search

		while (high > low) {
			// Search forward from left
			while (low <= high && list[low] <= pivot)
				low++;

			// Search backward from right
			while (low <= high && list[high] > pivot)
				high--;

			// Swap two elements in the list
			if (high > low) {
				int temp = list[high];
				list[high] = list[low];
				list[low] = temp;
			}
		}

		while (high > first && list[high] >= pivot)
			high--;

		// Swap pivot with list[high]
		if (pivot > list[high]) {
			list[first] = list[high];
			list[high] = pivot;
			return high;
		} else {
			return first;
		}
	}

	public static void heapSort(int[] list) {
		// Create a Heap of integers
		Heap<Integer> heap = new Heap<Integer>();

		// Add elements to the heap
		for (int i = 0; i < list.length; i++)
			heap.add(list[i]);

		// Remove elements from the heap
		for (int i = list.length - 1; i >= 0; i--)
			list[i] = heap.remove();
	}

	static class Heap<E extends Comparable<E>> {
		private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

		/** Create a default heap */
		public Heap() {
		}

		/** Create a heap from an array of objects */
		public Heap(E[] objects) {
			for (int i = 0; i < objects.length; i++)
				add(objects[i]);
		}

		/** Add a new object into the heap */
		public void add(E newObject) {
			list.add(newObject); // Append to the heap
			int currentIndex = list.size() - 1; // The index of the last node

			while (currentIndex > 0) {
				int parentIndex = (currentIndex - 1) / 2;
				// Swap if the current object is greater than its parent
				if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
					E temp = list.get(currentIndex);
					list.set(currentIndex, list.get(parentIndex));
					list.set(parentIndex, temp);
				} else
					break; // the tree is a heap now

				currentIndex = parentIndex;
			}
		}

		/** Remove the root from the heap */
		public E remove() {
			if (list.size() == 0)
				return null;

			E removedObject = list.get(0);
			list.set(0, list.get(list.size() - 1));
			list.remove(list.size() - 1);

			int currentIndex = 0;
			while (currentIndex < list.size()) {
				int leftChildIndex = 2 * currentIndex + 1;
				int rightChildIndex = 2 * currentIndex + 2;

				// Find the maximum between two children
				if (leftChildIndex >= list.size())
					break; // The tree is a heap
				int maxIndex = leftChildIndex;
				if (rightChildIndex < list.size()) {
					if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
						maxIndex = rightChildIndex;
					}
				}

				// Swap if the current node is less than the maximum
				if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
					E temp = list.get(maxIndex);
					list.set(maxIndex, list.get(currentIndex));
					list.set(currentIndex, temp);
					currentIndex = maxIndex;
				} else
					break; // The tree is a heap
			}

			return removedObject;
		}

		/** Get the number of nodes in the tree */
		public int getSize() {
			return list.size();
		}
	}

	static void radixSort(int[] list, int maxOrder) {
		for (int order = 1; order < maxOrder; order *= 10) {
			ArrayList<Integer>[] bucket = new ArrayList[10];

			for (int i = 0; i < bucket.length; i++) {
				bucket[i] = new java.util.ArrayList<>();
			}

			for (int i = 0; i < list.length; i++) {
				bucket[(list[i] / order) % 10].add(list[i]);
			}

			int k = 0;
			for (int i = 0; i < bucket.length; i++) {
				if (bucket[i] != null) {
					for (int j = 0; j < bucket[i].size(); j++)
						list[k++] = bucket[i].get(j);
				}
			}
		}
	}

}