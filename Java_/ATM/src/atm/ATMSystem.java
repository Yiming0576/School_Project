package atm;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		// contain accounts
		ArrayList<Account> accounts = new ArrayList<>();

		while (true) {
			System.out.println("--------------- Welcome to Yiming ATM System ---------------");
			System.out.println("1. Sign in");
			System.out.println("2. Sign up");

			System.out.println("Please choice an operation: ");
			int command = sc.nextInt();

			switch (command) {
			case 1:
				// sign in
				login(accounts, sc);
				break;
			case 2:
				// sign up
				register(accounts, sc);
				break;
			default:
				System.out.println("Pleae enter correct operation--");

			}

		}

	}

	// login an account
	private static void login(ArrayList<Account> accounts, Scanner sc) {
		System.out.println("--------------- Login System Page ---------------");
		// check the ArrayList, see if there has accounts or not
		if (accounts.size() == 0) {
			System.out.println("Sorry! There is no accounts. You need to sign up one.");
			return;
		}

		while (true) {
			// Enter carNumber
			System.out.println("Please enter your CardNumber: ");
			String cardId = sc.next();
			Account acc = getAccountByCardId(cardId, accounts);
			if (acc != null) {

				while (true) {
					// Enter password
					System.out.println("Please enter your password: ");
					String password = sc.next();

					// check password is correct or not
					if (acc.getPassword().equals(password)) {
						System.out.println("Congratulation, " + acc.getName()
								+ "Sir/Miss enter ATM system successfully!. Your card number is " + acc.getCardId());

						// after login
						showUserCommand(accounts, sc, acc);
						return; // exit login method

					} else {
						System.out.println("Your password is not correct.");
					}
				}
			} else {
				System.out.println("Sorry, System has not found your account.");

			}
		}

	}

	// after login
	private static void showUserCommand(ArrayList<Account> accounts, Scanner sc, Account acc) {
		// TODO Auto-generated method stub

		while (true) {
			System.out.println("--------------- User Page ---------------");
			System.out.println("1. check account");
			System.out.println("2. deposit");
			System.out.println("3. withdraw");
			System.out.println("4. transfer");
			System.out.println("5. change your password");
			System.out.println("6. exit");
			System.out.println("7. close account");
			System.out.println("Please choice your operation: ");
			int command = sc.nextInt();

			// user operation
			switch (command) {
			case 1:
				// check account
				showAccount(acc);
				break;
			case 2:
				// deposit
				depositMoney(acc, sc);
				break;
			case 3:
				// withdraw
				withdrawMoney(acc, sc);
				break;
			case 4:
				// transfer
				transferMoney(acc, sc, accounts);
				break;
			case 5:
				// change your password
				changePassowrd(acc, sc);
				return;
			case 6:
				// exit
				System.out.println("Exit successful! Happy to see you again.");
				return; // exit showUserCommand method
			case 7:
				// check whether is true or false  ( if true, close account. if false, keep account) 
				if (closeAccount(acc, accounts, sc)) {
					// close your account
					return;
				} else {
					// did not close account
					break;
				}

			default:
				System.out.println("Invalid operation!");

			}

		}

	}

	// close account
	private static boolean closeAccount(Account acc, ArrayList<Account> accounts, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Sign Out Page ---------------");
		System.out.println("Are you sure? You want to sign out. (y/n)");
		String rs = sc.next();
		switch (rs) {
		case "y":

			// check whether account's balance is 0 or not
			if (acc.getBalance() > 0) {
				System.out.println("You still have money in your account. Not allow Sign out!");
			} else {
				// close account
				accounts.remove(acc);
				System.out.println("You have closed your account.");
				return true;
			}
			break;
		default:
			System.out.println("Keep account.");
		}
		return false;
	}

	// change password
	private static void changePassowrd(Account acc, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Change Password Page ---------------");

		while (true) {
			System.out.println("Please enter your password: ");
			String password = sc.next();

			// check whether the password is correct or not
			if (acc.getPassword().equals(password)) {

				while (true) {
					// enter new password
					System.out.println("Please enter your new password: ");
					String newPassword = sc.next();

					// confirm new password
					System.out.println("Please confirm your password: ");
					String okPassword = sc.next();

					// check whether password is same or not
					if (okPassword.equals(newPassword)) {
						System.out.println("Password changed successful!");
						acc.setPassword(okPassword);
						return;
					} else {
						System.out.println("Two password are different. Please try again.");
					}
				}
			} else {
				System.out.println("Password is not correct! ");
			}
		}
	}

	// transfer money
	private static void transferMoney(Account acc, Scanner sc, ArrayList<Account> accounts) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Transfer Money Page ---------------");

		// check whether there has two accounts or not
		if (accounts.size() < 2) {
			System.out
					.println("In the current system, there are less than two accounts. You need to register accounts!");
			return;
		}

		// check whether balance is 0 or not
		if (acc.getBalance() == 0) {
			System.out.println("Insufficient balance!");
			return;
		}

		while (true) {
			// transfer money
			System.out.println("Enter the card number you want to transfer: ");
			String cardId = sc.next();

			// check whether the card number is same as yourself.
			if (cardId.equals(acc.getCardId())) {
				System.out.println("Sorry, You cannot transfter to yourself.");
				continue; // end of the loop
			}

			// check whether the card is in array or not
			Account account = getAccountByCardId(cardId, accounts);
			if (account == null) {
				System.out.println("Sorry, You enter invalid card number.");
			} else {

				// check the account name
				String userName = account.getName();
				String tip = "*" + userName.substring(1);
				System.out.println("Please enter [" + tip + "]' last name");
				String lastName = sc.next();

				// check whether last name is same or not
				if (userName.startsWith(lastName)) {

					while (true) {
						// transfer money
						System.out.println("Please enter transfer amount: ");
						double amount = sc.nextDouble();

						if (amount > acc.getdailyLimit()) {
							System.out.println("Your transfer money amount is over daily limit. Your daily limit is "
									+ acc.getdailyLimit());

							// check whether is insufficient or not
							if (amount > acc.getBalance()) {
								System.out.println("Sorry, insufficent balance. Your balance " + acc.getBalance());

							} else {
								// update balance
								acc.setBalance(acc.getBalance() - amount);
								account.setBalance(account.getBalance() + amount);
								System.out.println(" transferred successful! Your balance: " + acc.getBalance());
								return; // end of the method

							}
						}
					}

				} else {
					System.out.println("Sorry, you enter invalid last name.");
				}
			}
		}
	}

	// withdraw money from account
	private static void withdrawMoney(Account acc, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Withdraw Money Page ---------------");
		// check if account balance below 100 or not
		if (acc.getBalance() < 100) {
			System.out.println("Your account balance below 100, and you should make a deposit first.");
			return;
		}

		while (true) {
			System.out.println("Enter withdraw amount: ");
			double amount = sc.nextDouble();

			// check the amount its over withdrawLimit or not
			if (amount > acc.getdailyLimit()) {
				System.out.println("Sorry! Your withdraw amount is over withdrawlimit, and daily withdraw limit: "
						+ acc.getdailyLimit());
			} else {

				// check the amount whether its over balance or not
				if (acc.getBalance() < amount) {
					System.out.println("Insufficient balance! Your balance is " + acc.getBalance());
				} else {
					// withdraw successful
					System.out.println("Congratulation, withdraw " + amount + " successfully");

					// update balance
					acc.setBalance(acc.getBalance() - amount);
					showAccount(acc);
					return; // end of the method
				}
			}
		}

	}

	// deposit money into account
	private static void depositMoney(Account acc, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Deposit Money Page ---------------");
		System.out.println("Please enter the amount: ");
		double amount = sc.nextDouble();

		// update balance
		acc.setBalance(acc.getBalance() + amount);
		System.out.println("Congratulation, You deposited " + amount + " into your " + acc.getCardId() + " account");
		showAccount(acc);
	}

	// show account information
	private static void showAccount(Account acc) {
		// TODO Auto-generated method stub
		System.out.println("--------------- Account Information Page ---------------");
		System.out.println("Card number: " + acc.getCardId());
		System.out.println("Holder: " + acc.getName());
		System.out.println("Balance: " + acc.getBalance());
		System.out.println("Daily withdraw limit: " + acc.getdailyLimit());
	}

	// register an account
	private static void register(ArrayList<Account> accounts, Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("---------Sign up System Page------------");
		// create an account object to contain customer information
		Account account = new Account();

		// enter account name
		System.out.println("Enter your account name: ");
		String AccountName = sc.next();
		account.setName(AccountName);

		// enter account password
		while (true) {
			System.out.println("Enter your account password: ");
			String password = sc.next();
			System.out.println("Enter your password one more time: ");
			String okPassword = sc.next();

			// check the two password is same or not
			if (okPassword.equals(password)) {
				account.setPassword(okPassword);
				break;
			} else {
				System.out.println("Sorry! You two password are different. Please enter one more time: ");
			}
		}

		// enter withdraw limit
		System.out.println("Enter one day withdraw limit: ");
		double withdrawLimit = sc.nextDouble();
		account.setdailyLimit(withdrawLimit);

		// create a carNumber for account (8 numbers and different)
		String cardId = getRandomCardId(accounts);
		account.setCardId(cardId);
		accounts.add(account);

		System.out.println(
				"Congratulation! Sir/Miss, You got your account successful! Your account is :" + account.getCardId());
	}

	// create a random card Number
	private static String getRandomCardId(ArrayList<Account> accounts) {
		// TODO Auto-generated method stub
		Random r = new Random();

		while (true) {

			// contain card numbers
			String cardId = "";

			// create 8 random card numbers
			for (int i = 0; i < 8; i++) {
				cardId += r.nextInt(10);
			}

			// check if the card number already in ArrayList
			Account acc = getAccountByCardId(cardId, accounts);
			if (acc == null) {
				return cardId;
			}
		}
	}

	// check account by card Number
	private static Account getAccountByCardId(String cardId, ArrayList<Account> accounts) {
		for (int i = 0; i < accounts.size(); i++) {
			Account acc = accounts.get(i);
			if (acc.getCardId().equals(cardId)) {
				return acc;
			}
		}
		return null;
	}
}