package TicTacToe;

public class Player implements Playable{
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean makeMove(int row, int col, Board board) {
        char[][] currentBoard = board.getBoard();

        if (currentBoard[row][col] == ' ') {
            currentBoard[row][col] = getSymbol();
            board.setBoard(currentBoard);
            return true;
        } else {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
    }
}
