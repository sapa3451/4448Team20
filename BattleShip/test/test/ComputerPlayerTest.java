package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        IPlayer testComputer = new ComputerPlayer(computerBoard);
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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        IPlayer testComputer = new ComputerPlayer(computerBoard);
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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        IPlayer testComputer = new ComputerPlayer(computerBoard);
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