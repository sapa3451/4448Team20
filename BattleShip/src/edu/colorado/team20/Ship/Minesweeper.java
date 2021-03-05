package edu.colorado.team20.Ship;

public class Minesweeper extends Ship {
    public Minesweeper(int size, String name) {
        super(size, name);
        captainQHealth = 1; // minesweeper only has one health hit for captain's quarters
    }
}
