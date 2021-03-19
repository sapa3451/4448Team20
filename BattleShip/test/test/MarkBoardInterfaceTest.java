package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.SurfaceMark;
import edu.colorado.team20.Board.Interfaces.MarkBehavior;
import edu.colorado.team20.Board.SurfaceBoard;
import edu.colorado.team20.Ship.Battleship;
import edu.colorado.team20.Ship.Destroyer;
import edu.colorado.team20.Ship.Minesweeper;
import edu.colorado.team20.Ship.Ship;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkBoardInterfaceTest {
    @Test
    void CheckIfMarked() { // test to make sure that MarkBoard function is marking board correctly
        MarkBehavior markBehavior;
        markBehavior = new SurfaceMark();
        Ship battleship = new Battleship();
        Ship destroyer = new Destroyer();
        Ship minesweeper = new Minesweeper();
        Board board = new SurfaceBoard();
        minesweeper.setId(1);
        board.registerShip(minesweeper);
        battleship.setId(2);
        board.registerShip(battleship);
        destroyer.setId(3);
        board.registerShip(destroyer);
        board.SetShipPos(1,4, 'F',1,2, 1);
        assertFalse(board.SetShipPos(3,4, 'E',1,2, 1));
        markBehavior.MarkBoard(board,'F', 4);
        assertEquals('D', board.GetPositionChar('F', 4));
        board.SetShipPos(2,1, 'A',1,4, 2);
        markBehavior.MarkBoard(board,'A', 1);
        assertEquals('H', board.GetPositionChar('A', 1));
    }
}