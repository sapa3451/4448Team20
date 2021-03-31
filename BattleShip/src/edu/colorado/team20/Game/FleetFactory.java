package edu.colorado.team20.Game;

import edu.colorado.team20.GamePiece.GamePiece;

public class FleetFactory {

    private ShipFactory shipFactory;//Each fleet factory contains their own shipfactory

    public FleetFactory(){
       this.shipFactory = new ShipFactory();
    }

    public GamePiece[] createFleet(String[] wantedShips){
        //takes in list of each desired ships type
        GamePiece[] myFleet = new GamePiece[wantedShips.length];

        for(int i=0; i< wantedShips.length;i++){
            myFleet[i]=this.shipFactory.createShip(wantedShips[i]);
        }

        return myFleet;//Returns list of desired ship objects
    }
}
