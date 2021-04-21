package edu.colorado.team20.Game;

import edu.colorado.team20.GamePiece.GamePiece;

/**
 * Description: The factory pattern is deployed here used in Game Management to create the fleets that will be used
 */
public class FleetFactory {

    private final GamePieceFactory gamePieceFactory;//Each fleet factory contains their own shipfactory

    public FleetFactory(){
       this.gamePieceFactory = new GamePieceFactory();
    }

    /**
     * Description: This function is the main factory method for the fleet, this goes into the game piece factory to create the actual piece and puts them into a fleet
     * Params: the pieces to create
     * Returns: the fleet that was created
     */
    public GamePiece[] createFleet(String[] wantedShips){
        //takes in list of each desired ships type
        GamePiece[] myFleet = new GamePiece[wantedShips.length];

        for(int i=0; i< wantedShips.length;i++){
            myFleet[i]=this.gamePieceFactory.createShip(wantedShips[i]);
        }

        return myFleet;//Returns list of desired ship objects
    }
}
