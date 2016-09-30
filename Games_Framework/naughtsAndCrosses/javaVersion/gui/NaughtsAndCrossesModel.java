package gui;

import java.util.Observable;

import core.NaughtsAndCrossesGame;

public class NaughtsAndCrossesModel extends Observable {
	
	private NaughtsAndCrossesGame game;
	
	public NaughtsAndCrossesModel() {
		this.game = new NaughtsAndCrossesGame(1);
	}
	
	public NaughtsAndCrossesModel(NaughtsAndCrossesGame game) {
		this.game = game;
	}
	
	public String[][] getBoard() {
		return game.getBoard().getBoard();
	}
	
	public void makeMove(int row, int col) {
		game.makeMove(row, col);
		setChanged();
		notifyObservers();
	}

}
