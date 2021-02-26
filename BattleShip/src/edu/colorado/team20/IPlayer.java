package edu.colorado.team20;

public interface IPlayer {
    void Shot(IBoard board, char col, int row);
    IBoard getBoard();
    void placeBattleship();
    void placeMinesweeper();
    void placeDestroyer();
}
