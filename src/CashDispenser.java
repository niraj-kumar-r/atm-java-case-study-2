interface AtmCashDispenser {
    void dispenseCash(int amount);
}

public class CashDispenser implements AtmCashDispenser {
    private final int[] cashAvailable = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private final int[] cashAvailableDenominations = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000};
    private final int[] cashAvailableMax = {100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    //  cashAvailableMax indicates the max amount of bills (of a particular denomination) the dispenser can hold
    private int totalCashAvailable = 0;

    public CashDispenser() {
        for (int i = 0; i < cashAvailable.length; i++) {
            cashAvailable[i] = cashAvailableMax[i];
            totalCashAvailable += cashAvailable[i] * cashAvailableDenominations[i];
        }
    }

    public void fillCashDispenser() {
        for (int i = 0; i < cashAvailable.length; i++) {
            cashAvailable[i] = cashAvailableMax[i];
            totalCashAvailable += cashAvailable[i] * cashAvailableDenominations[i];
        }
    }

    public void fillCashDispenser(int[] cashAvailable) {
        for (int i = 0; i < cashAvailable.length; i++) {
            this.cashAvailable[i] = Math.min(cashAvailable[i], cashAvailableMax[i]);
            totalCashAvailable += this.cashAvailable[i] * cashAvailableDenominations[i];
        }
    }

    public int getTotalCashAvailable() {
        return totalCashAvailable;
    }

    public void dispenseCash(int amount) {
        if (isSufficientCashAvailable(amount)) {
            int[] cashToDispense = new int[cashAvailable.length];
            int tempAmount = amount;
            for (int i = cashAvailable.length - 1; i >= 0; i--) {
                if (tempAmount >= cashAvailableDenominations[i]) {
                    cashToDispense[i] = tempAmount / cashAvailableDenominations[i];
                    tempAmount = tempAmount % cashAvailableDenominations[i];
                }
            }
            for (int i = 0; i < cashToDispense.length; i++) {
                cashAvailable[i] -= cashToDispense[i];
            }
            totalCashAvailable -= amount;
            System.out.println("Dispensing cash: ");
            for (int i = 0; i < cashToDispense.length; i++) {
                if (cashToDispense[i] != 0) {
                    System.out.println(cashToDispense[i] + " x " + cashAvailableDenominations[i]);
                }
            }
        } else {
            System.out.println("Not enough cash available! Sorry for inconvenience.");
        }
    }

    public boolean isSufficientCashAvailable(int amount) {
        return totalCashAvailable >= amount;
    }
}
