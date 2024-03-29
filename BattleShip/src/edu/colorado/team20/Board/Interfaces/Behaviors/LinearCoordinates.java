package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Interfaces.CreateCoordinatesBehavior;

/**
 * Description: This implements the CreateCoordinatesBehavior, where for each type of ship, there is a specific way the proper cords
 * are created. In this case, we have a linear ship's coords being created, such as the battleships, minesweepers, and destroyers.
 */
public class LinearCoordinates implements CreateCoordinatesBehavior {
    public String createShipCoordinates(int row, char col, int direction, int size, int rowSize, int colSize) {
        String coordinates = ""; // string to hold coordinates

        // find minimums for board --> handle out of bounds for board
        final char colMinVal = 'A';
        final char colMaxVal = (char) (colMinVal + colSize);

        if (direction == 1) { // horizontal
            // need to check if ship is in column's bounds
            if ((char) (col + size) > colMaxVal) { // check if whole ship fits in column
                return "NULL"; // return NULL as failure
            }

            char indexCol = col;
            for (int i = 0; i < size; i++) {
                coordinates = coordinates + indexCol + (row - 1); // add position to coordinates
                indexCol += 1;

                if (i != size-1) { //add comma
                    coordinates = coordinates + ',';
                }
            }

        }
        else { // vertical
            // need to check if ship is in row's bounds
            if ((row + size) > rowSize) { // check if whole ship fits in row
                return "NULL"; // return NULL as failure
            }

            for (int i = row; i < size+row; i++) {
                coordinates = coordinates + col + (i - 1); // add position to coordinates

                if (i != size+row-1) { //add comma
                    coordinates = coordinates + ',';
                }
            }
        }

        return coordinates;
    }
}
