package edu.colorado.team20;

public interface BoardSubject {
    public void registerShip (Ship s);
    public void removeShip(Ship s);
    public void updateShipOnHit(int id);
    public void updateShipOnCQHit(int id);
}
