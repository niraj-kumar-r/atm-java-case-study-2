import java.util.ArrayList;
import java.util.List;

public class BankDatabase {
    private final List<Account> accounts = new ArrayList<>();

    //We add these two accounts by default for testing purposes. In practice the bank would have to add many accounts to the database.
    //But that is not the primary focus of this project.
    public BankDatabase() {
        addAccount(12345, 54321, 1200.0);
        addAccount(98765, 56789, 200.0);
    }

    private void addAccount(Account account) {
        accounts.add(account);
    }

    private void addAccount(int accountNumber, int pin, double totalBalance) {
        addAccount(new Account(accountNumber, pin, totalBalance));
    }
    private Account getAccount(int accountNumber) {
        for (Account currentAccount : accounts) {
            if (currentAccount.getAccountNumber() == accountNumber) {
                return currentAccount;
            }
        }

        return null;
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {

        Account userAccount = getAccount(userAccountNumber);

        if (userAccount != null) {
            return userAccount.validatePIN(userPIN);
        } else {
            return false;
        }
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }
}
