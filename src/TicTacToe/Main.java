package TicTacToe;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(new Player('X'), new Player('O'));
        game.play();
    }
}
