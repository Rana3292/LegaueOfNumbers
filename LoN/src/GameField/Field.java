package GameField;
import java.util.Random;

/**
 * 
 */

/**Field
 * 
 * A field has an integer value and a boolean to identify whether the field is striked.
 * The value of a field is immutable. The value is a random number.
 * 
 * @author rana
 *
 */

public class Field {
	/**
	 * Constructor with the default maximum value of 10.
	 */
	public Field(){
		this(10);
	}
	/** 
	 * @param maxRandomValues specifies the maximum value of a field 
	 */
	
	public Field(int maxRandomValues){
		Random r = new Random();
		value = r.nextInt(maxRandomValues);
	}
	/**
	 * @return whether the field is striked
	 */
	public boolean getStriked() {
		return striked;
	}
	/**
	 * @return the value of the field
	 */
	public int getValue(){
		return value;
	}
	/**
	 * Set the Field striked.
	 */
	public void setStriked(){
		striked = true;
	}
	/**
	 * Compares two fields, whether the values and the boolean (striked) is equals
	 * @param f an other Field
	 */
	public boolean equals(Field f){
		return (f.getStriked() == getStriked() && f.getValue() == f.getValue());
	}
	/**
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		return (getStriked())? value*value : value;
	}

	private boolean striked;
	private int value;

}
