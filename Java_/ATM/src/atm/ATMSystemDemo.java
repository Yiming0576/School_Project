package atm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code ATMSystem} class is the entry point for the ATM application.
 * It initializes the ATM system, provides a user interface for signing in and signing up,
 * and handles user input to perform these operations.
 */
public class ATMSystemDemo {
    
    /**
     * The main method to start the ATM system.
     * It initializes the necessary components, including a scanner for user input and
     * an {@code ATMUser} instance to handle user operations.
     * It then enters a loop to display the main menu and process user commands.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Account> accounts = new ArrayList<>();
        ATMUser atmUser = new ATMUser(accounts, sc);

        while (true) {
            System.out.println("--------------- Welcome to Yiming ATM System ---------------");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("Please choose an operation: ");
            int command = sc.nextInt();

            switch (command) {
                case 1:
                    atmUser.login();
                    break;
                case 2:
                    atmUser.register();
                    break;
                default:
                    System.out.println("Please enter a valid operation.");
            }
        }
    }
}
