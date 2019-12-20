/*
 *Emmanuel Salcedo
 *COMPSCI 76
 *Recursion, takes a string and displays it backwards with recursion
 * 
 */
import java.util.Scanner;

public class Emmanuel_Salcedo_Recursion {

	public static void main(String[] args) {
		// Create a scanner
				Scanner scan = new Scanner(System.in);

				// Prompt the user to enter a string
				System.out.print("Enter a string: ");
				String value = scan.nextLine();
				System.out.println(value);
				// Print the string in reverse
				reverseDisplay(value);
			}

			//Recursively displays a word backwards
			public static void reverseDisplay(String value) {
				reverseDisplay(value, value.length()-1);
			}

			//method to help recurse through the word
			private static void reverseDisplay(String value, int high) {
				if (high >= 0) {
					System.out.print(value.charAt(high));
					reverseDisplay(value, high - 1);
				}
			}

}