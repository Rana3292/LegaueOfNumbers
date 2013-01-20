package UI;


import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;



class GamePanel extends JFrame{


	public GamePanel(){
		super();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		/*JDialog dialog = new JDialog();
		dialog.add(new JLabel("You have choosen three values :)"));
		dialog.setSize(200, 200);
		dialog.setVisible(true);*/
		this.setLayout(new BorderLayout());
		
		boardPanel = new JPanel();
		board = new Board(boardPanel, Board.PLUSMINUS, 10);
		this.add(boardPanel, BorderLayout.CENTER);
		
		sidebarPanel = new JPanel();
		sidebar = new SideBar(sidebarPanel, board);
		this.add(sidebarPanel, BorderLayout.EAST);
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
	private JPanel boardPanel;
	private Board board;
	private JPanel sidebarPanel;
	private SideBar sidebar;
	private static final long serialVersionUID = -5533014914272850813L;
	
	public static void main(String[] args) {
		new GamePanel();
	}
}
