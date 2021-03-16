package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.UnderwaterRegularBoardShow;

public class UnderwaterBoard extends Board {

    public UnderwaterBoard() {
        super();
        showBehavior = new UnderwaterRegularBoardShow();
    }

    // TODO: if keeping ~ for underwater board need to update some features when checking board
}
