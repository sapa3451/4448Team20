package edu.colorado.team20.Player;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;

public final class ComputerPlayer extends Player{
    public ComputerPlayer(Board[] board) {
        super(board);
        placementBehavior = new RandomPlacement(); //a computer initially has an random behavior, both shot and placement
        shotBehavior = new CannonRandomShot();
    }

}
