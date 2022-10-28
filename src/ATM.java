public class ATM {
    private boolean isRunning;
    private final int password;
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private final Screen screen;
    private final Keypad keypad;
    private final CashDispenser cashDispenser;
    private final DepositSlot depositSlot;
    private final BankDatabase bankDatabase;


    public ATM(int password) {
        isRunning = false;
        this.password = password;
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();
    }

    public void run() {
        isRunning = true;
        while (isRunning) {
            int tries = 0;
            while (!userAuthenticated && tries < 3) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
                tries++;
            }

            if (userAuthenticated) {
                performTransactions();
                userAuthenticated = false;
                currentAccountNumber = 0;
                screen.displayMessageLine("Thank you! Goodbye!\n");
            } else {
                screen.displayMessageLine("Too many failed attempts. Goodbye!\n");
            }
        }
    }

    public void stop() {
        screen.displayMessageLine("Enter the password to stop the ATM:");
        int input = keypad.getInput();
        if (input == password) {
            isRunning = false;
        }
        else{
            screen.displayMessageLine("Wrong password!");
        }
    }

    private void authenticateUser() {
        screen.displayMessage("Please enter your account number: ");
        int accountNumber = keypad.getInput();
        screen.displayMessage("Please enter your PIN: ");
        int pin = keypad.getInput();

        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
        }
    }

    private void performTransactions() {
        Transaction currentTransaction = null;

        boolean userExited = false;

        while (!userExited) {
            int mainMenuSelection = displayMainMenu();

            switch (mainMenuSelection) {
                case 1:
                    screen.displayDollarAmount(bankDatabase.getTotalBalance(currentAccountNumber));
                    break;
                case 2:
                case 3:
                    currentTransaction = createTransaction(mainMenuSelection);

                    currentTransaction.execute();
                case 4:
                    screen.displayMessageLine("Exiting the system...");
                    userExited = true;
                    break;
                case 5:
                    stop();
                    userExited = !isRunning;
                    break;
                default:
                    screen.displayMessageLine("You did not enter a valid selection. Try again.");
                    break;
            }
        }
    }

    private Transaction createTransaction(int mainMenuSelection) {
        Transaction temp = null;

        switch (mainMenuSelection) {
            case 2:
                temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case 3:
                temp = new Deposit(currentAccountNumber, screen,bankDatabase, keypad, depositSlot);
                break;
        }
        return temp;
    }

    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit");
        screen.displayMessageLine("5 - Stop ATM");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput();
    }
}


