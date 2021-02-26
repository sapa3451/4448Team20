package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {
        IPlayer testComputer = new ComputerPlayer();
        IBoard computerBoard = new ComputerBoard();
        IBoard playerBoard = new PlayerBoard();
        testComputer.Shot(playerBoard, 'Z', -1);
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
        IPlayer testComputer = new ComputerPlayer();
        IBoard computerBoard = new ComputerBoard();
        IBoard playerBoard = new PlayerBoard();
        testComputer.Shot(playerBoard, 'Z', -1);
        testComputer.Shot(playerBoard, 'Z', -1);
        testComputer.Shot(playerBoard, 'Z', -1);
        testComputer.Shot(playerBoard, 'Z', -1);
        testComputer.Shot(playerBoard, 'Z', -1);
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

    @Test
    public void RandPlaceShipTest (){
        IPlayer testComputer = new ComputerPlayer();
        testComputer.placeBattleship();
        int count = 0;
        for (int i = 0; i < testComputer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testComputer.getBoard().getRowSize(); j++) {
                char s = testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(4,count);
        testComputer.placeDestroyer();
        count = 0;
        for (int i = 0; i < testComputer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testComputer.getBoard().getRowSize(); j++) {
                char s = testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(7,count);
        testComputer.placeMinesweeper();
        count = 0;
        for (int i = 0; i < testComputer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testComputer.getBoard().getRowSize(); j++) {
                char s = testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testComputer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9,count);
    }

}