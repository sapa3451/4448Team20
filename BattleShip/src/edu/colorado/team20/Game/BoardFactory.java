package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;

public class BoardFactory {

    public Board createBoard(String type){

        String typeFix=type.toLowerCase();

        if(typeFix.equals("surface")){
            return new SurfaceBoard();
        }
        else if(typeFix.equals("underwater")){
            return new UnderwaterBoard();
        }
        else{
            System.out.println("!!! Error! BoardFactory createBoard(): Input Not recognized, returning Surface Board !!!");
            return new SurfaceBoard();
        }
    }
}
