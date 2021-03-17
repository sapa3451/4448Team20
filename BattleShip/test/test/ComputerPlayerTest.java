package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.SurfaceBoard;
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

        Board playerSurfaceBoard = new SurfaceBoard();
        Board computerSurfaceBoard = new SurfaceBoard();

        Player testComputer = new ComputerPlayer(playerSurfaceBoard);

        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);

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

        Board playerSurfaceBoard = new SurfaceBoard();
        Board computerSurfaceBoard = new SurfaceBoard();

        Player testComputer = new ComputerPlayer(computerSurfaceBoard);

        testComputer.setShotBehavior(new CannonRandomShot());

        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);
        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);
        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);
        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);
        testComputer.performShot(playerSurfaceBoard, 'Z', -1, 1);

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
        Board computerSurfaceBoard = new SurfaceBoard();
        Player computerPlayer = new ComputerPlayer(computerSurfaceBoard);

        assertEquals(computerSurfaceBoard, computerPlayer.getBoard());

        computerPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), placementBehavior);

        computerPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), randomPlacementBehavior);

        computerPlayer.performPlacement(1, 4, 3);
        computerPlayer.performPlacement(1, 3, 2);
        computerPlayer.performPlacement(1, 2, 1);
        int count = 0;
        for (int i = 0; i < computerPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < computerPlayer.getBoard().getRowSize(); j++) {
                char s = computerPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (computerPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || computerPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);

    }
}