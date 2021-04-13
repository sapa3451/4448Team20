package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {
        //test for a computer shooting a single times to check and make sure board is marked
        System.out.println("Running SingleRandomShot Test...........");

        Board playerSurfaceBoard = new Board();
        playerSurfaceBoard.setMarkBehavior(new Mark());
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setMarkBehavior(new Mark());
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
        playerAirBoard.setMarkBehavior(new Mark());
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testComputer = new ComputerPlayer(playerBoards);

        testComputer.performTurn(playerBoards, 'Z', -1, 1);

        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(1,count);
    }

    @Test
    public void MultipleRandomShot () {
        //test for a computer shooting multiple times to check and make sure board is marked
        System.out.println("Running MultipleRandomShot Test...........");

        Board playerSurfaceBoard = new Board();
        playerSurfaceBoard.setMarkBehavior(new Mark());
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setMarkBehavior(new Mark());
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
        playerAirBoard.setMarkBehavior(new Mark());
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        Player testComputer = new ComputerPlayer(playerBoards);

        testComputer.setShotBehavior(new CannonRandomShot());

        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);

        String lastTurn = testComputer.getTurnShot(5);

        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5, count);

    }

    @Test
    public void RandomPlacementTest() {
        //Random ship placement test for computer
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerSurfaceBoard = new Board();
        playerSurfaceBoard.setMarkBehavior(new Mark());
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setMarkBehavior(new Mark());
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
        playerAirBoard.setMarkBehavior(new Mark());
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};
        Player computerPlayer = new ComputerPlayer(playerBoards);

        computerPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), placementBehavior);

        computerPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), randomPlacementBehavior);

        computerPlayer.performSurfacePlacement(1, 4, 3);
        computerPlayer.performSurfacePlacement(1, 3, 2);
        computerPlayer.performSurfacePlacement(1, 2, 1);
        int count = 0;
        for (int i = 0; i < computerPlayer.getBoards()[1].getColumnSize(); i++) {
            for (int j = 0; j < computerPlayer.getBoards()[1].getRowSize(); j++) {
                char s = computerPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j);
                if (computerPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'S' || computerPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);

    }

    @Test
    public void smartAITest() {
        int i = 1;
        int j = 1;
        String toAdd = "" + (char) ('A' + i) + (1 + j);
        System.out.println(toAdd);
    }
}