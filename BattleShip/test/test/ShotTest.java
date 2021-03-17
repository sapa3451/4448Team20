package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.SurfaceBoard;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserRandomShot;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO: going to need to add tests for laser shot

public class ShotTest {

    @Test
    public void SingleInputShotTest(){
        //test for a player shooting a single times to check and make sure board is marked

        System.out.println("Running SingleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        Board computerSurfaceBoard = new SurfaceBoard();

        shotBehavior.shot(computerSurfaceBoard,'A',1,1 );
        assertEquals(computerSurfaceBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleInputShotTest(){
        //test for a player shooting multiple times to check and make sure board is marked

        System.out.println("Running MultipleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        Board computerSurfaceBoard = new SurfaceBoard();

        shotBehavior.shot(computerSurfaceBoard,'A',1,1);
        shotBehavior.shot(computerSurfaceBoard, 'A', 2,1);
        shotBehavior.shot(computerSurfaceBoard, 'A', 10,1);
        shotBehavior.shot(computerSurfaceBoard, 'C', 1,1);
        shotBehavior.shot(computerSurfaceBoard, 'J', 4,1);

        assertEquals(computerSurfaceBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerSurfaceBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerSurfaceBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerSurfaceBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerSurfaceBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {
        //test for a computer shooting a single times to check and make sure board is marked

        System.out.println("Running SingleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerSurfaceBoard = new SurfaceBoard();

        shotBehavior.shot(playerSurfaceBoard, 'Z', -1,1);

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

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerSurfaceBoard = new SurfaceBoard();


        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);
        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);
        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);
        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);
        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);

        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5,count);

    }

    @Test
    public void LaserShotTest () {
        ShotBehavior shotBehavior;
        shotBehavior = new LaserRandomShot();
        Board playerSurfaceBoard = new SurfaceBoard();
        shotBehavior.shot(playerSurfaceBoard, 'Z', -1, 1);
    }

}
