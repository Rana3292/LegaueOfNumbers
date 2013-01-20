package GameField;
/**
 * 
 */

/** GameField
 * 
 * Represents a 2 dimensional GameField of Fields. 
 * 
 * @author rana
 *
 */
public class GameField {
	private final int height;
	private final int width;
	private Field[][] gameField;
	/**
	 * Constructor with an default field of the size 10 * 10
	 */
	public GameField(){
		this(10);
	}
	/**
	 * @param quadratic one int value for the width and height of the gameField
	 */
	public GameField(int quadratic){
		this(quadratic, quadratic);
	}
	/**
	 * @param height an  int Value for the height. Musst be higher then 1.
	 * @param width an int Value for the width. Musst be higher then 1.
	 * @exception IllegalArgumentException A size is smaller then 2. 
	 */
	public GameField(int height, int width){
		if (height < 2 || width < 2)
			throw new IllegalArgumentException("A size smaller then 1.");
		this.width = width;
		this.height = height;
		gameField = new Field[width][height];
		for (int i=0; i < width; i++){
			for (int j=0; j < height; j++){
				gameField[i][j] = new Field();
			}
		}
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
	/**
	 * 
	 * @param height an int value for the y value.
	 * @param width an int value for the x value.
	 * @return the Value of the Field of the GameField.
	 * @exception IllegalArgumentException The height or width is invalid.
	 */
	public int getValue(int height, int width){
		if (height < 0 || height > this.height || width < 0 || width > this.width)
			throw new IllegalArgumentException("Invalid values!");
		return gameField[width][height].getValue();
	}
	/**
	 * 
	 * @param height an int value for the y value.
	 * @param width an int value for the x value.
	 * @return whether the Field of the GameField is striked.
	 * @exception IllegalArgumentException The x or y value is invalid.
	 */
	public boolean getStriked(int height, int width){
		if (height < 0 || height > this.height || width < 0 || width > this.width)
			throw new IllegalArgumentException("Invalid values!");
		return gameField[width][height].getStriked();
	}
	/**
	 * 
	 * @param height an int value for the y value.
	 * @param width an int value for the x value.
	 * @exception IllegalArgumentException The x or y value is invalid.
	 */
	public void setStriked(int height, int width){
		if (height < 0 || height > this.height || width < 0 || width > this.width)
			throw new IllegalArgumentException("Invalid values!");
		gameField[width][height].setStriked();
	}
	
	
}
