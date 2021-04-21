package edu.colorado.team20.GameProbabilities;

import edu.colorado.team20.GameProbabilities.Interfaces.Command;

/**
 * Description: controller that follows along Command Pattern that implements delegation to call commands from other classes
 */
public class GameProbabilitiesController {
    private final Command[] probCommands;

    // constructor
    public GameProbabilitiesController() {
        // create array to hold prob commands
        probCommands = new Command[2];
    }

    /**
     * Description: sets command calls to functions for the controller in the commands array
     * Params: command slot number command is being set to, command that is being set in controller slot
     * Returns: none
     */
    public void setProbCommand(int slot, Command command) {
        this.probCommands[slot] = command;
    }

    /**
     * Description: calls the command that is defined in the controller slot
     * Params: slot number for controller array that holds commands
     * Returns: boolean
     */
    public boolean doCommand(int slot) {
        return probCommands[slot].execute();
    }

}
