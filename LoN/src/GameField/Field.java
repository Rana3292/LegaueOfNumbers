package GameField;
import java.util.Random;

/**
 * 
 */

/**
 * @author rana
 *
 */
public class Field {
	public Field(){
		this(10);
	}
	
	public Field(int maxRandomValues){
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
	
	public boolean equals(Field f){
		return (f.getStriked() == getStriked() && f.getValue() == f.getValue());
	}
	public int hashCode(){
		return (getStriked())? value*value : value;
	}

	private boolean striked;
	private int value;

}
