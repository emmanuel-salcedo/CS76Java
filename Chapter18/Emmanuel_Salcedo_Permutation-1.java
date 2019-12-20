/*
 *Emmanuel Salcedo
 *COMPSCI 76
 *Displays all the permutations of a string 
 * 
 */

import java.util.Scanner;

public class Emmanuel_Salcedo_Permutation {

	public static void main(String[] args) {
		// initialize scanner to take in the string that will be permuted
		Scanner scan = new Scanner(System.in);

		// Prompt the user to enter a string
		System.out.print("Enter a string: ");
		// save the whole line that is typed in to the string value
		String value = scan.nextLine();

		// run the recursive method to display all the permutations of the string value
		displayPermutation(value);
	}

	// main displayPermutatuion method with single string input
	public static void displayPermutation(String s) {
		// calls on helper method and inputs a empty string, and the value string
		displayPermutation("", s);
	}

	// helper recursion method, takes in two string values
	private static void displayPermutation(String permuted, String toPermute) {
		// set the length of the second string as out pivot point
		int n = toPermute.length();
		// if out our second string is empty then return the first string
		if (n == 0) { // base case
			System.out.println(permuted);
		} else {
			// loop through each character in the second string
			for (int i = 0; i < n; i++)
				// separates into two parts, first part is the string that has already
				// been permuted, add the new character and then add the part of the string that
				// is needed to be permuted until all the characters in string s2 have been
				// processed.
				displayPermutation(permuted + toPermute.charAt(i),
						toPermute.substring(0, i) + toPermute.substring(i + 1, n));
		}
	}

}
