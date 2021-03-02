package edu.colorado.team20;
//TODO: Create an abstract class that implements/declares certain behaviors from an interface for each player that is called
public interface IPlayer {
    void Shot(Board board, char col, int row);
    Board getBoard();
    void placeBattleship(int id);
    void placeMinesweeper(int id);
    void placeDestroyer(int id);
}
