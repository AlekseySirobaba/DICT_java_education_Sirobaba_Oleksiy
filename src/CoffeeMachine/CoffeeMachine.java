package CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int coffeeBeans;
    private int disposableCups;
    private int money;

    public CoffeeMachine(int water, int milk, int coffeeBeans, int disposableCups, int money) {
        this.water = water;
        this.milk = milk;
        this.coffeeBeans = coffeeBeans;
        this.disposableCups = disposableCups;
        this.money = money;
    }

    public int processAction(String action) {
        switch (action) {
            case "buy" -> buyCoffee();
            case "fill" -> fillMachine();
            case "take" -> takeMoney();
            case "remaining" -> printStatus();
            case "exit" -> { return 0; }
            default -> System.out.println("Invalid action!");
        }

        return 1;
    }

    private void buyCoffee() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino 0 - back");
        String choice = scanner.next();

        switch (choice) {
            case "1" -> makeCoffee(250, 0, 16, 4); // Espresso
            case "2" -> makeCoffee(350, 75, 20, 7); // Latte
            case "3" -> makeCoffee(200, 100, 12, 6); // Cappuccino
            case "4" -> {} // Or "do nothing"
            default -> System.out.println("Invalid choice!");
        }
    }

    private void makeCoffee(int waterNeeded, int milkNeeded, int coffeeBeansNeeded, int cost) {
        List<String> notEnoughOf = checkResources(waterNeeded, milkNeeded, coffeeBeansNeeded);
        if (notEnoughOf.isEmpty()) {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterNeeded;
            milk -= milkNeeded;
            coffeeBeans -= coffeeBeansNeeded;
            disposableCups--;
            money += cost;
        } else {
            System.out.println("Sorry, there is a shortage of: " + String.join(", ", notEnoughOf));
        }
    }

    private List<String> checkResources(int waterNeeded, int milkNeeded, int coffeeBeansNeeded) {
        List<String> notEnoughOf = new ArrayList<>();

        if (water < waterNeeded) {
            notEnoughOf.add("water");
        }
        if (milk < milkNeeded) {
            notEnoughOf.add("milk");
        }
        if (coffeeBeans < coffeeBeansNeeded) {
            notEnoughOf.add("coffee beans");
        }
        if (disposableCups < 1) {
            notEnoughOf.add("disposable cups");
        }

        return notEnoughOf;
    }

    private void fillMachine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        int addedWater = scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        int addedMilk = scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        int addedCoffeeBeans = scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        int addedCups = scanner.nextInt();

        water += addedWater;
        milk += addedMilk;
        coffeeBeans += addedCoffeeBeans;
        disposableCups += addedCups;
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    private void printStatus() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(coffeeBeans + " of coffee beans");
        System.out.println(disposableCups + " of disposable cups");
        System.out.println("$" + money + " of money");
    }
}
