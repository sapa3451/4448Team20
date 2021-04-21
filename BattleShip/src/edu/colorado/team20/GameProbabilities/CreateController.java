package edu.colorado.team20.GameProbabilities;

import edu.colorado.team20.GameProbabilities.Interfaces.Commands.BadLuckCommand;
import edu.colorado.team20.GameProbabilities.Interfaces.Commands.GoodLuckCommand;

/**
 * Description: This class is the primary class that creates the controller that the player will use throughout the game for their bad luck and good luck chances
 * Uses the command pattern
 */
public class CreateController {
    // creating all game turn probabilities
    private final BadLuckCalc badLuckCalc = new BadLuckCalc();
    private final GoodLuckCalc goodLuckCalc = new GoodLuckCalc();

    // creating commands
    private final BadLuckCommand callBadLuckCommand = new BadLuckCommand(badLuckCalc);
    private final GoodLuckCommand callGoodLuckCommand = new GoodLuckCommand(goodLuckCalc);

    /**
     * Description: function to create a controller and set it's command slots and return the new controller
     * Params: none
     * Returns: returns the new controller with slots commands set
     */
    public GameProbabilitiesController createController() {
        GameProbabilitiesController controller = new GameProbabilitiesController();

        // add commands to remote
        controller.setProbCommand(0, callBadLuckCommand);
        controller.setProbCommand(1, callGoodLuckCommand);

        return controller;
    }

}
