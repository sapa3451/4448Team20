package edu.colorado.team20;

public abstract class Player {
    protected final Board board;
    PlacementBehavior placementBehavior;
    public Player(Board board) {
        this.board = board;
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

}
