package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Need to make more test cases to make sure that all
//  cases are hit and that there will not be any missing errors that can
//  arise later when testing the board class


class BoardTest {

    @Test
    void Show() {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard(fleet);
        assertEquals(10, board.getColumnSize());
        assertEquals(10, board.getRowSize());
    }

    @Test
    void CheckSpot_MarkBoard() { // use this test to make sure that player can't hit the same spot twice
        // want to check if the check spot function is working
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard(fleet);

        // need to call MarkBoard to add a placement then check if place can be added again
        board.MarkBoard('A', 1);
        assertFalse(board.CheckSpot('A', 1));

        board.MarkBoard('B', 4);
        assertFalse(board.CheckSpot('B', 4));

        // need to return true since not shot at
        assertTrue(board.CheckSpot('J', 3));
        assertTrue(board.CheckSpot('F', 9));
        assertTrue(board.CheckSpot('D', 6));

        board.MarkBoard('J', 10);
        assertFalse(board.CheckSpot('J', 10));
    }

    @Test
    void CheckIfMarked() { // test to make sure that MarkBoard function is marking board correctly
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard(fleet);

        assertEquals(board.GetPositionChar('A', 1), 'E');
        assertEquals(board.GetPositionChar('D', 9), 'E');
        assertEquals(board.GetPositionChar('C', 4), 'E');

        board.MarkBoard('F', 4);
        assertEquals(board.GetPositionChar('F', 4), 'X');
        board.MarkBoard('A', 2);
        assertEquals(board.GetPositionChar('A', 2), 'X');
        board.MarkBoard('B', 3);
        assertEquals(board.GetPositionChar('B', 3), 'X');
        board.MarkBoard('J', 1);
        assertEquals(board.GetPositionChar('J', 1), 'X');

    }

    @Test
    void SetShipPos1() { // test to make sure that ships get placed correctly
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new PlayerBoard(fleet); // setting column and row
        board.SetShipPos(0, 1,'A',1,4);
        for (int i = 0; i < 2; i++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'S');
        }
        for (int i = 2; i < 3; i ++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'Q');
        }
        for (int i = 3; i < 4; i ++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'S');
        }
        board.Show();
    }

    @Test
    void SetShipPos2() { // test to make sure that ships get placed correctly
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new PlayerBoard(fleet);; // setting column and row
        board.SetShipPos(0,1,'A',0,4);
        for (int i = 0; i < 2; i++) {
            assertEquals(board.GetPositionChar((char) ('A'), 1 + i), 'S');
        }
        for (int i = 2; i < 3; i ++) {
            assertEquals(board.GetPositionChar((char) ('A'), 1 + i), 'Q');
        }
        for (int i = 3; i < 4; i ++) {
            assertEquals(board.GetPositionChar((char) ('A'), 1 + i), 'S');
        }
        board.Show();
    }

    @Test
    void SonarPulse() { // want to check if the area shows the ships correctly
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard(fleet); // setting column and row
        board.SetShipPos(1,1,'A',1,4);
        board.SetShipPos(2,4,'B',1,3);
        board.SetShipPos(3,4,'F',1,2);
        board.ShowSonarPulse('C', 4);

        board.ShowSonarPulse('A', 0);
    }

}