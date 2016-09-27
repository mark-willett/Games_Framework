
//Card class
function Card() {
	this.matched = false;
	this.selected = false;
	this.label;
}

Card.prototype.matchesCard = function(otherCard){
	if(otherCard.label == this.label){
		return true;
	} else {
		return false;
	}
}

Card.prototype.faceUp = function(){
	if(this.matched || this.selected){
		return true;
	} else {
		return false;
	}
}

//PlayingCard class
PlayingCard.prototype = new Card();
PlayingCard.prototype.constructor = PlayingCard;
function PlayingCard(suit, rank){
	Card.prototype.constructor.call(this);
	this.suit = suit;
	this.rank = rank;
	this.label = suit + rank;
}

//Deck class
function Deck(){
	this.cards = [];
}

Deck.prototype.addCard = function(card){
	this.cards.push(card);
}

Deck.prototype.drawRandomCard= function(){
	if(this.cards.length == 0){
		return null;
	} else {
		var index = Math.floor(Math.random() * (this.cards.length - 1));
		var drawnCard = this.cards.splice(index, 1);
		return drawnCard[0];
	}
}

//PlayingCardDeck class
PlayingCardDeck.prototype = new Deck();
PlayingCardDeck.prototype.constructor = PlayingCardDeck;
function PlayingCardDeck(){
	Deck.prototype.constructor.call(this);
	this.reloadDeck();
}

PlayingCardDeck.prototype.reloadDeck = function(){
	this.cards = [];
	var self = this;
	
	var suits = ['\u2660','\u2665','\u2666','\u2663'];
	var ranks = ["A","2","3","4","5","6","7","8","9","10","J","Q","K"];
	
	var card;
	suits.forEach(function (suitValue, suitIndex, suitArray){
		ranks.forEach(function (rankValue, rankIndex, rankArray){
			card = new PlayingCard(rankValue, suitValue);
			self.addCard(card);
		});
	});
}

//PlayingSurface class
function PlayingSurface(cardSlots) {
	this.cardSlots = cardSlots
	this.cardsOnTable = [];
	this.numberOfCardsSelected = 0;
	this.deck;
}

PlayingSurface.prototype.setUpPlayingCardTable = function(){
	this.deck = new PlayingCardDeck();
	this.placeCards();
}

PlayingSurface.prototype.placeCards = function(){
	var cardsReq = this.cardSlots / 2;
	
	var drawnCard;
	
	var unmixedCards = [];
	for(var i = 0; i < cardsReq; i++){
		drawnCard = this.deck.drawRandomCard();
		var cardCopy = new PlayingCard(drawnCard.suit, drawnCard.rank);
		unmixedCards.push(drawnCard);
		unmixedCards.push(cardCopy);
	}
	
	for(var i = 0; i < this.cardSlots; i++){
		var index = Math.floor(Math.random() * (unmixedCards.length - 1));
		var drawnCard = unmixedCards.splice(index, 1)[0];
		this.cardsOnTable.push(drawnCard);
	}
}

PlayingSurface.prototype.newGame = function(){
	this.cardsOnTable = [];
	this.numberOfCardsSelected = 0;
	this.deck.reloadDeck();
	this.placeCards();
}

PlayingSurface.prototype.selectCard = function(card) {
	var self = this;
	
	console.log("label : " + card.label);
	if(self.numberOfCardsSelected == 0){
		card.selected = true;
		self.numberOfCardsSelected = 1;		
	} else if (self.numberOfCardsSelected == 1){
		
		self.cardsOnTable.forEach(function (cardValue, cardIndex, cardArray){
			if(cardValue.selected && cardValue.label == card.label){
				cardValue.matched = true;
				card.matched = true;
			}
		});
		card.selected = true;
		
		self.numberOfCardsSelected = 2;
	} else if (self.numberOfCardsSelected == 2){
		self.cardsOnTable.forEach(function (cardValue, cardIndex, cardArray){
			cardValue.selected = false;
		});
		card.selected = true;
		self.numberOfCardsSelected = 1;
	} else {
		console.log("Too many cards selected");
		debugger
	}
}
