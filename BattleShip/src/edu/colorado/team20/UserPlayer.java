package edu.colorado.team20;

import edu.colorado.team20.Board;

import java.util.Scanner;

public final class UserPlayer extends Player{

    public UserPlayer(Board board) {
        super(board);
        placementBehavior = new InputPlacement();
        shotBehavior = new InputShot();
    }


    //TODO: connecting ships with player??? email dwight
    //TODO: captains quarters
    //TODO: sonar radar
    //TODO: ship health and board knowing which ships are which
}
