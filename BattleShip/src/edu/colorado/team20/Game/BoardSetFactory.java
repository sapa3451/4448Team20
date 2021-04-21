package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;

/**
 * Description:
 */
public class BoardSetFactory {

    private final BoardFactory boardFactory;

    public BoardSetFactory(){this.boardFactory = new BoardFactory();}

    /**
     * Description:
     * Params:
     * Returns:
     */
    public Board[] createBoardSet(String[] wantedBoards){
        //Takes in list of each desired board type

        Board[] mySet = new Board[wantedBoards.length];

        for(int i=0; i< wantedBoards.length;i++){
            mySet[i]=this.boardFactory.createBoard(wantedBoards[i]);
        }

        return mySet;//Returns list of desired board objects
    }

}
