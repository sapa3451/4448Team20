package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;

/**
 * Description: The factory pattern is deployed here used in the GameMangement to make an array of boards
 * at various elevations
 * Currently the desired boards are just 1 air, surface, and water, but this can be changed to be at any elevation
 * so the elevations are just 1,0,-1
 */
public class BoardSetFactory {

    private final BoardFactory boardFactory;

    /**
     * Description: instantiates a boardFactory to create all the boards we need to
     */
    public BoardSetFactory(){this.boardFactory = new BoardFactory();}

    /**
     * Description: takes in a set of desired board elevations and uses a board factory to
     * instantiate an array of those boards
     * Params: array of different desired board elevations
     * Returns: a board "set"(an array of newly instantiated boards at desired elevations)
     */
    public Board[] createBoardSet(int[] wantedBoards){
        //Takes in list of each desired board type

        Board[] mySet = new Board[wantedBoards.length];

        for(int i=0; i< wantedBoards.length;i++){
            mySet[i]=this.boardFactory.createBoard(wantedBoards[i]);
        }

        return mySet;//Returns list of desired board objects
    }

}
