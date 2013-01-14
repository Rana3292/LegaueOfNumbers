package MathematicalLogik;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MultDivPlusMinusCheckTest {
	private int a, b, c, d, res;
	@Before
	public void setUp(){
		a = 3;
		b = 2;
		c = 6;
		d = 6;
		res = 15;
	}
	@Test
	public void test() {
		assertEquals(true, MultDivPlusMinusCheck.check(c, d, a, res));
		assertEquals(true, MultDivPlusMinusCheck.check(c,b,1, 12));
		assertEquals(true, MultDivPlusMinusCheck.check(c, b, a, res));
		assertEquals(true, MultDivPlusMinusCheck.check(2,3,2,10));
		assertEquals(true, MultDivPlusMinusCheck.check(3,2,5,5));
	}

}
