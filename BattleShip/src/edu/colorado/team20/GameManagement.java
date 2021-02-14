package edu.colorado.team20;

public class GameManagement {
    // provides turn information
        // P --> user player turn
        // C --> computer turn
    char turnInfo;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
    }

    public String BeginGameDisplay() {
        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        return "Welcome to The Battleship Game!";
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

}