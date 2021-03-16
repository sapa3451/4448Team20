package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlacementTest {
    @Test
    public void SinglePlacementTest() {

        System.out.println("Running SingleInputPlacement Test...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new RandomPlacement(); //Because our placement is the same for random/input, we will use random for testing
        Board playerBoard = new PlayerBoard();
        placementBehavior.place(1, playerBoard, 4, 3);
        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                char s = playerBoard.GetPositionChar((char) ('A' + i), 1 + j);
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(4, count);

    }

    @Test
    public void MultiplePlacementTest() {

        System.out.println("Running MultipleInputPlacement Test...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new RandomPlacement(); //Because our placement is the same for random/input, we will use random for testing
        Board playerBoard = new PlayerBoard();
        placementBehavior.place(1, playerBoard, 4, 3);
        placementBehavior.place(1, playerBoard, 3, 2);
        placementBehavior.place(1, playerBoard, 2, 1);
        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                char s = playerBoard.GetPositionChar((char) ('A' + i), 1 + j);
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);
    }
}
