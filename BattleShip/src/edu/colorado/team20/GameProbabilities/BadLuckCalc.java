package edu.colorado.team20.GameProbabilities;

import java.util.Random;

/**
 * Description: class that is used to calculate bad luck
 */
public class BadLuckCalc {
    /**
     * Description: function to calculate if bad luck is suppose to happen to the player, return bool
     * value to represent if bad luck is true or not
     * Params: none
     * Returns: boolean value
     */
    public boolean calcBadLuck() {
        // create instance of Random class
        Random rand = new Random();

        // get player bad luck --> 10% chance a getting bad luck
        int num = rand.nextInt(99);
        return num < 10;
    }

}
