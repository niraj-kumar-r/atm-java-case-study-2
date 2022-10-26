public class Deposit extends Transaction {
    private final Keypad keypad;
    private final DepositSlot depositSlot;
    private final int amount;
    Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, DepositSlot depositSlot) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
        this.depositSlot = depositSlot;
        super.screen.displayMessageLine("Enter the amount to deposit:");
        amount = this.keypad.getInput();
    }
    public void execute(){
        if(depositSlot.isEnvelopeReceived()){
            bankDatabase.credit(super.accountNumber, amount);
            super.screen.displayMessageLine("Deposit successful!");
        }
        else{
            super.screen.displayMessageLine("Envelope not received!");
        }
    }
}
