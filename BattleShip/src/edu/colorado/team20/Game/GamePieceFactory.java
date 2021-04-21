package edu.colorado.team20.Game;

import edu.colorado.team20.GamePiece.*;

/**
 * Description: The factory pattern is deployed here used in FleetFactory to create the pieces that will be used
 */
public class GamePieceFactory {
    /**
     * Description: This function is the main factory method for the pieces, used by the fleet factory, this creates the pieces to go into the fleet
     * Params: the pieces to create
     * Returns: the fleet that was created
     */
    public GamePiece createShip(String type){

        String typeFix=type.toLowerCase();
        switch (typeFix) {
            case "minesweeper":
                return new Minesweeper();
            case "destroyer":
                return new Destroyer();
            case "battleship":
                return new Battleship();
            case "submarine":
                return new Submarine();
            case "bomber":
                return new Bomber();
            default: //if not recognized prints error state
                //and returns a minesweeper to avoid crashing
                System.out.println("!!! Error! ShipFactory createShip(): Input Not recognized, returing minesweeper !!!");
                return new Minesweeper();
        }
    }
}
