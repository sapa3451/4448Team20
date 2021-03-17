package edu.colorado.team20.Ship;

public class Submarine extends Ship {

    public Submarine(int size, String name) {
        super(size, name);
        captainQHealth = 2; //Submarine has 2 health for CQ
        quartersSpot = 5;
        underwater = true;
    }
}
