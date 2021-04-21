package edu.colorado.team20.GameProbabilities;

import edu.colorado.team20.GameProbabilities.Interfaces.Commands.BadLuckCommand;
import edu.colorado.team20.GameProbabilities.Interfaces.Commands.GoodLuckCommand;

/**
 * Description:
 */
public class CreateController {
    // creating all game turn probabilities
    private BadLuckCalc badLuckCalc = new BadLuckCalc();
    private GoodLuckCalc goodLuckCalc = new GoodLuckCalc();

    // creating commands
    private BadLuckCommand callBadLuckCommand = new BadLuckCommand(badLuckCalc);
    private GoodLuckCommand callGoodLuckCommand = new GoodLuckCommand(goodLuckCalc);

    /**
     * Description:
     * Params:
     * Returns:
     */
    public GameProbabilitiesController createController() {
        GameProbabilitiesController controller = new GameProbabilitiesController();

        // add commands to remote
        controller.setProbCommand(0, callBadLuckCommand);
        controller.setProbCommand(1, callGoodLuckCommand);

        return controller;
    }

}
