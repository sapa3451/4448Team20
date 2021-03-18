package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;

public class BoardSetFactory {

    private BoardFactory boardFactory;

    public BoardSetFactory(){this.boardFactory = new BoardFactory();}

    public Board[] createBoardSet(String[] wantedBoards){
        //Takes in list of each desired board type

        Board[] mySet = new Board[wantedBoards.length];

        for(int i=0; i< wantedBoards.length;i++){
            mySet[i]=this.boardFactory.createBoard(wantedBoards[i]);
        }

        return mySet;//Returns list of desired board objects
    }

}
