package test;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineShipCoordinates;
import edu.colorado.team20.Game.BoardFactory;
import edu.colorado.team20.Game.BoardSetFactory;
import edu.colorado.team20.Game.GameManagement;
import edu.colorado.team20.Ship.Ship;
import edu.colorado.team20.Ship.Submarine;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardFactoryTest {

    @Test
    void BoardFactoryTest(){

        BoardFactory myBoardFactory = new BoardFactory();

    //////////make surface board for testing//////////
        Board upwaterBoard=myBoardFactory.createBoard("surface");
        upwaterBoard.performShow();

        assertEquals(upwaterBoard.GetPositionChar('A', 1), 'E');
        assertEquals(upwaterBoard.GetPositionChar('F', 4), 'E');
        upwaterBoard.performMarkBoard('F', 4);
        assertEquals(upwaterBoard.GetPositionChar('F', 4), 'X');

    //////////make underwater board for testing//////////
        Board downwaterBoard=myBoardFactory.createBoard("underwater");
        downwaterBoard.performShow();

        Ship submarine1 = new Submarine();
        Ship submarine2 = new Submarine();
        downwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        GameManagement game = new GameManagement();
        List<Ship> playerFleet = new ArrayList<Ship>();
        playerFleet.add(submarine1);
        playerFleet.add(submarine2);
        for (Ship ship : playerFleet) {
            downwaterBoard.registerShip(ship);
            ship.setId(game.getIdNum());
            game.setIdNum();
        }

        assertEquals(playerFleet.get(1).getName(), downwaterBoard.getFleet().get(1).getName());

    //////////make input error board (defaults to surface) for testing//////////
        Board errorBoard=myBoardFactory.createBoard("abovewater");
        errorBoard.performShow();
    }

    @Test
    void BoardSetFactoryTest(){

        String[] standardBoardSet={"surface","underwater"};
        BoardSetFactory myBoardSetFactory = new BoardSetFactory();

        Board[] myBoardSet=myBoardSetFactory.createBoardSet(standardBoardSet);

        //////////make surface board for testing//////////
        myBoardSet[0].performShow();

        assertEquals(myBoardSet[0].GetPositionChar('A', 1), 'E');
        assertEquals(myBoardSet[0].GetPositionChar('F', 4), 'E');
        myBoardSet[0].performMarkBoard('F', 4);
        assertEquals(myBoardSet[0].GetPositionChar('F', 4), 'X');

        //////////make underwater board for testing//////////
        myBoardSet[1].performShow();

        Ship submarine1 = new Submarine();
        Ship submarine2 = new Submarine();
        myBoardSet[1].setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        GameManagement game = new GameManagement();
        List<Ship> playerFleet = new ArrayList<Ship>();
        playerFleet.add(submarine1);
        playerFleet.add(submarine2);
        for (Ship ship : playerFleet) {
            myBoardSet[1].registerShip(ship);
            ship.setId(game.getIdNum());
            game.setIdNum();
        }

        assertEquals(playerFleet.get(1).getName(), myBoardSet[1].getFleet().get(1).getName());
    }
}