package exercises;

import java.util.List;

import examples.FileHelper;

// Write the tests and the code to check for palindromes.

public class Palindrome {

	private List<String> words;

	public Palindrome() {
		words = loadWords();
	}

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}

	public boolean wordExist(String word) {
		return words.contains(word);
	}

	public boolean isWordPalindrom(String word) {
		return word.equals(new StringBuilder(word).reverse().toString());
	}
}
