package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.Mark;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShow;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonInputShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.BombRun;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialShotTest {

    @Test
    public void BombRunTest(){
        //test for a player shooting a single times to check and make sure board is marked

        System.out.println("Running Bomb Run Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new BombRun();

        Board playerSurfaceBoard = new Board();
        playerSurfaceBoard.setMarkBehavior(new Mark());
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setMarkBehavior(new Mark());
        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] computerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};

        shotBehavior.shot(computerBoards,'Z',-1);
    }
}
