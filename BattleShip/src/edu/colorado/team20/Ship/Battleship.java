package edu.colorado.team20.Ship;

public class Battleship extends Ship {
    public Battleship () {
        super();
        this.numOccupiedBoardCells=4;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="battleship";
        this.captainQHealth = 2; //Battleship has 2 health for CQ
        this.quartersSpot = 3;
        this.underwater = false;
    }
}
