package hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/words")
public class WordController {

	@GetMapping("/{word}")
	public Result check(@PathVariable("word") String word) {
		return new Result(word, palindrome(word), anagramOfPalindrome(word));
	}

	private boolean palindrome(String word) {
		for (int i = 0; i < word.length() / 2; i++) {
			if (word.charAt(i) != word.charAt(word.length() - i - 1)) return false;
		}
		return true;
	}

	private boolean anagramOfPalindrome(String word) {
		int[] counts = new int[255];
		for (int i = 0; i < word.length(); i++) {
			counts[word.charAt(i) - 'a']++;
		}

		int oddCount = 0;
		for (int count : counts) {
			if (count % 2 == 1) oddCount++;
			if (oddCount > 1) return false;
		}
		return true;
	}


	class Result {
		private String word;
		private boolean palindrome;
		private boolean anagramOfPalindrome;

		Result() {
		}

		Result(String word, boolean palindrome, boolean anagramOfPalindrome) {
			this.word = word;
			this.palindrome = palindrome;
			this.anagramOfPalindrome = anagramOfPalindrome;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public boolean isPalindrome() {
			return palindrome;
		}

		public void setPalindrome(boolean palindrome) {
			this.palindrome = palindrome;
		}

		public boolean isAnagramOfPalindrome() {
			return anagramOfPalindrome;
		}

		public void setAnagramOfPalindrome(boolean anagramOfPalindrome) {
			this.anagramOfPalindrome = anagramOfPalindrome;
		}
	}
}
