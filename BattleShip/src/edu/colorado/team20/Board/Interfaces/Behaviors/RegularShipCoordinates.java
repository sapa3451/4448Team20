package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;

public class RegularShipCoordinates implements CreateShipCoordinatesBehavior {
    public String createShipCoordinates(int row, char col, int direction, int size) {
        // TODO: need to make this customized to submarine
        String coordinates = ""; // string to hold coordinates

        if (direction == 1) { // horizontal
            char indexCol = col;
            for (int i = 0; i < size; i++) {
                coordinates = coordinates + indexCol + (row - 1) + ','; // add position to coordinates
                indexCol += 1;
            }
            System.out.println(coordinates);
        }
        else { // vertical
            for (int i = row; i < size+row; i++) {
                coordinates = coordinates + col + (i - 1) + ','; // add position to coordinates
            }
            System.out.println(coordinates);
        }

        return coordinates;
    }
}
