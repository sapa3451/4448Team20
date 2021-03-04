package edu.colorado.team20;

public interface BoardSubject {
    public void registerShip (Ship s);
    public void removeShip(Ship s);
    public int updateShipOnHit(int id);
    public int updateShipOnCQHit(int id);
}
