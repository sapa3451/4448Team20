package test;

import edu.colorado.team20.GameManagement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameManagementTest {

    @Test
    void TestBeginningGame() {
        GameManagement newGame = new GameManagement();
        assertEquals("Welcome to The Battleship Game!", newGame.BeginGameDisplay());
        assertEquals('P', newGame.GetTurn());
        newGame.ChangeTurn();
        assertEquals('C', newGame.GetTurn());
    }

}