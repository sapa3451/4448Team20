package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.LinearCoordinates;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShow;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineCoordinates;
import edu.colorado.team20.Player.Interfaces.Behaviors.BombRun;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonBarrage;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.TorpedoShot;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialShotTest {

    @Test
    public void BombRunTest () {
        //test to make sure the laser shot will now mark both boards on input
        ShotBehavior shotBehavior;
        shotBehavior = new BombRun();
        Board playerSurfaceBoard = new Board();

        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();

        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        playerBoards[0].setCreateCoordinatesBehavior(new LinearCoordinates());
        playerBoards[0].SetGamePiecePos(1,1,'A',1,4,3);

        String input = "1" + "\n" + "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shotBehavior.shot(playerBoards, 'Z', -1);
        assertEquals( 'H', playerBoards[0].GetPositionChar('A',1));
    }

    @Test
    public void CannonBarrageTest () {
        //test to make sure the laser shot will now mark both boards on input
        ShotBehavior shotBehavior;
        shotBehavior = new CannonBarrage();
        Board playerSurfaceBoard = new Board();

        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();

        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        playerBoards[0].setCreateCoordinatesBehavior(new LinearCoordinates());
        playerBoards[0].SetGamePiecePos(1,3,'E',1,4,3);

        String input = "E" + "\n" + "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shotBehavior.shot(playerBoards, 'Z', -1);
        assertEquals( 'H', playerBoards[0].GetPositionChar('E',3));
    }

    @Test
    public void TorpedoTest () {
        //test to make sure the laser shot will now mark both boards on input
        ShotBehavior shotBehavior;
        shotBehavior = new TorpedoShot();
        Board playerSurfaceBoard = new Board();

        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setzValue(-1);

        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] playerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};
        playerBoards[0].setCreateCoordinatesBehavior(new LinearCoordinates());
        playerBoards[0].SetGamePiecePos(1,3,'E',1,4,3);

        String input = "E" + "\n" + "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        shotBehavior.shot(playerBoards, 'Z', -1);
        assertEquals( 'D', playerBoards[0].GetPositionChar('E',3));
    }
}
