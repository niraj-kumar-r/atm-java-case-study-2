interface AtmScreen {
    void displayMessage(String message);
    void displayDollarAmount(double amount);
}

public class Screen implements AtmScreen {
    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayMessageLine(String message) {
        System.out.println(message);
    }

    public void displayMessageLine(int message) {
        System.out.println(message);
    }

    public void displayMessageLine(double message) {
        System.out.println(message);
    }

    // display a dollar amount
    public void displayDollarAmount(double amount) {
        System.out.printf("$%,.2f\n", amount);
    }
}
