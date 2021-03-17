package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.SurfaceBoard;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    public void SingleUserShotTest() {
        //test for a player shooting a single times to check and make sure board is marked
        System.out.println("Running SingleUserShot Test...........");

        Board playerSurfaceBoard = new SurfaceBoard();

        Player testPlayer = new UserPlayer(playerSurfaceBoard);

        testPlayer.setShotBehavior(new CannonInputShot());

        testPlayer.performShot(playerSurfaceBoard, 'A', 1, 1);
        assertEquals(playerSurfaceBoard.GetPositionChar('A', 1), 'X');

        assertEquals("A1", testPlayer.getTurnShot(1));
    }

    @Test
    public void MultipleUserShotTest() {
        //test for a player shooting multiple times to check and make sure board is marked
        System.out.println("Running MultipleUserShot Test...........");
        Board PlayerSurfaceBoard = new SurfaceBoard();

        Player testPlayer = new UserPlayer(PlayerSurfaceBoard);

        testPlayer.performShot(PlayerSurfaceBoard, 'A', 1, 1);
        testPlayer.performShot(PlayerSurfaceBoard, 'A', 2, 1);
        testPlayer.performShot(PlayerSurfaceBoard, 'A', 10, 1);
        testPlayer.performShot(PlayerSurfaceBoard, 'C', 1, 1);
        testPlayer.performShot(PlayerSurfaceBoard, 'J', 4, 1);

        assertEquals(PlayerSurfaceBoard.GetPositionChar('A', 1), 'X');
        assertEquals(PlayerSurfaceBoard.GetPositionChar('A', 2), 'X');
        assertEquals(PlayerSurfaceBoard.GetPositionChar('A', 10), 'X');
        assertEquals(PlayerSurfaceBoard.GetPositionChar('C', 1), 'X');
        assertEquals(PlayerSurfaceBoard.GetPositionChar('J', 4), 'X');
    }

    @Test
    public void RandomPlacementTest() {
        //Random placement test for player, currently no way to test input, but random should work the same
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerSurfaceBoard = new SurfaceBoard();
        Player userPlayer = new ComputerPlayer(playerSurfaceBoard);

        assertEquals(playerSurfaceBoard, userPlayer.getBoard());

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performPlacement(1, 99, 1);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.performPlacement(1, 4, 3);
        userPlayer.performPlacement(1, 3, 2);
        userPlayer.performPlacement(1, 2, 1);
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