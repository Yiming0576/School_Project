package atm;

/**
 * Represents a bank account in the ATM system.
 * Each account has a card ID, name, password, balance, and daily withdrawal limit.
 */
public class Account {
    private String cardId;
    private String name;
    private String password;
    private double balance;
    private double dailyLimit;

    /**
     * Constructs a new Account with the specified card ID, name, password, and daily limit.
     * The initial balance is set to 0.0.
     *
     * @param cardId the card ID associated with the account
     * @param name the name of the account holder
     * @param password the password for the account
     * @param dailyLimit the daily withdrawal limit for the account
     */
    public Account(String cardId, String name, String password, double dailyLimit) {
        this.cardId = cardId;
        this.name = name;
        this.password = password;
        this.dailyLimit = dailyLimit;
        this.balance = 0.0;
    }

    /**
     * Returns the card ID associated with the account.
     *
     * @return the card ID
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * Sets the card ID for the account.
     *
     * @param cardId the new card ID
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * Returns the name of the account holder.
     *
     * @return the name of the account holder
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the account holder.
     *
     * @param name the new name of the account holder
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the password for the account.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the account.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance for the account.
     *
     * @param balance the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Returns the daily withdrawal limit for the account.
     *
     * @return the daily withdrawal limit
     */
    public double getDailyLimit() {
        return dailyLimit;
    }

    /**
     * Sets the daily withdrawal limit for the account.
     *
     * @param dailyLimit the new daily withdrawal limit
     */
    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    // Additional methods can be added here if needed
}