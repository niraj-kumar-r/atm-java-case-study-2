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

    abstract void execute();
}
