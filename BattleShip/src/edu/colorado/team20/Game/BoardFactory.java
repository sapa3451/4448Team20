package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;

/**
 * Description: The factory pattern is deployed here used in the board set factory to instantiate board objects of different
 * desired types.
 * Currently the desired boards are air, surface, or water, but this can be changed to be at any elevation
 */
public class BoardFactory {

    /**
     * Description: This function is the main factory method for the boards, the wanted board has a type, and if the type is entered, the board will be created
     * Params: type of board to create
     * Returns: the board that was created
     */
    public Board createBoard(int zElevation){

        Board newBoard = new Board();
        newBoard.setShowBehavior(new RegularShow());
        newBoard.setzValue(zElevation);
        return newBoard;
    }
}
