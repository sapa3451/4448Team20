package edu.colorado.team20.Game;

import edu.colorado.team20.GamePiece.GamePiece;

/**
 * Description:
 */
public class FleetFactory {

    private final GamePieceFactory gamePieceFactory;//Each fleet factory contains their own shipfactory

    public FleetFactory(){
       this.gamePieceFactory = new GamePieceFactory();
    }

    /**
     * Description:
     * Params:
     * Returns:
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
