package edu.colorado.team20;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void Show() {
        Board board = new Board();
        // assertEquals( (what expecting), (what function to call) )
    }

    @Test
    void CheckSpot_MarkBoard() {
        // want to check if the check spot function is working
        Board board = new Board();

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

}