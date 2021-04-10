package edu.colorado.team20.Player;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;

import java.util.ArrayList;
import java.util.List;

public final class ComputerPlayer extends Player{
    private final List<String> shotStack;
    public ComputerPlayer(Board[] board) {
        super(board);
        placementBehavior = new RandomPlacement(); //a computer initially has an random behavior, both shot and placement
        shotBehavior = new CannonRandomShot(); //initially only has a cannon
        shotStack = new ArrayList<>();
    }

    /* This perform shot is specific to computer player
    * This utilizes the shot stack I made so we can have a semi-smart AI
    * I tried using the turn information hashmap we have, but ran into some problems with it storing
    * random shots as Z-1 and attempted and failed (already hit there) multiple times
    * This is for now, if we wanna change the data structure used for this, totally okay with me
    * Below is a long list of loops, checks, and such to ensure the AI is making the right moves that are informed based on previous shots*/
    public void performShot (Board[] board, char col, int row, int turnNum) {
        this.addToShotStack(board);
        if (shotStack.isEmpty()) {  //if stack is empty, a random shot will happen
            shotBehavior.shot(board, 'Z', -1);
            this.addShotFromTurn(turnNum, col + String.valueOf(row));
        } else { //otherwise, this will pull from the stack and take the shot given using last in, first out
            String knownCords = shotStack.get(0); //will always use first element in stack
            char c = knownCords.charAt(0);
            int r;
            if (knownCords.length() == 2) {
                r = Integer.parseInt(String.valueOf(knownCords.charAt(1)));
            }
            else { //if double digits, i.e 10, then get 10 instead
                char r1 = knownCords.charAt(1);
                char r2 = knownCords.charAt(2);
                String r3 = "" + r1 + r2;
                r = Integer.parseInt(r3);
            }
            boolean isSurrounded = shotBehavior.shot(board, c, r); //shot now returns a boolean, this is checking to see if the latest cord popped
            if (!isSurrounded) {                                   //from the stack has no available spots next to it, if it doesn't, computer will take a random shot
                this.shotStack.remove(knownCords); //removes cords from stack so it no longer attempts to shoot around that shot
                shotBehavior.shot(board, 'Z', -1);
                this.addShotFromTurn(turnNum, col + String.valueOf(row));
            }
            this.removeFromShotStack(board);
            this.addShotFromTurn(turnNum, c + String.valueOf(r));
        }
    }

    /*This function goes through and finds every spot that it needs to add to its stack. It finds the Hs and Ws, gets the position, and then adds that
    * to the stack. If the cord is already in the stack, it will not add it again.*/
    public void addToShotStack(Board[] board) {
        for (int k = 0; k < board.length; k++) {
            for (int i = 0; i < board[k].getColumnSize(); i++) {
                for (int j = 0; j < board[k].getRowSize(); j++) {
                    if (board[k].GetPositionChar((char) ('A' + i), 1 + j) == 'H' || board[k].GetPositionChar((char) ('A' + i), 1 + j) == 'W') {
                        String toAdd = "" + (char) ('A' + i) + (1 + j);
                        if (!shotStack.contains(toAdd)) {
                            shotStack.add(0, toAdd);
                        }
                    }
                }
            }
        }
    }

    /* This function will remove a cord from the stack if necessary. When a ship is destroyed, it removes all cords associated with that ship from the stack.
    * This only removes the cord IF there is no ships below/above that have been hit. If there is a hit above/below, the cord will stay in the stack regardless
    * if it destroyed a ship below.*/
    public void removeFromShotStack(Board[] board) {
        for (int k = 0; k < board.length; k++) {
            for (int i = 0; i < board[k].getColumnSize(); i++) {
                for (int j = 0; j < board[k].getRowSize(); j++) {
                    if (board[k].GetPositionChar((char) ('A' + i), 1 + j) == 'D') {
                        boolean allBoards = true;
                        for (int m = 0; m < board.length; m++) {
                            if (board[m].GetPositionChar((char) ('A' + i), 1 + j) == 'H') {
                                allBoards = false;
                            }
                        }
                        if (allBoards == true) {
                            String toRemove = "" + (char) ('A' + i) + (1 + j);
                            shotStack.remove(toRemove);
                        }
                    }
                }
            }
        }
    }

}
