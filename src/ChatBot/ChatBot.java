package ChatBot;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Hello! My name is EduBot.\nI was created in 2022.\nPlease, remind me your name.");

        String name = inputReader.nextLine();
        System.out.printf("What a great name you have, %s!", name);

        short remainder3, remainder5, remainder7;
        System.out.println("\nLet me guess your age.\nEnter remainders of dividing your age by 3, 5 and 7.");
        remainder3 = inputReader.nextShort();
        remainder5 = inputReader.nextShort();
        remainder7 = inputReader.nextShort();
        System.out.printf("Your age is %d; that`s a good time to start programming!",
                (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105);

        short end_num;
        System.out.println("\nNow I will prove to you that I can count to any number you want.");
        end_num = inputReader.nextShort();
        for (int start_num = 0; start_num <= end_num; start_num++) {
            System.out.printf("%d !\n", start_num);
        }

        short answer;
        System.out.println("""
                Let`s test your programming knowledge.
                Why do we use methods?
                1. To repeat statement multiple times.
                2. To decompose a program into several subroutines.
                3. To determine the execution time of a program.
                4. To interrupt the execution of a program.""");

        while (true) {
            answer = inputReader.nextShort();

            if (answer == 2) {
                System.out.println("Great, you`re right!");
                break;
            }

            else {
                System.out.println("Please, try again.");
            }

        }

        System.out.println("Goodbye, have a nice day!");

    }
}
