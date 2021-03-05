package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.HiddenBoardShow;

public final class ComputerBoard extends Board {

    public ComputerBoard() {
        super();
        showBehavior = new HiddenBoardShow(); //a computer only needs to display its board to an opponent, so its ships that are still intact will be hidden
    }

}
