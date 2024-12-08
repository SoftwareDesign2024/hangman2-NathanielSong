package game.logic;

import game.Executioners.Executioner;
import game.Executioners.ExecutionerCheater;
import game.Executioners.ExecutionerDefault;
import game.Guessers.Guesser;
import game.Guessers.GuesserAuto;
import game.Guessers.GuesserDefault;
import util.HangmanDictionary;

public class HangmanCheater {
    private int myNumGuessesLeft;
    private Guesser guesser;
    private ExecutionerCheater executioner;

    //Create Hangman game with the given dictionary, word length, and number of guesses
    public HangmanCheater(HangmanDictionary dictionary, int wordLength, int numGuesses) {
        myNumGuessesLeft = numGuesses;
        guesser = new GuesserDefault();
        executioner = new ExecutionerCheater(dictionary, wordLength);
    }

    // Play the game
    public void play() {
        boolean gameOver = false;
        while (!gameOver) {
        	printWord();
            printStatus();
            char guess = guesser.makeGuess(executioner);
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