package edu.colorado.team20.Ship;

public class Submarine extends Ship {

    public Submarine() {
        super();
        this.numOccupiedBoardCells=5;
        this.totShipHealth = this.numOccupiedBoardCells+1;
        this.name="submarine";
        this.captainQHealth = 2; //Submarine has 2 health for CQ
        this.quartersSpot = 5;
        this.underwater = true;
    }
}
