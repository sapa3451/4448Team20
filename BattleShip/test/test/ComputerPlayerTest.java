package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.ComputerBoard;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomShot;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {

        System.out.println("Running SingleRandomShot Test...........");

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testComputer = new ComputerPlayer(computerBoard);

        testComputer.performShot(playerBoard, 'Z', -1, 1);

        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(1,count);
    }

    @Test
    public void MultipleRandomShot () {

        System.out.println("Running MultipleRandomShot Test...........");

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testComputer = new ComputerPlayer(computerBoard);

        testComputer.setShotBehavior(new RandomShot());

        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);

        String lastTurn = testComputer.getTurnShot(5);

        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5, count);

    }

    @Test
    public void RandomPlacementTest() {

        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board computerBoard = new ComputerBoard();
        Player computerPlayer = new ComputerPlayer(computerBoard);

        assertEquals(computerBoard, computerPlayer.getBoard());

        computerPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), placementBehavior);

        computerPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), randomPlacementBehavior);

        computerPlayer.performPlacement(1, 4);
        computerPlayer.performPlacement(1, 3);
        computerPlayer.performPlacement(1, 2);
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