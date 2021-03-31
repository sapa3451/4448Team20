package edu.colorado.team20.GamePiece;

public class Destroyer extends GamePiece {
    public Destroyer() {
        super();
        this.numOccupiedBoardCells=3;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="destroyer";
        this.captainQHealth = 2; //Destroyer has 2 health for CQ
        this.quartersSpot = 2;
        this.underwater = false;
    }
}
