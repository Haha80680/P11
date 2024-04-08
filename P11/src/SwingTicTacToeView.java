import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingTicTacToeView extends JFrame {
  private final JButton[][] buttons = new JButton[3][3];
  private final JLabel statusLabel = new JLabel();

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

  public void setButtonListener(ActionListener listener) {
    for (JButton[] buttonRow : buttons) {
      for (JButton button : buttonRow) {
        button.addActionListener(listener);
      }
    }
  }

  public void setGameStatus(String text) {
    statusLabel.setText(text);
  }

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
