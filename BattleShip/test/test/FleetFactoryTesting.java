package test;

import edu.colorado.team20.Game.FleetFactory;
import edu.colorado.team20.Game.GameManagement;
import edu.colorado.team20.Game.ShipFactory;
import edu.colorado.team20.Ship.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FleetFactoryTesting {

    @Test
    void ShipCreateTest(){
    //Test just for the shipFactory
        // uses the factory's "createShip" method to make each type of ship
        //include one with Developer input error to demonstrate error handling

        ShipFactory myShipFactory = new ShipFactory();
        Ship myDestroyer = myShipFactory.createShip("Destroyer");
        Ship myMineSweeper = myShipFactory.createShip("minesweeper");
        Ship myBattleship = myShipFactory.createShip("battleship");
        Ship mySub = myShipFactory.createShip("submarine");
        Ship errorShip = myShipFactory.createShip("sub");

        assertEquals(myMineSweeper.getSize(),2);
        assertEquals(myMineSweeper.getName(),"minesweeper");
        assertEquals(myDestroyer.getSize(),3);
        assertEquals(myDestroyer.getName(),"destroyer");
        assertEquals(myBattleship.getSize(),4);
        assertEquals(myBattleship.getName(),"battleship");
        assertEquals(mySub.getSize(),5);
        assertEquals(mySub.getName(),"submarine");

        assertEquals(errorShip.getSize(),2);
        assertEquals(errorShip.getName(),"minesweeper");

    }

    @Test
    void FleetCreateTest(){
        //Test just for the fleetFactory
        // uses the factory's "createFleet" a fleet
        String[] standardFleet={"minesweeper","destroyer","battleship","submarine"};//desired list of ships
        FleetFactory myFleetFactory = new FleetFactory();
        Ship[] myFleet = myFleetFactory.createFleet(standardFleet);

        assertEquals(myFleet.length,4);

        assertEquals(myFleet[0].getSize(),2);
        assertEquals(myFleet[0].getName(),"minesweeper");
        assertEquals(myFleet[1].getSize(),3);
        assertEquals(myFleet[1].getName(),"destroyer");
        assertEquals(myFleet[2].getSize(),4);
        assertEquals(myFleet[2].getName(),"battleship");
        assertEquals(myFleet[3].getSize(),5);
        assertEquals(myFleet[3].getName(),"submarine");
    }
}
