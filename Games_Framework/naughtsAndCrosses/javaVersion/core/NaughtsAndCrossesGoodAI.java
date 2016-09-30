package core;

import ai.NaughtsAndCrossesAI;

/**
 * Created by Dom on 26/09/2016.
 */

public class NaughtsAndCrossesGoodAI extends NaughtsAndCrossesAI {
	
	private int maxDepth = 10;

    public NaughtsAndCrossesGoodAI(NaughtsAndCrossesGame game) {
        this.game = game;
    }
    
    @Override
    public void makeMove() {
    	makeMove(game.getBoard().getCopy());
    }

    //@Override
    public void makeMove(Board board) {
        //Board board = game.getBoard().getCopy();
        int[] bestMove = new int[2];
        int bestScore = -1;
        int bestDepth = Integer.MAX_VALUE;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(board.getBoard()[i][j] == "") {
//                    System.out.println("AI is checking " + i + " " + j);
                    //Simulate playing on that square
                    Board copy = board.getCopy();
                    copy.makeMove(i, j, "O");
                    //copy.printBoard();
                    int score[] = minimise(copy, 0);
//                    System.out.println("Score was: " + score[0] + " with depth: " + score[1]);
                    if((score[0] > bestScore) || (score[0] == bestScore && score[1] < bestDepth)) {
                    	bestScore = score[0];
                        bestDepth = score[1];
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                    
//                    if(score[0] >= bestScore && score[1] < bestDepth) {
//                        bestScore = score[0];
//                        bestDepth = score[1];
//                        bestMove[0] = i;
//                        bestMove[1] = j;
//                    }

                }
            }
        }
//        System.out.println("Best move: [" + bestMove[0] + ", " + bestMove[1] + "]");
//        if(bestScore == 1) {
//        	System.out.println("Results in a win after " + bestDepth + " moves");
//        } else if (bestScore == -1) {
//        	System.out.println("Results in a loss after " + bestDepth + " moves");
//        } else {
//        	System.out.println("Results in a draw after " + bestDepth + " moves");
//        }
        game.makeMove(bestMove[0], bestMove[1]);
    }

    public int[] maximise(Board board, int depth) {
//    	System.out.println("Max Step, depth " + depth);
        if(board.checkGameState().hasWon() || board.isFull() || depth > maxDepth) {
//        	System.out.println("Max step returning score of " + getScore(board) + " at depth of " + depth);
            return new int[] {getScore(board), depth};
        } else {
            int max = -1;
            int solutionDepth = depth;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board.getBoard()[i][j] == "") {
                        Board copy = board.getCopy();
                        copy.makeMove(i, j, "O");
                        int min[] = minimise(copy, depth + 1);
                        if (min[0] >= max) {
                            max = min[0];
                            solutionDepth = min[1];
                        }
                    }

                }
            }
            return new int[] {max, solutionDepth};
        }
    }

    public int[] minimise(Board board, int depth) {
//    	System.out.println("Min Step, depth " + depth);
        if(board.checkGameState().hasWon() || board.isFull() || depth > maxDepth) {
//        	System.out.println("Min step returning score of " + getScore(board) + " at depth of " + depth);
            return new int[] {getScore(board), depth};
        } else {
            int min = 1;
            int solutionDepth = depth;
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(board.getBoard()[i][j] == "") {
                        Board copy = board.getCopy();
                        copy.makeMove(i, j, "X");
                        int[] max = maximise(copy, depth + 1);
                        if (min >= max[0]) {
                            min = max[0];
                            solutionDepth = max[1];
                        }
                    }

                }
            }
            return new int[] {min, solutionDepth};
        }
    }

    public int getScore(Board board) {
        GameState state = board.checkGameState();
        if(!state.hasWon()) {
            return 0;
        } else {
            if(state.getWinner() == NaughtsAndCrossesGame.CROSS) {
                return -1;
            } else {
                return 1;
            }
        }
    }


}