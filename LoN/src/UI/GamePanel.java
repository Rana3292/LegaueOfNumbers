package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



class GamePanel extends JFrame{


	public GamePanel(){
		super();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		boardPanel = new JPanel();
		board = new Board(boardPanel);
		this.add(boardPanel);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	private JPanel boardPanel;
	private Board board;
	private static final long serialVersionUID = -5533014914272850813L;
	
	public static void main(String[] args) {
		new GamePanel();
	}
}
