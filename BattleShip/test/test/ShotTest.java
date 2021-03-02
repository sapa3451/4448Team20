package test;

import edu.colorado.team20.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShotTest {

    @Test
    public void SingleUserShotTest(){

        System.out.println("Running SingleUserShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new UserShot();

        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        //Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);

        //IPlayer testPlayer = new UserPlayer(playerBoard);

        shotBehavior.makeShot(computerBoard,'A',1);
        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
    }

    @Test
    public void MultipleUserShotTest(){

        System.out.println("Running MultipleUserShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new UserShot();

        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};
        //Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);

        //IPlayer testPlayer = new UserPlayer(playerBoard);

        shotBehavior.makeShot(computerBoard,'A',1);
        shotBehavior.makeShot(computerBoard, 'A', 2);
        shotBehavior.makeShot(computerBoard, 'A', 10);
        shotBehavior.makeShot(computerBoard, 'C', 1);
        shotBehavior.makeShot(computerBoard, 'J', 4);

        assertEquals(computerBoard.GetPositionChar('A',1), 'X');
        assertEquals(computerBoard.GetPositionChar('A',2), 'X');
        assertEquals(computerBoard.GetPositionChar('A',10), 'X');
        assertEquals(computerBoard.GetPositionChar('C',1), 'X');
        assertEquals(computerBoard.GetPositionChar('J',4), 'X');
    }

    @Test
    public void SingleRandomShot () {

        System.out.println("Running SingleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new RandomShot();

        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};

        Board playerBoard = new PlayerBoard(fleet);
        //Board computerBoard = new ComputerBoard(fleet);
        //IPlayer testComputer = new ComputerPlayer(computerBoard);

        shotBehavior.makeShot(playerBoard, 'Z', -1);

        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(1,count);
    }

    @Test
    public void MultipleRandomShot () {

        System.out.println("Running MultipleRandomShot Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new RandomShot();

        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};

        Board playerBoard = new PlayerBoard(fleet);
        //Board computerBoard = new ComputerBoard(fleet);
        //IPlayer testComputer = new ComputerPlayer(computerBoard);

        shotBehavior.makeShot(playerBoard, 'Z', -1);
        shotBehavior.makeShot(playerBoard, 'Z', -1);
        shotBehavior.makeShot(playerBoard, 'Z', -1);
        shotBehavior.makeShot(playerBoard, 'Z', -1);
        shotBehavior.makeShot(playerBoard, 'Z', -1);

        int count = 0;
        for (int i = 0; i < playerBoard.getColumnSize(); i++) {
            for (int j = 0; j < playerBoard.getRowSize(); j++) {
                if (playerBoard.GetPositionChar((char) ('A' + i), 1 + j) == 'X') {
                    count += 1;
                }
            }
        }
        assertEquals(5,count);

    }

    @Test
    public void SonarBehavior(){

        System.out.println("Running Sonar Test...........");

        ShotBehavior shotBehavior;
        shotBehavior= new SonarShot();

        Ship battleship = new Battleship(4, "battleship");
        Ship destroyer = new Destroyer(3, "destroyer");
        Ship minesweeper = new Minesweeper(2, "minesweeper");
        Ship[] fleet = {battleship, destroyer, minesweeper};

        //Board playerBoard = new PlayerBoard(fleet);
        Board computerBoard = new ComputerBoard(fleet);
        //IPlayer testComputer = new ComputerPlayer(computerBoard);

        shotBehavior.makeShot(computerBoard,'F',7);
    }
}
