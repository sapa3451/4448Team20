package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShipCoordinates;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineShipCoordinates;
import edu.colorado.team20.Board.SurfaceBoard;
import edu.colorado.team20.Board.UnderwaterBoard;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
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

        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] computerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};

        shotBehavior.shot(computerBoards,'A',1,1 );
        assertEquals(computerBoards[0].GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleInputShotTest(){
        //test for a player shooting multiple times to check and make sure board is marked

        System.out.println("Running MultipleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] computerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};

        shotBehavior.shot(computerBoards,'A',1,1);
        shotBehavior.shot(computerBoards, 'A', 2,1);
        shotBehavior.shot(computerBoards, 'A', 10,1);
        shotBehavior.shot(computerBoards, 'C', 1,1);
        shotBehavior.shot(computerBoards, 'J', 4,1);

        assertEquals(computerBoards[0].GetPositionChar('A',1), 'X');
        assertEquals(computerBoards[0].GetPositionChar('A',2), 'X');
        assertEquals(computerBoards[0].GetPositionChar('A',10), 'X');
        assertEquals(computerBoards[0].GetPositionChar('C',1), 'X');
        assertEquals(computerBoards[0].GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {
        //test for a computer shooting a single times to check and make sure board is marked

        System.out.println("Running SingleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};

        shotBehavior.shot(playerBoards, 'Z', -1,1);

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
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};


        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);

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
    public void LaserInputShotTest () {
        //test to make sure the laser shot will now mark both boards on input
        ShotBehavior shotBehavior;
        shotBehavior = new LaserInputShot();
        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        playerBoards[0].setCreateShipCoordinatesBehavior(new RegularShipCoordinates());
        playerBoards[0].SetShipPos(1,2,'A',1,4,3);
        playerBoards[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
        playerBoards[1].SetShipPos(1,2,'A',1,5,5);
        shotBehavior.shot(playerBoards, 'A', 2, 1);
        assertEquals(playerBoards[1].GetPositionChar('A',2), 'H');
        assertEquals(playerBoards[0].GetPositionChar('A',2), 'H');
    }

    @Test
    public void LaserRandomShotTest () {
        //test to make sure the laser shot will now mark both boards on random
        ShotBehavior shotBehavior;
        shotBehavior = new LaserRandomShot();
        Board playerSurfaceBoard = new SurfaceBoard();
        Board playerUnderwaterBoard = new UnderwaterBoard();
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        shotBehavior.shot(playerBoards, 'Z', -1, 1);
        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(3,count);
        count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerUnderwaterBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(3,count);
    }

}
