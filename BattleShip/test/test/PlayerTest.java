package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
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
        Board PlayerBoard = new PlayerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.setShotBehavior(new InputShot());

        testPlayer.performShot(PlayerBoard, 'A', 1, 1);
        assertEquals(PlayerBoard.GetPositionChar('A', 1), 'X');

        assertEquals("A1", testPlayer.getTurnShot(1));
    }

    @Test
    public void MultipleUserShotTest() {

        System.out.println("Running MultipleUserShot Test...........");
        Board playerBoard = new PlayerBoard();
        Board PlayerBoard = new PlayerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.performShot(PlayerBoard, 'A', 1, 1);
        testPlayer.performShot(PlayerBoard, 'A', 2, 1);
        testPlayer.performShot(PlayerBoard, 'A', 10, 1);
        testPlayer.performShot(PlayerBoard, 'C', 1, 1);
        testPlayer.performShot(PlayerBoard, 'J', 4, 1);

        assertEquals(PlayerBoard.GetPositionChar('A', 1), 'X');
        assertEquals(PlayerBoard.GetPositionChar('A', 2), 'X');
        assertEquals(PlayerBoard.GetPositionChar('A', 10), 'X');
        assertEquals(PlayerBoard.GetPositionChar('C', 1), 'X');
        assertEquals(PlayerBoard.GetPositionChar('J', 4), 'X');
    }

    @Test
    public void RandomPlacementTest() {

        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerBoard = new PlayerBoard();
        Player userPlayer = new ComputerPlayer(playerBoard);

        assertEquals(playerBoard, userPlayer.getBoard());

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performPlacement(1, 99);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.performPlacement(1, 4);
        userPlayer.performPlacement(1, 3);
        userPlayer.performPlacement(1, 2);
        int count = 0;
        for (int i = 0; i < userPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < userPlayer.getBoard().getRowSize(); j++) {
                char s = userPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (userPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || userPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);

    }
}