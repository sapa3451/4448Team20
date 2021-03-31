package edu.colorado.team20.GamePiece;

public class Battleship extends GamePiece {
    public Battleship () {
        super();
        this.numOccupiedBoardCells=4;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="battleship";
        this.captainQHealth = 2; //Battleship has 2 health for CQ
        this.quartersSpot = 3;
        this.underwater = false;
        this.isSub = false;
    }
}
