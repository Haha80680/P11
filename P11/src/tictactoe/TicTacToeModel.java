package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This implementation of the tictactoe.TicTacToe interface.
 */
public class TicTacToeModel implements TicTacToe {
  private Player[][] board = new Player[3][3];
  private Player currentTurn = Player.X;
  private boolean gameOver = false;
  private Player winner = null;

  /**
   * Execute a move in the position specified by the given row and column.
   * @param r the row of the intended move.
   * @param c the column of the intended move.
   */
  @Override
  public void move(int r, int c) {
    validateMove(r, c);
    board[r][c] = currentTurn;
    if (isWin(r, c)) {
      winner = currentTurn;
      gameOver = true;
    } else if (isBoardFull()) {
      gameOver = true;
    }
    currentTurn = (currentTurn == Player.X) ? Player.O : Player.X;
  }

  /**
   * Get the current turn.
   * @return the current turn.
   */
  @Override
  public Player getTurn() {
    return currentTurn;
  }

  /**
   * Return whether the game is over.
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    return gameOver;
  }

  /**
   * Return the winner of the game.
   * @return the winner of the game.
   */
  @Override
  public Player getWinner() {
    return winner;
  }

  /**
   * Return the current game state.
   * @return the current game state.
   */
  @Override
  public Player[][] getBoard() {
    return copyBoard();
  }

  /**
   * Return the current player mark at a given row and column.
   * @param r the row
   * @param c the column
   * @return the player at the given position.
   */
  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return board[r][c];
  }

  /**
   * Validate the move.
   * @param r the row.
   * @param c the column.
   */
  private void validateMove(int r, int c) {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Position is out of bounds.");
    }
    if (board[r][c] != null) {
      throw new IllegalArgumentException("Position already taken.");
    }
    if (gameOver) {
      throw new IllegalStateException("The game is already over.");
    }
  }

  /**
   * Check if the board is full.
   * @return true if the board is full, false otherwise.
   */
  private boolean isBoardFull() {
    for (Player[] row : board) {
      for (Player p : row) {
        if (p == null) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check if there is a win.
   * @param r the row.
   * @param c the column.
   * @return true if there is a win, false otherwise.
   */
  private boolean isWin(int r, int c) {
    // Check rows, columns, and diagonals for a win
    return (checkLine(board[r][0], board[r][1], board[r][2]) ||
            checkLine(board[0][c], board[1][c], board[2][c]) ||
            checkLine(board[0][0], board[1][1], board[2][2]) ||
            checkLine(board[0][2], board[1][1], board[2][0]));
  }

  /**
   * Check if there is a line.
   * @param a the first player.
   * @param b the second player.
   * @param c the third player.
   * @return true if there is a line, false otherwise.
   */
  private boolean checkLine(Player a, Player b, Player c) {
    return a != null && a == b && b == c;
  }

  /**
   * Copy the board.
   * @return the copied board.
   */
  private Player[][] copyBoard() {
    Player[][] newBoard = new Player[3][];
    for (int i = 0; i < board.length; i++) {
      newBoard[i] = board[i].clone();
    }
    return newBoard;
  }

  /**
   * Return the string representation of the board.
   * @return the string representation of the board.
   */
  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard())
            .map(row -> " " + Arrays.stream(row)
                    .map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
  }
}


