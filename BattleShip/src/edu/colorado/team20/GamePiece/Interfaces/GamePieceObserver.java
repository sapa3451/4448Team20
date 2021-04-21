package edu.colorado.team20.GamePiece.Interfaces;

/**
 * Description: interface that is created to implement Observer pattern.
 * Has methods to update the health on game pieces and to update the health of a captain's Q
 */
public interface GamePieceObserver {
    boolean update(int damage);
    boolean updateCQ(int damage);

}
