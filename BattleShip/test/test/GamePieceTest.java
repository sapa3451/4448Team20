package test;

import edu.colorado.team20.GamePiece.Battleship;
import edu.colorado.team20.GamePiece.Destroyer;
import edu.colorado.team20.GamePiece.Submarine;
import edu.colorado.team20.GamePiece.Minesweeper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePieceTest {
    @Test
    public void GetShipInfo() {//test to make sure all ships are initiated properly
        Minesweeper minesweeper = new Minesweeper();

        assertEquals(minesweeper.getSize(), 2);

        assertEquals(minesweeper.getName(), "minesweeper");

        minesweeper.setId(1);
        assertEquals(minesweeper.getId(), 1);

        assertFalse(minesweeper.canbeInAir());


    }

    @Test
    public void GetShipHitInfoBattleShip() { //tests to make sure health is working correctly
        Battleship battleship = new Battleship();

        assertEquals(5, battleship.getTotShipHealth());

        assertEquals(2, battleship.getCaptainQHealth());

        battleship.update(1);
        assertEquals(4, battleship.getTotShipHealth());

        battleship.updateCQ(1);
        assertEquals(3, battleship.getTotShipHealth());
        assertEquals(1, battleship.getCaptainQHealth());

        battleship.updateCQ(1);
        assertEquals(0, battleship.getTotShipHealth());
        assertEquals(0, battleship.getCaptainQHealth());
        assertTrue(battleship.checkSunk());
    }

    @Test
    public void GetShipHitInfoDestroyer() { //tests to make sure health is working correctly
        Destroyer destroyer = new Destroyer();

        assertEquals(4, destroyer.getTotShipHealth());

        assertEquals(2, destroyer.getCaptainQHealth());

        destroyer.update(1);
        assertEquals(3, destroyer.getTotShipHealth());

        destroyer.updateCQ(1);
        assertEquals(2, destroyer.getTotShipHealth());
        assertEquals(1, destroyer.getCaptainQHealth());

        destroyer.updateCQ(1);
        assertEquals(0, destroyer.getTotShipHealth());
        assertEquals(0, destroyer.getCaptainQHealth());
        assertTrue(destroyer.checkSunk());
    }

    @Test
    public void GetShipHitInfoSubmarine() { //tests to make sure health is working correctly
        Submarine submarine= new Submarine();

        assertTrue(submarine.canbeUnderwater());

        assertEquals(6, submarine.getTotShipHealth());

        assertEquals(2, submarine.getCaptainQHealth());

        submarine.update(1);
        assertEquals(5, submarine.getTotShipHealth());

        submarine.updateCQ(1);
        assertEquals(4, submarine.getTotShipHealth());
        assertEquals(1, submarine.getCaptainQHealth());

        submarine.updateCQ(1);
        assertEquals(0, submarine.getTotShipHealth());
        assertEquals(0, submarine.getCaptainQHealth());
        assertTrue(submarine.checkSunk());
    }
}