package edu.colorado.team20.Player;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;

/**
 * Description: An extension of the Player class, this subclass sets up the User's behaviors and inherits all other functions.
 */
public final class UserPlayer extends Player{

    public UserPlayer(Board[] board) {
        super(board);
        placementBehavior = new InputPlacement(); //a player initially has an input behavior, both shot and placement
        shotBehavior = new CannonInputShot(); //initially only has a cannon
    }
}
