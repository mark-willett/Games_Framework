package gui;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class NaughtsAndCrossesView extends GridPane implements Observer {
	
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;
	
	private NaughtsAndCrossesModel model;
	private Button[] buttons;
	
	public NaughtsAndCrossesView(NaughtsAndCrossesModel model) {
		this.model = model;
		setPrefWidth(WIDTH);
		setPrefHeight(HEIGHT);
		buttons = new Button[9];
		for(int i = 0; i < 9; i++) {
			buttons[i] = new Button();
			buttons[i].setId(""+i);
			buttons[i].setPrefWidth(WIDTH/3);
			buttons[i].setPrefHeight(HEIGHT/3);
			add(buttons[i], i/3, i%3);
			
		}
	}
	
	public Button[] getButtons() {
		return buttons;
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(model.getBoard()[i][j] != null) { 
					buttons[3*i + j].setText(model.getBoard()[i][j]);
				}
			}
		}
		
	}

}
