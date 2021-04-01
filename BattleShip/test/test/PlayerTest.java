package test;

import edu.colorado.team20.Board.AirBoard;
import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineShipCoordinates;
import edu.colorado.team20.Board.SurfaceBoard;
import edu.colorado.team20.Board.UnderwaterBoard;
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
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board playerAirBoard = new AirBoard();
        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testPlayer = new UserPlayer(playerBoards);

        testPlayer.setShotBehavior(new CannonInputShot());

        testPlayer.performShot(playerBoards, 'A', 1, 1);
        assertEquals(playerBoards[0].GetPositionChar('A', 1), 'X');

        assertEquals("A1", testPlayer.getTurnShot(1));
    }

    @Test
    public void MultipleUserShotTest() {
        //test for a player shooting multiple times to check and make sure board is marked
        System.out.println("Running MultipleUserShot Test...........");
        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board playerAirBoard = new AirBoard();
        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testPlayer = new UserPlayer(playerBoards);

        testPlayer.performShot(playerBoards, 'A', 1, 1);
        testPlayer.performShot(playerBoards, 'A', 2, 1);
        testPlayer.performShot(playerBoards, 'A', 10, 1);
        testPlayer.performShot(playerBoards, 'C', 1, 1);
        testPlayer.performShot(playerBoards, 'J', 4, 1);

        assertEquals(playerBoards[0].GetPositionChar('A', 1), 'X');
        assertEquals(playerBoards[0].GetPositionChar('A', 2), 'X');
        assertEquals(playerBoards[0].GetPositionChar('A', 10), 'X');
        assertEquals(playerBoards[0].GetPositionChar('C', 1), 'X');
        assertEquals(playerBoards[0].GetPositionChar('J', 4), 'X');
    }

    @Test
    public void SurfacePlacementTest() {
        //Random placement test for player, currently no way to test input, but random should work the same
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board playerAirBoard = new AirBoard();
        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};
        Player userPlayer = new ComputerPlayer(playerBoards);

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performSurfacePlacement(1, 99, 1);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.performSurfacePlacement(1, 4, 3);
        userPlayer.performSurfacePlacement(1, 3, 2);
        userPlayer.performSurfacePlacement(1, 2, 1);
        int count = 0;
        for (int i = 0; i < userPlayer.getBoards()[1].getColumnSize(); i++) {
            for (int j = 0; j < userPlayer.getBoards()[1].getRowSize(); j++) {
                char s = userPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j);
                if (userPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'S' || userPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);

    }

    @Test
    public void UnderwaterPlacementTest() {
        //Random placement test for player, currently no way to test input, but random should work the same
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();

        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();

        Board playerAirBoard = new AirBoard();
        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};
        Player userPlayer = new ComputerPlayer(playerBoards);

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performSurfacePlacement(1, 99, 1);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.getBoards()[2].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
        userPlayer.performUnderwaterPlacement(1, 5, 5);
        userPlayer.performUnderwaterPlacement(2, 5, 5);
        userPlayer.performUnderwaterPlacement(3, 5, 5);
        int count = 0;
        for (int i = 0; i < userPlayer.getBoards()[2].getColumnSize(); i++) {
            for (int j = 0; j < userPlayer.getBoards()[2].getRowSize(); j++) {
                char s = userPlayer.getBoards()[2].GetPositionChar((char) ('A' + i), 1 + j);
                if (userPlayer.getBoards()[2].GetPositionChar((char) ('A' + i), 1 + j) == 'S' || userPlayer.getBoards()[2].GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(15, count);

    }
}