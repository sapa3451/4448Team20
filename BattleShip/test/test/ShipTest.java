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

}