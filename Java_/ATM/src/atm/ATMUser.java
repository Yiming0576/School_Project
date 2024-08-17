package atm;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code ATMUser} class handles user operations in the ATM system.
 * It provides functionalities for user login, registration, account management, and transactions.
 */
public class ATMUser {
    private ArrayList<Account> accounts;
    private Scanner sc;

    /**
     * Constructs an {@code ATMUser} instance with the given list of accounts and scanner.
     *
     * @param accounts the list of accounts in the ATM system
     * @param sc the scanner for user input
     */
    public ATMUser(ArrayList<Account> accounts, Scanner sc) {
        this.accounts = accounts;
        this.sc = sc;
    }

    /**
     * Allows a user to log into the ATM system.
     * Prompts the user for card number and password, then verifies the credentials.
     */
    public void login() {
        System.out.println("--------------- Login System Page ---------------");
        if (accounts.isEmpty()) {
            System.out.println("Sorry! There are no accounts. You need to sign up first.");
            return;
        }

        while (true) {
            System.out.println("Please enter your CardNumber: ");
            String cardId = sc.next();
            Account acc = getAccountByCardId(cardId);
            if (acc != null) {
                while (true) {
                    System.out.println("Please enter your password: ");
                    String password = sc.next();
                    if (acc.getPassword().equals(password)) {
                        System.out.println("Congratulations, " + acc.getName() +
                                "! You have successfully entered the ATM system. Your card number is " + acc.getCardId());
                        showUserCommands(acc);
                        return;
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                }
            } else {
                System.out.println("Account not found.");
            }
        }
    }

    /**
     * Registers a new user account in the ATM system.
     * Prompts for user details such as name, password, and daily withdrawal limit, and creates a new account.
     */
    public void register() {
        System.out.println("--------- Sign Up System Page ---------");
        System.out.println("Enter your account name: ");
        String name = sc.next();

        String password = getPasswordFromUser();
        System.out.println("Enter your daily withdrawal limit: ");
        double dailyLimit = sc.nextDouble();

        String cardId = generateUniqueCardId();
        Account newAccount = new Account(cardId, name, password, dailyLimit);
        accounts.add(newAccount);

        System.out.println("Congratulations! Your account has been created. Your card number is " + cardId);
    }

    /**
     * Prompts the user to enter and confirm a password.
     *
     * @return the validated password
     */
    private String getPasswordFromUser() {
        while (true) {
            System.out.println("Enter your password: ");
            String password = sc.next();
            System.out.println("Re-enter your password: ");
            String confirmPassword = sc.next();
            if (password.equals(confirmPassword)) {
                return password;
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
        }
    }

    /**
     * Generates a unique card ID for a new account.
     * <p>
     * Note: This is a placeholder implementation and should be replaced with a method
     * to generate a unique card ID that does not exist in the accounts list.
     *
     * @return a unique card ID
     */
    private String generateUniqueCardId() {
        // Generate a unique card ID
        // Implement a method to generate a unique card ID that does not exist in the accounts list
        return "00000000"; // Placeholder implementation
    }

    /**
     * Retrieves an account by its card ID.
     *
     * @param cardId the card ID of the account to retrieve
     * @return the account with the specified card ID, or {@code null} if not found
     */
    private Account getAccountByCardId(String cardId) {
        for (Account acc : accounts) {
            if (acc.getCardId().equals(cardId)) {
                return acc;
            }
        }
        return null;
    }

    /**
     * Displays the user commands menu and handles user input for various operations.
     * The operations include checking account information, depositing money, withdrawing money,
     * transferring money, changing the password, closing the account, and exiting the system.
     *
     * @param acc the account of the currently logged-in user
     */
    private void showUserCommands(Account acc) {
        while (true) {
            System.out.println("--------------- User Page ---------------");
            System.out.println("1. Check account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Change password");
            System.out.println("6. Exit");
            System.out.println("7. Close account");
            System.out.println("Please choose your operation: ");
            int command = sc.nextInt();

            switch (command) {
                case 1:
                    showAccount(acc);
                    break;
                case 2:
                    depositMoney(acc);
                    break;
                case 3:
                    withdrawMoney(acc);
                    break;
                case 4:
                    transferMoney(acc);
                    break;
                case 5:
                    changePassword(acc);
                    return;
                case 6:
                    System.out.println("Exit successful! We hope to see you again.");
                    return;
                case 7:
                    if (closeAccount(acc)) {
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid operation!");
            }
        }
    }

    /**
     * Displays the account information of the currently logged-in user.
     *
     * @param acc the account to display information for
     */
    private void showAccount(Account acc) {
        System.out.println("--------------- Account Information Page ---------------");
        System.out.println("Card number: " + acc.getCardId());
        System.out.println("Holder: " + acc.getName());
        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Daily withdrawal limit: " + acc.getDailyLimit());
    }

    /**
     * Allows the user to deposit money into their account.
     *
     * @param acc the account to deposit money into
     */
    private void depositMoney(Account acc) {
        System.out.println("--------------- Deposit Money Page ---------------");
        System.out.println("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        acc.setBalance(acc.getBalance() + amount);
        System.out.println("Successfully deposited " + amount + ". New balance: " + acc.getBalance());
    }

    /**
     * Allows the user to withdraw money from their account.
     *
     * @param acc the account to withdraw money from
     */
    private void withdrawMoney(Account acc) {
        System.out.println("--------------- Withdraw Money Page ---------------");
        System.out.println("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        if (amount > acc.getDailyLimit()) {
            System.out.println("Amount exceeds daily withdrawal limit. Limit: " + acc.getDailyLimit());
        } else if (amount > acc.getBalance()) {
            System.out.println("Insufficient balance. Your balance: " + acc.getBalance());
        } else {
            acc.setBalance(acc.getBalance() - amount);
            System.out.println("Successfully withdrew " + amount + ". New balance: " + acc.getBalance());
        }
    }

    /**
     * Allows the user to transfer money to another account.
     *
     * @param acc the account to transfer money from
     */
    private void transferMoney(Account acc) {
        System.out.println("--------------- Transfer Money Page ---------------");
        if (accounts.size() < 2) {
            System.out.println("Not enough accounts for transfer.");
            return;
        }

        System.out.println("Enter recipient card number: ");
        String cardId = sc.next();
        if (cardId.equals(acc.getCardId())) {
            System.out.println("Cannot transfer to yourself.");
            return;
        }

        Account recipient = getAccountByCardId(cardId);
        if (recipient == null) {
            System.out.println("Invalid recipient card number.");
            return;
        }

        System.out.println("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        if (amount > acc.getDailyLimit()) {
            System.out.println("Amount exceeds daily transfer limit. Limit: " + acc.getDailyLimit());
        } else if (amount > acc.getBalance()) {
            System.out.println("Insufficient balance. Your balance: " + acc.getBalance());
        } else {
            acc.setBalance(acc.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            System.out.println("Successfully transferred " + amount + ". Your new balance: " + acc.getBalance());
        }
    }

    /**
     * Allows the user to change their account password.
     *
     * @param acc the account to change the password for
     */
    private void changePassword(Account acc) {
        System.out.println("--------------- Change Password Page ---------------");
        System.out.println("Enter your current password: ");
        String currentPassword = sc.next();
        if (acc.getPassword().equals(currentPassword)) {
            String newPassword = getPasswordFromUser();
            acc.setPassword(newPassword);
            System.out.println("Password changed successfully!");
        } else {
            System.out.println("Current password is incorrect.");
        }
    }

    /**
     * Allows the user to close their account.
     * The account can only be closed if the balance is zero.
     *
     * @param acc the account to be closed
     * @return {@code true} if the account was successfully closed, {@code false} otherwise
     */
    private boolean closeAccount(Account acc) {
        System.out.println("--------------- Close Account Page ---------------");
        System.out.println("Are you sure you want to close your account? (y/n)");
        String response = sc.next();
        if ("y".equalsIgnoreCase(response)) {
            if (acc.getBalance() > 0) {
                System.out.println("Account balance is not zero. Cannot close account.");
                return false;
            } else {
                accounts.remove(acc);
                System.out.println("Account closed successfully.");
                return true;
            }
        } else {
            System.out.println("Account closure cancelled.");
            return false;
        }
    }
}
