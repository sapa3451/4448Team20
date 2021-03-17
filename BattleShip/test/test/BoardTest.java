package test;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineShipCoordinates;
import edu.colorado.team20.Board.Interfaces.Behaviors.SonarBoardShow;
import edu.colorado.team20.Game.GameManagement;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.UserPlayer;
import edu.colorado.team20.Ship.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: going to need to implement tests for underwater board


class BoardTest {

    @Test
    void Show() {
        Board board = new SurfaceBoard();
        assertEquals(10, board.getColumnSize());
        assertEquals(10, board.getRowSize());
    }

    @Test
    void CheckSpot_MarkBoard() { // use this test to make sure that player can't hit the same spot twice
        Board board = new SurfaceBoard();

        // need to call MarkBoard to add a placement then check if place can be added again
        board.MarkBoard('A', 1);
        assertFalse(board.CheckSpot('A', 1));

        board.MarkBoard('B', 4);
        assertFalse(board.CheckSpot('B', 4));

        // need to return true since not shot at
        assertTrue(board.CheckSpot('J', 3));
        assertTrue(board.CheckSpot('F', 9));
        assertTrue(board.CheckSpot('D', 6));

        board.MarkBoard('J', 10);
        assertFalse(board.CheckSpot('J', 10));

        board.SetShipPos(0,1,'A',0,2, 1);
        board.MarkBoard('A',1);
        board.SetShipPos(0,1,'B',0,3, 2);
        board.MarkBoard('B',2);
        board.MarkBoard('B',2);
    }

    @Test
    void CheckIfMarked() { // test to make sure that MarkBoard function is marking board correctly
        Board board = new SurfaceBoard();

        assertEquals(board.GetPositionChar('A', 1), 'E');
        assertEquals(board.GetPositionChar('D', 9), 'E');
        assertEquals(board.GetPositionChar('C', 4), 'E');

        board.MarkBoard('F', 4);
        assertEquals(board.GetPositionChar('F', 4), 'X');
        board.MarkBoard('A', 2);
        assertEquals(board.GetPositionChar('A', 2), 'X');
        board.MarkBoard('B', 3);
        assertEquals(board.GetPositionChar('B', 3), 'X');
        board.MarkBoard('J', 1);
        assertEquals(board.GetPositionChar('J', 1), 'X');

    }

    @Test
    void SetShipPos1() { // test to make sure that ships get placed correctly
        Board board = new SurfaceBoard(); // setting column and row
        board.SetShipPos(0, 1,'A',1,4, 3);
        for (int i = 0; i < 2; i++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'S');
        }
        for (int i = 2; i < 3; i ++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'Q');
        }
        for (int i = 3; i < 4; i ++) {
            assertEquals(board.GetPositionChar((char) ('A' + i), 1), 'S');
        }
        board.performShow();
    }

    @Test
    void SetShipPos2() { // test to make sure that ships cannot overlap
        Board board = new SurfaceBoard();; // setting column and row
        board.SetShipPos(0,1,'A',0,4, 3);
        assertEquals(false, board.SetShipPos(0,1,'A',0,4, 3));
    }

    @Test
    void SonarPulse() { // want to check if the area shows the ships correctly
        Board board = new SurfaceBoard(); // setting column and row
        board.SetShipPos(1,1,'A',1,4, 3);
        board.SetShipPos(2,4,'B',1,3, 2);
        board.SetShipPos(3,4,'F',1,2, 1);

        board.setShowBehavior(new SonarBoardShow('A', 1));
        board.performShow();
    }

    @Test
    void CaptainsQs() {
        //want to make sure that health goes down
        GameManagement game = new GameManagement();
        Ship Pbattleship = new Battleship(4, "battleship");
        Ship Pdestroyer = new Destroyer(3, "destroyer");
        Ship Pminesweeper = new Minesweeper(2, "minesweeper");

        Ship[] playerFleet = {Pbattleship, Pdestroyer, Pminesweeper};
        Board playerBoard = new SurfaceBoard();

        // place player boards
        // give ships ids and place them
        for (Ship ship : playerFleet) {
            playerBoard.registerShip(ship);
            ship.setId(game.getIdNum());
            game.setIdNum();
            String name = ship.getName();
            switch(name) {
                case "battleship":
                    playerBoard.SetShipPos(1,1,'A',1,4, 3);
                    break;

                case "destroyer":
                    playerBoard.SetShipPos(2,4,'B',1,3, 2);
                    break;

                case "minesweeper":
                    playerBoard.SetShipPos(3,4,'F',1,2, 1);
                    break;

                default:
                    System.out.println("Not found!");
                    break;
            }
        }

        assertEquals("F3", playerBoard.getShipCaptainQPos(3));

        // get the captains quarters of the minesweeper
        String pos = playerBoard.getShipCaptainQPos(3);
        String col = String.valueOf(pos.charAt(0));
        String row = String.valueOf(pos.charAt(1));
        assertEquals(Pminesweeper.getId(), 3);

        // check to see if minesweeper gets destroyed from one hit of captainQ
        Pminesweeper.updateCQ(1);
        playerBoard.removeShip(Pminesweeper.getId());
        playerBoard.updateShipOnHit(Pminesweeper.getId());
        assertEquals(Pminesweeper.getTotShipHealth(), 0);
        String PminesweeperPos = playerBoard.getShipCoordinates(Pminesweeper.getId());
        playerBoard.updateShipChars(PminesweeperPos);
        playerBoard.performShow();

        // testing battleship
        pos = playerBoard.getShipCaptainQPos(1);
        col = String.valueOf(pos.charAt(0));
        row = String.valueOf(pos.charAt(1));
        assertEquals(Pbattleship.getId(), 1);

        // take a shot at captainQ once --> ship should still be alive
        Pbattleship.updateCQ(1);
        assertEquals(Pbattleship.getCaptainQHealth(), 1);
        assertEquals(Pbattleship.getTotShipHealth(), 4); // make sure it still is equal;
        Pbattleship.updateCQ(1);
        assertEquals(Pbattleship.getCaptainQHealth(), 0);
        assertEquals(Pbattleship.getTotShipHealth(), 0); // make sure it still is equal;
        String PbattleshipPos = playerBoard.getShipCoordinates(Pbattleship.getId());
        playerBoard.updateShipChars(PbattleshipPos);
        playerBoard.performShow();

        // testing destroyer
        pos = playerBoard.getShipCaptainQPos(1);
        col = String.valueOf(pos.charAt(0));
        row = String.valueOf(pos.charAt(1));
        assertEquals(Pdestroyer.getId(), 2);

        // take a shot at captainQ once --> ship shuld still be alive
        Pdestroyer.updateCQ(1);
        assertEquals(Pdestroyer.getCaptainQHealth(), 1);
        assertEquals(Pdestroyer.getTotShipHealth(), 3); // make sure it still is equal;
        Pdestroyer.updateCQ(1);
        assertEquals(Pdestroyer.getCaptainQHealth(), 0);
        assertEquals(Pdestroyer.getTotShipHealth(), 0); // make sure it still is equal;
        String PdestroyerPos = playerBoard.getShipCoordinates(Pbattleship.getId());
        playerBoard.updateShipChars(PdestroyerPos);
        playerBoard.performShow();
    }

    // test underwater board
    @Test
    void UnderwaterBoard() {
        // perform the underwater baord show
        Board playerUnderwaterBoard = new UnderwaterBoard(); // create underwater board
        playerUnderwaterBoard.performShow();

        // create submarine
        Ship submarine1 = new Submarine(5, "submarine");
        Ship submarine2 = new Submarine(5, "submarine");
        playerUnderwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        GameManagement game = new GameManagement();
        Ship[] playerFleet = {submarine1, submarine2};
        for (Ship ship : playerFleet) {
            playerUnderwaterBoard.registerShip(ship);
            ship.setId(game.getIdNum());
            game.setIdNum();
        }

        // add submarine to the board
        // TODO: need to check if coordinates will be out of bounds --> since submarine is unique
        playerUnderwaterBoard.SetShipPos(submarine1.getId(), 4, 'B', 0, submarine1.getSize(), submarine1.getQuartersSpotInt());
        playerUnderwaterBoard.performShow();

        // set another submarine
        playerUnderwaterBoard.SetShipPos(submarine2.getId(), 7, 'F', 1, submarine2.getSize(), submarine2.getQuartersSpotInt());
        playerUnderwaterBoard.performShow();
    }

    // test create coordinate behavior
    @Test
    void CreateRegularShipCoordinate() { // test to make sure that ships cannot overlap
        Board board = new SurfaceBoard(); // setting column and row
        assertEquals(false, board.SetShipPos(0,1,'J',1,4, 3));
    }

    // test create coordinate behavior
    @Test
    void CreateSubmarineCoordinates() { // test to make sure that ships cannot overlap
        Board underwaterBoard = new UnderwaterBoard(); // setting column and row
        underwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        Ship submarine1 = new Submarine(5, "submarine");
        Ship submarine2 = new Submarine(5, "submarine");
        Ship submarine3 = new Submarine(5, "submarine");
        Ship submarine4 = new Submarine(5, "submarine");
        Ship submarine5 = new Submarine(5, "submarine");
        Ship submarine6 = new Submarine(5, "submarine");
        Ship submarine7 = new Submarine(5, "submarine");
        Ship submarine8 = new Submarine(5, "submarine");
        Ship submarine9 = new Submarine(5, "submarine");
        Ship submarine10 = new Submarine(5, "submarine");
        Ship submarine11 = new Submarine(5, "submarine");
        Ship submarine12 = new Submarine(5, "submarine");


        GameManagement game = new GameManagement();
        Ship[] playerFleet = {submarine1, submarine2};
        for (Ship ship : playerFleet) {
            underwaterBoard.registerShip(ship);
            ship.setId(game.getIdNum());
            game.setIdNum();
        }

        // place ships outside of board
        assertEquals(false, underwaterBoard.SetShipPos(submarine1.getId(),1,'J',1, submarine1.getSize(), submarine1.getQuartersSpotInt()));
        assertEquals(false, underwaterBoard.SetShipPos(submarine2.getId(), 1,'C',1, submarine2.getSize(), submarine2.getQuartersSpotInt()));
        assertEquals(false, underwaterBoard.SetShipPos(submarine3.getId(), 3,'A',0, submarine3.getSize(), submarine3.getQuartersSpotInt()));
        assertEquals(false, underwaterBoard.SetShipPos(submarine4.getId(), 10,'H',1, submarine4.getSize(), submarine4.getQuartersSpotInt()));
        assertEquals(false, underwaterBoard.SetShipPos(submarine5.getId(), 1,'A',0, submarine5.getSize(), submarine5.getQuartersSpotInt()));
        assertEquals(false, underwaterBoard.SetShipPos(submarine6.getId(), 1,'G',1, submarine6.getSize(), submarine6.getQuartersSpotInt()));



        // place ships inside of board
        assertEquals(true, underwaterBoard.SetShipPos(submarine7.getId(), 2,'C',1, submarine7.getSize(), submarine7.getQuartersSpotInt()));
        assertEquals(true, underwaterBoard.SetShipPos(submarine8.getId(), 10,'G',1, submarine8.getSize(), submarine8.getQuartersSpotInt()));
        assertEquals(true, underwaterBoard.SetShipPos(submarine9.getId(), 4,'A',1, submarine9.getSize(), submarine9.getQuartersSpotInt()));
        assertEquals(true, underwaterBoard.SetShipPos(submarine10.getId(), 5,'J',0, submarine10.getSize(), submarine10.getQuartersSpotInt()));
        assertEquals(true, underwaterBoard.SetShipPos(submarine11.getId(), 6,'D',0, submarine11.getSize(), submarine11.getQuartersSpotInt()));
        assertEquals(true, underwaterBoard.SetShipPos(submarine12.getId(), 10,'A',1, submarine12.getSize(), submarine12.getQuartersSpotInt()));
        underwaterBoard.performShow();
    }

}