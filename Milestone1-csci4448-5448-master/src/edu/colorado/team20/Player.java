package edu.colorado.team20;
import java.util.Locale;
import java.util.Scanner;  // Import the Scanner class

public class Player {

    // ask player for shot decision
    // might want to return values/call function to represent shot being done?
    public String GetDecisionShot() {
        // variables for input
        char colVal;
        int rowVal;
        String r;
        String c;

        System.out.println("It is your turn!");
        System.out.println("Decision: ");
        System.out.println("Type which column (A-J) you would like to target: ");

        // take in user input
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
        input = input.toUpperCase(); // set to uppercase
        c = input;

        // we want to check input is okay for column
        boolean correct = false;
        while (!correct) {
            // check if valid input
            if (input.length() == 1) { // check if single letter
                char[] col = input.toCharArray(); // set to char array
                if (col[0] >= 'A' && col[0] <= 'J') {  // check if valid column input
                    correct = true;
                    // set column value
                    colVal = col[0];
                }
            }
            // invalid input
            if (!correct) {
                System.out.println("Invalid column! Please enter a valid column (A-J): ");
                input = sc.nextLine(); // Read user input
                input = input.toUpperCase(); // set to uppercase
            }
        }

        System.out.println("Type which row (1-10) you would like to target: ");

        // take in user input
        input = sc.nextLine();
        r = input;//reads string

        // we want to check input is okay for column
        correct = false;
        while (!correct) {
            // check if valid input
            try {
                // checking valid integer using parseInt() method
                rowVal = Integer.parseInt(input); // set value
                correct = true;
            }
            catch (NumberFormatException e) { // throw error and get input again
                System.out.println("Invalid row! Please enter a valid row (1-10):");
                input = sc.nextLine(); // Read user input
            }
        }
        //added a return method to this function, which is then used to call and update the board with a hit
        return c+r;
        // maybe implement function to call to reference shot being completed

    }
}
