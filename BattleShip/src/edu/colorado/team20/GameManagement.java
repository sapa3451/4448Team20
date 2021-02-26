package edu.colorado.team20;

public class GameManagement {
    // provides turn information
    // P --> user player turn
    // C --> computer turn
    private char turnInfo;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
    }

    public String BeginGame() {
        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        return "Welcome to The Battleship Game!"; // why are we returning this?
    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or computer
        turnInfo  = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn(){
        return turnInfo;
    }


    //TODO: Add end game check
}
