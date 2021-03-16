package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;

public class SubmarineShipCoordinates implements CreateShipCoordinatesBehavior {
    public String createShipCoordinates(int row, char col, int direction, int size) {
        String coordinates = ""; // string to hold coordinates

        if (direction == 1) { // horizontal
            char indexCol = col;
            for (int i = 0; i < size; i++) {
                if (i == 2) { // need to add coordinate for row above
                    // add coordinate to start to keep in row column format
                    coordinates = indexCol + String.valueOf(row - 2) + ',' + coordinates;
                }
                else {
                    coordinates = coordinates + indexCol + (row - 1) + ','; // add position to coordinates
                    indexCol += 1;
                }
            }
        }
        else { // vertical
            for (int i = row; i < size+row-1; i++) {
                if (i == row+1) { // need to add coordinate for column above
                    // add coordinate to start to keep in row column format
                    coordinates = (char) (col - 1) + String.valueOf(i - 1) + ',' + coordinates;
                }
                coordinates = coordinates + col + (i - 1) + ','; // add position to coordinates
            }
        }

        return coordinates;
    }
}
