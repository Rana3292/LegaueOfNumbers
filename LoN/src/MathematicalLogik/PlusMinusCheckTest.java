/**
 * 
 */
package MathematicalLogik;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * @author rana
 *
 */
public class PlusMinusCheckTest {
	private PlusMinusCheck pmc;
	private int a, b, c, d, e, res;
	@Before public void setUp(){
		pmc = new PlusMinusCheck();
		a = 2;
		b = 3;
		c = 5;
		d = 15;
		e = 10;
		res = 10;
	}

	@Test
	public void AddingThreeNumbers() {
		assertEquals(true, pmc.check(a, b, c, res));
	}
	@Test
	public void SubtractThreeNumbers(){
		assertEquals(true, pmc.check(d, a, b, res));
	}
	@Test
	public void AddAndSubtract(){
		assertEquals(true, pmc.check(e, d, c, res));
	}

}