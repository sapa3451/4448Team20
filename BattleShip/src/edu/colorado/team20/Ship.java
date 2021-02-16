package edu.colorado.team20;
// This is the  baseclass for your ship.  Modify accordingly
// TODO: practice good OO design
public class Ship {

    private String shipName;
    private int shipSize;
    private char[] column;
    private int[] row;

    public Ship(String shipName, int shipSize) {

        this.shipName = shipName;
        this.shipSize = shipSize;

    }

    public void setColumnAndRow (char column, int row, int direction) {
        this.column = new char[this.shipSize];
        this.row = new int[this.shipSize];
        if (direction == 1) {
            int colConvert = convertToInt(column);
            for (int i = 0; i < this.shipSize; i++) {
                this.column[i] = convertToChar(colConvert);
                colConvert += 1;
            }
            for (int i = 0; i < this.shipSize; i++) {
                this.row[i] = row;
            }
        }
        else {
            for (int i = 0; i < this.shipSize; i++) {
                this.column[i] = column;
            }
            for (int i = 0; i < this.shipSize; i++) {
                this.row[i] = row;
                row += 1;
            }
        }
    }

    public char[] getColumn() {
        return column;
    }

    public int[] getRow() {
        return row;
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipSize() {
        return shipSize;
    }


    private static int convertToInt(char column)
    {
        int toReturn;
        switch (column)
        {
            case 'A':   toReturn = 0;
                break;
            case 'B':   toReturn = 1;
                break;
            case 'C':   toReturn = 2;
                break;
            case 'D':   toReturn = 3;
                break;
            case 'E':   toReturn = 4;
                break;
            case 'F':   toReturn = 5;
                break;
            case 'G':   toReturn = 6;
                break;
            case 'H':   toReturn = 7;
                break;
            case 'I':   toReturn = 8;
                break;
            case 'J':   toReturn = 9;
                break;
            default:    toReturn = -1;
                break;
        }

        return toReturn;
    }

    private static char convertToChar(int val)
    {
        char toReturn;
        switch (val)
        {
            case 0:   toReturn = 'A';
                break;
            case 1:   toReturn = 'B';
                break;
            case 2:   toReturn = 'C';
                break;
            case 3:   toReturn = 'D';
                break;
            case 4:   toReturn = 'E';
                break;
            case 5:   toReturn = 'F';
                break;
            case 6:   toReturn = 'G';
                break;
            case 7:   toReturn = 'H';
                break;
            case 8:   toReturn = 'I';
                break;
            case 9:   toReturn = 'J';
                break;
            default:    toReturn = 'Z';
                break;
        }

        return toReturn;
    }
    // TODO: create appropriate getter and setter methods
    // TODO: Understand encapsulation
    // TODO: Understand what these todo comments mean
}
