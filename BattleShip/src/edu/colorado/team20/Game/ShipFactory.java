package edu.colorado.team20.Game;

import edu.colorado.team20.Ship.*;


public class ShipFactory {
    public Ship createShip(String type){

        String typeFix=type.toLowerCase();
        //Does above to avoid Dev errors cuasing confusion
        //checks for current possible ship types
        if(typeFix.equals("minesweeper")){
            return new Minesweeper();
        }
        else if(typeFix.equals("destroyer")){
            return new Destroyer();
        }
        else if (typeFix.equals("battleship")){
            return new Battleship();
        }
        else if(typeFix.equals("submarine")){
            return new Submarine();
        }
        else{//if not recognized prints error state
            //and returns a minesweeper to avoid crashing
            System.out.println("!!! Error! ShipFactory createShip(): Input Not recognized, returing minesweeper !!!");
            return new Minesweeper();
        }
    }
}
