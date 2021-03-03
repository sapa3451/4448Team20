package edu.colorado.team20;

import edu.colorado.team20.Board;

public interface ShotBehavior {
    void shot(Board board, char col, int row, int turnNum);
}
