package MathematicalLogik;

public class MultDivPlusMinusCheck {

	public static boolean check(int c, int b, int a, int res) {
		if (MultDivCheck.check(a, b, c, res))
			return true;
		if (PlusMinusCheck.check(a, b, c, res))
			return true;
		if (a*b + c == res || b*c + a == res || a*c + b == res)
			return true;
		if (a%b == 0 && a/b + c == res || b%a == 0 && b/a + c== res ||
				b%c == 0 && b/c+ a == res || c%b == 0 && c/b + a == res ||
				a%c == 0 && a/c + b == res || c%a == 0 && c/a + b == res)
			return true;
		if ((a+b)*c == res || (b+c)*a == res || (a+c)*b == res){
			return true;
		}
		if ((a-b)*c == res || (b-a)*c == res || 
			(b-c)*a == res || (c-b)*a == res || 
			(a-c)*b == res || (c-a)*b == res){
			return true;
		}
		if ((a+b)%c == 0 && (a+b)/c == res || 
				(b+c)%a == 0 && (b+c)/a == res || 
				(a+c)%b ==0 && (a+c)/b == res){
			return true;
		}
		if ((a-b)%c == 0 && (a-b)/c == res || (b-a)%c == 0 && (b-a)/c == res ||
				(b-c)%a == 0 && (b-c)/a == res || (c-b)%a == 0 && (c-b)/a == res ||
				(a-c)%b == 0 && (a-c)/b == res || (c-a)%b == 0 && (c-a)/b == res){
			return true;
		}
		return false;
	}
	
}
