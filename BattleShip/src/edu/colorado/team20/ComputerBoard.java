package edu.colorado.team20;

import javafx.util.Pair;
import edu.colorado.team20.Ship;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class ComputerBoard extends Board {

    public ComputerBoard() {
        super();
        showBehavior = new HiddenBoardShow();
    }

}
