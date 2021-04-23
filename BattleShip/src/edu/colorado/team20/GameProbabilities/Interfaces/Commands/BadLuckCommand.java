package edu.colorado.team20.GameProbabilities.Interfaces.Commands;

import edu.colorado.team20.GameProbabilities.BadLuckCalc;
import edu.colorado.team20.GameProbabilities.Interfaces.Command;

/**
 * Description: class that implements a command to call bad luck execution
 */
public class BadLuckCommand implements Command {

    BadLuckCalc badLuckCalc;

    // constructor
    public BadLuckCommand(BadLuckCalc calc) {
        this.badLuckCalc = calc;
    }

    /**
     * Description: call the method that executes the bad luck calculation,
     * then return bool determining if bad luck happens
     * Params: none
     * Returns: boolean value
     */
    public boolean execute() {
        // bad luck happens
        // bad luck doesn't happen
        return badLuckCalc.calcBadLuck();
    }
}
