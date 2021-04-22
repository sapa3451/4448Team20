package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Game.BoardSetFactory;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("unused")
class PlayerTest {

    @Test
    public void SingleUserShotTest() {
        //test for a player shooting a single times to check and make sure board is marked
        System.out.println("Running SingleUserShot Test...........");

        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testPlayer = new UserPlayer(playerBoards);

        testPlayer.setShotBehavior(new CannonInputShot());

        testPlayer.performTurn(playerBoards, 'A', 1, 1);
        assertEquals(playerBoards[1].GetPositionChar('A', 1), 'X');

    }

    @Test
    public void MultipleUserShotTest() {
        //test for a player shooting multiple times to check and make sure board is marked
        System.out.println("Running MultipleUserShot Test...........");
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testPlayer = new UserPlayer(playerBoards);

        testPlayer.performTurn(playerBoards, 'A', 1, 1);
        testPlayer.performTurn(playerBoards, 'A', 2, 1);
        testPlayer.performTurn(playerBoards, 'A', 10, 1);
        testPlayer.performTurn(playerBoards, 'C', 1, 1);
        testPlayer.performTurn(playerBoards, 'J', 4, 1);

        assertEquals(playerBoards[1].GetPositionChar('A', 1), 'X');
        assertEquals(playerBoards[1].GetPositionChar('A', 2), 'X');
        assertEquals(playerBoards[1].GetPositionChar('A', 10), 'X');
        assertEquals(playerBoards[1].GetPositionChar('C', 1), 'X');
        assertEquals(playerBoards[1].GetPositionChar('J', 4), 'X');
    }

    @Test
    public void SurfacePlacementTest() {
        //Random placement test for player, currently no way to test input, but random should work the same
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

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
        System.out.println("Running UnderwaterPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();

        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet={1,0,-1};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        Player userPlayer = new ComputerPlayer(playerBoards);

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performSurfacePlacement(1, 99, 1);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.getBoards()[2].setCreateCoordinatesBehavior(new SubmarineCoordinates());
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

    @Test
    public void AirPlacementTest() {
        //Random placement test for player, currently no way to test input, but random should work the same
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();

        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet={1,0,-1};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        Player userPlayer = new ComputerPlayer(playerBoards);

        userPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), placementBehavior);
        userPlayer.performSurfacePlacement(1, 99, 1);

        userPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(userPlayer.getPlacementBehavior(), randomPlacementBehavior);

        userPlayer.getBoards()[2].setCreateCoordinatesBehavior(new SubmarineCoordinates());
        userPlayer.performAirPlacement(1, 5, 5);
        userPlayer.performAirPlacement(2, 5, 5);
        userPlayer.performAirPlacement(3, 5, 5);
        int count = 0;
        for (int i = 0; i < userPlayer.getBoards()[0].getColumnSize(); i++) {
            for (int j = 0; j < userPlayer.getBoards()[0].getRowSize(); j++) {
                char s = userPlayer.getBoards()[0].GetPositionChar((char) ('A' + i), 1 + j);
                if (userPlayer.getBoards()[0].GetPositionChar((char) ('A' + i), 1 + j) == 'S' || userPlayer.getBoards()[0].GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(15, count);
    }
    @Test
    public void TestProbController() { // make sure that luck probabilities happens at least once
        // loop through
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};
        Player userPlayer = new UserPlayer(playerBoards);

        userPlayer.setShotBehavior(new CannonInputShot());

        char col = 'A';
        int row = 1;
        for (int i = 1; i < 11; i++) {
            System.out.println("Turn Num: " + i);
            userPlayer.performTurn(playerBoards, col, row, i);
            String input = col +  "\n" +  1 + "\n";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            col++;
            row++;
        }

        userPlayer.setShotBehavior(new CannonRandomShot());

        for (int i = 1; i < 21; i++) {
            System.out.println("Turn Num: " + i);
            userPlayer.performTurn(playerBoards, 'Z', -1, i);
        }

    }
}