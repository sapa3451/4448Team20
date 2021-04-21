package edu.colorado.team20.Player.Interfaces;

import edu.colorado.team20.Board.Board;

public interface ShotBehavior {
    /**
     * Description:
     * Params:
     * Returns:
     */
    boolean shot(Board[] board, char col, int row);
}
