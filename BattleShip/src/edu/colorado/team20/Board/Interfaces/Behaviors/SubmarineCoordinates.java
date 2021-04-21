
package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Interfaces.CreateShipCoordinatesBehavior;

/**
 * Description:
 */
public class SubmarineCoordinates implements CreateShipCoordinatesBehavior {

    public String createShipCoordinates(int row, char col, int direction, int size, int rowSize, int colSize) {
        String coordinates = ""; // string to hold coordinates
        String captainsQ = "";

        // find minimums for board --> handle out of bounds for board
        final char colMinVal = 'A';
        final char colMaxVal = (char) (colMinVal + colSize);
        final int rowMinVal = 0;

        if (direction == 1) { // horizontal
            // need to check if ship is in column's bounds
            if ((char) (col + size-1) > colMaxVal) { // check if whole ship fits in column
                return "NULL"; // return NULL as failure
            }
            if ((row - 2) < rowMinVal) { // need to check row above for submarine
                return "NULL";
            }

            char indexCol = col;
            for (int i = 0; i < size; i++) {
                if (i == 2) { // need to add coordinate for row above
                    // add coordinate to start to keep in row column format
                    coordinates = indexCol + String.valueOf(row - 2) + ',' + coordinates;
                }
                else if (i == 4) { // check if captainsQ position
                    captainsQ = captainsQ + indexCol + (row - 1); // get positon for Q
                    coordinates = coordinates + indexCol + (row - 1); // add position to coordinates
                    indexCol += 1;
                }
                else {
                    coordinates = coordinates + indexCol + (row - 1); // add position to coordinates
                    indexCol += 1;
                }

                if (i != size-1 && i != 2) { //add comma
                    coordinates = coordinates + ',';
                }

            }
        }
        else { // vertical
            // need to check if ship is in row's bounds
            if ((row + size-1) > rowSize) { // check if whole ship fits in row
                return "NULL"; // return NULL as failure
            }
            if ((char) (col - 1) < colMinVal) { // need to check column to left for submarine
                return "NULL";
            }

            for (int i = row; i < size+row-1; i++) {
                if (i == row+1) { // need to add coordinate for column above
                    // add coordinate to start to keep in row column format
                    coordinates = (char) (col - 1) + String.valueOf(i - 1) + ',' + coordinates;
                }
                else if (i == row) {
                    captainsQ = captainsQ + col + (i - 1); // get position for Q
                }
                coordinates = coordinates + col + (i - 1); // add position to coordinates

                if (i != size+row-2) { //add comma
                    coordinates = coordinates + ',';
                }
            }
        }

        // add captainsQ to the string
        coordinates = coordinates + '-' + captainsQ;

        return coordinates;
    }
}