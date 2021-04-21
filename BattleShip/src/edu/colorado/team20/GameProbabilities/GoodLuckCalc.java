package edu.colorado.team20.GameProbabilities;

import java.util.Random;

/**
 * Description: class that is used to calculate good luck
 */
public class GoodLuckCalc {
    /**
     * Description: function to calculate if good luck is suppose to happen to the player, return bool
     * value to represent if good luck is true or not
     * Params: none
     * Returns: boolean value
     */
    public boolean calcGoodLuck() {
        // create instance of Random class
        Random rand = new Random();

        // get player bad luck --> 12% chance a getting good luck
        int num = rand.nextInt(99);
        return num < 12;
    }

}
