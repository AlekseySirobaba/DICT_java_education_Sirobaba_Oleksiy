package ChatBot;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Question {
    private final String question;
    private final String[] answers;
    private final int correctAnswer;

    public Question(
            String question,
            String[] answers,
            int correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public void askQuestion(BufferedReader inputReader) throws IOException {
        int usersAnswer = -1;

        System.out.println(question);
        for (int key = 0; key < answers.length; key++) {
            System.out.printf("%d. %s%n", key + 1, answers[key]);
        }

        while (!(usersAnswer - 1 == correctAnswer)) {
            System.out.println("Answer:");
            usersAnswer = InputParser.requestIntInput(inputReader);
            System.out.println(!(usersAnswer - 1 == correctAnswer)
                    ? "Nope :3. Try again!"
                    : "Good! You are V-E-R-Y clever)))");
        }
    }
}
