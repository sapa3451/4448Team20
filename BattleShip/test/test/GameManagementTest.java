package test;

import edu.colorado.team20.GameManagement;
import edu.colorado.team20.Player;
import edu.colorado.team20.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameManagementTest {

    @Test
    void TestBeginningGame() { //This checks to make sure the beginning of the game is set up and displayed properly, and turns work
        GameManagement newGame = new GameManagement();
        assertEquals("Welcome to The Battleship Game!", newGame.BeginGameDisplay());
        assertEquals('P', newGame.GetTurn());
        newGame.ChangeTurn();
        assertEquals('C', newGame.GetTurn());
    }

    @Test
    void TestEndGame() { //this test checks to see if all of a player's ships are sunk
        GameManagement newGame = new GameManagement();
        Player player = new Player();

        Ship minesweeper = new Ship("minesweeper", 2);
        Ship destroyer = new Ship("destroyer", 3);
        Ship battleship = new Ship("battleship", 4);

        minesweeper.setColumnAndRow('A', 1, 1);
        destroyer.setColumnAndRow('A', 2, 1);
        battleship.setColumnAndRow('A', 3, 1);

        player.UpdateAndSetPlayerShips(minesweeper);
        player.UpdateAndSetPlayerShips(destroyer);
        player.UpdateAndSetPlayerShips(battleship);

        minesweeper.setHitAndSunk('A', 1);
        minesweeper.setHitAndSunk('B', 1);

        destroyer.setHitAndSunk('A', 2);
        destroyer.setHitAndSunk('B', 2);
        destroyer.setHitAndSunk('C', 2);

        battleship.setHitAndSunk('A', 3);
        battleship.setHitAndSunk('B', 3);
        battleship.setHitAndSunk('C', 3);
        battleship.setHitAndSunk('D', 3);

        player.UpdateAndSetPlayerShips(minesweeper);
        player.UpdateAndSetPlayerShips(destroyer);
        player.UpdateAndSetPlayerShips(battleship);

        boolean result = newGame.CheckSunkShips(player);
        assertEquals(true, result);

    }

}