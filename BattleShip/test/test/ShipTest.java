package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    public void GetShipNameAndSize () {//test to make sure all ships are initiated properly
        Battleship battleship = new Battleship(4, "battleship");
        Minesweeper minesweeper = new Minesweeper(2, "minesweeper");
        Destroyer destroyer = new Destroyer(3, "destroyer");
        assertEquals(4, battleship.getNumOccupiedBoardCells());
        for (int i = 0; i < 4; i++) {
            if (i != 2) {
                assertEquals('S', battleship.getShipSections()[i]);
            }
            else {
                assertEquals('Q', battleship.getShipSections()[i]);
            }
        }

        assertEquals(3, destroyer.getNumOccupiedBoardCells());
        for (int i = 0; i < 3; i++) {
            if (i != 1) {
                assertEquals('S', destroyer.getShipSections()[i]);
            }
            else {
                assertEquals('Q', destroyer.getShipSections()[i]);
            }
        }
        assertEquals(2, minesweeper.getNumOccupiedBoardCells());
        for (int i = 0; i < 2; i++) {
            if (i != 0) {
                assertEquals('S', minesweeper.getShipSections()[i]);
            }
            else {
                assertEquals('Q', minesweeper.getShipSections()[i]);
            }
        }
    }


}