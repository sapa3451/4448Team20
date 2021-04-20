package edu.colorado.team20.GameProbabilities;

import edu.colorado.team20.GameProbabilities.Interfaces.Command;
import edu.colorado.team20.GameProbabilities.Interfaces.Commands.BadLuckCommand;
import edu.colorado.team20.GameProbabilities.Interfaces.Commands.GoodLuckCommand;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;

public class GameProbabilitiesController {
    private Command[] probCommands;

    public GameProbabilitiesController() {
        // create array to hold prob commands
        probCommands = new Command[2];
    }

    public void setProbCommand(int slot, Command command) {
        this.probCommands[slot] = command;
    }

    public boolean doCommand(int slot) {
        return probCommands[slot].execute();
    }

}
