package game.Executioners;

import util.HangmanDictionary;

/*** 
 * @author Nat Song 
 * 
 * This is the default executioner which follows the functionality
 * of the parent class Executioner.
 * 
 * ***/

public class ExecutionerDefault extends Executioner{

    public ExecutionerDefault(HangmanDictionary dictionary, int wordLength) {
        super(dictionary, wordLength);
    }
}