package edu.colorado.team20;

public abstract class Ship {
    protected int shipSize;
    protected char[] shipSections;
    public int getShipSize() {
        return shipSize;
    }
    public char[] getShipSections() {
        return shipSections;
    }
    // TODO: Find a way to show way how many times the captain's quarters has been hit to know if
    //  it is going to be a destroyed ship
    public boolean checkSunk(int size) {
        for (int i = 0; i < size; i++) {
            if (this.shipSections[i] != 'H') {
                return false;
            }
        }
        return true;
    }

    public abstract void displayInfo();
}

