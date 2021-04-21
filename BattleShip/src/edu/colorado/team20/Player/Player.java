package edu.colorado.team20.Player;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.GameProbabilities.CreateController;
import edu.colorado.team20.GameProbabilities.GameProbabilitiesController;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;

/**
 * Description:
 */
public abstract class Player {
    private final Board[] boards;
    protected PlacementBehavior placementBehavior;
    protected ShotBehavior shotBehavior;
    protected final GameProbabilitiesController probController; // player has access to calling probabilities commands

    public Player(Board[] board) {
        this.boards = board;

        // create command controller
        CreateController controllerCreator = new CreateController();
        probController = controllerCreator.createController();
    }

    public Board[] getBoards() {
        return boards;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performSurfacePlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() == 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performUnderwaterPlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() < 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performAirPlacement(int id, int size, int quartersPos) {
        for (Board board : this.boards){
            if (board.getzValue() > 0){
                placementBehavior.place(id, board, size, quartersPos);
            }
        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performTurn(Board[] board, char col, int row, int turnNum) {
        if (turnNum <= 2) { // first two rounds doesn't call probController
            shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
        }
        else {
            // probController calls commands to determine how turn goes
            String outcome = useProbController();

            switch (outcome) {
                case "bad": { // bad luck happens --> lose turn
                    System.out.println("Bad luck has struck! Your ship malfunctioned and you lost a turn!");
                    break;
                }
                case "good": { // good luck happens -->
                    shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                    System.out.println("Good luck has struck! You get an extra shot!");
                    shotBehavior.shot(board, 'Z', -1); // using strategy method, this is a behavior (in ShotBehavior)
                    break;
                }
                default: {
                    shotBehavior.shot(board, col, row); // using strategy method, this is a behavior (in ShotBehavior)
                }
            }

        }
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
    public void performSpecialShot(Board[] board, char col, int row){shotBehavior.shot(board, col, row);}

    public void setPlacementBehavior(PlacementBehavior pb){
        placementBehavior = pb;
    }

    public PlacementBehavior getPlacementBehavior(){
        return placementBehavior;
    }

    public void setShotBehavior(ShotBehavior sb) {
        shotBehavior = sb;
    }

    public ShotBehavior getShotBehavior() {
        return shotBehavior;
    }

    /**
     * Description:
     * Params:
     * Returns:
     */
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
