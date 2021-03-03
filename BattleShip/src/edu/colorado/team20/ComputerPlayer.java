package edu.colorado.team20;

import edu.colorado.team20.Board;

import java.util.Random;

public final class ComputerPlayer extends Player{
    public ComputerPlayer(Board board) {
        super(board);
        placementBehavior = new RandomPlacement();
        shotBehavior = new RandomShot();

    }

}
