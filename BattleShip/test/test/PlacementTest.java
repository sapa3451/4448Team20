package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShow;
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
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        placementBehavior.place(1, playerSurfaceBoard, 4, 3);
        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                char s = playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j);
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
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
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        placementBehavior.place(1, playerSurfaceBoard, 4, 3);
        placementBehavior.place(1, playerSurfaceBoard, 3, 2);
        placementBehavior.place(1, playerSurfaceBoard, 2, 1);
        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                char s = playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j);
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);
    }
}
