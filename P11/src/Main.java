import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      TicTacToeModel model = new TicTacToeModel();
      TicTacToeView view = new TicTacToeView(model);
      view.setVisible(true);
    });
  }
}
