package test;

import edu.colorado.team20.Ship.Battleship;
import edu.colorado.team20.Ship.Destroyer;
import edu.colorado.team20.Ship.Minesweeper;
import org.junit.jupiter.api.Test;
import sun.security.krb5.internal.crypto.Des;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    public void GetShipInfo() {//test to make sure all ships are initiated properly
        Minesweeper minesweeper = new Minesweeper(2, "minesweeper");

        assertEquals(minesweeper.getSize(), 2);

        assertEquals(minesweeper.getName(), "minesweeper");

        minesweeper.setId(1);
        assertEquals(minesweeper.getId(), 1);


    }

    @Test
    public void GetShipHitInfoBattleShip() { //tests to make sure health is working correctly
        Battleship battleship = new Battleship(4, "battleship");

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
        assertEquals(true, battleship.checkSunk());
    }

    @Test
    public void GetShipHitInfoDestroyer() { //tests to make sure health is working correctly
        Destroyer destroyer = new Destroyer(4, "destroyer");

        assertEquals(5, destroyer.getTotShipHealth());

        assertEquals(2, destroyer.getCaptainQHealth());

        destroyer.update(1);
        assertEquals(4, destroyer.getTotShipHealth());

        destroyer.updateCQ(1);
        assertEquals(3, destroyer.getTotShipHealth());
        assertEquals(1, destroyer.getCaptainQHealth());

        destroyer.updateCQ(1);
        assertEquals(0, destroyer.getTotShipHealth());
        assertEquals(0, destroyer.getCaptainQHealth());
        assertEquals(true, destroyer.checkSunk());
    }
}