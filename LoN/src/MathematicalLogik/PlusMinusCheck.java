/**
 * 
 */
package MathematicalLogik;

/**
 * @author rana
 *
 */
public class PlusMinusCheck {

	/**
	 * @param a first value
	 * @param b second value
	 * @param c third value
	 * @param res wanted result
	 * @return whether it's valid with + and - operations. 
	 */
	public static boolean check(int a, int b, int c, int res) {
		if (a+b+c==res){ // equals a + c + b; b + a + c; b + c + a; c + a + b; c + b + a;
			return true;
		}
		if (a - b - c == res){ // equals a - c - b
			return true;
		}
		if (b - a - c == res){ // equals b - c - a
			return true;
		}
		if (c - a - b == res){ // equals c - b - a
			return true;
		}
		
		if (a - b + c == res){ // equals a - c + b
			return true; 
		}
		if (b - a + c == res){ // equals b - c + a
			return true;
		}
		if (a + b - c == res){ // equals b + a - c
			return true;
		}
		return false;
	}
	
}
