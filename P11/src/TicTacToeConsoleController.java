import java.io.IOException;
import java.util.Scanner;

/**
 * This is a controller for the TicTacToe game that reads input from the console and writes output to the console.
 */
public class TicTacToeConsoleController implements TicTacToeController {
  private final Scanner scanner;
  private final Appendable appendable;

  /**
   * Construct a new TicTacToeConsoleController.
   * @param in the input source.
   * @param out the output destination.
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    this.scanner = new Scanner(in);
    this.appendable = out;
  }

  /**
   * Play a game of Tic Tac Toe.
   * @param m a non-null tic tac toe Model
   */
  public void playGame(TicTacToe m) {
    if (m == null) {
      throw new IllegalArgumentException("The model cannot be null.");
    }
    boolean printBoardNext = true;
    // Play the game
    try {
      while (!m.isGameOver()) {
        if (printBoardNext) {
          appendable.append(m.toString()).append("\n");
          appendable.append("Enter a move for ").append(m.getTurn().toString()).append(":\n");
        }
        // Check if there is no more input
        if (!scanner.hasNext()) {
          appendable.append("Game is over!\n");
          break;
        }
        String input = scanner.next().trim();
        // Check if the input is "q", if so, quit the game
        if ("q".equalsIgnoreCase(input)) {
          appendable.append("Game quit! Ending game state:\n").append(m.toString()).append("\n");
          break;
        }
        int row;
        // Check if the input is a number
        try {
          row = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
          appendable.append("Not a valid number: ").append(input).append("\n");
          printBoardNext = false;
          if(scanner.hasNext()) {
            scanner.next();
          }
          continue;
        }
        // Check if the row number is valid
        if (!scanner.hasNextInt()) {
          appendable.append("Expecting a column number after the row number.\n");
          printBoardNext = false;
          continue;
        }
        int col = scanner.nextInt() - 1;
        // Check if the column number is valid
        try {
          m.move(row, col);
          printBoardNext = true;
        } catch (IllegalArgumentException e) {
          appendable.append("Not a valid move: ").append(String.valueOf(row + 1)).append(" ").append(String.valueOf(col + 1)).append("\n");
          printBoardNext = false;
        }
      }
      // Print the final game state
      if (m.isGameOver()) {
        appendable.append(m.toString()).append("\n");
        Player winner = m.getWinner();
        // Check if there is a winner, if so, print the winner
        if (winner != null) {
          appendable.append("Game is over! ");
          appendable.append(winner.toString()).append(" wins.\n");
          // Check if there is no winner, if so, print a tie game
        } else {
          appendable.append("Game is over! Tie game.\n");
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("An error occurred.",e);
    }
  }
}


