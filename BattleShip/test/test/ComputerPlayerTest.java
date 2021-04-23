package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.*;
import edu.colorado.team20.Game.BoardSetFactory;
import edu.colorado.team20.Game.FleetFactory;
import edu.colorado.team20.GamePiece.GamePiece;
import edu.colorado.team20.Player.ComputerPlayer;
import edu.colorado.team20.Player.Interfaces.Behaviors.InputPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.LaserRandomShot;
import edu.colorado.team20.Player.Interfaces.Behaviors.RandomPlacement;
import edu.colorado.team20.Player.Interfaces.Behaviors.CannonRandomShot;
import edu.colorado.team20.Player.Interfaces.PlacementBehavior;
import edu.colorado.team20.Player.Player;
import edu.colorado.team20.Player.UserPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ComputerPlayerTest {

    @Test
    public void SingleRandomShot () {
        //test for a computer shooting a single times to check and make sure board is marked
        System.out.println("Running SingleRandomShot Test...........");

        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerSurfaceBoard};

        Player testComputer = new ComputerPlayer(playerBoards);

        testComputer.performTurn(playerBoards, 'Z', -1, 1);

        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(1,count);
    }

    @Test
    public void MultipleRandomShot () {
        //test for a computer shooting multiple times to check and make sure board is marked
        System.out.println("Running MultipleRandomShot Test...........");

        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerSurfaceBoard};

        Player testComputer = new ComputerPlayer(playerBoards);

        testComputer.setShotBehavior(new CannonRandomShot());

        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);
        testComputer.performTurn(playerBoards, 'Z', -1, 1);


        int count = 0;
        for (int i = 0; i < playerSurfaceBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerSurfaceBoard.getRowSize(); j++) {
                if (playerSurfaceBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5, count);

    }

    @Test
    public void RandomPlacementTest() {
        //Random ship placement test for computer
        System.out.println("Running RandomPlacementTest...........");

        PlacementBehavior placementBehavior;
        placementBehavior = new InputPlacement();
        PlacementBehavior randomPlacementBehavior;
        randomPlacementBehavior = new RandomPlacement();
        Board playerSurfaceBoard = new Board();
         
        playerSurfaceBoard.setShowBehavior(new RegularShow());

        Board playerUnderwaterBoard = new Board();
         
        playerUnderwaterBoard.setShowBehavior(new RegularShow());

        Board playerAirBoard = new Board();
         
        playerAirBoard.setShowBehavior(new RegularShow());

        Board[] playerBoards = new Board[]{playerAirBoard, playerSurfaceBoard, playerUnderwaterBoard};
        Player computerPlayer = new ComputerPlayer(playerBoards);

        computerPlayer.setPlacementBehavior(placementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), placementBehavior);

        computerPlayer.setPlacementBehavior(randomPlacementBehavior);
        assertEquals(computerPlayer.getPlacementBehavior(), randomPlacementBehavior);

        computerPlayer.performSurfacePlacement(1, 4, 3);
        computerPlayer.performSurfacePlacement(1, 3, 2);
        computerPlayer.performSurfacePlacement(1, 2, 1);
        int count = 0;
        for (int i = 0; i < computerPlayer.getBoards()[1].getColumnSize(); i++) {
            for (int j = 0; j < computerPlayer.getBoards()[1].getRowSize(); j++) {
                if (computerPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'S' || computerPlayer.getBoards()[1].GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(9, count);

    }

    @Test
    public void smartAITest() {
        FleetFactory fleetFactory = new FleetFactory();
        String[] inputFleet = {"minesweeper", "destroyer", "battleship", "submarine", "bomber"};//Set standard list of pieces w/ user input
        GamePiece[] playerFleet = fleetFactory.createFleet(inputFleet);

        BoardSetFactory boardSetFactory = new BoardSetFactory();
        int[] standardBoardSet = {1, 0, -1};
        Board[] playerBoards = boardSetFactory.createBoardSet(standardBoardSet);
        Board[] computerBoards = boardSetFactory.createBoardSet(standardBoardSet);

        Player computerPlayer = new ComputerPlayer(computerBoards);
        Player userPlayer = new UserPlayer(playerBoards);

        int idNum = 0;

        userPlayer.setPlacementBehavior(new RandomPlacement());
        for (GamePiece gamePiece : playerFleet) {
            if (gamePiece.canbeUnderwater()) {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() < 0) {
                        board.setCreateCoordinatesBehavior(new SubmarineCoordinates());
                    }
                }
                userPlayer.performUnderwaterPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() < 0) {
                        board.registerShip(gamePiece);
                    }
                }
            } else if (gamePiece.canbeInAir()) {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() > 0) {
                        board.setCreateCoordinatesBehavior(new PlaneCoordinates());
                    }
                }
                userPlayer.performAirPlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() > 0) {
                        board.registerShip(gamePiece);
                    }
                }
            } else {
                gamePiece.setId(idNum);
                idNum++;
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() == 0) {
                        board.setCreateCoordinatesBehavior(new LinearCoordinates());
                    }
                }
                userPlayer.performSurfacePlacement(gamePiece.getId(), gamePiece.getSize(), gamePiece.getQuartersSpotInt());
                for (Board board : userPlayer.getBoards()) {
                    if (board.getzValue() == 0) {
                        board.registerShip(gamePiece);
                    }
                }
            }
        }
        boolean allShipsSunk = false;
        while (!allShipsSunk) {
            boolean firstShipSunk = false;
            for (int i = 0; i < playerBoards[1].getColumnSize(); i++) {
                for (int j = 0; j < playerBoards[1].getRowSize(); j++) {
                    if (playerBoards[1].GetPositionChar((char) ('A' + i), 1 + j) == 'D') {
                        firstShipSunk = true;
                    }
                }
            }
            if (firstShipSunk){
                computerPlayer.setShotBehavior(new LaserRandomShot());
            }
            allShipsSunk = true;
            for (Board boards : playerBoards) {
                for (int i = 0; i < boards.getColumnSize(); i++) {
                    for (int j = 0; j < boards.getRowSize(); j++) {
                        if (boards.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || boards.GetPositionChar((char) ('A' + i), 1 + j) == 'Q' || boards.GetPositionChar((char) ('A' + i), 1 + j) == 'W') {
                            computerPlayer.performTurn(playerBoards, 'Z', -1, 1);
                        }
                    }
                }
            }
            for (Board boards : playerBoards) {
                for (int i = 0; i < boards.getColumnSize(); i++) {
                    for (int j = 0; j < boards.getRowSize(); j++) {
                        if (boards.GetPositionChar((char) ('A' + i), 1 + j) == 'S' || boards.GetPositionChar((char) ('A' + i), 1 + j) == 'Q' || boards.GetPositionChar((char) ('A' + i), 1 + j) == 'H') {
                            allShipsSunk = false;
                        }
                    }
                }
            }
        }
        assertTrue(allShipsSunk);
    }
}