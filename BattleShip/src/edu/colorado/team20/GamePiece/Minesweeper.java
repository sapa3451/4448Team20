package edu.colorado.team20.GamePiece;

/**
 * Description: class that extends GamePiece to define Minesweeper game piece with
 * specified attributes and constructor
 */
public class Minesweeper extends GamePiece {
    public Minesweeper() {
        super();
        this.numOccupiedBoardCells=2;
        this.name="minesweeper";
        this.captainQHealth = 1; // minesweeper only has one health hit for captain's quarters
        this.totShipHealth = this.numOccupiedBoardCells;
        this.quartersSpot = 1;
        this.goesUnderwater = false;
        this.goesInAir = false;
    }
}
