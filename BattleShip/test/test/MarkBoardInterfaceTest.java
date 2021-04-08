package test;

import edu.colorado.team20.Board.Board;
import edu.colorado.team20.Board.Interfaces.Behaviors.Mark;
import edu.colorado.team20.Board.Interfaces.Behaviors.RegularShow;
import edu.colorado.team20.Board.Interfaces.MarkBehavior;
import edu.colorado.team20.GamePiece.Battleship;
import edu.colorado.team20.GamePiece.Destroyer;
import edu.colorado.team20.GamePiece.Minesweeper;
import edu.colorado.team20.GamePiece.GamePiece;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkBoardInterfaceTest {
    @Test
    void CheckIfMarked() { // test to make sure that MarkBoard function is marking board correctly
        MarkBehavior markBehavior;
        markBehavior = new Mark();
        GamePiece battleship = new Battleship();
        GamePiece destroyer = new Destroyer();
        GamePiece minesweeper = new Minesweeper();
        Board board = new Board();
        board.setMarkBehavior(new Mark());
        board.setShowBehavior(new RegularShow());
        minesweeper.setId(1);
        board.registerShip(minesweeper);
        battleship.setId(2);
        board.registerShip(battleship);
        destroyer.setId(3);
        board.registerShip(destroyer);
        board.SetGamePiecePos(1,4, 'F',1,2, 1);
        assertFalse(board.SetGamePiecePos(3,4, 'E',1,2, 1));
        markBehavior.MarkBoard(board,'F', 4);
        assertEquals('D', board.GetPositionChar('F', 4));
        board.SetGamePiecePos(2,1, 'A',1,4, 2);
        markBehavior.MarkBoard(board,'A', 1);
        assertEquals('H', board.GetPositionChar('A', 1));
    }
}
