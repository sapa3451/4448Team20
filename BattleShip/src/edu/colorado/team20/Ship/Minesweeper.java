package edu.colorado.team20.Ship;

public class Minesweeper extends Ship {
    public Minesweeper() {
        super();
        this.numOccupiedBoardCells=2;
        this.name="minesweeper";
        this.captainQHealth = 1; // minesweeper only has one health hit for captain's quarters
        this.totShipHealth = this.numOccupiedBoardCells;
        this.quartersSpot = 1;
        this.underwater = false;
        this.isSub = false;
    }
}
