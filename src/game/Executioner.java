package game;

import util.DisplayWord;
import util.HangmanDictionary;

/*** 
 * @author Nat Song 
 * ***/

public class Executioner {
    private String secretWord;
    private DisplayWord displayWord;

    public Executioner(HangmanDictionary dictionary, int wordLength) {
        this.secretWord = makeSecretWord(dictionary, wordLength);
        this.displayWord = new DisplayWord(secretWord);
    }

    // Make the secret word
    private String makeSecretWord(HangmanDictionary dictionary, int wordLength) {
        return dictionary.getRandomWord(wordLength).toLowerCase();
    }

    // Check if the guess is in the secret word
    public boolean checkGuess(char guess) {
        if (secretWord.indexOf(guess) >= 0) {
            displayWord.update(guess, secretWord);
            return true;
        }
        return false;
    }

    // Check if the game is won
    public boolean isGameWon() {
        return displayWord.equals(secretWord);
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String getDisplayWord() {
        return displayWord.toString();
    }
}