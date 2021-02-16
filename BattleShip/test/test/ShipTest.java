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
        char [] colArray = testShip1.getColumn();
        int [] rowArray = testShip1.getRow();
        assertEquals('A', colArray[0]);
        assertEquals('B', colArray[1]);
        assertEquals('C', colArray[2]);
        assertEquals(5, rowArray[0]);
        assertEquals(5, rowArray[1]);
        assertEquals(5, rowArray[2]);
    }

}