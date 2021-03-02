package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    public void SingleShot () {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.Shot(computerBoard, 'A', 1);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleShot () {
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.Shot(computerBoard, 'A', 1);
        testPlayer.Shot(computerBoard, 'A', 2);
        testPlayer.Shot(computerBoard, 'A', 10);
        testPlayer.Shot(computerBoard, 'C', 1);
        testPlayer.Shot(computerBoard, 'J', 4);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void PlaceBattleshipTest (){
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
<<<<<<< Updated upstream
        IPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeBattleship(battleship.getId());
=======
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeBattleship();
>>>>>>> Stashed changes
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(4,count);
    }
    @Test
    public void PlaceMinesweeperTest (){
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
<<<<<<< Updated upstream
        IPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeMinesweeper(minesweeper.getId());
=======
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeMinesweeper();
>>>>>>> Stashed changes
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(2,count);
    }
    @Test
    public void PlaceDestroyerTest (){
        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
<<<<<<< Updated upstream
        IPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeDestroyer(destroyer.getId());
=======
        UserPlayer testPlayer = new UserPlayer(playerBoard);
        testPlayer.placeDestroyer();
>>>>>>> Stashed changes
        int count = 0;
        for (int i = 0; i < testPlayer.getBoard().getColumnSize(); i++) {
            for (int j = 0; j < testPlayer.getBoard().getRowSize(); j++) {
                char s = testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j);
                if (testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'S' || testPlayer.getBoard().GetPositionChar((char) ('A' + i), 1 + j) == 'Q') {
                    count += 1;
                }
            }
        }
        assertEquals(3,count);
    }
}