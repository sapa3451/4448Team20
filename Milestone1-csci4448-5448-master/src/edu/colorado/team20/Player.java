package edu.colorado.team20;
import java.util.Locale;
import java.util.Scanner;  // Import the Scanner class

public class Player {

    // ask player for shot decision
    public void GetDecisionShot() {
        // variables for input
        char colVal;
        int rowVal;

        System.out.println("It is your turn!");
        System.out.println("Decision: ");
        System.out.println("Type which column (A-J) you would like to target: ");

        // take in user input
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream
        String input = sc.nextLine();    //reads string
        input = input.toUpperCase(); // set to uppercase

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
        input = sc.nextLine();    //reads string

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

    }
}
