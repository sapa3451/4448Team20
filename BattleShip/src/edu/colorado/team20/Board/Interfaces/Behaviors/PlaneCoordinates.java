package edu.colorado.team20.Board.Interfaces.Behaviors;

import edu.colorado.team20.Board.Interfaces.CreateCoordinatesBehavior;

/**
 * Description: This implements the CreateCoordinatesBehavior, where for each type of ship, there is a specific way the proper cords
 * are created. In this case, we have a plane's coords being created, such as the bomber.
 */
public class PlaneCoordinates implements CreateCoordinatesBehavior {
    public String createShipCoordinates(int row, char col, int direction, int size, int rowSize, int colSize) {
        String coordinates = ""; // string to hold coordinates
        String captainsQ = "";

        // find minimums for board --> handle out of bounds for board
        final char colMinVal = 'A';
        final char colMaxVal = (char) (colMinVal + colSize-1);
        final int rowMinVal = 0;

        // need to check if ship is in column's bounds
        if ((char) (col + 1) > colMaxVal) { // check if whole plane fits in column
            return "NULL"; // return NULL as failure
        }
        if ((char) (col + -1) < colMinVal) { // check if whole plane fits in column
            return "NULL"; // return NULL as failure
        }
        if ((row - 2) < rowMinVal) { // need to check row above for other piece
            return "NULL";
        }
        if ((row + 2) > rowSize) { // need to check row above for other piece
            return "NULL";
        }

        for (int i = row; i < row+3; i++) {
            if (i == row+1) { // need to add coordinate for column above
                // add coordinate to start to keep in row column format
                coordinates = (char) (col - 1) + String.valueOf(i - 1) + ',' + coordinates;
                coordinates = coordinates + col + (i - 1);
                coordinates = (char) (col + 1) + String.valueOf(i - 1) + ',' + coordinates;
            }
            else if (i == row) {
                captainsQ = captainsQ + col + (i - 1);
                coordinates = coordinates + col + (i - 1);
            }
            else {
                coordinates = coordinates + col + (i - 1); // add position to coordinates
            }

            if (i != row+2) { //add comma
                coordinates = coordinates + ',';
            }
        }

        // add captainsQ to the string
        coordinates = coordinates + '-' + captainsQ;

        return coordinates;
    }
}
