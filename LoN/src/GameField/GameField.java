package GameField;
/**
 * 
 */

/**
 * @author rana
 *
 */
public class GameField {
	private final int height;
	private final int width;
	
	public GameField(){
		this(20);
	}
	public GameField(int quadratic){
		this(quadratic, quadratic);
	}
	public GameField(int height, int width){
		if (height < 2 || width < 2)
			throw new IllegalArgumentException("A size smaller then 1.");
		this.width = width;
		this.height = height;
	}
	/**
	 * @return the height of the gameField. 
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the width of the gameField.
	 */
	public int getWidth() {
		return width;
	}
}
