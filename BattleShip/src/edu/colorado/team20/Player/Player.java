package edu.colorado.team20.Player;

import java.util.HashMap;
import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

public abstract class Player {
    private final Board airBoard;
    private final Board surfaceBoard;
    private final Board underwaterBoard;
    PlacementBehavior placementBehavior;
    protected final HashMap<Integer, String> shotDecisionInfo; // keep track of shots per round
    ShotBehavior shotBehavior;

    public Player(Board[] board) {
        this.airBoard = board[0];
        this.surfaceBoard = board[1];
        this.underwaterBoard = board[2];
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
    }

    public Board[] getBoards() {
        return new Board[]{this.airBoard, this.surfaceBoard, this.underwaterBoard};
    }

    public void performSurfacePlacement(int id, int size, int quartersPos) {
        placementBehavior.place(id, this.surfaceBoard, size, quartersPos);
    }

    public void performUnderwaterPlacement(int id, int size, int quartersPos) {
        placementBehavior.place(id, this.underwaterBoard, size, quartersPos);
    }

    public void performAirPlacement(int id, int size, int quartersPos) {
        placementBehavior.place(id, this.airBoard, size, quartersPos);
    }

    // TODO: try making this take in array
    public void performShot (Board[] board, char col, int row, int turnNum) {
        shotBehavior.shot(board, col, row); //using strategy method, this is a behavior (in ShotBehavior)
        this.addShotFromTurn(turnNum, col+String.valueOf(row));
    }

    public void setPlacementBehavior(PlacementBehavior pb){
        placementBehavior = pb;
    }

    public PlacementBehavior getPlacementBehavior(){
        return placementBehavior;
    }

    public void addShotFromTurn(int turnNum, String position) { shotDecisionInfo.put(turnNum, position); }

    public String getTurnShot(int turnNum) { return shotDecisionInfo.get(turnNum); }

    public void setShotBehavior(ShotBehavior sb) {
        shotBehavior = sb;
    }
}
