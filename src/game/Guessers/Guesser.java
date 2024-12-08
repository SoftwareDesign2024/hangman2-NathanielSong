package game.Guessers;

import java.util.ArrayList;
import java.util.List;
import game.Executioners.ExecutionerCheater;

/***
 * @author Nat
 * 
 * This is the abstract Guesser class that holds all common functionality
 * between guesser classes. A guesser is a solution which guesses letters for the hangman game.
 * 
 * ***/

public abstract class Guesser {
	protected List<Character> guessedLetters; // List of letters that the user has guessed
    protected StringBuilder myLettersLeftToGuess; // List of letters that have not been guessed
    protected static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz"; // all letters in the alphabet used to reference 'all letters'
    
    public Guesser() {
        guessedLetters = new ArrayList<>();
        myLettersLeftToGuess = new StringBuilder(ALPHABET);
    }
    
    // gives a display of letters left to guess
    // used to show user remaining options
    public String getRemainingLetters() {
        return myLettersLeftToGuess.toString();
    }

    // gives a list of guessed characters
    public List<Character> getGuessedLetters() {
        return guessedLetters;
    }
    
    // Record that a specific letter was guessed
    public void recordGuess (int index) {
        myLettersLeftToGuess.deleteCharAt(index);
    }	
    
    // Record that a specific letter was guessed
    public void recordGuess(char guess) {
        int index = myLettersLeftToGuess.indexOf("" + guess);
        if (index >= 0) {
            myLettersLeftToGuess.deleteCharAt(index);
        }
    }
    
    // if guess hasnt been made, then make the guess.
    // Otherwise let user know that the characrer has already been guessed.
    protected void checkGuess(char guess) {
    	if (guessedLetters.contains(guess)) {
            System.out.println("You already guessed that letter.");
        } else {
            guessedLetters.add(guess);
        }    	
    }

    // Methods to make a guess. Different based on type of guesser
    public abstract char makeGuess(); // Process a guess by updating the necessary internal state.
	public abstract char makeGuess(ExecutionerCheater executioner); // if provided call cheat method in executioner
}
