package game.Executioners;

import util.DisplayWord;
import util.HangmanDictionary;

/***
 * @author Nat\
 * 
 * This is the abstract Executioner class holding methods fundimental to 
 * specific types of executioners
 * 
 * ***/

public abstract class Executioner {
	protected String mySecretWord; // The current secret word
    protected DisplayWord myDisplayWord; // This is what the player can see of 
    
    public Executioner(HangmanDictionary dictionary, int wordLength) {
    	mySecretWord = makeSecretWord(dictionary, wordLength);
        myDisplayWord = new DisplayWord(mySecretWord);
    }
    
    // Make the secret word
    private String makeSecretWord(HangmanDictionary dictionary, int wordLength) {
        return dictionary.getRandomWord(wordLength).toLowerCase();
    }
    
    // Check if the guess is in the secret word
    public boolean checkGuess(char guess) {
        if (mySecretWord.indexOf(guess) >= 0) {
            myDisplayWord.update(guess, mySecretWord);
            return true;
        }
        return false;
    }
    
    // Check if the game is won
    public boolean isGameWon() {
        return myDisplayWord.equals(mySecretWord);
    }

    // return the current secret word
    public String getSecretWord() {
        return mySecretWord;
    }

    // return the current display word
    public String getDisplayWord() {
        return myDisplayWord.toString();
    }
}
