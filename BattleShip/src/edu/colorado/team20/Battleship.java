package edu.colorado.team20;

public class Battleship extends Ship {
    public Battleship (int size) {
        this.shipSize = size;
        this.shipSections = new char[size];
        for (int i = 0; i < size; i++) {
            this.shipSections[i] = 'I';
        }
        this.shipSections[2] = 'Q';
    }
    public void displayInfo () {
        System.out.println(this.shipSize);
        for (int i = 0; i < this.shipSize; i++) {
            System.out.print(this.shipSections[i]);
        }
        System.out.println();
    }
}
