package edu.colorado.team20.Player;

import java.util.HashMap;
import java.util.Random;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.GameProbabilities.CreateController;
import edu.colorado.team20.GameProbabilities.GameProbabilitiesController;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

public abstract class Player {
    private final Board[] boards;
    PlacementBehavior placementBehavior;
    protected final HashMap<Integer, String> shotDecisionInfo; // keep track of shots per round
    ShotBehavior shotBehavior;
    GameProbabilitiesController probController = new GameProbabilitiesController(); // player has access to calling probabilities commands

    public Player(Board[] board) {
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
        this.boards = board;

        // create command controller
        CreateController controllerCreator = new CreateController();
        probController = controllerCreator.createController();
    }

    public Board[] getBoards() {
        return boards;
    }

    public void performSurfacePlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() == 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    public void performUnderwaterPlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() < 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    public void performAirPlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() > 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    public void performTurn(Board[] board, char col, int row, int turnNum) {
        if (turnNum <= 2) { // first two rounds doesn't call probController
            shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
            // TODO: is this actually getting the correct col and row??
            this.addShotFromTurn(turnNum, col+String.valueOf(row));
        }
        else {
            // probController calls commands to determine how turn goes
            String outcome = useProbController();

            switch (outcome) {
                case "bad": { // bad luck happens --> lose turn
                    System.out.println("Bad luck has struck! Your ship malfunctioned and you lost a turn!");
                    this.addShotFromTurn(turnNum, "badLuck");
                    break;
                }
                case "good": { // good luck happens -->
                    // TODO: the shots are not getting added into the map correctly --> needs to be fixed
                    shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                    this.addShotFromTurn(turnNum, col+String.valueOf(row));
                    System.out.println("Good luck has struck! You get an extra shot!");
                    shotBehavior.shot(board, 'Z', -1); // using strategy method, this is a behavior (in ShotBehavior)
                    // TODO: when mapping gets fixed, add both shots into one turn for a mapping
                    this.addShotFromTurn(turnNum, col+String.valueOf(row));
                    break;
                }
                default: {
                    shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                    this.addShotFromTurn(turnNum, col+String.valueOf(row));
                }
            }

        }
    }

    public void performSpecialShot(Board[] board, char col, int row){shotBehavior.shot(board, col, row);}

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
