package MathematicalLogik;

public class MultDivPlusMinusCheck {

	public static boolean check(int c, int b, int a, int res) {
		if (MultDivCheck.check(a, b, c, res))
			return true;
		if (PlusMinusCheck.check(a, b, c, res))
			return true;
		if (a*b + c == res || b*c + a == res || a*c + b == res)
			return true;
		if (b!= 0 && a%b == 0 && a/b + c == res || a != 0 && b%a == 0 && b/a + c== res ||
			c != 0 && b%c == 0 && b/c+ a == res ||b !=0 && c%b == 0 && c/b + a == res ||
			c != 0 && a%c == 0 && a/c + b == res || a != 0 && c%a == 0 && c/a + b == res)
			return true;
		if ((a+b)*c == res || (b+c)*a == res || (a+c)*b == res){
			return true;
		}
		if ((a-b)*c == res || (b-a)*c == res || 
			(b-c)*a == res || (c-b)*a == res || 
			(a-c)*b == res || (c-a)*b == res){
			return true;
		}
		if (c != 0 && (a+b)%c == 0 && (a+b)/c == res || 
			a != 0 && (b+c)%a == 0 && (b+c)/a == res || 
			b != 0 && (a+c)%b ==0 && (a+c)/b == res){
			return true;
		}
		if (c != 0 &&(a-b)%c == 0 && (a-b)/c == res ||c != 0 && (b-a)%c == 0 && (b-a)/c == res ||
			a != 0 &&(b-c)%a == 0 && (b-c)/a == res ||a != 0 && (c-b)%a == 0 && (c-b)/a == res ||
			b != 0 &&(a-c)%b == 0 && (a-c)/b == res ||b != 0 && (c-a)%b == 0 && (c-a)/b == res){
			return true;
		}
		if((a*b)-c == res || (a*c)-b == res || (b*c)-a == res){
			return true;
		}
		if(b != 0 && a%b==0 && (a/b)-c == res || a != 0 && b%a == 0 && (b/a)-c == res || 
			c != 0 && b%c == 0 && (b/c)-a == res || b != 0 && c%b == 0 && (c/b)-a == res ||
			c != 0 && a%c == 0 && (a/c)-b == res || a != 0 && c%a == 0 && (c/a)-b == res){
			return true;
		}
		return false;
	}
	
}
