package edu.colorado.team20.Ship;

public class Battleship extends Ship {
    public Battleship (int size, String name) {
        super(size, name);
        captainQHealth = 2; //Battleship has 2 health for CQ
    }
}