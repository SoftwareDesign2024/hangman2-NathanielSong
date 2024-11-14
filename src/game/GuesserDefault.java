package game;

import java.util.ArrayList;
import java.util.List;

/*** 
 * @author Nat Song 
 * ***/

public class GuesserDefault {
    private List<Character> guessedLetters;
    private StringBuilder myLettersLeftToGuess;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public GuesserDefault() {
        guessedLetters = new ArrayList<>();
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
    }

    // Make a guess, track it, and update available letters
    public char makeGuess(String input) {
        char guess = input.toLowerCase().charAt(0);
        if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed that letter.");
        } else {
            guessedLetters.add(guess);
        }
        return guess;
    }

    // Record that a specific letter was guessed
    public void recordGuess(char guess) {
        int index = myLettersLeftToGuess.indexOf("" + guess);
        if (index >= 0) {
            myLettersLeftToGuess.deleteCharAt(index);
        }
    }

    public String getRemainingLetters() {
        return myLettersLeftToGuess.toString();
    }

    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }
}