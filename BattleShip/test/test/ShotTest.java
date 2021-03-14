package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.ComputerBoard;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TODO: going to need to add tests for laser shot

public class ShotTest {

    @Test
    public void SingleInputShotTest(){

        System.out.println("Running SingleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        Board computerBoard = new ComputerBoard();

        shotBehavior.shot(computerBoard,'A',1,1 );
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleInputShotTest(){

        System.out.println("Running MultipleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        Board computerBoard = new ComputerBoard();

        shotBehavior.shot(computerBoard,'A',1,1);
        shotBehavior.shot(computerBoard, 'A', 2,1);
        shotBehavior.shot(computerBoard, 'A', 10,1);
        shotBehavior.shot(computerBoard, 'C', 1,1);
        shotBehavior.shot(computerBoard, 'J', 4,1);

        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {

        System.out.println("Running SingleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerBoard = new PlayerBoard();

        shotBehavior.shot(playerBoard, 'Z', -1,1);

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

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerBoard = new PlayerBoard();


        shotBehavior.shot(playerBoard, 'Z', -1, 1);
        shotBehavior.shot(playerBoard, 'Z', -1, 1);
        shotBehavior.shot(playerBoard, 'Z', -1, 1);
        shotBehavior.shot(playerBoard, 'Z', -1, 1);
        shotBehavior.shot(playerBoard, 'Z', -1, 1);

        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5,count);

    }

}
