interface BankAccount {
    double getTotalBalance();

    void debit(double amount);

    void credit(double amount);
}

public class Account implements BankAccount {
    private final int accountNumber;
    private final int pin;
    private double totalBalance;

    public Account(int theAccountNumber, int thePIN, double theTotalBalance) {
        accountNumber = theAccountNumber;
        pin = thePIN;
        totalBalance = theTotalBalance;
    }

    public boolean validatePIN(int userPIN) {
        return userPIN == pin;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        totalBalance -= amount;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
}
