package edu.colorado.team20.GameProbabilities.Interfaces.Commands;

import edu.colorado.team20.GameProbabilities.BadLuckCalc;
import edu.colorado.team20.GameProbabilities.GoodLuckCalc;
import edu.colorado.team20.GameProbabilities.Interfaces.Command;

public class GoodLuckCommand implements Command {
    GoodLuckCalc goodLuckCalc = new GoodLuckCalc();

    public GoodLuckCommand(GoodLuckCalc calc) {
        this.goodLuckCalc = calc;
    }

    public boolean execute() {
        if (goodLuckCalc.calcGoodLuck()) { return true; } // bad luck happens
        else { return false; } // bad luck doesn't happen
    }
}
