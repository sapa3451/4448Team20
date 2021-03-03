package test;

import edu.colorado.team20.*;
import edu.colorado.team20.Board;
import edu.colorado.team20.ComputerBoard;
import edu.colorado.team20.PlayerBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void SingleShot () {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.performShot(computerBoard, 'A', 1, 0);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleShot () {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.performShot(computerBoard, 'A', 1, 0);
        testPlayer.performShot(computerBoard, 'A', 2, 0);
        testPlayer.performShot(computerBoard, 'A', 10, 0);
        testPlayer.performShot(computerBoard, 'C', 1, 0);
        testPlayer.performShot(computerBoard, 'J', 4, 0);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void PlaceBattleshipTest (){
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.performPlacement(battleship.getId(), battleship.getSize());
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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.performPlacement(minesweeper.getId(), minesweeper.getSize());
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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard();
        Board computerBoard = new ComputerBoard();
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.performPlacement(destroyer.getId(), destroyer.getSize());
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