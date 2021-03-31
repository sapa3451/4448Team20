package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.GamePiece.GamePiece;

public interface BoardSubject { //observer pattern used here, the board is the subject that will send updates to the ships and add them/removed them as listeners
    void registerShip(GamePiece s);
    void removeShip(int id);
    int updateGamePieceOnHit(int id);
    int updateGamePieceOnCQHit(int id);
}
