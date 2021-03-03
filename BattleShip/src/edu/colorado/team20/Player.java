package edu.colorado.team20;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public abstract class Player {
    protected final Board board; // isn't this tight coupling??
    PlacementBehavior placementBehavior;
    protected HashMap<Integer, String> shotDecisionInfo; // keep track of shots per round


    public Player(Board board) {
        this.board = board;
        shotDecisionInfo = new HashMap<>(); //create an empty hashmap
    }

    public Board getBoard () {
        return board;
    }

    public void performPlacement(int id, int size) {
        placementBehavior.place(id, board, size);
    }

    public void setPlacementBehavior(PlacementBehavior pb){
        placementBehavior = pb;
    }

    public void addShotFromTurn(int turnNum, String position) { shotDecisionInfo.put(turnNum, position); }

    public String getTurnShot(int turnNum) { return shotDecisionInfo.get(turnNum); }

    public void checkCaptainsQ(Ship hitShip, Board oppBoard, int turnNum) {

        // get position and char on board
        String position = this.getTurnShot(turnNum);
        char col = position.charAt(0);
        int row = position.charAt(1) - '0';
        char posChar = oppBoard.GetPositionChar(col, row);

        // check if it is captains quarters has been hit
        if (posChar == 'W') { // W means hit captain's quarters
            if (hitShip.updateCaptainQHealth(1)) { // reduce health to captainQ
                String startPos = oppBoard.startPos.get(hitShip.getId());
                oppBoard.updateShipChars(startPos.charAt(0), (startPos.charAt(1) - '0')-1, hitShip.getSize(), startPos.charAt(2) - '0');
            }
        }
        else {
            if (hitShip.updateHealth(1)) { // reduce the damage of the ship
                String startPos = oppBoard.startPos.get(hitShip.getId());
                oppBoard.updateShipChars(startPos.charAt(0), (startPos.charAt(1) - '0')-1, hitShip.getSize(), startPos.charAt(2) - '0');
            }
        }
    }
}
