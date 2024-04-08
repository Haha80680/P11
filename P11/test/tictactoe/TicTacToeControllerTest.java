package tictactoe;

import static org.junit.Assert.assertEquals;
import java.io.StringReader;
import java.util.Arrays;
import org.junit.Test;

import tictactoe.FailingAppendable;
import tictactoe.TicTacToe;
import tictactoe.TicTacToeConsoleController;
import tictactoe.TicTacToeController;
import tictactoe.TicTacToeModel;

/**
 * Test cases for the tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner
  // Input where the q comes instead of an integer for the row
  // Input where the q comes instead of an integer for the column
  // Input where non-integer garbage comes instead of an integer for the row
  // Input where non-integer garbage comes instead of an integer for the column
  // Input where the move is integers, but outside the bounds of the board
  // Input where the move is integers, but invalid because the cell is occupied
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input, but not quit, and game not over
  // THIS IS NOT AN EXHAUSTIVE LIST

  /**
   * Test a single valid move.
   */
  @Test
  public void testSingleValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for X:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "Enter a move for O:\n"
        + "Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   | X |  \n"
        + "-----------\n"
        + "   |   |  \n", gameLog.toString());
  }

  /**
   * Test bogus input as row.
   */
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
        Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  \n"
        + "-----------\n"
        + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test the tie game when input is valid.
   */
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  /**
   * Test the failing appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  /**
   * Test the game if X wins in a line.
   * X wins in the second row.
   */
  @Test
  public void testXWinsInLine() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 1 1 1 2 2 1 2 2 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if X wins in a column.
   * X wins in the first column.
   */
  @Test
  public void testXWinsInColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 2 1 2 2 3 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if X wins in a diagonal.
   */
  @Test
public void testXWinsInDiagonal() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("1 1 1 2 2 2 3 2 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if O wins in a line.
   * O wins in the first row.
   */
  @Test
  public void testOwinsinLine() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 1 1 1 3 2 1 2 2 2 1 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if O wins in a column.
   * O wins in the second column.
   */
  @Test
  public void testOwinsinColumn() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 1 1 2 1 1 2 2 3 3 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if O wins in a diagonal.
   */
  @Test
  public void testOwinsinDiagonal() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 1 1 1 3 1 2 2 1 3 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
  }

  /**
   * Test the game if the input is invalid.
   */
  @Test
  public void testNotValidMove() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 10");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Game is over!", lines[lines.length - 1]);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Enter a move for X:\n"
            + "Not a valid move: 2 10\n"
            + "Game is over!\n",gameLog.toString());
  }
}
