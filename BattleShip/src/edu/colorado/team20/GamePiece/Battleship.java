package edu.colorado.team20.GamePiece;

/**
 * Description: class that extends GamePiece to define Battleship game piece with
 * specified attributes and constructor
 */
public class Battleship extends GamePiece {
    public Battleship () {
        super();
        this.numOccupiedBoardCells=4;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="battleship";
        this.captainQHealth = 2; //Battleship has 2 health for CQ
        this.quartersSpot = 3;
        this.goesUnderwater = false;
        this.goesInAir = false;
    }
}
