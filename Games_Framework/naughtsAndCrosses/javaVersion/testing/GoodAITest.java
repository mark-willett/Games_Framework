package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import core.Board;
import core.NaughtsAndCrossesGame;
import core.NaughtsAndCrossesGoodAI;

public class GoodAITest {
	
	private Board b1, b2, b3;
	private NaughtsAndCrossesGoodAI ai;
	
	@Before
	public void setUp() {
		ai = new NaughtsAndCrossesGoodAI(new NaughtsAndCrossesGame(1));
		b1 = new Board(new String[][]{ 
			{"X", "O", "X"},
			{"O", "X", "O"},
			{"O", "X", ""}
			});
	}

	@Test
	public void test() {
		assertEquals(0, ai.maximise(b1, 0)[0]);
		assertEquals(-1, ai.minimise(b1, 0)[0]);
	}
	
	@Test
	public void test2() {
		b2 = new Board(new String[][]{ 
			{"X", "", "O"},
			{"", "O", ""},
			{"", "X", ""}
			});
		b2.printBoard();
		ai.makeMove(b2);
		//b2.printBoard();
	}
	
	@Test
	public void test3() {
		b3 = new Board(new String[][]{ 
			{"", "", ""},
			{"", "", ""},
			{"", "", ""}
			});
		b3.printBoard();
		ai.makeMove(b3);
		b3.printBoard();
	}

}
