package TicTacToe;

import java.util.Scanner;

public class Game implements Gameable {
    private final Board board;
    private final Player playerX;
    private final Player playerO;
    private Player currentPlayer;

    public Game(Player playerX, Player playerO) {
        this.board = new Board();
        this.playerX = playerX;
        this.playerO = playerO;
        this.currentPlayer = playerX;
    }

    public boolean checkWinner() {
        char[][] currentBoard = board.getBoard();

        for (int i = 0; i < 3; i++) {
            if ((currentBoard[i][0] == currentPlayer.getSymbol() && currentBoard[i][1] == currentPlayer.getSymbol() &&
                    currentBoard[i][2] == currentPlayer.getSymbol()) ||
                    (currentBoard[0][i] == currentPlayer.getSymbol() && currentBoard[1][i] == currentPlayer.getSymbol() &&
                            currentBoard[2][i] == currentPlayer.getSymbol())) {
                return true;
            }
        }

        return (currentBoard[0][0] == currentPlayer.getSymbol() && currentBoard[1][1] == currentPlayer.getSymbol() &&
                currentBoard[2][2] == currentPlayer.getSymbol()) ||
                (currentBoard[0][2] == currentPlayer.getSymbol() && currentBoard[1][1] == currentPlayer.getSymbol() &&
                        currentBoard[2][0] == currentPlayer.getSymbol());
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");

        while (true) {
            board.display();
            System.out.printf("Now it's Player's %s turn!\n", currentPlayer.getSymbol());
            System.out.println("Enter the coordinates (row and column) separated by a space: ");

            try {
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (currentPlayer.makeMove(row, col, board)) {
                    if (checkWinner()) {
                        board.display();
                        System.out.println(currentPlayer.getSymbol() + " wins!");
                        break;
                    } else if (board.isBoardFull()) {
                        board.display();
                        System.out.println("It's a draw!");
                        break;
                    }

                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
