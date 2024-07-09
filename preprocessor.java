public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	// Constructor to initialize the initial string
	public preprocessor(String str) {
		this.initial_string = str;
		this.preprocessed_string = ""; // Initialize to empty to handle possible no-letter cases
	}

	// Preprocesses the string by capitalizing it and removing non-alphabetic
	// characters
	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}

	// Capitalizes the initial string
	private void capitalize() {

		this.preprocessed_string = this.initial_string.toUpperCase();
	}

	// Removes non-alphabetic characters from the preprocessed string
	private void clean() {
		// Building a new string with only supported alphabetic characters
		StringBuilder sb = new StringBuilder();
		for (char c : this.preprocessed_string.toCharArray()) {
			// Check if the character is a letter and is within 'A' to 'Z'
			if (Character.isLetter(c) && c >= 'A' && c <= 'Z') {
				sb.append(c); // Append the character to the new string
			}
		}
		this.preprocessed_string = sb.toString();
	}

	// Getter method to retrieve the preprocessed string
	public String get_preprocessed_string() {
		return this.preprocessed_string;
	}
}
