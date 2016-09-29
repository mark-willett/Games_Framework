//handles initial creation of model and linking of buttons to actions

function SetUp(){

	this.noOfButtons = 12;

	this.playingSurface = new PlayingSurface(this.noOfButtons);
	this.playingSurface.setUpPlayingCardTable();

	//this.backG = 'C:\\Users\\Dom\\git\\Games_Framework\\Games_Framework\\cardMatching\\gear-backs-transparent.png';
	//this.foreG = 'C:\\Users\\Dom\\git\\Games_Framework\\Games_Framework\\cardMatching\\card-fronts-transparent.png';

	this.buttonListeners = [];

	for(var i = 0; i < this.noOfButtons; i++){
		this.buttonListeners.push(new ButtonController(this, this.playingSurface, i));
	}

	this.newGameListener = new NewGameButtonController(this, this.playingSurface);
	this.updateUI();
}

SetUp.prototype.updateUI = function (){
	var currentButton;
	var currentButtonImgID;
	for(var i = 0; i < this.noOfButtons; i++){
		currentButton = this.buttonListeners[i];
		currentButtonButID = indexToButtonName(i);
		currentButtonSuitID = indexToSuitName(i);
		currentButtonRankID = indexToRankName(i);
		console.log("passing : " + currentButton.card.suit);
		suitString = unicodeToSuit(currentButton.card.suit);
		console.log("submitting : " + suitString);
		
		if(currentButton.card.faceUp()){
			document.getElementById(currentButtonButID).setAttribute('class', 'faceUp');
			document.getElementById(currentButtonRankID).textContent = currentButton.card.rank;
			document.getElementById(currentButtonSuitID).textContent = currentButton.card.suit;
			document.getElementById(currentButtonSuitID).setAttribute('suit', suitString);
		} else {
			document.getElementById(currentButtonButID).setAttribute('class', 'faceDown');
			document.getElementById(currentButtonRankID).textContent = "";
			document.getElementById(currentButtonSuitID).textContent = "";
		}
	}

	document.getElementById('turnNumber').innerHTML = 'Turns: ' + this.playingSurface.numberOfTurns;
}

SetUp.prototype.HTMLButtonClicked = function (buttonLabel){
	var buttonIndex = indexFinder(buttonLabel);
	this.buttonListeners[buttonIndex].buttonClicked();
}

function unicodeToSuit(unicode){
	
	switch(unicode) {
	case "\u2660":
		return "spades";
	case "\u2665":
		return "hearts";
	case "\u2666":
		return "diamonds";
	case "\u2663":
		return "clubs";
	}
}

function indexFinder(buttonLabel){
	switch(buttonLabel) {
	case "but1":
		return 0;
	case "but2":
		return 1;
	case "but3":
		return 2;
	case "but4":
		return 3;
	case "but5":
		return 4;
	case "but6":
		return 5;
	case "but7":
		return 6;
	case "but8":
		return 7;
	case "but9":
		return 8;
	case "but10":
		return 9;
	case "but11":
		return 10;
	case "but12":
		return 11;
	}
}

function indexToButtonName(index){
	switch(index) {
	case 0:
		return "but1";
	case 1:
		return "but2";
	case 2:
		return "but3";
	case 3:
		return "but4";
	case 4:
		return "but5";
	case 5:
		return "but6";
	case 6:
		return "but7";
	case 7:
		return "but8";
	case 8:
		return "but9";
	case 9:
		return "but10";
	case 10:
		return "but11";
	case 11:
		return "but12";
	}
}

function indexToRankName(index){
	switch(index) {
	case 0:
		return "rank1";
	case 1:
		return "rank2";
	case 2:
		return "rank3";
	case 3:
		return "rank4";
	case 4:
		return "rank5";
	case 5:
		return "rank6";
	case 6:
		return "rank7";
	case 7:
		return "rank8";
	case 8:
		return "rank9";
	case 9:
		return "rank10";
	case 10:
		return "rank11";
	case 11:
		return "rank12";
	}
}

function indexToSuitName(index){
	switch(index) {
	case 0:
		return "suit1";
	case 1:
		return "suit2";
	case 2:
		return "suit3";
	case 3:
		return "suit4";
	case 4:
		return "suit5";
	case 5:
		return "suit6";
	case 6:
		return "suit7";
	case 7:
		return "suit8";
	case 8:
		return "suit9";
	case 9:
		return "suit10";
	case 10:
		return "suit11";
	case 11:
		return "suit12";
	}
}

SetUp.prototype.HTMLNewGameClicked = function(){
	this.newGameListener.newGameClicked();
}