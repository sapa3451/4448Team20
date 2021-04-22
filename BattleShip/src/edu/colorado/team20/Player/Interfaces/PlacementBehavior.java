package edu.colorado.team20.Player.Interfaces;

import edu.colorado.team20.Board.Board;

/**
 * Description: In interface to implement how a player(or CP) places a game peice on a given board object
 * At current implementation, game piece placement based on user input it used by actual player and randomized placement is used by CP
 */
public interface PlacementBehavior {
    /**
     * Description: a function to put a game peice on a board and save necessary info for later use
     * Params:an ID number to identify the ship later, the board to place the ship on, the size of the ship, the position on the ship of the Captain Quaters
     * Returns: nothing
     */
    void place(int id, Board board, int size, int quartersPos);
}
