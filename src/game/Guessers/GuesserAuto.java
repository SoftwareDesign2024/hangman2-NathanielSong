package game.Guessers;

import game.Executioners.ExecutionerCheater;
import util.ConsoleReader;
import util.DisplayWord;
import util.HangmanDictionary;

/*** 
 * @author Nat Song 
 * 
 * The GuesserAuto is an extention of the Guesser class.
 * This class is a solution to guess letters for the hangman game by making guesses based
 * on the most common letters in the english alphabet.
 * 
 * ***/

public class GuesserAuto extends Guesser {
    
    private String mySecretWord; // word that is being guessed
    private int myNumGuessesLeft; // how many guesses are remaining
    private DisplayWord myDisplayWord; // what is shown to the user
    private String myLetters; // List of letters ordered by frequency (most common letters)
    private int myIndex; // index of the list of letters that we are currently on
    private static final String LETTERS_ORDERED_BY_FREQUENCY = "etaoinshrldcumfpgwybvkxjqz"; // alphabet ordered by most common letters
    
    
    public GuesserAuto(HangmanDictionary dictionary, int wordLength, int numGuesses) {
    	super();
    	mySecretWord = getSecretWord(dictionary, wordLength);
        myLetters = LETTERS_ORDERED_BY_FREQUENCY;
        myIndex = 0;
        myNumGuessesLeft = numGuesses;
        myDisplayWord = new DisplayWord(mySecretWord);
    }

    // Process a guess by updating the necessary internal state.
    public char makeGuess() {
        // do not count repeated guess as a miss
    	Character guess = myLetters.charAt(myIndex++);
        int index = myLettersLeftToGuess.indexOf("" + guess);
        recordAndCheckGuess(index, guess);
        return guess;
    }
    
    // Process a guess by updating the necessary internal state. 
    // If executioner is provided, then cheat.
    public char makeGuess(ExecutionerCheater executioner) {
        // do not count repeated guess as a miss
    	Character guess = myLetters.charAt(myIndex++);
    	executioner.cheat(guess);
        int index = myLettersLeftToGuess.indexOf("" + guess);
        recordAndCheckGuess(index, guess);
        return guess;
    } 
    
    
    // Returns true only if given guess is in the secret word.
    private boolean checkGuessInSecret (char guess) {
        if (mySecretWord.indexOf(guess) >= 0) {
            myDisplayWord.update(guess, mySecretWord);
            return true;
        }
        return false;
    }
    
    // Returns a secret word.
    private String getSecretWord (HangmanDictionary dictionary, int wordLength) {
        String result = ConsoleReader.promptString("Choose a secret word that is " + wordLength + " letters long: ");
        while (! dictionary.contains(result, wordLength)) {
            result = ConsoleReader.promptString("That word is not recognized, please choose another: ");
        }
        return result;
    }
    
    // records the guess then checks if the guess is in the word that is being observed.
    private void recordAndCheckGuess(int index, char guess) {
    	if (index >= 0) {
            recordGuess(index);
            checkGuess(guess);
        }
    }
    
    // checkGuess is overridden becasue we are basing the guess on the index of a sorted alphabet.
    // in other words, no letter is ever used twice
    @Override
    protected void checkGuess(char guess) {
    	if (! checkGuessInSecret(guess)) {
            myNumGuessesLeft -= 1;
        }
    }
}