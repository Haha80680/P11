package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tictactoe.Player;
import tictactoe.TicTacToeModel;

public class TicTacToeView extends JFrame implements ActionListener {
  private final TicTacToeModel model;
  private final JButton[][] buttons = new JButton[3][3];
  private final JLabel statusLabel = new JLabel("Turn: X", SwingConstants.CENTER);

  public TicTacToeView(TicTacToeModel model) {
    this.model = model;
    setTitle("Tic Tac Toe");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(3, 3));
    initializeBoard(boardPanel);

    add(boardPanel, BorderLayout.CENTER);
    add(statusLabel, BorderLayout.SOUTH);
  }

  private void initializeBoard(JPanel boardPanel) {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        JButton button = new JButton();
        button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        button.setActionCommand(row + " " + col);
        button.addActionListener(this);
        buttons[row][col] = button;
        boardPanel.add(button);
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    String[] parts = command.split(" ");
    int row = Integer.parseInt(parts[0]);
    int col = Integer.parseInt(parts[1]);

    if (model.isGameOver() || model.getMarkAt(row, col) != null) {
      return;
    }

    model.move(row, col);
    updateBoard();
    updateStatus();

    if (model.isGameOver()) {
      announceGameOver();
    }
  }

  private void updateBoard() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        Player mark = model.getMarkAt(row, col);
        buttons[row][col].setText(mark == null ? "" : mark.toString());
      }
    }
  }

  private void updateStatus() {
    if (model.isGameOver()) {
      Player winner = model.getWinner();
      if (winner != null) {
        statusLabel.setText("Winner: " + winner);
      } else {
        statusLabel.setText("It's a tie!");
      }
    } else {
      statusLabel.setText("Turn: " + model.getTurn());
    }
  }

  private void announceGameOver() {
    Player winner = model.getWinner();
    if (winner != null) {
      JOptionPane.showMessageDialog(this, winner + " wins!");
    } else {
      JOptionPane.showMessageDialog(this, "The game ended in a tie!");
    }
  }
}
