package edu.colorado.team20;

public class GameManagement {
    // provides turn information
    // P --> user player turn
    // C --> computer turn
    private char turnInfo;
    int idNum = 1;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
    }

    public void BeginGame() {
        Ship Pbattleship = new Battleship(4, "battleship");
        Ship Pdestroyer = new Destroyer(3, "destroyer");
        Ship Pminesweeper = new Minesweeper(2, "minesweeper");
        Ship Cbattleship = new Battleship(4, "battleship");
        Ship Cdestroyer = new Destroyer(3, "destroyer");
        Ship Cminesweeper = new Minesweeper(2, "minesweeper");

        Ship[] playerFleet = {Pbattleship, Pdestroyer, Pminesweeper};
        Ship[] compFleet = {Cbattleship, Cdestroyer, Cminesweeper};

        PlayerBoard playerBoard = new PlayerBoard(playerFleet);
        ComputerBoard computerBoard = new ComputerBoard(compFleet);


        UserPlayer player = new UserPlayer(playerBoard);
        ComputerPlayer computer = new ComputerPlayer(computerBoard);

        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        // give ships ids and place them
        for (Ship ship : compFleet) {
            ship.setID(idNum);
            idNum++;
            computer.performPlacement(ship.getId(), ship.getSize());
        }

        // give ships ids and place them
        for (Ship ship : playerFleet) {
            ship.setID(idNum);
            idNum++;

            String name = ship.getName();
            // place ship
            switch(name) {
                case "battleship":
                    player.placeBattleship(ship.getId());
                    break;

                case "destroyer":
                    player.placeDestroyer(ship.getId());
                    break;

                case "minesweeper":
                    player.placeMinesweeper(ship.getId());
                    break;

                default:
                    System.out.println("Not found!");
                    break;
            }
        }

        // for testing purposes to see if ids work correctly
        for (Ship ship : compFleet) {
            System.out.println(ship.getId());
        }
        for (Ship ship : playerFleet) {
            System.out.println(ship.getId());
        }

        player.Shot(computer.getBoard(),'Z', -1);

        System.out.println("The computer is now taking their shot!");
        System.out.println();

        computer.Shot(player.getBoard(), 'Z', -1);


    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or computer
        turnInfo  = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn(){
        return turnInfo;
    }


    //TODO: Add end game check, more functions
}
