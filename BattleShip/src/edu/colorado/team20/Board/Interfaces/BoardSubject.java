package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.Ship.Ship;

public interface BoardSubject {
    public void registerShip (Ship s);
    public void removeShip(int id);
    public int updateShipOnHit(int id);
    public int updateShipOnCQHit(int id);
}
