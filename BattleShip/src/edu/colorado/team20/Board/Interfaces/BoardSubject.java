package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.Ship.Ship;

public interface BoardSubject {
    void registerShip(Ship s);
    void removeShip(int id);
    int updateShipOnHit(int id);
    int updateShipOnCQHit(int id);
}
