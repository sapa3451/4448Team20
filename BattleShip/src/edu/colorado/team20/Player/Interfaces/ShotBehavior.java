package edu.colorado.team20.Player.Interfaces;

import edu.colorado.team20.Board.Board;

public interface ShotBehavior {
    boolean shot(Board[] board, char col, int row);
}
