package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * The view class that can collaborate with SwingTicTacToeController class and TicTacToeModel class.
 * By MVC pattern, make the game playable on GUI.
 */
public class SwingTicTacToeView extends JFrame {
  private final JButton[][] buttons = new JButton[3][3];
  private final JLabel statusLabel = new JLabel();

  /**
   * Constructs a SwingTicTacToeView with the specified title. Set the buttons and other data for
   * the GUI.
   *
   * @param title Given title in format of String. Define the title of the GUI.
   */
  public SwingTicTacToeView(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(600, 600);
    setLayout(new BorderLayout());

    JPanel boardPanel = new JPanel();
    boardPanel.setLayout(new GridLayout(3, 3));
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(200, 200));
        button.setActionCommand(i + " " + j);
        buttons[i][j] = button;
        boardPanel.add(button);
      }
    }

    add(boardPanel, BorderLayout.CENTER);
    add(statusLabel, BorderLayout.SOUTH);

    setVisible(true);
  }

  /**
   * Add the listener to each button.
   *
   * @param listener input listener to be added.
   */
  public void setButtonListener(ActionListener listener) {
    for (JButton[] buttonRow : buttons) {
      for (JButton button : buttonRow) {
        button.addActionListener(listener);
      }
    }
  }

  /**
   * Update the game status in the status Label.
   *
   * @param text the new status of the game in String.
   */
  public void setGameStatus(String text) {
    statusLabel.setText(text);
  }

  /**
   * Update the view of the piece in the board.
   *
   * @param board the new board with new location of the pieces
   */
  public void updateBoard(Player[][] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] != null) {
          buttons[i][j].setText(board[i][j].toString());
          buttons[i][j].setEnabled(false);
        } else {
          buttons[i][j].setText("");
          buttons[i][j].setEnabled(true);
        }
      }
    }
  }
}
