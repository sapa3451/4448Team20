package edu.colorado.team20;

public class Main {

    public static void main(String[] args) {
        Ship battleship = new Battleship(4);
        Ship destroyer = new Destroyer(3);
        Ship minesweeper = new Minesweeper(2);
        IPlayer player = new UserPlayer();
        IPlayer computer = new ComputerPlayer();
        computer.placeBattleship();
        computer.placeDestroyer();
        computer.placeMinesweeper();
        player.placeBattleship();
        player.placeDestroyer();
        player.placeMinesweeper();
        player.Shot(computer.getBoard(),'Z', -1);
        computer.Shot(player.getBoard(), 'Z', -1);
    }
}