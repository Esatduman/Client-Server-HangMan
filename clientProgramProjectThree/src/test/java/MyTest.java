import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyTest {

	private gameLogic game;

    @BeforeEach
    public void setUp() {
        game = new gameLogic();
    }

    @Test
    public void testSelectCategory() {
        game.selectCategory("Programming Languages");
        Assertions.assertEquals("Programming Languages", game.getCurrentCategory());
    }

    @Test
    public void testSelectCategoryInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.selectCategory("Invalid Category");
        });
    }

    @Test
    public void testGetLength() {
        game.selectCategory("Chicago Suburbs");
        Assertions.assertEquals(9, game.getLength());
    }

    @Test
    public void testGetRemainingGuesses() {
        game.selectCategory("UIC CS Professors");
        Assertions.assertEquals(6, game.getRemainingGuesses());
    }

    @Test
    public void testCheckUserGuessCorrect() {
        game.selectCategory("Programming Languages");
        game.selectWord("JAVA"); // Set the word to be guessed
        boolean result = game.checkUserGuess('J');
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckUserGuessIncorrect() {
        game.selectCategory("Programming Languages");
        game.selectWord("JAVA"); // Set the word to be guessed
        boolean result = game.checkUserGuess('Z');
        Assertions.assertFalse(result);
    }

    @Test
    public void testHasUserWon() {
        game.selectCategory("Programming Languages");
        game.selectWord("JAVA"); // Set the word to be guessed
        game.checkUserGuess('J');
        game.checkUserGuess('A');
        game.checkUserGuess('V');
        Assertions.assertTrue(game.hasUserWon());
    }

    @Test
    public void testSelectWord() {
        String word = game.selectWord("Programming Languages");
        Assertions.assertNotNull(word);
    }

    @Test
    public void testGetCategoryWords() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.getCategoryWords("Invalid Category");
        });
    }

    @Test
    public void testUpdateGuessedWord() {
        game.selectWord("PYTHON");
        String updatedWord = game.updateGuessedWord("P_____", 'Y', 1);
        Assertions.assertEquals("PY____", updatedWord);
    }

    @Test
    public void testGetGuessedWord() {
        game.selectWord("PYTHON");
        Assertions.assertEquals("______", game.getGuessedWord());
    }

}
