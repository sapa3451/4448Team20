package edu.colorado.team20.Game;

import edu.colorado.team20.Ship.Battleship;
import edu.colorado.team20.Ship.Destroyer;
import edu.colorado.team20.Ship.Minesweeper;
import edu.colorado.team20.Game.ShipFactory;
import edu.colorado.team20.Ship.Ship;

public class FleetFactory {

    private ShipFactory shipFactory;//Each fleet factory contains their own shipfactory

    public FleetFactory(){
       shipFactory = new ShipFactory();
    }

    public Ship[] createFleet(String[] wantedShips){
        //takes in list of each desired ships type
        Ship[] myFleet = new Ship[wantedShips.length];

        for(int i=0; i< wantedShips.length;i++){
            myFleet[i]=this.shipFactory.createShip(wantedShips[i]);
        }

        return myFleet;//Returns hardcoded list of each ship type
    }
}
