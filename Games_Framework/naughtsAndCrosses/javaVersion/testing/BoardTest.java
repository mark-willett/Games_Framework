package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Board;
import core.GameState;

public class BoardTest {
	
	private Board b1, b2, b3, b4, b5, b6, b7;
	
	@Before
	public void setUp() {
		//Empty Board
		b1 = new Board();
		
		//Full board, draw
		b2 = new Board(new String[][]{ 
								{"X", "O", "X"},
								{"O", "X", "O"},
								{"O", "X", "O"}
								});
		
		//
		b3 = new Board(new String[][]{ 
								{"X", "O", ""},
								{"", "X", ""},
								{"", "", "O"}
								});
	}

	@Test
	public void isFullTest() {
		//b1.printBoard();
		//b2.printBoard();
		assertTrue(b2.isFull());
		assertFalse(b1.isFull());
	}
	
	@Test 
	public void copyTest() {
		b4 = b3.getCopy();
		b3.printBoard();
		b4.printBoard();
		assertTrue(b3.equals(b4));
		b4.makeMove(2, 0, "X");
		b3.printBoard();
		b4.printBoard();
		assertFalse(b3.equals(b4));
	}
	
	@Test
	public void checkRowsTest() {
		b5 = new Board(new String[][]{ 
			{"X", "X", "X"},
			{"", "O", ""},
			{"", "", "O"}
			});
		GameState state = b5.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "X");
		assertEquals(state.getLine(), new int[][]{{0,0},{0,1},{0,2}});
		
		b5 = new Board(new String[][]{ 
			{"X", "", "X"},
			{"O", "O", "O"},
			{"", "X", ""}
			});
		state = b5.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "O");
		assertEquals(state.getLine(), new int[][]{{1,0},{1,1},{1,2}});
		
		b5 = new Board(new String[][]{ 
			{"X", "", "X"},
			{"", "O", ""},
			{"O", "O", "O"}
			});
		state = b5.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "O");
		assertEquals(state.getLine(), new int[][]{{2,0},{2,1},{2,2}});
	}
	
	@Test
	public void checkColumnsTest() {
		b6 = new Board(new String[][]{ 
			{"X", "O", "X"},
			{"X", "O", ""},
			{"X", "", "O"}
			});
		GameState state = b6.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "X");
		assertEquals(state.getLine(), new int[][]{{0,0},{1,0},{2,0}});
		
		b6 = new Board(new String[][]{ 
			{"X", "O", "X"},
			{"X", "O", ""},
			{"", "O", "O"}
			});
		state = b6.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "O");
		assertEquals(state.getLine(), new int[][]{{0,1},{1,1},{2,1}});
		
		b6 = new Board(new String[][]{ 
			{"X", "O", "X"},
			{"", "O", "X"},
			{"X", "", "X"}
			});
		state = b6.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "X");
		assertEquals(state.getLine(), new int[][]{{0,2},{1,2},{2,2}});
	}
	
	@Test
	public void checkDiagonalsTest() {
		b7 = new Board(new String[][]{ 
			{"X", "", "O"},
			{"", "X", ""},
			{"O", "", "X"}
			});
		GameState state = b7.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "X");
		assertEquals(state.getLine(), new int[][]{{0,0},{1,1},{2,2}});
		
		b7 = new Board(new String[][]{ 
			{"X", "", "O"},
			{"", "O", ""},
			{"O", "", "X"}
			});
		state = b7.checkGameState();
		assertTrue(state.hasWon());
		assertEquals(state.getWinner(), "O");
		assertEquals(state.getLine(), new int[][]{{2,0},{1,1},{0,2}});
	}

}
