package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.Ship.Ship;

public interface BoardSubject { //observer pattern used here, the board is the subject that will send updates to the ships and add them/removed them as listeners
    void registerShip(Ship s);
    void removeShip(int id);
    int updateShipOnHit(int id);
    int updateShipOnCQHit(int id);
}
