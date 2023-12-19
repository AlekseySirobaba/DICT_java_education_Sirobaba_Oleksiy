package Hangman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class HangmanGame {
    private static final List<String> WORDS = Arrays.asList("python", "javascript", "java", "kotlin");
    private static final int MAX_TRIES = 8;

    public static void main(String[] args) {
        System.out.println("HANGMAN");
        System.out.println("Type \"play\" to play the game, \"exit\" to quit:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        while (!input.equalsIgnoreCase("exit")) {
            if (input.equalsIgnoreCase("play")) {
                playGame();
            } else {
                System.out.println("Invalid input. Type \"play\" to play the game, \"exit\" to quit:");
            }

            input = scanner.nextLine().trim();
        }

        System.out.println("Thanks for playing!");
    }

    private static void playGame() {
        String selectedWord = selectRandomWord();
        char[] guessedWord = new char[selectedWord.length()];
        Arrays.fill(guessedWord, '-');

        List<Character> guessedLetters = new ArrayList<>();
        int triesLeft = MAX_TRIES;

        System.out.println(guessedWord);

        while (triesLeft > 0) {
            System.out.println("\nInput a letter: ");
            char guess = getValidGuess();

            if (guessedLetters.contains(guess)) {
                System.out.println("You've already guessed this letter");
                System.out.println(String.valueOf(guessedWord));
                continue;
            }

            guessedLetters.add(guess);

            if (selectedWord.contains(String.valueOf(guess))) {
                updateGuessedWord(selectedWord, guessedWord, guess);
                if (!String.valueOf(guessedWord).contains("-")) {
                    System.out.println("You guessed the word!");
                    System.out.println("You survived!");
                    break;
                }
            } else {
                triesLeft--;
                System.out.println("That letter doesn't appear in the word");
            }

            System.out.println(String.valueOf(guessedWord));
        }

        if (triesLeft == 0) {
            System.out.println("You lost!");
        }

        System.out.println("Type \"play\" to play the game, \"exit\" to quit:");
    }

    private static String selectRandomWord() {
        Random random = new Random();
        return WORDS.get(random.nextInt(WORDS.size()));
    }

    private static char getValidGuess() {
        Scanner scanner = new Scanner(System.in);
        char guess;

        while (true) {
            String input = scanner.nextLine().trim();

            Pattern pattern = Pattern.compile("^[a-z]+$");
            Matcher matcher = pattern.matcher(input);
            boolean isEnglishLetters = matcher.find();

            if (input.length() != 1) {
                System.out.println("You should input a single letter");
            } else if (!isEnglishLetters || !Character.isLowerCase(input.charAt(0))) {
                System.out.println("Please enter a lowercase English letter");
            } else {
                guess = input.charAt(0);
                break;
            }
        }

        return guess;
    }

    private static void updateGuessedWord(String word, char[] guessedWord, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedWord[i] = guess;
            }
        }
    }
}
