package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShow;
import edu.colorado.team20.Player.Interfaces.Behaviors.BombRun;
import edu.colorado.team20.Player.Interfaces.ShotBehavior;
import org.junit.jupiter.api.Test;

public class SpecialShotTest {

    @Test
    public void BombRunTest(){
        //test for a player shooting a single times to check and make sure board is marked

        System.out.println("Running Bomb Run Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new BombRun();

        Board playerSurfaceBoard = new Board();
        playerSurfaceBoard.setShowBehavior(new RegularShow());
        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        Board[] computerBoards = new Board[]{playerSurfaceBoard, playerUnderwaterBoard};

        shotBehavior.shot(computerBoards,'A',1);
    }
}
