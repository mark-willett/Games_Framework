//New Game controller
function NewGameButtonController(controller, model){
	this.controller = controller;
	this.model = model;
}

NewGameButtonController.prototype.newGameClicked = function(){
	this.model.newGame();
	this.model.numberOfTurns = 0;
	this.controller.buttonListeners.forEach(function (buttonValue, buttonIndex, buttonArray){
		buttonValue.resetButton();
	});
	this.controller.updateUI();
}