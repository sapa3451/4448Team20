package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.GamePiece.GamePiece;

/**
 * Description:
 */

public interface BoardSubject {
    void registerShip(GamePiece s);
    void removeShip(int id);
    int updateGamePieceOnHit(int id);
    int updateGamePieceOnCQHit(int id);
}
