package GameField;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 */

/**
 * @author rana
 *
 */
public class FieldTest{
	@Test public void getStrikedStandardKont(){
		Field gameField= new Field();
		assertEquals(false, gameField.getStriked());
	}
	@Test public void getStrikedTrue(){
		Field gameField = new Field();
		gameField.setStriked();
		assertEquals(true, gameField.getStriked());
	}
	@Test public void getValueStandardKont(){
		Field gameField = new Field();
		assertNotNull(gameField.getValue());
	}
	@Test public void randomValuesBorder(){
		Field gameField = new Field(10);
		assertTrue(gameField.getValue() < 10);
	}
}
