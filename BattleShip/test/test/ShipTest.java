package test;

import edu.colorado.team20.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    public void GetShipNameAndSize () {
        Ship testShip1 = new Ship("Destroyer", 4);
        assertEquals("Destroyer", testShip1.getShipName());
        assertEquals(4, testShip1.getShipSize());
    }

    @Test
    public void GetPlacementTest () {
        //direction 1 is horizontal, 0 is vertical
        Ship testShip1 = new Ship("Destroyer", 3);
        testShip1.setColumnAndRow('A', 5, 1);
        char[] colArray = testShip1.getColumn();
        int[] rowArray = testShip1.getRow();
        assertEquals('A', colArray[0]);
        assertEquals('B', colArray[1]);
        assertEquals('C', colArray[2]);
        assertEquals(5, rowArray[0]);
        assertEquals(5, rowArray[1]);
        assertEquals(5, rowArray[2]);
        Ship testShip2 = new Ship("Minesweeper", 2);
        testShip2.setColumnAndRow('J', 1, 0);
        colArray = testShip2.getColumn();
        rowArray = testShip2.getRow();
        assertEquals('J', colArray[0]);
        assertEquals('J', colArray[1]);
        assertEquals(1, rowArray[0]);
        assertEquals(2, rowArray[1]);

    }

    @Test
    public void GetHitTest () {
        Ship testShip1 = new Ship("Destroyer", 3);
        testShip1.setColumnAndRow('A', 1, 1);
        testShip1.setHitAndSunk('A', 1);
        assertEquals(false, testShip1.getSunk());
        testShip1.setHitAndSunk('B', 1);
        testShip1.setHitAndSunk('C', 1);
        char[] colArray = testShip1.getColumn();
        int[] rowArray = testShip1.getRow();
        assertEquals('H', colArray[0]);
        assertEquals('H', colArray[1]);
        assertEquals('H', colArray[2]);
        assertEquals(-1, rowArray[0]);
        assertEquals(-1, rowArray[1]);
        assertEquals(-1, rowArray[2]);
        assertEquals(true, testShip1.getSunk());
    }

}