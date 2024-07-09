import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character, Map<Character, Character>>();

	// Constructor to fill the English alphabet and the Vigenère cipher table.
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}

	// Fills the 'english_alphabet' set with the English alphabet.
	private void fill_english_alphabet() {
		// do not edit this method
		for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
			english_alphabet.add(c);
		}
	}

	// Constructs the Vigenère cipher table and stores it in 'map'.
	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the
		// "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.
		Iterator<Character> rowStartIterator = english_alphabet.iterator(); // Iterator to access each character in the
																			// alphabet.
		int size = english_alphabet.size(); // Total number of characters in the alphabet

		// Loop through each character in the alphabet to set up the rows of the cipher
		// table.
		for (int i = 0; i < size; i++) {
			if (!rowStartIterator.hasNext()) {
				rowStartIterator = english_alphabet.iterator(); // Reset iterator if end is reached.
			}
			char rowStartChar = rowStartIterator.next(); // Current character to start the row with.
			Map<Character, Character> rowMap = new HashMap<>(); // Map for this row's cipher

			// Create an iterator for columns starting anew for each row.
			Iterator<Character> columnIterator = english_alphabet.iterator();

			// Advance the columnIterator to the starting position of this row
			for (int shift = 0; shift < i; shift++) {
				if (!columnIterator.hasNext()) {
					columnIterator = english_alphabet.iterator();
				}
				columnIterator.next();
			}

			// Use columnIterator to fill in the rowMap
			for (int j = 0; j < size; j++) {
				if (!columnIterator.hasNext()) {
					columnIterator = english_alphabet.iterator();
				}
				char key = (char) (65 + j); // Convert int position back to char starting from 'A'
				char value = columnIterator.next();
				rowMap.put(key, value); // Map the plain text character to its cipher equivalent.
			}

			// Add the completed rowMap to the main map under the row's starting character.
			map.put(rowStartChar, rowMap);
		}
	}

	// Prints the complete Vigenère cipher table.
	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for (Character k : map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");

	}

	// Returns the map representing the Vigenère cipher table.
	public Map get_map() {
		return this.map;
	}
}