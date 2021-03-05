package edu.colorado.team20.Ship;

import edu.colorado.team20.Ship.Interfaces.ShipObservers;

public abstract class Ship implements ShipObservers {
    protected int numOccupiedBoardCells;
    protected char[] shipSections;
    protected int totShipHealth;
    protected int captainQHealth;
    protected String name;
    int id;
    protected int shipSize;

    public Ship(int numOccupiedBoardCells, String shipName) {
        this.numOccupiedBoardCells = numOccupiedBoardCells;
        int quarters = numOccupiedBoardCells /2;
        this.shipSections = new char[numOccupiedBoardCells];
        if (numOccupiedBoardCells == 2) {
            quarters = 0;
        }
        for (int i = 0; i < numOccupiedBoardCells; i++) {
            this.shipSections[i] = 'S';
        }
        this.shipSections[quarters] = 'Q';
        this.totShipHealth = this.getSize();
        this.name = shipName;
    }

    public void setId(int ID) { this.id = ID; }

    public int getId() { return this.id; }

    public int getSize () {
        return this.numOccupiedBoardCells;
    }

    public String getName () {
        return this.name;
    }

    public char[] getShipSections() {
        return this.shipSections;
    }

    public int getTotShipHealth() { return this.totShipHealth; }

    public boolean update(int damage) {
        this.totShipHealth = this.totShipHealth - damage;
        if (this.totShipHealth == 0) { return true; } // ship destroyed
        return false; // ship still has life
    }

    public int getCaptainQHealth() { return this.captainQHealth; }

    public boolean updateCQ(int damage) {
        this.captainQHealth = this.captainQHealth - damage;
        if (this.captainQHealth == 0) {
            // set tot health to 0
            this.update(this.totShipHealth);
            //System.out.println("You've destroyed the " + this.getName() + " captain's quarters! " + this.getName() + " is now destroyed!");
            return true; // return true --> ship is destroyed
        }
        return false; // return false --> ship still has health
    }


    public boolean checkSunk(int size) {
        if (this.getTotShipHealth() == 0) {
            return true;
        }
        return false;
    }

    public void displayInfo() {
        System.out.println(this.numOccupiedBoardCells);
        for (int i = 0; i < this.numOccupiedBoardCells; i++) {
            System.out.print(this.shipSections[i]);
        }
        System.out.println();
    }

    // TODO: Find a way to show way how many times the captain's quarters has been hit to know if
    //  it is going to be a destroyed ship
}

