package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;

public class BoardFactory {

    public Board createBoard(String type){

        String typeFix=type.toLowerCase();

        switch (typeFix) {
            case "air":
                Board AirBoard = new Board();
                AirBoard.setShowBehavior(new RegularShow());
                AirBoard.setMarkBehavior(new Mark());
                AirBoard.setzValue(1);
                return AirBoard;
            case "underwater":
                Board UnderwaterBoard = new Board();
                UnderwaterBoard.setShowBehavior(new RegularShow());
                UnderwaterBoard.setMarkBehavior(new Mark());
                UnderwaterBoard.setzValue(-1);
                return UnderwaterBoard;
            case "surface":
                Board SurfaceBoard = new Board();
                SurfaceBoard.setShowBehavior(new RegularShow());
                SurfaceBoard.setMarkBehavior(new Mark());
                SurfaceBoard.setzValue(0);
                return SurfaceBoard;
            default:
                Board ErrorBoard = new Board();
                ErrorBoard.setShowBehavior(new RegularShow());
                ErrorBoard.setMarkBehavior(new Mark());
                ErrorBoard.setzValue(0);
                System.out.println("!!! Error! BoardFactory createBoard(): Input Not recognized, returning Surface Board !!!");
                return ErrorBoard;
        }
    }
}
