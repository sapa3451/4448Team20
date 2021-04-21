package edu.colorado.team20.Board.Interfaces;

public interface CreateCoordinatesBehavior {
    /**
     * Description: This uses the Strategy Pattern and has multiple behaviors. It creates the proper coordinates for specific pieces used in the game.
     * Params: row where player wants piece, column where player wants piece, direction the player wants the piece, size of the piece
     *         number of rows in the board, number of columns in the board.
     * Returns: A string that contains the coordinates of the ship, with a ',' as the deliminator
     */
    String createShipCoordinates(int row, char col, int direction, int size, int rowSize, int colSize);
}
