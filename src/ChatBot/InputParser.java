package ChatBot;

import java.io.BufferedReader;
import java.io.IOException;

public class InputParser {
    public static int requestIntInput(BufferedReader inputReader) throws IOException {
        boolean valuesCorrect = false;
        int outputNumber = 0;

        while (!valuesCorrect) {
            try {
                outputNumber = Integer.parseInt(inputReader.readLine());
                valuesCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("Please use, only numbers (::)");
            }
        }

        return outputNumber;
    }
}
