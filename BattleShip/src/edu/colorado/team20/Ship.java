package edu.colorado.team20;

public abstract class Ship {
    protected int numOccupiedBoardCells;
    protected char[] shipSections;
    protected int shipHealth;
    protected String name;
    int id;

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
        this.shipHealth = numOccupiedBoardCells + 1;
        name = shipName;
    }

    public void setID(int ID) { id = ID; }
    public int getId() { return id; }

    public String getName () {
        return name;
    }

    public int getNumOccupiedBoardCells() {
        return numOccupiedBoardCells;
    }

    public char[] getShipSections() {
        return shipSections;
    }
    public boolean checkSunk(int size) {
        for (int i = 0; i < size; i++) {
            if (this.shipSections[i] != 'H') {
                return false;
            }
        }
        return true;
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

