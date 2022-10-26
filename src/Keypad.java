import java.util.Scanner;

interface AtmKeyPad {
    int getInput();
}

public class Keypad implements AtmKeyPad {
    private final Scanner input;
    public Keypad() {
        input = new Scanner(System.in);
    }
    public int getInput() {
        return input.nextInt();
    }
}
