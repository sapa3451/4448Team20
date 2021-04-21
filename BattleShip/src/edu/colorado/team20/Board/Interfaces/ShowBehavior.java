package edu.colorado.team20.Board.Interfaces;

import edu.colorado.team20.Board.Board;

public interface ShowBehavior {

/**
 * Description: This uses the Strategy Pattern and has multiple behaviors. Each board needs a specific way to show under certain circumstances, and this
 *              allows an easy use and dynamic switching of behavior at runtime.
 * Params: The board that is going to be shown
 * Returns: None
 */
    void show(Board board);
}
