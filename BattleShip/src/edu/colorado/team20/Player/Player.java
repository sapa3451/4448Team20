package edu.colorado.team20.Player;

import java.util.HashMap;
import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

public abstract class Player {
    private final Board board; // isn't this tight coupling??
    PlacementBehavior placementBehavior;
    protected HashMap<Integer, String> shotDecisionInfo; // keep track of shots per round
    ShotBehavior shotBehavior;
    public Player(Board board) {
        this.board = board;
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
    }

    public Board getBoard () {
        return board;
    }

    public void performPlacement(int id, int size) {
        placementBehavior.place(id, this.board, size);
    }

    public void performShot (Board board, char col, int row, int turnNum) {
        shotBehavior.shot(board, col, row, turnNum); //using strategy method, this is a behavior (in ShotBehavior)
        this.addShotFromTurn(turnNum, col+String.valueOf(row));
    }

    public void setPlacementBehavior(PlacementBehavior pb){
        placementBehavior = pb;
    }

    public void addShotFromTurn(int turnNum, String position) { shotDecisionInfo.put(turnNum, position); }

    public String getTurnShot(int turnNum) { return shotDecisionInfo.get(turnNum); }

    public void setShotBehavior(ShotBehavior sb) {
        shotBehavior = sb;
    }
}
