package test;

import edu.colorado.team20.Ship;
import edu.colorado.team20.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void PlaceShipTest () {
        Ship testShip1 = new Ship("Destroyer", 3);
        Ship testShip2 = new Ship("Battleship", 4);
        Player player = new Player();
        String [] testInput1 = {"A","1","HORIZONTALLY"};
        String [] testInput2 = {"B","2","vertically"};
        player.GetShipPlacement(testShip1,testInput1);

        char [] colArray = testShip1.getColumn();
        int [] rowArray = testShip1.getRow();
        assertEquals('A', colArray[0]);
        assertEquals('B', colArray[1]);
        assertEquals('C', colArray[2]);
        assertEquals(1, rowArray[0]);
        assertEquals(1, rowArray[1]);
        assertEquals(1, rowArray[2]);

        player.GetShipPlacement(testShip2,testInput2);
        char [] colArray2 = testShip2.getColumn();
        int [] rowArray2 = testShip2.getRow();
        assertEquals('B', colArray2[0]);
        assertEquals('B', colArray2[1]);
        assertEquals('B', colArray2[2]);
        assertEquals('B', colArray2[3]);
        assertEquals(2, rowArray2[0]);
        assertEquals(3, rowArray2[1]);
        assertEquals(4, rowArray2[2]);
        assertEquals(5, rowArray2[3]);
    }
}