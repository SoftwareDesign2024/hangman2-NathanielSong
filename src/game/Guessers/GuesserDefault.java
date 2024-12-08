package game.Guessers;

import game.Executioners.ExecutionerCheater;
import util.ConsoleReader;

/*** 
 * @author Nat Song 
 * 
 * The class GuesserDefault is an extension of the Guesser class.
 * This class is a solution to guessing letters in the game hangman.
 * The way guesses are made is that the user is prompted to enter a guess,
 * then the guess is checked through the executioner.
 * 
 * ***/

public class GuesserDefault extends Guesser {

    public GuesserDefault() {
        super();
    }

    // Make a guess, track it, and update available letters
    public char makeGuess() {
    	char guess = getUserInput().toLowerCase().charAt(0);
        checkGuess(guess);
        return guess;
    }
    
    // Same as above method, but if an executioner is provided then cheat
    public char makeGuess(ExecutionerCheater executioner) {
    	char guess = getUserInput().toLowerCase().charAt(0);
        executioner.cheat(guess);
        checkGuess(guess);
        return guess;
    }

    // Prompts the user to make a guess until a valid guess is submitted (returns the user's guess)
    private String getUserInput() {
    	String guessInput = ConsoleReader.promptString("Make a guess: ");
    	while (!(guessInput.length() == 1 && Character.isAlphabetic(guessInput.charAt(0)))) {
    		System.out.println("Please enter a single letter...");
    		guessInput = ConsoleReader.promptString("Make a guess: ");
    	}
    	return guessInput;
    }
}