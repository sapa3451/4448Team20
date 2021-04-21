package edu.colorado.team20.GameProbabilities;

import java.util.Random;

/**
 * Description:
 */
public class GoodLuckCalc {
    /**
     * Description:
     * Params:
     * Returns:
     */
    public boolean calcGoodLuck() {
        // create instance of Random class
        Random rand = new Random();

        // get player bad luck --> 12% chance a getting good luck
        int num = rand.nextInt(99);
        if (num < 12) { return true; }
        else { return false; }
    }

}
