package edu.colorado.team20.Board;

import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceRegularBoardShow;
import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceMark;

public class SurfaceBoard extends Board{
    public SurfaceBoard() {
        super();
        showBehavior = new SurfaceRegularBoardShow(); //board will start with teh regular show, must set to hidden show if computer will be using board
        markBehavior = new SurfaceMark();
    }
}
