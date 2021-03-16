package edu.colorado.team20.Player.Interfaces;

import edu.colorado.team20.Board.Board;

public interface PlacementBehavior {
    void place(int id, Board board, int size, int quartersPos);
}
