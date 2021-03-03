package edu.colorado.team20;

import javafx.util.Pair;
import edu.colorado.team20.Ship;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public final class PlayerBoard extends Board {

    public PlayerBoard() {
        super();
        showBehavior = new RegularBoardShow();
    }

    @Override
    public void ShowSonarPulse(char c, int i) {

    }
}
