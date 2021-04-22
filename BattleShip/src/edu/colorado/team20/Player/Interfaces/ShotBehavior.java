package edu.colorado.team20.Player.Interfaces;

import edu.colorado.team20.Board.Board;

/**
 * Description: In interface to implement when a player is supposed to "shoot at" the other players board
 * At current implementation: shots that take in user input are used by UserPlayer and randomized shots are used by CP,
 * cannon shot is the "default" shot, when either player sinks their first ship their behavior is then set to laser shot in managment,
 * and the UserPlayer can choose to a special ability shot twice a game, the specific special shot being decided by which extra ship they chose
 */
public interface ShotBehavior {
    /**
     * Description: a function that marks the board in different shapes depending on the shot type
     * Params: a set of boards at different z elevations to mark, and the column and row coords for dev test shot
     * Returns: if the shot was a hit, or just true
     */
    boolean shot(Board[] board, char col, int row);
}
