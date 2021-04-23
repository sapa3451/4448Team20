package edu.colorado.team20.GameProbabilities.Interfaces.Commands;

import edu.colorado.team20.GameProbabilities.GoodLuckCalc;
import edu.colorado.team20.GameProbabilities.Interfaces.Command;

/**
 * Description: class that implements a command to call good luck execution
 */
public class GoodLuckCommand implements Command {
    GoodLuckCalc goodLuckCalc;

    public GoodLuckCommand(GoodLuckCalc calc) {
        this.goodLuckCalc = calc;
    }

    /**
     * Description: call the method that executes the good luck calculation,
     * then return bool determining if good luck happens
     * Params: none
     * Returns: boolean value
     */
    public boolean execute() {
        // good luck happens
        // good luck doesn't happen
        return goodLuckCalc.calcGoodLuck();
    }
}
