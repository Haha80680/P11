package tictactoe;

public class Main {
  public static void main(String[] args) {
    TicTacToe m = new TicTacToeModel();
    SwingTicTacToeView v = new SwingTicTacToeView("Tic-Tac-Toe");
    TicTacToeController c = new SwingTicTacToeController(v, m);
    c.playGame(m);
  }
}
