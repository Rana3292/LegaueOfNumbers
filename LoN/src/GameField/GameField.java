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
	private Field[][] gameField;
	
	public GameField(){
		this(10);
	}
	public GameField(int quadratic){
		this(quadratic, quadratic);
	}
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
	
	public int getValue(int height, int width){
		return gameField[width][height].getValue();
	}
	
	public boolean getStriked(int height, int width){
		return gameField[width][height].getStriked();
	}
	public void setStriked(int height, int width){
		gameField[width][height].setStriked();
	}
	
	
}
