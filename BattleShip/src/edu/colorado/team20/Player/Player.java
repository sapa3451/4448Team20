package edu.colorado.team20.Player;

import java.util.HashMap;
import java.util.Random;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.GameProbabilities.CreateController;
import edu.colorado.team20.GameProbabilities.GameProbabilitiesController;
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
    GameProbabilitiesController probController = new GameProbabilitiesController(); // player has access to calling probabilities commands

    public Player(Board[] board) {
        this.airBoard = board[0];
        this.surfaceBoard = board[1];
        this.underwaterBoard = board[2];
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
        this.boards = board;

        // create command controller
        CreateController controllerCreator = new CreateController();
        probController = controllerCreator.createController();
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

    public void performTurn(Board[] board, char col, int row, int turnNum) {
        if (turnNum <= 2) { // first two rounds doesn't call probController
            shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
            this.addShotFromTurn(turnNum, col+String.valueOf(row));
        }
        else {
            // probController calls commands to determine how turn goes
            String outcome = useProbController();

            switch (outcome) {
                case "bad": {
                    System.out.println("Bad luck has struck! Your ship malfunctioned and you lost a turn!");
                    break;
                }
                case "good": {
                    // TODO: need to determine what happens when good luck strikes\
                    System.out.println("Good luck has struck!");
                    break;
                }
                default: {
                    shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                    this.addShotFromTurn(turnNum, col+String.valueOf(row));
                }
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

    public String useProbController() {
        // call bad luck then good luck in order
        if (probController.doCommand(0)) { // bad luck has happens
            return "bad";
        }
        else if (probController.doCommand(1)) { // good luck happens
            return "good";
        }
        else { // normal turn happens
            return "";
        }

    }

}
