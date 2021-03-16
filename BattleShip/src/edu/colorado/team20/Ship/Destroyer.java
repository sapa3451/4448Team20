package edu.colorado.team20.Ship;

public class Destroyer extends Ship {
    public Destroyer(int size, String name) {
        super(size, name);
        captainQHealth = 2; //Destroyer has 2 health for CQ
        quartersSpot = 2;
    }
}
