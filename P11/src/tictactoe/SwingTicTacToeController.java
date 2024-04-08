package tictactoe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTicTacToeController implements TicTacToeController {
  private final TicTacToe model;
  private final SwingTicTacToeView view;

  public SwingTicTacToeController(SwingTicTacToeView view, TicTacToe model) {
    this.view = view;
    this.model = model;
    this.view.setButtonListener(new ButtonListener());
  }

  @Override
  public void playGame(TicTacToe m) {
    view.updateBoard(model.getBoard());
    view.setGameStatus("Turn: " + model.getTurn());
  }

  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (model.isGameOver()) {
        return;
      }

      String command = e.getActionCommand();
      String[] parts = command.split(" ");
      int row = Integer.parseInt(parts[0]);
      int col = Integer.parseInt(parts[1]);

      if (model.getMarkAt(row, col) == null) {
        model.move(row, col);
        view.updateBoard(model.getBoard());
        if (model.isGameOver()) {
          if (model.getWinner() != null) {
            view.setGameStatus("Game over! " + model.getWinner() + " wins!");
          } else {
            view.setGameStatus("Game over! It's a tie!");
          }
        } else {
          view.setGameStatus("Turn: " + model.getTurn());
        }
      }
    }
  }
}
