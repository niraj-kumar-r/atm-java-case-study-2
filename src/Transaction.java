/**
 * An abstract class that represents a transaction. Can be extended to create and execute different kinds of transactions.
 * This way for eg, we can make any additional checks (futureproof) in a subclass withdrawal that extends this class,
 * instead of directly debiting money using the debit method
 */
public abstract class Transaction {
    protected final int accountNumber;

    protected final BankDatabase bankDatabase;

    protected final Screen screen;

    public Transaction(int accountNumber, Screen screen, BankDatabase bankDatabase) {
        this.accountNumber = accountNumber;
        this.screen = screen;
        this.bankDatabase = bankDatabase;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Screen getScreen() {
        return screen;
    }

    /**
     * An abstract method that can help execute the different kinds of transactions in each subclass.
     */
    abstract void execute();
}
