package edu.colorado.team20.main;

public class GameManagement {
    // provides turn information
        // P --> user player turn
        // C --> computer turn
    char turnInfo;

    GameManagement() {
        turnInfo = 'P'; // set to player first always
    }

    public void BeginGameDisplay() {
        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
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
