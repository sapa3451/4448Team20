package edu.colorado.team20;

public class GameManagement {
    // provides turn information
    // P --> user player turn
    // C --> computer turn
    private int turnNum;
    private char turnInfo;
    int idNum = 1;

    public GameManagement() {
        turnInfo = 'P'; // set to player first always
        turnNum = 1; // initialize first round
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

        Board playerBoard = new PlayerBoard(playerFleet);
        Board computerBoard = new ComputerBoard(compFleet);

        UserPlayer player = new UserPlayer(playerBoard); // TODO: this need to be Player not ComputerPlayer --> there is functiosn that are used in UserPlayer that are cuasing trouble when I change it
        ComputerPlayer computer = new ComputerPlayer(computerBoard);

        System.out.println("Welcome to The Battleship Game!");
        System.out.println();
        System.out.println();

        System.out.println("You will place your ships, and then you will fire the first shot to being your battle. Good luck!");
        System.out.println();
        System.out.println();

        // give ships ids and place them
        for (Ship ship : compFleet) {
            ship.setId(idNum);
            idNum++;
            computer.performPlacement(ship.getId(), ship.getSize());
        }

        // give ships ids and place them
        for (Ship ship : playerFleet) {
            ship.setId(idNum);
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

        int id = player.Shot(computer.getBoard(), 'Z', -1, this.turnNum);
        // both player and computer player's will return ship id if hit
        if (id != -1) { // check if return id
            // need to find id of opponent ship (computer) to adjust health
            Ship hitShip = null;
            for (Ship ship : compFleet) {
                if (id == ship.getId()) {
                    hitShip = ship;
                }
            }
            player.checkCaptainsQ(hitShip, computer.getBoard(), this.getTurnNum());
        }

        System.out.println("The computer is now taking their shot!");
        System.out.println();

        id = computer.Shot(player.getBoard(), 'Z', -1, this.turnNum);
        // both player and computer player's will return ship id if hit
        if (id != -1) { // check if return id
            // need to find id of opponent ship (computer) to adjust health
            Ship hitShip = null;
            for (Ship ship : playerFleet) {
                if (id == ship.getId()) {
                    hitShip = ship;
                }
            }
            computer.checkCaptainsQ(hitShip, player.getBoard(), this.getTurnNum());
        }

        // TODO: make sure when round ends add one to the turnNum!

    }

    // change the turn
    public void ChangeTurn() {
        // change the turn to either player or computer
        turnInfo = (turnInfo == 'P') ? 'C' : 'P';
    }

    // method to return turn info
    public char GetTurn() {
        return turnInfo;
    }


    //TODO: Add end game check, more functions
    public boolean EndGame(Ship[] playerFleet, Ship[] compFleet) {
        int playerCount = 0;
        int compCount = 0;
        for (int i = 0; i < playerFleet.length; i++) {
            if (playerFleet[i].checkSunk(playerFleet[i].getSize())) {
                playerCount++;
            }
            if (compFleet[i].checkSunk(compFleet[i].getSize())) {
                compCount++;
            }
        }
        return playerCount == playerFleet.length || compCount == compFleet.length;
    }

    public int getTurnNum() { return turnNum; }
    public int getIdNum() { return this.idNum; }
    public void setIdNum() { this.idNum++; }

}
