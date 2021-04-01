package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.UnderwaterMark;
import edu.colorado.team20.Board.Interfaces.Behaviors.UnderwaterRegularBoardShow;

public class UnderwaterBoard extends Board {

    public UnderwaterBoard() {
        super();
        showBehavior = new UnderwaterRegularBoardShow(); //board will start with teh regular show, must set to hidden show if computer will be using board
        markBehavior = new UnderwaterMark();
        id = -1; // set id to neg 1 to represent starting underwater board
    }

    public boolean SetGamePiecePos(int id, int row, char col, int direction, int size, int quartersPos) {
        char positionChar = board[row-1][alphaMap.get(col)];

        if (positionChar != 'E') { //Checks if ship is already at that location
            System.out.println("Ship already placed here!");
            return false;
        }
        else {
            // get coordinates for shape of ship
            // IMPORTANT: coordinates already takes care of zero indexing
            String coordinates = this.performCreateShipCoordinates(row, col, direction, size);
            String captainsQ = "";
            if (coordinates == "NULL") { return false; } // ship doesn't fit on board for col/row

            // underwater boards have unique captain's Q quarters so will have info in string
            String[] positions = coordinates.split("-");
            coordinates = positions[0];
            captainsQ = positions[1];

            char coordCol = ' ';
            int coordRow = -1;

            // check if any ships are already placed there
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3,"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                if(board[coordRow][alphaMap.get(coordCol)] != 'E') {
                    System.out.println("Ship already placed here!");
                    return false;
                }
            }

            gamePieceCoordinates.put(id, coordinates); // add current ship coordinates to map with id

            // update the board with ship
            for (int i = 0; i < coordinates.length(); i=i+3) { //example String: "A1,A2,A3"
                coordCol = coordinates.charAt(i);
                coordRow = Integer.parseInt(String.valueOf(coordinates.charAt(i+1)));
                this.board[coordRow][alphaMap.get(coordCol)] = 'S';
                this.idBoard[coordRow][alphaMap.get(coordCol)] = id;
            }

            // place the captain's quarters on board and place coordinate in shipCapQ mapping
            char Qcol = captainsQ.charAt(0);
            int Qrow = Integer.parseInt(String.valueOf(captainsQ.charAt(1)));
            board[Qrow][alphaMap.get(Qcol)] = 'Q'; // mark captain's Q on board
            gamePieceCapQPos.put(id, Qcol + String.valueOf(Qrow)); // add captain's quarter's to map

        }
        return true;
    }
}
