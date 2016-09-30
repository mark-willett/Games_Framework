package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NaughtsAndCrossesApp extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		//Set up model
		NaughtsAndCrossesModel model = new NaughtsAndCrossesModel();
		
		//Set up view
		NaughtsAndCrossesView view = new NaughtsAndCrossesView(model);
		model.addObserver(view);
		
		//Set up scene
		Scene scene = new Scene(view, view.getPrefWidth(), view.getPrefHeight());
		
		//Set up controller
		NaughtsAndCrossesController controller = new NaughtsAndCrossesController(view, model);
		
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
