import java.util.Random;

/**
 * 
 */

/**
 * @author rana
 *
 */
public class GameField {
	public GameField(){
		this(10);
	}
	
	public GameField(int maxRandomValues){
		Random r = new Random();
		value = r.nextInt(maxRandomValues);
	}
	
	public boolean getStriked() {
		return striked;
	}
	public int getValue(){
		return value;
	}
	

	public void setStriked(){
		striked = true;
	}
	
	

	private boolean striked;
	private int value;

}
