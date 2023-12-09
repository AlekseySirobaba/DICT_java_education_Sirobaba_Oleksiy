package ChatBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ChatBot {
    public static void main(String[] args) throws
            InterruptedException, IOException {
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(greeting());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(askName(inputReader));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(guessAge(inputReader));
        TimeUnit.SECONDS.sleep(2);
        bombPlanting(inputReader);
        TimeUnit.SECONDS.sleep(3);
        System.out.println(funnyTest(inputReader));
        TimeUnit.SECONDS.sleep(2);


        System.out.println("\n(I will die in front of the computer if I will try to attach the soundtrack, so imagine the romantic music you like ;) )\n");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("It was nice to spend time with you, Senpai...");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("I-I-I'm looking forward to have m-m-more time together in the future (｡・//ε//・｡)");
        TimeUnit.SECONDS.sleep(4);
        System.out.println("See you next time the programme will run! And...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("*Kiss*");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("*11-45-G runs away to the hard drive*");
        TimeUnit.SECONDS.sleep(10);
        System.out.println("...");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Okay, if you don't like the Goodbye like this - fine.\nI will give you the default (boring) one :)");
        System.out.println("Goodbye, have a nice day!");
    }

    private static String greeting() {
        return "Hello! My name is 11-45-G.\nI was created in 2320. ^-^";
    }

    private static String askName(BufferedReader inputReader) {
        String name = "";
        int counter = 0;

        System.out.println("Please, remind me your name.");
        while (name.isEmpty()) {
            try {
                name = inputReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (name.isEmpty() && counter >= 3) {
                System.out.println("ENTER YOUR NAME D:<");
            } else if (name.isEmpty()) {
                System.out.println("Input is empty. Don't be shy, enter your name! :)");
            }

            counter++;
        }

        return String.format("What a great name you have, %s!", name);
    }

    private static String guessAge(BufferedReader inputReader) throws
            InterruptedException, IOException {
        int remainder3, remainder5, remainder7;
        String answer;

        System.out.println("Would you like to play a game? :>\n(Answer Y for Yes or N for No)");
        try {
            answer = inputReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (answer.equals("Y")) {
            System.out.println("Good, I like your mood! Let's play then!!! XD");
        } else {
            System.out.println("YOU DON'T HAVE ANOTHER CHOICE ANYWAY! AHAHAHHAHAHAHAHAHAHHAHAHA");
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Let me guess you age. (:");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Enter remainders of dividing your age by 3, 5 and 7.");

        remainder3 = InputParser.requestIntInput(inputReader);
        remainder5 = InputParser.requestIntInput(inputReader);
        remainder7 = InputParser.requestIntInput(inputReader);

        TimeUnit.SECONDS.sleep(1);
        return String.format(
                "Your age is %d; that's a good time to continue procrastinate! :D",
                (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105);
    }

    private static void bombPlanting(BufferedReader inputReader) throws
            InterruptedException, IOException {
        int amountOfTime;

        System.out.println("The previous game was REALLY fun! Let's play another game now -> CS:GO!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("We are the terrorists and we need to plant the bomb...");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("But hey! I forgot to set a timer on a bomb!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Let's do it now!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Write me a number at which the bomb will make BOOM");

        amountOfTime = InputParser.requestIntInput(inputReader);

        System.out.println("Good! Let's activate the bomb now...");
        TimeUnit.MILLISECONDS.sleep(750);
        System.out.println("*Bomb has been planted*");
        for(int sec = 0; sec <= amountOfTime; sec++) {
            System.out.printf("%d!%n", sec);
            TimeUnit.MILLISECONDS.sleep(750);
        }
        System.out.println("!BOOM!");
    }

    private static String funnyTest(BufferedReader inputReader) throws InterruptedException, IOException {
        String[] answers1 = {"H2", "Woda", "What is water?", "H2O"};
        String[] answers2 = {"Rainbow", "Blue", "Yellow", "We have a star?"};
        String[] answers3 = {"I'm not a gay. Why are YOU a gay?", "HOMOPHOBIC PIG!", "True", "False"};

        System.out.println("And now... IQ TEST!!!");
        TimeUnit.SECONDS.sleep(1);

        Question[] testQuestionsAndAnswers = {
            new Question("The formula of water?", answers1, 3),
            new Question("The colour of the sun?", answers2, 2),
            new Question("Why are you gay?", answers3, 0)
        };

        for (int key = 0; key < testQuestionsAndAnswers.length; key++) {
            System.out.printf("Question #%d%n", key + 1);
            Question testComponent = testQuestionsAndAnswers[key];
            testComponent.askQuestion(inputReader);
            TimeUnit.MILLISECONDS.sleep(500);
        }

        return "WOW! You have 450 IQ! Big brainy boy, I'm proud of you ;)";
    }
}