package test;

import edu.colorado.team20.ComputerPlayer;
import edu.colorado.team20.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {
    @Test
    public void SingleRandomShot () {
        ComputerPlayer CPU = new ComputerPlayer();
        Board cpuBoard = new Board('P');
        String randShot = CPU.RandomShot(cpuBoard);
        char randCol=randShot.charAt(0);
        int randRow=Character.getNumericValue(randShot.charAt(1));

        System.out.println(randCol);
        System.out.println(randRow);
        //to know what position RandomShot is shooting at

     //   assertFalse(cpuBoard.CheckSpot(randCol,randRow));
        assertEquals(cpuBoard.GetPositionChar(randCol, randRow), 'X');

    }
}