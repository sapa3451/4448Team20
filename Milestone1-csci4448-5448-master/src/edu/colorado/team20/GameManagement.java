package edu.colorado.team20;

public class GameManagement {
    // provides turn information (player always first)
        // P --> user player turn
        // C --> computer turn
    char turnInfo = 'P';

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
