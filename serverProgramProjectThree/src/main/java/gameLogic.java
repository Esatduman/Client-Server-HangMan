import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.Serializable;


public class gameLogic implements Serializable{
	
	// Categories
	private List<String> wordCategories = Arrays.asList("Programming Languages", "Chicago Suburbs", "UIC CS Professors");

	
	// Category Words
	private List<String> prgmLangWords;
	private List<String> chicagoSuburbsWords;
	private List<String> uicProfWords;
	
	private String currentCategory;
	boolean winCondition;
	String currentWord;
	String guessedWord; 
	private int remainingGuesses;
	
	public gameLogic() {
		setWords();
		remainingGuesses = 6;
	}
	
	public void setWords() {
		prgmLangWords = Arrays.asList("JAVA", "PYTHON", "CPLUSPLUS", "FSHARP", "JAVASCRIPT",
									"HTMLCSS", "CSHARP", "SQL", "GOLANG", "ASSEMBLY");
		chicagoSuburbsWords = Arrays.asList("EVANSTON", "AURORA", "LAKEZURICH", "CICERO", "JOLIET", 
									  "NAPERVILLE", "SCHAUMBURG", "ROCKFORD", "WAUKEGAN", "BERWYN");
		uicProfWords = Arrays.asList("HALLENBECK", "BELLO", "MARATOS", "HAYES", "THEYS",
									"KIDANE", "REED", "KOEHLER", "DIAZ", "SOLWORTH");
		
		selectCategory(wordCategories.get(0));

	}
	
	public void selectCategory(String category) {
		currentCategory = category;
		currentWord = selectWord(category);
		guessedWord = new String(new char[currentWord.length()]).replace('\0', '_');
		remainingGuesses = 6;
		
	}
	
	public String getCurrentCategory() {
		return currentCategory;
	}
	
	public int getLength() {
		return currentWord.length();
	}
	
	public int getRemainingGuesses() {
		return remainingGuesses;
	}
	
	public boolean checkUserGuess(char guess) {
		
		boolean correctGuess = false;

	    for (int i = 0; i < currentWord.length(); i++) {
	        if (currentWord.charAt(i) == guess) {
	            guessedWord = updateGuessedWord(guessedWord, guess, i);
	            correctGuess = true;
	        }
	    }

	    if (!correctGuess) {
	        remainingGuesses--;
	    }
	
	    return correctGuess;
		
	}
	
	public boolean hasUserWon() {
		winCondition = false;
		
		if (guessedWord.equals(currentWord)) {
			winCondition = true;
		}
		
		return winCondition;
	}
	
	public String selectWord(String category) {
		List<String> words = getCategoryWords(category);
		Random rand = new Random();
		int index = rand.nextInt(words.size());
		
		return words.get(index);
	}
	
	public List<String> getCategoryWords(String category){
		switch(category.toLowerCase()) {
		case "programming languages":
			return prgmLangWords;
		case "chicago suburbs":
			return chicagoSuburbsWords;
		case "uic cs professors":
			return uicProfWords;
		default:
			throw new IllegalArgumentException("Invalid Category: " + category);
		}
	}
	
	public String updateGuessedWord(String guessWord, char guess, int index) {
		if (guessedWord == null) {
			System.out.println("EMPTY WORD");
		}
		
		
		char[] guessedWordToChar = guessedWord.toCharArray();
	    
		if (index >= 0 && index <= guessedWordToChar.length) {
			guessedWordToChar[index] = guess;
			guessedWord = new String(guessedWordToChar);
			
			if (guessedWord.equals(currentWord)) {
				System.out.println("CORRECT");
			}
			
		} else {
			System.out.println("Invalid index");
		}
	    
		
	    return guessedWord;
	}
	
	public String getGuessedWord(){
		return guessedWord;
	}
	
	public String getCurrentWord() {
		return currentWord;
	}
	
	public void resetGame() {
		setWords();
		guessedWord = new String(new char[currentWord.length()]).replace('\0', '_');
		remainingGuesses = 6;
	}
	
}
