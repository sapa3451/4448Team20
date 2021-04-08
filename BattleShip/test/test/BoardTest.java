package test;

import edu.colorado.team20.Board.*;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Game.GameManagement;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.GamePiece.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: going to need to implement tests for underwater board


class BoardTest {

    @Test
    void ShowAndGetters() {
        PlacementBehavior placementBehavior;
        placementBehavior = new RandomPlacement();

        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow());

        placementBehavior.place(1, board, 4, 3);
        assertEquals(10, board.getColumnSize());
        assertEquals(10, board.getRowSize());
        HashMap<Integer, String> result = board.getCoordinatesMapping();
        assertEquals(board.getCoordinatesMapping(), result);
    }

    @Test
    void CheckSpot_MarkBoard() { // use this test to make sure that player can't hit the same spot twice
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow());

        // need to call MarkBoard to add a placement then check if place can be added again
        board.performMarkBoard('A', 1);
        assertFalse(board.CheckSpot('A', 1));

        board.performMarkBoard('B', 4);
        assertFalse(board.CheckSpot('B', 4));

        // need to return true since not shot at
        assertTrue(board.CheckSpot('J', 3));
        assertTrue(board.CheckSpot('F', 9));
        assertTrue(board.CheckSpot('D', 6));

        board.performMarkBoard('J', 10);
        assertFalse(board.CheckSpot('J', 10));

        board.SetGamePiecePos(0,1,'A',0,2, 1);
        board.performMarkBoard('A',1);
        board.SetGamePiecePos(0,1,'B',0,3, 2);
        board.performMarkBoard('B',2);
        board.performMarkBoard('B',2);
    }

    @Test
    void CheckIfMarked() { // test to make sure that MarkBoard function is marking board correctly
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow());

        assertEquals(board.GetPositionChar('A', 1), 'E');
        assertEquals(board.GetPositionChar('D', 9), 'E');
        assertEquals(board.GetPositionChar('C', 4), 'E');

        board.performMarkBoard('F', 4);
        assertEquals(board.GetPositionChar('F', 4), 'X');
        board.performMarkBoard('A', 2);
        assertEquals(board.GetPositionChar('A', 2), 'X');
        board.performMarkBoard('B', 3);
        assertEquals(board.GetPositionChar('B', 3), 'X');
        board.performMarkBoard('J', 1);
        assertEquals(board.GetPositionChar('J', 1), 'X');

    }

    @Test
    void SetShipPos1() { // test to make sure that ships get placed correctly
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow()); // setting column and row

        board.SetGamePiecePos(0, 1,'A',1,4, 3);
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
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow()); // setting column and row

        board.SetGamePiecePos(0,1,'A',0,4, 3);
        assertFalse(board.SetGamePiecePos(0, 1, 'A', 0, 4, 3));
    }

    @Test
    void SonarPulse() { // want to check if the area shows the ships correctly
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow()); // setting column and row

        board.SetGamePiecePos(1,1,'A',1,4, 3);
        board.SetGamePiecePos(2,4,'B',1,3, 2);
        board.SetGamePiecePos(3,4,'F',1,2, 1);

        board.setShowBehavior(new SonarBoardShow('A', 1));
        board.performShow();
    }

    @Test
    void CaptainsQs() {
        //want to make sure that health goes down
        GameManagement game = new GameManagement();
        GamePiece pbattleship = new Battleship();
        GamePiece Pdestroyer = new Destroyer();
        GamePiece Pminesweeper = new Minesweeper();

        GamePiece[] playerFleet = {pbattleship, Pdestroyer, Pminesweeper};
        Board playerBoard = new Board();
        playerBoard.setMarkBehavior(new Mark());
        playerBoard.setShowBehavior(new RegularShow());

        // place player boards
        // give ships ids and place them
        for (GamePiece gamePiece : playerFleet) {
            playerBoard.registerShip(gamePiece);
            gamePiece.setId(game.getIdNum());
            game.setIdNum();
            String name = gamePiece.getName();
            switch(name) {
                case "battleship":
                    playerBoard.SetGamePiecePos(1,1,'A',1,4, 3);
                    break;

                case "destroyer":
                    playerBoard.SetGamePiecePos(2,4,'B',1,3, 2);
                    break;

                case "minesweeper":
                    playerBoard.SetGamePiecePos(3,4,'F',1,2, 1);
                    break;

                default:
                    System.out.println("Not found!");
                    break;
            }
        }

        assertEquals("F3", playerBoard.getGamePieceCaptainQPos(3));

        // get the captains quarters of the minesweeper
        String pos = playerBoard.getGamePieceCaptainQPos(3);
        String col = String.valueOf(pos.charAt(0));
        String row = String.valueOf(pos.charAt(1));
        assertEquals(Pminesweeper.getId(), 3);

        // check to see if minesweeper gets destroyed from one hit of captainQ
        Pminesweeper.updateCQ(1);
        playerBoard.removeShip(Pminesweeper.getId());
        playerBoard.updateGamePieceOnHit(Pminesweeper.getId());
        assertEquals(Pminesweeper.getTotShipHealth(), 0);
        String PminesweeperPos = playerBoard.getGamePieceCoordinates(Pminesweeper.getId());
        playerBoard.updateGamePieceChars(PminesweeperPos);
        playerBoard.performShow();

        // testing battleship
        pos = playerBoard.getGamePieceCaptainQPos(1);
        assertEquals(pbattleship.getId(), 1);

        // take a shot at captainQ once --> ship should still be alive
        pbattleship.updateCQ(1);
        assertEquals(pbattleship.getCaptainQHealth(), 1);
        assertEquals(pbattleship.getTotShipHealth(), 4); // make sure it still is equal;
        pbattleship.updateCQ(1);
        assertEquals(pbattleship.getCaptainQHealth(), 0);
        assertEquals(pbattleship.getTotShipHealth(), 0); // make sure it still is equal;
        String PbattleshipPos = playerBoard.getGamePieceCoordinates(pbattleship.getId());
        playerBoard.updateGamePieceChars(PbattleshipPos);
        playerBoard.performShow();

        // testing destroyer
        pos = playerBoard.getGamePieceCaptainQPos(1);
        assertEquals(Pdestroyer.getId(), 2);

        // take a shot at captainQ once --> ship shuld still be alive
        Pdestroyer.updateCQ(1);
        assertEquals(Pdestroyer.getCaptainQHealth(), 1);
        assertEquals(Pdestroyer.getTotShipHealth(), 3); // make sure it still is equal;
        Pdestroyer.updateCQ(1);
        assertEquals(Pdestroyer.getCaptainQHealth(), 0);
        assertEquals(Pdestroyer.getTotShipHealth(), 0); // make sure it still is equal;
        String PdestroyerPos = playerBoard.getGamePieceCoordinates(pbattleship.getId());
        playerBoard.updateGamePieceChars(PdestroyerPos);
        playerBoard.performShow();
    }

    // test underwater board
    @Test
    void UnderwaterBoard() {
        // perform the underwater baord show
        Board playerUnderwaterBoard = new Board();
        playerUnderwaterBoard.setMarkBehavior(new Mark());
        playerUnderwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        playerUnderwaterBoard.performShow();

        // create submarine
        GamePiece submarine1 = new Submarine();
        GamePiece submarine2 = new Submarine();
        playerUnderwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        GameManagement game = new GameManagement();
        List<GamePiece> playerFleet = new ArrayList<>();
        playerFleet.add(submarine1);
        playerFleet.add(submarine2);
        for (GamePiece gamePiece : playerFleet) {
            playerUnderwaterBoard.registerShip(gamePiece);
            gamePiece.setId(game.getIdNum());
            game.setIdNum();
        }

        assertEquals(playerFleet.get(1).getName(), playerUnderwaterBoard.getFleet().get(1).getName());

        // add submarine to the board
        playerUnderwaterBoard.SetGamePiecePos(submarine1.getId(), 4, 'B', 0, submarine1.getSize(), submarine1.getQuartersSpotInt());
        playerUnderwaterBoard.performShow();

        // set another submarine
        playerUnderwaterBoard.SetGamePiecePos(submarine2.getId(), 7, 'F', 1, submarine2.getSize(), submarine2.getQuartersSpotInt());
        playerUnderwaterBoard.performShow();

        playerUnderwaterBoard.performMarkBoard('I', 7);
    }

    // test create coordinate behavior
    @Test
    void CreateRegularShipCoordinate() { // test to make sure that ships cannot overlap
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow()); // setting column and row
        assertFalse(board.SetGamePiecePos(0, 1, 'J', 1, 4, 3));
    }

    @Test
    void HiddenBoardTest() {
        //test to make sure our hidden boards do not show ships
        PlacementBehavior placementBehavior;
        placementBehavior = new RandomPlacement();
        Board playerBoard = new Board();
        playerBoard.setMarkBehavior(new Mark());
        playerBoard.setShowBehavior(new RegularShow());
        placementBehavior.place(1, playerBoard, 4, 3);
        playerBoard.setShowBehavior(new HiddenShow());
        playerBoard.performShow();

        Board underwaterBoard = new Board();
        underwaterBoard.setMarkBehavior(new Mark());
        underwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        underwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());
        placementBehavior.place(1, underwaterBoard, 5, 5);
        underwaterBoard.setShowBehavior(new HiddenShow());
        underwaterBoard.performShow();
    }

    // test create coordinate behavior
    @Test
    void CreateSubmarineCoordinates() { // test to make sure that ships cannot overlap
        Board underwaterBoard = new Board();
        underwaterBoard.setMarkBehavior(new Mark());
        underwaterBoard.setShowBehavior(new RegularShow());// create underwater board
        underwaterBoard.setCreateShipCoordinatesBehavior(new SubmarineShipCoordinates());

        GamePiece submarine1 = new Submarine();
        GamePiece submarine2 = new Submarine();
        GamePiece submarine3 = new Submarine();
        GamePiece submarine4 = new Submarine();
        GamePiece submarine5 = new Submarine();
        GamePiece submarine6 = new Submarine();
        GamePiece submarine7 = new Submarine();
        GamePiece submarine8 = new Submarine();
        GamePiece submarine9 = new Submarine();
        GamePiece submarine10 = new Submarine();
        GamePiece submarine11 = new Submarine();
        GamePiece submarine12 = new Submarine();


        GameManagement game = new GameManagement();
        GamePiece[] playerFleet = {submarine1, submarine2};
        for (GamePiece gamePiece : playerFleet) {
            underwaterBoard.registerShip(gamePiece);
            gamePiece.setId(game.getIdNum());
            game.setIdNum();
        }

        // place ships outside of board
        assertFalse(underwaterBoard.SetGamePiecePos(submarine1.getId(), 1, 'J', 1, submarine1.getSize(), submarine1.getQuartersSpotInt()));
        assertFalse(underwaterBoard.SetGamePiecePos(submarine2.getId(), 1, 'C', 1, submarine2.getSize(), submarine2.getQuartersSpotInt()));
        assertFalse(underwaterBoard.SetGamePiecePos(submarine3.getId(), 3, 'A', 0, submarine3.getSize(), submarine3.getQuartersSpotInt()));
        assertFalse(underwaterBoard.SetGamePiecePos(submarine4.getId(), 10, 'H', 1, submarine4.getSize(), submarine4.getQuartersSpotInt()));
        assertFalse(underwaterBoard.SetGamePiecePos(submarine5.getId(), 1, 'A', 0, submarine5.getSize(), submarine5.getQuartersSpotInt()));
        assertFalse(underwaterBoard.SetGamePiecePos(submarine6.getId(), 1, 'G', 1, submarine6.getSize(), submarine6.getQuartersSpotInt()));



        // place ships inside of board
        assertTrue(underwaterBoard.SetGamePiecePos(submarine7.getId(), 2, 'C', 1, submarine7.getSize(), submarine7.getQuartersSpotInt()));
        assertTrue(underwaterBoard.SetGamePiecePos(submarine8.getId(), 10, 'G', 1, submarine8.getSize(), submarine8.getQuartersSpotInt()));
        assertTrue(underwaterBoard.SetGamePiecePos(submarine9.getId(), 4, 'A', 1, submarine9.getSize(), submarine9.getQuartersSpotInt()));
        assertTrue(underwaterBoard.SetGamePiecePos(submarine10.getId(), 5, 'J', 0, submarine10.getSize(), submarine10.getQuartersSpotInt()));
        assertTrue(underwaterBoard.SetGamePiecePos(submarine11.getId(), 6, 'D', 0, submarine11.getSize(), submarine11.getQuartersSpotInt()));
        assertTrue(underwaterBoard.SetGamePiecePos(submarine12.getId(), 10, 'A', 1, submarine12.getSize(), submarine12.getQuartersSpotInt()));
        underwaterBoard.performShow();
    }

    // create test for Air board
    @Test
    void AirBoard() {
        // create Boards
        Board playerAirBoard = new Board();
        playerAirBoard.setMarkBehavior(new Mark());
        playerAirBoard.setShowBehavior(new RegularShow());
        playerAirBoard.performShow();

        // add plane to Air board
        GamePiece bomber = new Bomber();
        bomber.setId(1);
        playerAirBoard.setCreateShipCoordinatesBehavior(new PlaneShipCoordinates());
        playerAirBoard.SetGamePiecePos(bomber.getId(), 3, 'C', 0, bomber.getSize(), bomber.getQuartersSpotInt());
        playerAirBoard.performShow();

        //TODO: create more tests to check plane stays in bounds

    }

}