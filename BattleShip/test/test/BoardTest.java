package test;

import edu.colorado.team20.Board;
import edu.colorado.team20.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void Show() {
        Board board = new Board('C');
        // assertEquals( (what expecting), (what function to call) )
    }

    @Test
    void CheckSpot_MarkBoard() { // use this test to make sure that player can't hit the same spot twice
        // want to check if the check spot function is working
        Board board = new Board('C');

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
        Board board = new Board('C');

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
    void SetShipPos() { // test to make sure that ships get placed correctly
        // create Ship object to test with
        Ship ship1 = new Ship("minesweeper",2);
        ship1.setColumnAndRow('A', 1, 0); // setting column and row

        // check to make sure that ships get set on board
        Board board = new Board('C');
        board.SetShipPos(ship1);
        char[] col = ship1.getColumn();
        int[] row = ship1.getRow();

        // set ship on the board
        for (int i = 0; i < col.length; i++) {
            for (int j = 0; j < row.length; j++) {
                assertEquals(board.GetPositionChar(col[i], row[j]), 'S');
            }
        }
        board.Show();

        Ship ship2 = new Ship("destroyer",3);
        ship2.setColumnAndRow('D', 3, 1); // setting column and row

        // check to make sure that ships get set on board
        board.SetShipPos(ship2);
        col = ship2.getColumn();
        row = ship2.getRow();

        // set ship on the board
        for (int i = 0; i < col.length; i++) {
            for (int j = 0; j < row.length; j++) {
                assertEquals(board.GetPositionChar(col[i], row[j]), 'S');
            }
        }
        board.Show();

        Ship ship3 = new Ship("battleship",4);
        ship3.setColumnAndRow('D', 9, 1); // setting column and row

        // check to make sure that ships get set on board
        board.SetShipPos(ship3);
        col = ship3.getColumn();
        row = ship3.getRow();

        // set ship on the board
        for (int i = 0; i < col.length; i++) {
            for (int j = 0; j < row.length; j++) {
                assertEquals(board.GetPositionChar(col[i], row[j]), 'S');
            }
        }
        board.Show();
    }

}