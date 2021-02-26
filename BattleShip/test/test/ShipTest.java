package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    public void GetShipNameAndSize () {//test to make sure all ships are initiated properly
        Battleship battleship = new Battleship(4);
        Minesweeper minesweeper = new Minesweeper(2);
        Destroyer destroyer = new Destroyer(3);
        assertEquals(4, battleship.getShipSize());
        for (int i = 0; i < 4; i++) {
            if (i != 2) {
                assertEquals('I', battleship.getShipSections()[i]);
            }
            else {
                assertEquals('Q', battleship.getShipSections()[i]);
            }
        }

        assertEquals(3, destroyer.getShipSize());
        for (int i = 0; i < 3; i++) {
            if (i != 1) {
                assertEquals('I', destroyer.getShipSections()[i]);
            }
            else {
                assertEquals('Q', destroyer.getShipSections()[i]);
            }
        }
        assertEquals(2, minesweeper.getShipSize());
        for (int i = 0; i < 2; i++) {
            if (i != 0) {
                assertEquals('I', minesweeper.getShipSections()[i]);
            }
            else {
                assertEquals('Q', minesweeper.getShipSections()[i]);
            }
        }
    }


}