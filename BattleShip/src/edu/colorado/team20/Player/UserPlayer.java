package edu.colorado.team20.Player;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputShot;

public final class UserPlayer extends Player{

    public UserPlayer(Board board) {
        super(board);
        placementBehavior = new InputPlacement();
        shotBehavior = new InputShot();
    }


    //TODO: connecting ships with player??? email dwight
    //TODO: captains quarters
    //TODO: sonar radar
    //TODO: ship health and board knowing which ships are which
}
