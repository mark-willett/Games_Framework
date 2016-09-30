package core;

public class GameState {
	
	private int[][] line;
	private boolean hasWon;
	private String winner;

	/**
	 * Constructs an instance of the GameState where there is not currently a
	 * winner
	 */
	public GameState() {
		hasWon = false;
	}

	/**
	 * Constructs an instance of the GameState where there is a winner
	 * 
	 * @param line
	 *            the winning line
	 * @param hasWon
	 *            boolean value, whether one player has won
	 * @param winner
	 *            the player who one
	 */
	public GameState(int[][] line, boolean hasWon, String winner) {
		if ((winner == "X" || winner == "O") && line.length == 3) {
			this.winner = winner;
			this.hasWon = hasWon;
			this.line = line;
		}
	}

	/**
	 * Gets the winning line
	 * 
	 * @return the coordinates of the winning line
	 */
	public int[][] getLine() {
		return line;
	}

	/**
	 * Gets whether one player has won or not
	 * 
	 * @return a boolean, whether one player has won
	 */
	public boolean hasWon() {
		return hasWon;
	}

	/**
	 * Gets the symbol of the winning player, "X" or "O"
	 * 
	 * @return the symbol of the winning player
	 */
	public String getWinner() {
		return winner;
	}
}
