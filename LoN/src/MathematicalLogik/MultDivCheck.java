/**
 * 
 */
package MathematicalLogik;

/**
 * @author Lerentis
 *
 */
public class MultDivCheck {

  /**
	 * @param a first value
	 * @param b second value
	 * @param c third value
	 * @param res wanted result
	 * @return whether it's valid with * and / operations. 
	 */
	public boolean check(int a, int b, int c, int res) {
		if (a*b*c == res){ // Associative property terminates the Rest of the Permutation
			return true;
		}
		//Begin Permutation for Division
		if(b != 0 && c != 0){
			if (a%b == 0 && b%c == 0){
				if(a/b/c == res){ //Associative and kommutative
					return true;
				}
			}
		}
		if(a != 0 && c != 0){
			if (b%a == 0 && a%c == 0){
				if(b/a/c == res){
					return true;
				}
			}
		}
		if(a != 0 && b != 0){
			if (c%a==0 && (c/a)%b==0){
				if(c/a/b == res){
					return true;
				}
			}
		}
		//Begin Permutation for Division AND Multiplication
		if(a !=0){
			if ((b%a == 0 && b/a*c == res) || ((b*c)%a == 0 && b*c/a == res) || (c%a == 0) && c/a*b == res ){
				return true;
			}
		}
		if(b !=0){
			if ((a%b == 0 && a/b*c == res) || ((a*c)%b == 0 && a*c/b == res) || (c%b == 0 && c/b*a == res)){
				return true;
			}
		}
		if(c !=0){
			if (((a*b)%c == 0 && a*b/c == res) || ((b*a)%c == 0 && b*a/c == res) || (a%c == 0 && a/c*b == res)){
				return true;
			}
		}
		//If Factor is Zero OR no proper Result has been Found the Return Value is False
		return false;
	}
	
}
