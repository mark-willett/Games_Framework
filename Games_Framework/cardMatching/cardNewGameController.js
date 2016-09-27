//New Game controller
function NewGameButtonController(controller, model){
	this.controller = controller;
	this.model = model;
}

NewGameButtonController.prototype.newGameClicked = function(){
	this.model.newGame();
	this.controller.updateUI();
}