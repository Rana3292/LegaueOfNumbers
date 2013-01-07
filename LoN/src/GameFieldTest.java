import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author rana
 *
 */
public class GameFieldTest{
	@Test public void getStrikedStandardKont(){
		GameField gameField= new GameField();
		assertEquals(false, gameField.getStriked());
	}
	@Test public void getStrikedTrue(){
		GameField gameField = new GameField();
		gameField.setStriked();
		assertEquals(true, gameField.getStriked());
	}
	@Test public void getValueStandardKont(){
		GameField gameField = new GameField();
		assertNotNull(gameField.getValue());
	}
	@Test public void randomValuesBorder(){
		GameField gameField = new GameField(10);
		assertTrue(gameField.getValue() < 10);
	}
}
