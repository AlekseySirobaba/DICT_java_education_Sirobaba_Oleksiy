package CoffeeMachine;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(400, 540, 120, 9, 550);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String action = scanner.next();
            int output = coffeeMachine.processAction(action);

            if (output == 0) {
                break;
            }
        }
    }
}
