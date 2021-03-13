package edu.colorado.team20.Ship;

import edu.colorado.team20.Ship.Interfaces.ShipObservers;

public abstract class Ship implements ShipObservers {
    protected int numOccupiedBoardCells;
    protected int totShipHealth;
    protected int captainQHealth;
    protected String name;
    protected int id;
    protected boolean sunk;

    public Ship(int numOccupiedBoardCells, String shipName) {
        this.numOccupiedBoardCells = numOccupiedBoardCells;
        this.totShipHealth = this.getSize()+1;
        this.name = shipName;
        this.sunk = false;
    }

    public void setId(int ID) { this.id = ID; }

    public int getId() { return this.id; }

    public int getSize () {
        return this.numOccupiedBoardCells;
    }

    public String getName () {
        return this.name;
    }

    public int getTotShipHealth() { return this.totShipHealth; }

    public boolean update(int damage) { //Use of the observer strategy here, function in ShipObservers
        this.totShipHealth = this.totShipHealth - damage;
        if (this.totShipHealth == 0) {
            this.sunk = true;
            return true;
        } // ship destroyed
        return false; // ship still has life
    }

    public int getCaptainQHealth() { return this.captainQHealth; }

    public boolean updateCQ(int damage) { //Use of the observer strategy here, function in ShipObservers
        this.captainQHealth = this.captainQHealth - damage;
        this.totShipHealth = this.totShipHealth - damage;
        if (this.captainQHealth == 0) {
            // set tot health to 0
            this.update(this.totShipHealth);
            this.sunk = true;
            //System.out.println("You've destroyed the " + this.getName() + " captain's quarters! " + this.getName() + " is now destroyed!");
            return true; // return true --> ship is destroyed
        }
        return false; // return false --> ship still has health
    }

    public boolean checkSunk() {
        return sunk;
    }

    // TODO: Find a way to show way how many times the captain's quarters has been hit to know if
    //  it is going to be a destroyed ship
}
