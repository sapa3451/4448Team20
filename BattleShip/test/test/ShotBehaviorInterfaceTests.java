package test;

import edu.colorado.team20.*;
import edu.colorado.team20.Board;
import edu.colorado.team20.ComputerBoard;
import edu.colorado.team20.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShotBehaviorInterfaceTest {

    @Test
    public void SingleUserShotTest(){

        System.out.println("Running SingleUserShot Test...........");

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.performShot(computerBoard,'A',1,1);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleUserShotTest(){

        System.out.println("Running MultipleUserShot Test...........");
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testPlayer = new UserPlayer(playerBoard);

        testPlayer.performShot(computerBoard,'A',1,1);
        testPlayer.performShot(computerBoard, 'A', 2,1);
        testPlayer.performShot(computerBoard, 'A', 10,1);
        testPlayer.performShot(computerBoard, 'C', 1,1);
        testPlayer.performShot(computerBoard, 'J', 4,1);

        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {

        System.out.println("Running SingleRandomShot Test...........");

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testComputer = new ComputerPlayer(computerBoard);

        testComputer.performShot(playerBoard, 'Z', -1, 1);

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

        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();

        Player testComputer = new ComputerPlayer(computerBoard);

        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);
        testComputer.performShot(playerBoard, 'Z', -1, 1);

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
