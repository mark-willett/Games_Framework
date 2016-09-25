
var board = createEmptyBoard();
var isCrossesTurn = true;
var hasWon = false;
var winningLine = [[,],[,],[,]];

function newGame() {
	board = createEmptyBoard();
	isCrossesTurn = true;
	hasWon = false;
	winningLine = [[,],[,],[,]];
	clearButtons();
}


function clearButtons() {
	for(var i = 0; i < 3; i++) {
		for(j = 0; j < 3; j++) {
			var button = document.getElementById(getButtonId(i, j));
			button.textContent = "";
			button.style.background = "#FFFFFF";
		}
	}
}

function createEmptyBoard() {
	var outer = new Array(3);
	for(var i = 0; i < outer.length; i++) {
		outer[i] = new Array(3);
	}
	
	return outer;
}

function printBoard() {
	var str = "";
	for(var i = 0; i < board.length; i++) {
		for(j = 0; j < board[i].length; j++) {
			if(board[i][j] === undefined) {
				//console.log("_");
				str += "_";
			} else {
				//console.log(board[i][j]);				
				str += board[i][j];
			}
			if(j < board[i].length - 1) {
				//console.log("|");
			}
			
		}
		//console.log("\n")
		str += "\n";
	}
	alert(str);
}

function makeMove(buttonId) {
	var button = document.getElementById(buttonId);
	if(!hasWon) {
		if(button.textContent === "") {
			var marker;
			if(isCrossesTurn) {
				marker = "X";
			} else {
				marker = "O"
			}
			button.textContent = marker;
			
			var coords = getBoardPosition(buttonId);
			
			//console.log(coords);
			addMoveToArray(coords[0], coords[1], marker);
			
			isCrossesTurn = !isCrossesTurn;
			
			checkForWin();
			console.log(isCrossesTurn);
			if(!isCrossesTurn) {
				aiMove();
			}
			
		}
	}
}

function aiMove() {
	if(!hasWon) {
		var row = Math.floor(Math.random() * 3);
		var col = Math.floor(Math.random() * 3);
		while(board[row][col] == "X" || board[row][col] == "O") {
			row = Math.floor(Math.random() * 3);
			col = Math.floor(Math.random() * 3);
		}
		
		var buttonId = getButtonId(row, col);
		makeMove(buttonId);
	}
}



function addMoveToArray(row, col, marker) {
	
	board[row][col] = marker;
	//alert(board[row][col]);
}

function getBoardPosition(buttonId) {
	switch(buttonId) {
		case "top-left":
			return [0, 0];
		case "top-middle":
			return [0, 1];
		case "top-right":
			return [0, 2];
		case "middle-left":
			return [1, 0];
		case "middle":
			return [1, 1];
		case "middle-right":
			return [1, 2];
		case "bottom-left":
			return [2, 0];
		case "bottom-middle":
			return [2, 1];
		case "bottom-right":
			return [2, 2];

	}
}

function checkForWin() {
	
	checkRows();
	
	checkColumns();
	
	checkDiagonals();
	
	if(hasWon) {
		for(var i = 0; i < 3; i++) {
			updateButtonColor(getButtonId(winningLine[i][0], winningLine[i][1]));
		}
	}

}

function updateButtonColor(buttonId) {
	document.getElementById(buttonId).style.background = "#00FF00";
}

function checkRows() {
	for(var i = 0; i < 3; i++) {
		if(!hasWon) {
			checkRow(i);
			
		}
		//alert("Row " + i + " hasWon = " + hasWon);
	}
}

function checkRow(row) {
	var crosses = 0;
	var naughts = 0;
	for(var i = 0; i < 3; i++) {
		if(board[row][i] == "X") {
			crosses++;
		} else if (board[row][i] == "O") {
			naughts++;
		}
	}

	if(crosses == 3 || naughts == 3) {
		hasWon = true;
		winningLine = [[row, 0], [row, 1], [row, 2]];
	} 
	
	return hasWon;
}

function checkColumns() {
	for(var i = 0; i < 3; i++) {
		if(!hasWon) {
			checkColumn(i);			
		}
	}
}

function checkColumn(column) {
	var crosses = 0;
	var naughts = 0;
	for(var i = 0; i < 3; i++) {
		if(board[i][column] == "X") {
			crosses++;
		} else if (board[i][column] == "O") {
			naughts++;
		} 
	}
	
	if(crosses == 3 || naughts == 3) {
		hasWon = true;
		winningLine = [[0, column], [1, column], [2, column]];
	} 
	
	return hasWon;
}

function checkDiagonals() {
	if(!hasWon) {
		checkTopDiagonal();
	}
	
	if(!hasWon) {
		checkBottomDiagonal();		
	}
	
}

function checkTopDiagonal() {
	var crosses = 0;
	var naughts = 0;
	
	var rowStart = 0;
	var colStart = 0;
	
	for(var i = 0; i < 3; i++) {
		var row = rowStart + i;
		var col = colStart + i;
		if(board[row][col] == "X") {
			crosses++;
		} else if (board[row][col] == "O") {
			naughts++;
		} 
	}
	
	if(crosses == 3 || naughts == 3) {
		hasWon = true;
		winningLine = [[0, 0], [1, 1], [2, 2]];
	} 
	
	
}

function checkBottomDiagonal() {
	var crosses = 0;
	var naughts = 0;
	
	var rowStart = 2;
	var colStart = 0;
	
	for(var i = 0; i < 3; i++) {
		var row = rowStart - i;
		var col = colStart + i;
		if(board[row][col] == "X") {
			crosses++;
		} else if (board[row][col] == "O") {
			naughts++;
		} 
	}
	
	if(crosses == 3 || naughts == 3) {
		hasWon = true;
		winningLine = [[2, 0], [1, 1], [0, 2]];
	} 
}

function getButtonId(row, col) {
	switch(row) {
		case 0:
			switch(col) {
				case 0:
					return "top-left";
				case 1:
					return "top-middle";
				case 2:
					return "top-right";
			}
		case 1:
			switch(col) {
				case 0:
					return "middle-left";
				case 1:
					return "middle";
				case 2:
					return "middle-right";
			}
		case 2:
			switch(col) {
				case 0:
					return "bottom-left";
				case 1:
					return "bottom-middle";
				case 2:
					return "bottom-right";
			}
	}
}
