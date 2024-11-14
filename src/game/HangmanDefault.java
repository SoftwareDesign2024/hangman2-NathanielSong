package game;

import util.ConsoleReader;
import util.HangmanDictionary;

/**
 * This class represents the traditional word-guessing game Hangman
 * that plays interactively with the user.
 *
 * @author Robert C. Duvall
 * @author Shannon Duvall
 * 
 * Edited by Nat Song
 */

public class HangmanDefault {
    private int myNumGuessesLeft;
    private GuesserDefault guesser;
    private ExecutionerDefault executioner;

    //Create Hangman game with the given dictionary, word length, and number of guesses
    public HangmanDefault(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        this.myNumGuessesLeft = numGuesses;
        this.guesser = new GuesserDefault();
        this.executioner = new ExecutionerDefault(dictionary, wordLength);
    }

    // Play the game
    public void play() {
        boolean gameOver = false;

        while (!gameOver) {
            printStatus();
            String guessInput = getUserInput();
            char guess = guesser.makeGuess(guessInput);
            processGuess(guess);
            gameOver = isGameOver();
        }
        printWord();
    }

    // Returns true only if the guesser has used up all their chances to guess.
    private boolean isGameLost() {
        return myNumGuessesLeft == 0;
    }

    // Returns true only if the guesser has guessed all letters in the secret word.
    private boolean isGameWon() {
        return executioner.isGameWon();
    }

    // Print game status
    private void printStatus() {
        System.out.println(executioner.getDisplayWord());
        System.out.println("# misses left = " + myNumGuessesLeft);
        System.out.println("letters not yet guessed = " + guesser.getRemainingLetters());
        System.out.println();
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
    
    // Checks if the guess is in the word then records the guess
    private void processGuess(char guess) {
    	if (!executioner.checkGuess(guess)) {
            myNumGuessesLeft -= 1;
        }
        guesser.recordGuess(guess);
    }
    
    // Returns whether the game has ended or not
    private boolean isGameOver() {
    	boolean gameOver = false;
    	if (isGameLost()) {
            System.out.println("YOU ARE HUNG!!!");
            gameOver = true;
        } else if (isGameWon()) {
            System.out.println("YOU WIN!!!");
            gameOver = true;
        }
    	return gameOver;
    }
    
    // Prints the secret word
    private void printWord() {
    	System.out.println("The secret word was " + executioner.getSecretWord());
    }
}