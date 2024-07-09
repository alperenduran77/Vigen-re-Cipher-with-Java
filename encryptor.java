import java.util.Map;

public class encryptor {
    private Map<Character, Map<Character, Character>> map;
    private String key;
    private String keystream = "";
    private String plain_text;
    private String cipher_text = "";

    // Constructor: Initializes the encryptor with a cipher table, key, and text.
    public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
        this.map = _map;
        this.key = _key.toUpperCase(); // Ensure key is uppercase
        this.plain_text = text.toUpperCase(); // Ensure text is uppercase
    }

    // Method to orchestrate the encryption process.
    public void encrypt() {
        // do not edit this method
        generate_keystream();
        generate_cipher_text();
    }

    // Generates a keystream that matches the length of the plain text using the
    // key.
    private void generate_keystream() {
        if (plain_text.length() > key.length()) {
            StringBuilder ks = new StringBuilder();
            while (ks.length() < plain_text.length()) {
                ks.append(key); // Repeatedly append the key until it matches or exceeds the length of the plain
                                // text.
            }
            keystream = ks.substring(0, plain_text.length()); // Trim the excess to match the exact length.
        } else {
            keystream = key.substring(0, plain_text.length()); // Use the portion of the key that matches the plain text
                                                               // length.
        }
    }

    // Constructs the cipher text from the plain text and the keystream using the
    // Vigenère cipher table.
    private void generate_cipher_text() {
        for (int i = 0; i < plain_text.length(); i++) {
            char plainChar = plain_text.charAt(i); // Character from the plain text.
            char keyChar = keystream.charAt(i); // Corresponding character from the keystream.
            Map<Character, Character> row = map.get(plainChar); // Fetch the row from the map based on the plain
                                                                // character.
            char cipherChar = row.get(keyChar); // Fetch the cipher character based on the keystream character.
            cipher_text += cipherChar; // Append the cipher character to the output.
        }
    }

    // Getter method to retrieve the keystream.
    public String get_keystream() {
        return keystream;
    }

    // Getter method to retrieve the cipher text.
    public String get_cipher_text() {
        return cipher_text;
    }
}
