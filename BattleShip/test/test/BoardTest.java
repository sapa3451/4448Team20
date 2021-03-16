package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.ComputerBoard;
import edu.colorado.team20.Board.Interfaces.Behaviors.SubmarineShipCoordinates;
import edu.colorado.team20.Board.PlayerBoard;
import edu.colorado.team20.Board.Interfaces.Behaviors.SonarBoardShow;
import edu.colorado.team20.Board.UnderwaterBoard;
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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard();
        assertEquals(10, board.getColumnSize());
        assertEquals(10, board.getRowSize());
    }

    @Test
    void CheckSpot_MarkBoard() { // use this test to make sure that player can't hit the same spot twice
        Board board = new ComputerBoard();

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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard();

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
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new PlayerBoard(); // setting column and row
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
        Board board = new PlayerBoard();; // setting column and row
        board.SetShipPos(0,1,'A',0,4, 3);
        assertEquals(false, board.SetShipPos(0,1,'A',0,4, 3));
    }

    @Test
    void SonarPulse() { // want to check if the area shows the ships correctly
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board board = new ComputerBoard(); // setting column and row
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
        Board playerBoard = new PlayerBoard();

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

}