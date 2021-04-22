package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Game.BoardSetFactory;
import edu.colorado.team20.Game.FleetFactory;
import edu.colorado.team20.GamePiece.GamePiece;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserRandomShot;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ShotTest {

    @Test
    public void SingleInputShotTest(){
        //test for a player shooting a single times to check and make sure board is marked

        System.out.println("Running SingleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();

        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet={1,0,-1};
        Board[] computerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        shotBehavior.shot(computerBoards,'A',1);
        assertEquals(computerBoards[1].GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleInputShotTest(){
        //test for a player shooting multiple times to check and make sure board is marked

        System.out.println("Running MultipleInputShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonInputShot();
        BoardSetFactory boardSetFactory = new BoardSetFactory();

        int[] standardBoardSet={1,0,-1};
        Board[] computerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        shotBehavior.shot(computerBoards,'A',1);
        shotBehavior.shot(computerBoards, 'A', 2);
        shotBehavior.shot(computerBoards, 'A', 10);
        shotBehavior.shot(computerBoards, 'C', 1);
        shotBehavior.shot(computerBoards, 'J', 4);

        assertEquals(computerBoards[1].GetPositionChar('A',1), 'X');
        assertEquals(computerBoards[1].GetPositionChar('A',2), 'X');
        assertEquals(computerBoards[1].GetPositionChar('A',10), 'X');
        assertEquals(computerBoards[1].GetPositionChar('C',1), 'X');
        assertEquals(computerBoards[1].GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {
        //test for a computer shooting a single times to check and make sure board is marked

        System.out.println("Running SingleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new CannonRandomShot();

        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerSurfaceBoard};

        shotBehavior.shot(playerBoards, 'Z', -1);

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

        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet={1,0,-1};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);


        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);

        int count = 0;
        for (int i = 0; i < playerBoards[1].getColumnSize(); i++) {
            for (int j = 0; j < playerBoards[1].getRowSize(); j++) {
                if (playerBoards[1].GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
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
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        playerBoards[0].setCreateCoordinatesBehavior(new LinearCoordinates());
        playerBoards[0].SetGamePiecePos(1,2,'A',1,4,3);
        playerBoards[1].setCreateCoordinatesBehavior(new SubmarineCoordinates());
        playerBoards[1].SetGamePiecePos(1,2,'A',1,5,5);
        shotBehavior.shot(playerBoards, 'A', 2);
        assertEquals(playerBoards[1].GetPositionChar('A',2), 'H');
        assertEquals(playerBoards[0].GetPositionChar('A',2), 'H');
    }

    @Test
    public void LaserRandomShotTest () {
        //test to make sure the laser shot will now mark both boards on random
        ShotBehavior shotBehavior;
        shotBehavior = new LaserRandomShot();
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);
        shotBehavior.shot(playerBoards, 'Z', -1);
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
        for (int i = 0; i < playerUnderwaterBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerUnderwaterBoard.getRowSize(); j++) {
                if (playerUnderwaterBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(3,count);
    }

    @Test
    public void LaserDepthShotTest () {
        //test to make sure the laser only shoots until a depth of 5
        ShotBehavior shotBehavior;
        shotBehavior = new LaserInputShot();

        Board underwaterBoardNotTooDeep = new Board();
         
        underwaterBoardNotTooDeep.setShowBehavior(new RegularShow());// create underwater board

        underwaterBoardNotTooDeep.setzValue(-4);

        Board underwaterBoardTooDeep = new Board();
         
        underwaterBoardTooDeep.setShowBehavior(new RegularShow());// create underwater board

        underwaterBoardTooDeep.setzValue(-5);
        Board[] playerBoards = new Board[]{underwaterBoardTooDeep,underwaterBoardNotTooDeep};
        shotBehavior.shot(playerBoards, 'A', 2);
        underwaterBoardTooDeep.performShow();
        int count = 0;
        for (int i = 0; i < underwaterBoardNotTooDeep.getColumnSize(); i++) {
            for (int j = 0; j < underwaterBoardNotTooDeep.getRowSize(); j++) {
                if (underwaterBoardNotTooDeep.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(1,count);
        count = 0;
        for (int i = 0; i < underwaterBoardTooDeep.getColumnSize(); i++) {
            for (int j = 0; j < underwaterBoardTooDeep.getRowSize(); j++) {
                if (underwaterBoardTooDeep.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(0,count);
        shotBehavior = new LaserRandomShot();
        shotBehavior.shot(playerBoards, 'Z', -1);
        underwaterBoardTooDeep.performShow();
        count = 0;
        for (int i = 0; i < underwaterBoardNotTooDeep.getColumnSize(); i++) {
            for (int j = 0; j < underwaterBoardNotTooDeep.getRowSize(); j++) {
                if (underwaterBoardNotTooDeep.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(2,count);
        count = 0;
        for (int i = 0; i < underwaterBoardTooDeep.getColumnSize(); i++) {
            for (int j = 0; j < underwaterBoardTooDeep.getRowSize(); j++) {
                if (underwaterBoardTooDeep.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(0,count);
    }

    @Test
    public void LaserGoesThroughAllThree () {
        //test to make sure the laser shot will now mark both boards on input
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        String[] standardFleet={"battleship","submarine", "bomber"};//Set standard list of pieces
        FleetFactory fleetFactory = new FleetFactory();
        GamePiece[] playerFleet = fleetFactory.createFleet(standardFleet);
        int idNum = 1;
        for (GamePiece gamePiece : playerFleet) {
            for (int i = 0; i < 3; i++) {
                playerBoards[i].registerShip(gamePiece);
                gamePiece.setId(idNum);
                idNum++;
            }
        }
        ShotBehavior shotBehavior;
        shotBehavior = new LaserInputShot();

        playerBoards[1].setCreateCoordinatesBehavior(new LinearCoordinates());
        playerBoards[1].SetGamePiecePos(playerFleet[0].getId(),5,'C',1,4,3);
        playerBoards[2].setCreateCoordinatesBehavior(new SubmarineCoordinates());
        playerBoards[2].SetGamePiecePos(playerFleet[1].getId(),5,'C',1,5,5);
        playerBoards[0].setCreateCoordinatesBehavior(new PlaneCoordinates());
        playerBoards[0].SetGamePiecePos(playerFleet[2].getId(),4,'C',1,5,0);

        shotBehavior.shot(playerBoards, 'C', 5);

        assertEquals(playerBoards[2].GetPositionChar('C',5), 'H');
        assertEquals(playerBoards[1].GetPositionChar('C',5), 'H');
        assertEquals(playerBoards[0].GetPositionChar('C',5), 'H');

        shotBehavior.shot(playerBoards, 'C', 4);
        assertEquals(playerBoards[0].GetPositionChar('C',5), 'D');
    }

    @Test
    public void CheckAlreadyShotPositions() {
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};

        ShotBehavior shotBehavior = new CannonInputShot();

        // make first shot
        String input = "A" + "\n" + "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shotBehavior.shot(playerBoards, 'Z', -1);

        // need to check if game output saying shot was already made at first position
        String input2 = "A" + "\n" + "1" + "\n" + "C" + "\n" + "3";
        InputStream in2 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in2);

        shotBehavior.shot(playerBoards, 'Z', -1);

        // test laser shot for same spot
        shotBehavior = new LaserInputShot();

        // make first shot
        String input3 = "F" + "\n" + "5";
        InputStream in3 = new ByteArrayInputStream(input3.getBytes());
        System.setIn(in3);

        shotBehavior.shot(playerBoards, 'Z', -1);

        // need to check if game output saying shot was already made at first position
        String input4 = "F" + "\n" + "5" + "\n" + "E" + "\n" + "3";
        InputStream in4 = new ByteArrayInputStream(input4.getBytes());
        System.setIn(in4);

        shotBehavior.shot(playerBoards, 'Z', -1);

    }

}
