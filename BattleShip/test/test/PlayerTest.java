package test;

import edu.colorado.team20.Ship;
import edu.colorado.team20.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void PlaceShipTest () {
        Ship testShip1 = new Ship("Destroyer", 4);
        Player player = new Player();
        player.GetShipPlacement(testShip1);
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