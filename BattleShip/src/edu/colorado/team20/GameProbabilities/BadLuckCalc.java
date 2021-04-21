package edu.colorado.team20.GameProbabilities;

import java.util.Random;

/**
 * Description:
 */
public class BadLuckCalc {
    /**
     * Description:
     * Params:
     * Returns:
     */
    public boolean calcBadLuck() {
        // create instance of Random class
        Random rand = new Random();

        // get player bad luck --> 10% chance a getting bad luck
        int num = rand.nextInt(99);
        if (num < 10) { return true; }
        else { return false; }
    }

}
