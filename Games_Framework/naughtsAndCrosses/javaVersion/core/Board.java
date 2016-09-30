package core;

/**
 * @author Dom
 *
 */
public class Board {
	private String[][] board;

	public Board() {
		this.board = new String[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = "";
			}
		}
	}

	public Board(String[][] board) {
		this.board = board;
	}

	public String[][] getBoard() {
		return board;
	}
	
	/**
	 * Prints the board to the console
	 */
	public void printBoard() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(getBoard()[i][j] == "") {
					System.out.print("[ ]");
				} else {
					System.out.print("[" + getBoard()[i][j] + "]");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Gets a copy of the board
	 * @return a copy of the Board
	 */
	public Board getCopy() {
		String[][] boardCopy = new String[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		return new Board(boardCopy);
	}

	/**
	 * Checks whether the board is full or not
	 * @return whether the board is full
	 */
	public boolean isFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == "") {
					return false;
				}
			}
		}
		return true;
	}

	public boolean makeMove(int row, int col, String marker) {
		if (row >= 0 && row < 3 && col >= 0 && col < 3) {
			if (board[row][col] == "") {
				board[row][col] = marker;
				return true;
			}
		}
		return false;
	}

	/**
	 * // * Checks the state of the game, i.e whether one player // * has won
	 * and the winning line // * @return the current GameState //
	 */
	public GameState checkGameState() {
		GameState gameState = checkAllRows();
		if (gameState.hasWon()) {
			return gameState;
		} else {
			gameState = checkAllCols();
			if (gameState.hasWon()) {
				return gameState;
			} else {
				gameState = checkDiagonals();
				return gameState;
			}
		}
	}

	/**
	 * Checks all the rows to see if there is a winning line
	 * 
	 * @return the current GameState
	 */
	private GameState checkAllRows() {
		for (int i = 0; i < 3; i++) {
			GameState gameState = checkRow(i);
			if (gameState.hasWon()) {
				return gameState;
			}
		}
		return new GameState();
	}

	/**
	 * Checks a given row to see if there is a winning line
	 * 
	 * @param row
	 *            the index of the row to check
	 * @return the current GameState
	 */
	private GameState checkRow(int row) {
		int countX = 0;
		int countO = 0;

		for (String tile : board[row]) {
			if (tile == NaughtsAndCrossesGame.CROSS) {
				countX++;
			} else if (tile == NaughtsAndCrossesGame.NAUGHT) {
				countO++;
			}
		}

		int[][] line = { { row, 0 }, { row, 1 }, { row, 2 } };
		if (countX == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.CROSS);
		} else if (countO == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.NAUGHT);
		} else {
			return new GameState();
		}
	}

	/**
	 * Checks all columns to see if there is a winning line
	 * 
	 * @return the current GameState
	 */
	private GameState checkAllCols() {
		for (int i = 0; i < 3; i++) {
			GameState gameState = checkCol(i);
			if (gameState.hasWon()) {
				return gameState;
			}
		}
		return new GameState();
	}

	/**
	 * Check a given column for a winning line
	 * 
	 * @param col
	 *            the index of the column to check
	 * @return the current GameState
	 */
	private GameState checkCol(int col) {
		int countX = 0;
		int countO = 0;

		for (int i = 0; i < 3; i++) {
			String tile = board[i][col];
			if (tile == NaughtsAndCrossesGame.CROSS) {
				countX++;
			} else if (tile == NaughtsAndCrossesGame.NAUGHT) {
				countO++;
			}

		}

		int[][] line = { { 0, col }, { 1, col }, { 2, col } };
		if (countX == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.CROSS);
		} else if (countO == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.NAUGHT);
		} else {
			return new GameState();
		}
	}

	/**
	 * Checks both diagonals for a winning line
	 * 
	 * @return the current GameState
	 */
	private GameState checkDiagonals() {
		GameState gameState = checkTopDiagonal();
		if (gameState.hasWon()) {
			return gameState;
		} else {
			gameState = checkBottomDiagonal();
			return gameState;
		}
	}

	/**
	 * Checks the diagonal going from top-left to bottom-right for a winning
	 * line
	 * 
	 * @return the current GameState
	 */
	private GameState checkTopDiagonal() {
		int countX = 0;
		int countO = 0;
		int rowStart = 0;
		int colStart = 0;

		for (int i = 0; i < 3; i++) {
			int row = rowStart + i;
			int col = colStart + i;
			String tile = board[row][col];
			if (tile == NaughtsAndCrossesGame.CROSS) {
				countX++;
			} else if (tile == NaughtsAndCrossesGame.NAUGHT) {
				countO++;
			}
		}

		int[][] line = { { 0, 0 }, { 1, 1 }, { 2, 2 } };
		if (countX == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.CROSS);
		} else if (countO == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.NAUGHT);
		} else {
			return new GameState();
		}
	}

	/**
	 * Checks the diagonal going from bottom-left to top-right for a winning
	 * line
	 * 
	 * @return the current GameState
	 */
	private GameState checkBottomDiagonal() {
		int countX = 0;
		int countO = 0;
		int rowStart = 2;
		int colStart = 0;

		for (int i = 0; i < 3; i++) {
			int row = rowStart - i;
			int col = colStart + i;
			String tile = board[row][col];
			if (tile == NaughtsAndCrossesGame.CROSS) {
				countX++;
			} else if (tile == NaughtsAndCrossesGame.NAUGHT) {
				countO++;
			}
		}

		int[][] line = { { 2, 0 }, { 1, 1 }, { 0, 2 } };
		if (countX == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.CROSS);
		} else if (countO == 3) {
			return new GameState(line, true, NaughtsAndCrossesGame.NAUGHT);
		} else {
			return new GameState();
		}
	}
	
	public boolean equals(Board other) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(this.getBoard()[i][j] != other.getBoard()[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	
}
