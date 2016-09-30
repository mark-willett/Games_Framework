package core;

import ai.NaughtsAndCrossesAI;
import ai.NaughtsAndCrossesRandomAI;

public class NaughtsAndCrossesGame {
	
	public static final String NAUGHT = "O";
    public static final String CROSS = "X";

    //private String[][] board;
    private Board board;
    private boolean isCrossesTurn;
    private boolean hasWon;
    private String winner;
    private int[][] winningLine;
    private NaughtsAndCrossesAI ai;
    private int numberOfPlayers;

    /**
     * Constructs a new instance of the class
     */
    public NaughtsAndCrossesGame(int numberOfPlayers) {
        //board = new String[3][3];
        board = new Board();
        isCrossesTurn = true;
        hasWon = false;
        this.numberOfPlayers = numberOfPlayers;
        if(numberOfPlayers == 1) {
            ai = new NaughtsAndCrossesGoodAI(this);
        }
    }

    /**
     * Checks whether it is crosses turn or not
     * @return whether it is crosses turn
     */
    public boolean isCrossesTurn() {
        return isCrossesTurn;
    }

    public boolean hasWon() {
        return hasWon;
    }

    public String getWinner() {
        return winner;
    }

    public int[][] getWinningLine() {
        return winningLine;
    }

    /**
     * Gets the board
     * @return the board
     */
//    public String[][] getBoard() {
//        return board;
//    }
    public Board getBoard() {
        return board;
    }

    /**
     * Sets whose turn it is
     */
    public void nextTurn() {
        isCrossesTurn =  !isCrossesTurn;
        if(numberOfPlayers == 1 && !isCrossesTurn && !hasWon()) {
            ai.makeMove();
        }
    }

    /**
     * Makes a move, either placing a naught or a cross
     * depending on whose go it is
     * @param row the row to place the marker on
     * @param col the column to place the marker on
     * @return an "X" or an "O" depending on whose turn it is
     */
    public String makeMove(int row, int col) {
//        String output = "";
//        if(row >= 0 && row < 3 && col >= 0 && col < 3) {
//            if(board[row][col] == null) {
//                if(isCrossesTurn) {
//                    output = "X";
//                } else {
//                    output = "O";
//                }
//                board[row][col] = output;
//                nextTurn();
//            }
//        }

        String marker;
        if(isCrossesTurn) {
            marker = CROSS;
        } else {
            marker = NAUGHT;
        }

        if(board.makeMove(row, col, marker)) {
            updateGameState();

            nextTurn();
            return marker;
        } else {
            return "";
        }

    }

    private void updateGameState() {
        GameState state = board.checkGameState();
        this.hasWon = state.hasWon();
        if(hasWon) {
            this.winner = state.getWinner();
            this.winningLine = state.getLine();
        }
    }


}
