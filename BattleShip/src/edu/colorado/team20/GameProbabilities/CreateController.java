package edu.colorado.team20.GameProbabilities;

import edu.colorado.team20.GameProbabilities.Interfaces.Commands.BadLuckCommand;
import edu.colorado.team20.GameProbabilities.Interfaces.Commands.GoodLuckCommand;

public class CreateController {
    // creating all game turn probabilities
    BadLuckCalc badLuckCalc = new BadLuckCalc();
    GoodLuckCalc goodLuckCalc = new GoodLuckCalc();

    // creating commands
    BadLuckCommand callBadLuckCommand = new BadLuckCommand(badLuckCalc);
    GoodLuckCommand callGoodLuckCommand = new GoodLuckCommand(goodLuckCalc);

    public GameProbabilitiesController createController() {
        GameProbabilitiesController controller = new GameProbabilitiesController();

        // add commands to remote
        controller.setProbCommand(0, callBadLuckCommand);
        controller.setProbCommand(1, callGoodLuckCommand);

        return controller;
    }

}
