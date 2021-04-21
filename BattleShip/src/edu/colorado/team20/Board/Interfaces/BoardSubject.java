package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.GamePiece.GamePiece;

/**
 * Description: This is use of the Observer Pattern between Board and GamePiece. We have the Board updating the ships on when they get hit.
 * When a ship is sunk, it will no longer be an observer and will be removed from the list.
 */
public interface BoardSubject {
    void registerShip(GamePiece s);
    void removeShip(int id);
    int updateGamePieceOnHit(int id);
    int updateGamePieceOnCQHit(int id);
}
