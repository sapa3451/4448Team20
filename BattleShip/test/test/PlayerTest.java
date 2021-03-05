package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.ComputerBoard;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.Ship.Battleship;
import edu.colorado.team20.Ship.Destroyer;
import edu.colorado.team20.Ship.Minesweeper;
import edu.colorado.team20.Ship.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void SingleUserShotTest() {

        System.out.println("Running SingleUserShot Test...........");

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.performShot(computerBoard, 'A', 1, 1);
        assertEquals(computerBoard.GetPositionChar('A', 1), 'X');
    }

    @Test
    public void MultipleUserShotTest() {

        System.out.println("Running MultipleUserShot Test...........");
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.performShot(computerBoard, 'A', 1, 1);
        testPlayer.performShot(computerBoard, 'A', 2, 1);
        testPlayer.performShot(computerBoard, 'A', 10, 1);
        testPlayer.performShot(computerBoard, 'C', 1, 1);
        testPlayer.performShot(computerBoard, 'J', 4, 1);

        assertEquals(computerBoard.GetPositionChar('A', 1), 'X');
        assertEquals(computerBoard.GetPositionChar('A', 2), 'X');
        assertEquals(computerBoard.GetPositionChar('A', 10), 'X');
        assertEquals(computerBoard.GetPositionChar('C', 1), 'X');
        assertEquals(computerBoard.GetPositionChar('J', 4), 'X');
    }
}