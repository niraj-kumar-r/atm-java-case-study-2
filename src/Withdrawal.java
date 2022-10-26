public class Withdrawal extends Transaction {
    private final Keypad keypad;
    private final CashDispenser cashDispenser;

    private final int amount;
    Withdrawal(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, CashDispenser cashDispenser) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
        this.cashDispenser = cashDispenser;
        super.screen.displayMessageLine("Enter the amount to withdraw:");
        amount = this.keypad.getInput();
    }

    public void execute(){
        if(bankDatabase.getTotalBalance(super.accountNumber) >= amount){
            if(cashDispenser.isSufficientCashAvailable(amount)){
                bankDatabase.debit(super.accountNumber, amount);
                cashDispenser.dispenseCash(amount);
                super.screen.displayMessageLine("Withdrawal successful!");
            }
            else{
                super.screen.displayMessageLine("Not enough cash available! Sorry for inconvenience.");
            }
        }
        else{
            super.screen.displayMessageLine("Not enough money in your account!");

        }
    }
}