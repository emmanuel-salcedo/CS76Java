/*
 * Emmanuel Salcedo
 * CS 76
 * 
 * (Count the occurrences of words in a text file) Rewrite Listing 21.9 to read the text 
 * from a text file. The text file is passed as a command-line argument. Words are delimited
 *  by white space characters, punctuation marks (, ; . : ?), quotation marks (' "), and parentheses. 
 *  Count the words in a case-sensitive fashion (e.g., consider Good and good to be the same word). 
 *  The words must start with a letter. Display the output of words in alphabetical order, 
 *  with each word preceded by the number of times it occurs. 
 */

import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Emmanuel_Salcedo_Sets_and_Maps {

	public static void main(String[] args) throws Exception {
		// from console ask for text file
		if (args.length != 1) {
			System.out.println("Usage: Emmanuel_Salcedo_Sets_and_Maps textFileName");
			System.exit(1);
		}
		// check for the text file and see if it exists
		File user_file = new File(args[0]);

		// if it does not exist then exit the program and let the user know
		if (!user_file.exists()) {
			System.out.println("Source file " + args[0] + " does not exist");
			System.exit(1);
		}

		// create a tree map map with strings and integers
		Map<String, Integer> map = new TreeMap<>();
		// try and read from the user input file

		try (Scanner scanner = new Scanner(user_file)) {

			// go through all the words in file
			while (scanner.hasNext()) {
				// split txt into a scanner by delimiters
				String[] words = scanner.nextLine().split("[ @~{}\\[\\]$#^&*\n\t\r.,:?')(]");
				// count the words
				CountWords(map, words);
			}

			// create a set from the TreeMap
			Set<Map.Entry<String, Integer>> wordSet = map.entrySet();
			// print out each entry in the set

			// for each Map in the set , print out all the entrys
			for (Map.Entry<String, Integer> entry : wordSet)
				System.out.println(entry.getValue() + "\t" + entry.getKey());
		}
	}

	private static void CountWords(Map<String, Integer> map, String[] words) {
		// loops through the array of words
		for (int i = 0; i < words.length; i++) {
			// make current word into a lower case word
			String key = words[i].toLowerCase();

			// check to see if the current key is a word
			if (key.length() > 0 && Character.isLetter(key.charAt(0))) {

				// if its a new word then add it to the map and initialize the count
				if (!map.containsKey(key)) {
					map.put(key, 1);

					// get the current count and increase the count by 1
				} else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
	}
}
