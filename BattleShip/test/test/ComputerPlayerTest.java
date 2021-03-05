package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.ComputerBoard;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

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
        assertEquals(5, count);

    }
}