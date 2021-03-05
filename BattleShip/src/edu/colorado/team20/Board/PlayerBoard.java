package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.RegularBoardShow;

public final class PlayerBoard extends Board {

    public PlayerBoard() {
        super();
        showBehavior = new RegularBoardShow(); //a players board will show their own ships and any shots against there board
    }
}
