package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceRegularBoardShow;
import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceMark;

public class SurfaceBoard extends Board{
    public SurfaceBoard() {
        super();
        showBehavior = new SurfaceRegularBoardShow();
        markBehavior = new SurfaceMark();
    }

    public void performMarkBoard(char col, int row){
        markBehavior.MarkBoard(this, col, row);
    }
}
