package test;

import edu.colorado.team20.GameManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameManagementTest {

    @Test
    void TestBeginningGame() { //This checks to make sure the beginning of the game is set up and displayed properly, and turns work
        GameManagement newGame = new GameManagement();
        assertEquals("Welcome to The Battleship Game!", newGame.BeginGame());
        assertEquals('P', newGame.GetTurn());
        newGame.ChangeTurn();
        assertEquals('C', newGame.GetTurn());
    }

}