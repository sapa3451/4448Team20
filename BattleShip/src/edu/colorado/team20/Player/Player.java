package edu.colorado.team20.Player;

import java.util.HashMap;
import java.util.Random;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

public abstract class Player {
    private final Board airBoard;
    private final Board surfaceBoard;
    private final Board underwaterBoard;
    private final Board[] boards;
    PlacementBehavior placementBehavior;
    protected final HashMap<Integer, String> shotDecisionInfo; // keep track of shots per round
    ShotBehavior shotBehavior;

    public Player(Board[] board) {
        this.airBoard = board[0];
        this.surfaceBoard = board[1];
        this.underwaterBoard = board[2];
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
        this.boards = board;
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

    public void performShot (Board[] board, char col, int row, int turnNum) {
        // TODO: see if this needs to be here or somewhere else
        if (turnNum < 2) { // first two rounds doesn't have bad luck
            shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
            this.addShotFromTurn(turnNum, col+String.valueOf(row));
        }
        else {
            // call random player luck --> if get bad luck skip their turn
            if (!this.getPlayerBadLuck()) {
                shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                this.addShotFromTurn(turnNum, col+String.valueOf(row));
            }
            else {
                System.out.println("Bad luck has struck! You lost a turn!");
            }
        }
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

    public ShotBehavior getShotBehavior() {
        return shotBehavior;
    }

    public boolean getPlayerBadLuck() {
        // create instance of Random class
        Random rand = new Random();

        // get player bad luck --> 10% chance a getting bad luck
        int num = rand.nextInt(99);
        if (num < 10) { return true; }
        else { return false; }
    }
}
