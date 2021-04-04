package edu.colorado.team20.Game;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;

public class BoardFactory {

    public Board createBoard(String type){

        String typeFix=type.toLowerCase();

        switch (typeFix) {
            case "air":
                Board AirBoard = new Board();
                AirBoard.setShowBehavior(new AirRegularBoardShow());
                AirBoard.setMarkBehavior(new AirMark());
                AirBoard.setId(1);
                return AirBoard;
            case "underwater":
                Board UnderwaterBoard = new Board();
                UnderwaterBoard.setShowBehavior(new UnderwaterRegularBoardShow());
                UnderwaterBoard.setMarkBehavior(new UnderwaterMark());
                UnderwaterBoard.setId(-1);
                return UnderwaterBoard;
            case "surface":
                Board SurfaceBoard = new Board();
                SurfaceBoard.setShowBehavior(new SurfaceRegularBoardShow());
                SurfaceBoard.setMarkBehavior(new SurfaceMark());
                SurfaceBoard.setId(0);
                return SurfaceBoard;
            default:
                Board ErrorBoard = new Board();
                ErrorBoard.setShowBehavior(new SurfaceRegularBoardShow());
                ErrorBoard.setMarkBehavior(new SurfaceMark());
                ErrorBoard.setId(0);
                System.out.println("!!! Error! BoardFactory createBoard(): Input Not recognized, returning Surface Board !!!");
                return ErrorBoard;
        }
    }
}
