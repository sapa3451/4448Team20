package edu.colorado.team20;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    private String shipName;
    private int shipSize;

    public Ship(String shipName, int shipSize) {

        this.shipName = shipName;
        this.shipSize = shipSize;

    }

    public String getShipName() {
        return shipName;
    }

    public int getShipSize() {
        return shipSize;
    }
    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean
}
