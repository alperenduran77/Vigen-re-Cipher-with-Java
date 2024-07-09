import java.util.Map;
import java.util.Iterator;

public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String cipher_text;
	private String plain_text = "";

	// Constructor initializes the decryptor with a cipher table, key, and the
	// ciphertext.
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		this.map = _map;
		this.key = _key.toUpperCase(); // Ensure key is uppercase
		this.cipher_text = text.toUpperCase(); // Ensure ciphertext is uppercase
	}

	// Method to orchestrate the decryption process.
	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	// Generates a keystream that matches the length of the cipher text using the
	// key.
	private void generate_keystream() {
		if (cipher_text.length() > key.length()) {
			StringBuilder ks = new StringBuilder();
			while (ks.length() < cipher_text.length()) {
				ks.append(key); // Append the key to itself until the length meets/exceeds the ciphertext length
			}
			keystream = ks.substring(0, cipher_text.length()); // Ensure the keystream is the exact length of the
																// ciphertext
		} else {
			keystream = key.substring(0, cipher_text.length()); // If the key is longer or equal, just use the needed
																// portion
		}
	}

	// Generates the plaintext from the ciphertext and keystream using the Vigenère
	// cipher table.
	private void generate_plain_text() {
		StringBuilder plainTextBuilder = new StringBuilder();
		for (int i = 0; i < cipher_text.length(); i++) {
			char cipherChar = cipher_text.charAt(i); // Character from the cipher text.
			char keyChar = keystream.charAt(i); // Corresponding character from the keystream.
			// You must use map.get(x).keySet() with an iterator in this method
			Iterator<Character> keySetIterator = map.get(keyChar).keySet().iterator(); // Iterator to access each
																						// character in the map
			while (keySetIterator.hasNext()) { // Iterate through the possible plaintext characters
				char possiblePlainTextChar = keySetIterator.next(); // Current possible plaintext character
				if (map.get(keyChar).get(possiblePlainTextChar) == cipherChar) { // If the cipher character matches the
																					// map value
					plainTextBuilder.append(possiblePlainTextChar); // Append the plaintext character to the output
					break;
				}
			}
		}
		plain_text = plainTextBuilder.toString();
	}

	// Returns the keystream used for decryption.
	public String get_keystream() {
		return keystream;
	}

	// Returns the decrypted plaintext.
	public String get_plain_text() {
		return plain_text;
	}
}
