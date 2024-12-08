package game.Executioners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import util.DisplayWord;
import util.HangmanDictionary;

/*** 
 * @author Nat Song 
 * 
 * The ExecutionerCheater class extends all Executioner functionality
 * This class implements cheating for the executioner which means that
 * the executioner will swap the current secret word based on what letters
 * have been guessed. This will make it much harder to guess the correct word.
 * 
 * ***/

public class ExecutionerCheater extends Executioner{

	// List of words that are usable
    private List<String> myRemainingWords;

    public ExecutionerCheater(HangmanDictionary dictionary, int wordLength) {
        super(dictionary, wordLength);
        myRemainingWords = dictionary.getWords(wordLength);
    }
    
    // cheat by changing the secret word to something that fits the current display word.
    public void cheat(char guess) {
        // create template of guesses and find one with most matching remaining words
        HashMap<DisplayWord, List<String>> templatedWords = new HashMap<DisplayWord, List<String>>();
        for (String w : myRemainingWords) {
            DisplayWord template = new DisplayWord(myDisplayWord);
            template.update(guess, w);
            if (!templatedWords.containsKey(template)) {
                templatedWords.put(template, new ArrayList<>());
            }
            templatedWords.get(template).add(w);
        }
        int max = 0;
        DisplayWord maxKey = new DisplayWord("");
        for (Entry<DisplayWord, List<String>> entry : templatedWords.entrySet()) {
            //System.out.println(entry.getValue());
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                maxKey = entry.getKey();
            }
        }

        // update secret word to match template of guesses
        myRemainingWords = templatedWords.get(maxKey);
        Collections.shuffle(myRemainingWords);
        mySecretWord = myRemainingWords.get(0);
        myDisplayWord = maxKey;
    }
}