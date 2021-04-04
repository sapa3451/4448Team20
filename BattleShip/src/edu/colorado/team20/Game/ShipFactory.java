package edu.colorado.team20.Game;

import edu.colorado.team20.GamePiece.*;


public class ShipFactory {
    public GamePiece createShip(String type){

        String typeFix=type.toLowerCase();
        //Does above to avoid Dev errors cuasing confusion
        //checks for current possible ship types
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
