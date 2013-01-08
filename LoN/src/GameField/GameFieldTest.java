package GameField;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author rana
 *
 */
public class GameFieldTest {
	private GameField field, field10x10, field15x3;
	@Before public void setUp(){
		field = new GameField();
		field10x10 = new GameField(10);
		field15x3 = new GameField(15, 3);
	}
	
	@Test
	public void getHeight() {
		assertEquals(20, field.getHeight());
	}
	@Test
	public void getWidth(){
		assertEquals(20, field.getWidth());
	}
	@Test
	public void getHeightQuadraticField(){
		assertEquals(10, field10x10.getHeight());
	}
	@Test
	public void getWidthQuadraticField(){
		assertEquals(10, field10x10.getWidth());
	}
	@Test
	public void getHeight15x3Field(){
		assertEquals(15, field15x3.getHeight());
	}
	@Test
	public void getWidth15x3Field(){
		assertEquals(3, field15x3.getWidth());
	}
	@Test (expected = IllegalArgumentException.class)
	public void negativeHeight(){
		final int NEGATIVE_VALUE = -10;
		new GameField(NEGATIVE_VALUE, 6);
	}

}
