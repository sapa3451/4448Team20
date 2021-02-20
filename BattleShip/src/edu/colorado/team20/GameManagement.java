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

    public boolean CheckSunkShips(Player player) {
        char[] col = player.getMinesweeper().getColumn();
        int[] row = player.getMinesweeper().getRow();
        for (int i = 0; i < col.length; i++) {
            if (col[i] != 'H' && row[i]!= -1) {
                return false;
            }
        }
        char[] col2 = player.getBattleship().getColumn();
        int[] row2 = player.getBattleship().getRow();
        for (int i = 0; i < col2.length; i++) {
            if (col2[i] != 'H' && row2[i]!= -1) {
                return false;
            }
        }
        char[] col3 = player.getDestroyer().getColumn();
        int[] row3 = player.getDestroyer().getRow();
        for (int i = 0; i < col3.length; i++) {
            if (col3[i] != 'H' && row3[i]!= -1) {
                return false;
            }
        }
        return true;
    }

}
