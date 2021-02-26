package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void SingleShot () {
        IPlayer testPlayer = new UserPlayer();
        IBoard computerBoard = new ComputerBoard();
        testPlayer.Shot(computerBoard, 'A', 1);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleShot () {
        IPlayer testPlayer = new UserPlayer();
        IBoard computerBoard = new ComputerBoard();
        testPlayer.Shot(computerBoard, 'A', 1);
        testPlayer.Shot(computerBoard, 'A', 2);
        testPlayer.Shot(computerBoard, 'A', 10);
        testPlayer.Shot(computerBoard, 'C', 1);
        testPlayer.Shot(computerBoard, 'J', 4);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void PlaceBattleshipTest (){
        IPlayer testPlayer = new UserPlayer();
        testPlayer.placeBattleship();
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(4,count);
    }
    @Test
    public void PlaceMinesweeperTest (){
        IPlayer testPlayer = new UserPlayer();
        testPlayer.placeMinesweeper();
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(2,count);
    }
    @Test
    public void PlaceDestroyerTest (){
        IPlayer testPlayer = new UserPlayer();
        testPlayer.placeDestroyer();
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(3,count);
    }
}