//Button Controller
function ButtonController(controller, model, index) {
	this.controller = controller;
	this.model = model;
	this.index = index;
	this.card = this.model.cardsOnTable[this.index]
	this.background = controller.backG;
	this.label = "";
}

ButtonController.prototype.buttonClicked = function(){
	this.model.selectCard(this.card);
	this.controller.updateUI();
}