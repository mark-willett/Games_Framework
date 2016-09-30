package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class NaughtsAndCrossesController {
	
	public NaughtsAndCrossesController(NaughtsAndCrossesView view, NaughtsAndCrossesModel model) {
		for(Button button : view.getButtons()) {
			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					int index = Integer.parseInt(button.getId());
					model.makeMove(index/3, index%3);
//					button.setText("X");
				}
				
			});
		}
	}

}
