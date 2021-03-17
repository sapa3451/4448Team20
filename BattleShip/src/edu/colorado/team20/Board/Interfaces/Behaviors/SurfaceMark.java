package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.MarkBehavior;

//This is the start of the interface for our plan to mark surface/underwater stuff
//TODO: before any further implementation, underwater board must be implemented

public class SurfaceMark implements MarkBehavior {
    public void MarkBoard(Board board, char col, int row){
        // call check spot in the beginning to check if spot is valid
        char positionChar = board.getBoard()[row-1][board.getAlphaMap().get(col)];

        if (positionChar == 'E') { // if shot decision was empty --> mark as X
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'X'; // subtract one from row because indexing of array
        }
        else if (positionChar == 'Q') {
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'W'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'H') {
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'H'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'X') {
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'X'; // if gets hit more than once keep at W
        }
        else if (positionChar == 'S') {
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'H'; // if gets hit more than once keep at W
        }
        else { // decision was a ship --> mark as D
            board.getBoard()[row-1][board.getAlphaMap().get(col)] = 'D'; // subtract one from row because indexing of array
        }
        int id = board.getIdBoard()[row-1][board.getAlphaMap().get(col)];
        if (id != 0 && positionChar == 'Q' || positionChar == 'W') { // captainsQ got hit
            if (board.updateShipOnCQHit(id) == 0) { // need to check if captainsQ is 0 health
                // update the board to sink whole ship
                board.updateShipChars(board.getShipCoordinates(id));
                board.removeShip(id); //removes a ship as an observer when sunk
            }
        }
        else if (id != 0){
            if (board.updateShipOnHit(id) == 0) {
                board.updateShipChars(board.getShipCoordinates(id));
                board.removeShip(id); //removes a ship as an observer when sunk
            }
        }
        board.performShow();
    }
}
