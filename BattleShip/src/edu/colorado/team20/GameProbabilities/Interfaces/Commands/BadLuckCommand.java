package edu.colorado.team20.GameProbabilities.Interfaces.Commands;

import edu.colorado.team20.GameProbabilities.BadLuckCalc;
import edu.colorado.team20.GameProbabilities.Interfaces.Command;
import edu.colorado.team20.Player.Player;

public class BadLuckCommand implements Command {

    BadLuckCalc badLuckCalc = new BadLuckCalc();

    public BadLuckCommand(BadLuckCalc calc) {
        this.badLuckCalc = calc;
    }

    public boolean execute() {
        if (badLuckCalc.calcBadLuck()) { return true; } // bad luck happens
        else { return false; } // bad luck doesn't happen
    }
}
