package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.RegularBoardShow;

public final class PlayerBoard extends Board {

    public PlayerBoard() {
        super();
        showBehavior = new RegularBoardShow();
    }
}
