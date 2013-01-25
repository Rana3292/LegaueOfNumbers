package UI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

/**GamePanel
 * A graphical userinterface for the Game.
 * The Main of the Game.
 * @author rana
 *
 */

class GamePanel extends JFrame{

/** Costum constructor for the GamePanel
 * 
 */
	public GamePanel(){
		super();
		
		try{
			handler = new FileHandler("Logging.txt");
		} catch (IOException e){ 
			handler = new ConsoleHandler();
		}
		log.addHandler(handler);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Legue of Numbers");
		

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		/*JDialog dialog = new JDialog();
		dialog.add(new JLabel("You have choosen three values :)"));
		dialog.setSize(200, 200);
		dialog.setVisible(true);*/
		startDialog = new JDialog();
		pStartDialog = new JPanel(new BorderLayout());
		pStartDialog.add(new JLabel("Choose the mathematical operation:"), BorderLayout.NORTH);
		rbPlusMinus = new JRadioButton("+;-");
		rbMultDiv = new JRadioButton("*;/");
		rbPlusMinusMultDiv = new JRadioButton("+;-;*;/");
		rbPlusMinusMultDiv.setSelected(true);
		bgMathematicalOperations = new ButtonGroup();
		bgMathematicalOperations.add(rbPlusMinus);
		bgMathematicalOperations.add(rbMultDiv);
		bgMathematicalOperations.add(rbPlusMinusMultDiv);
		pRadioButtons = new JPanel(new GridLayout(3,1));
		pRadioButtons.add(rbPlusMinus);
		pRadioButtons.add(rbMultDiv);
		pRadioButtons.add(rbPlusMinusMultDiv);
		pStartDialog.add(pRadioButtons, BorderLayout.CENTER);
		bStartDialog = new JButton("Spielen! :)");
		bStartDialog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action performed");
				if (rbPlusMinus.isSelected()){
					log.finer("Plus and Minus are allowed.");
					init(Board.PLUSMINUS);
				}
				if (rbMultDiv.isSelected()){
					log.finer("Mult and Div are allowed.");
					init(Board.MULITDIV);
				}
				if (rbPlusMinusMultDiv.isSelected()){
					log.finer("Plus, Minus, Mult and Div are allowed.");
					init(Board.PLUSMINUS);
				}
				if (rbMultDiv.isSelected()){
					init(Board.MULITDIV);
				}
				if (rbPlusMinusMultDiv.isSelected()){
					System.out.println("plusminusMultDiv");
					init(Board.PLUSMINUSMULTDIV);
				}
				startDialog.setTitle("Start Settings");
				startDialog.setVisible(false);
			}
		});
		pStartDialog.add(bStartDialog, BorderLayout.SOUTH);
		startDialog.add(pStartDialog);
		startDialog.setSize(200, 200);
		startDialog.pack();
		startDialog.setVisible(true);
		
	}
	/**
	 * Initializes the GUI of the game
	 * @param mathematicalOperations The choosen Operations for the game (Can be find at Board as public static fields.)
	 */
	private void init(int mathematicalOperations){
		this.setLayout(new BorderLayout());
		boardPanel = new JPanel();
		switch(mathematicalOperations){
		case Board.PLUSMINUS: 
			board = new Board(boardPanel, Board.PLUSMINUS, 10);
			break;
		case Board.MULITDIV:
			board = new Board(boardPanel, Board.MULITDIV, 10);
			break;
		case Board.PLUSMINUSMULTDIV:
			board = new Board(boardPanel, Board.PLUSMINUSMULTDIV, 10);
			break;
		}
		this.add(boardPanel, BorderLayout.CENTER);
		
		sidebarPanel = new JPanel();
		sidebar = new SideBar(sidebarPanel, board);
		sidebarPanel.setSize(150, board.getHeightGameField()*50);
		sidebarPanel.setMinimumSize(new Dimension(150, board.getHeightGameField()*50));
		this.add(sidebarPanel, BorderLayout.EAST);

		
		menuBar = new JMenuBar();
		mHelp = new JMenu("?");
		menuBar.add(mHelp);
		miHelp = new JMenuItem("?");
		miHelp.setMnemonic(KeyEvent.VK_F1);
		miHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog help = new JDialog();
				//JTextArea taHelp = new JTextArea()
				JLabel lHelp = new JLabel();
				lHelp.setText("<html><body><h1>Willkommen bei der Hilfsfunktion von Legaure of Numbers</h1> Das Spielprinzip ist einfach und faszinierend zu gleich. <br>" +
						"Es wird dir rechts eine Zahl angezeigt, diese Zahl sollst du in dem linken Feld mit den vielen Zahlen finden.  <br>" +
						"Dafür stehen dir die Rechenoperationen zur Verfügung, die du am Anfang des Spieles ausgewählt hast.<br>" +
						"Um die Zahl zu erreichen musst du drei aneinanderliegenden Zahlen so kombinieren (mit den Rechenoperationen), dass die reche Zahl das Ergebnis ist. <br>" +
						"Das Spiel ist für zwei Spieler ausgelegt. Daher musst du bevor du ins Zahlenfeld klickst, rechts auf den Button mit deinem Spieler(1 oder 2) klicken. " +
						"<br>Liegst du richtig, kriegst du den Punkt. Solltest du dich verrechnet haben, kriegt dein Gegner den Punkt <br>Jetzt steht dem Spielspaß nichts mehr im Wege! Los!" +
						"</body></html>");
				help.add(lHelp);
				help.setTitle("Help");
				help.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				help.setSize(400,400);
				help.setVisible(true);
				
			}
		});
		mHelp.add(miHelp);
		miWrittenBy = new JMenuItem("Wir");
		miWrittenBy.setMnemonic(KeyEvent.VK_W);
		miWrittenBy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog writtenBy = new JDialog();
				JLabel lWrittenBy = new JLabel();
				lWrittenBy.setText("<html>" +
					"<body>" +
						"<h1> About us </h1>" +
							"We developed this game for a software project at university. </br>" + 
					"</body>" +
				"</html>");
				writtenBy.add(lWrittenBy);
				writtenBy.setTitle("Written By");
				writtenBy.setSize(400,400);
				writtenBy.setVisible(true);
				writtenBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		mHelp.add(miWrittenBy);
		menuBar.setVisible(true);
		this.setJMenuBar(menuBar);
		
		board.addObserver(sidebar);
		
		this.setTitle("Legaue of Numbers");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.pack();
		this.setVisible(true);
	}
	
	private JDialog startDialog;
		private JPanel pStartDialog;
		private JRadioButton rbPlusMinus;
		private JRadioButton rbMultDiv;
		private JRadioButton rbPlusMinusMultDiv;
		private ButtonGroup bgMathematicalOperations;
		private JPanel pRadioButtons;
		private JButton bStartDialog;
	private JPanel boardPanel;
	private Board board;
	private JPanel sidebarPanel;
	private SideBar sidebar;
	private static final long serialVersionUID = -5533014914272850813L;
	
	private JMenuBar menuBar;
	private JMenu mHelp;
	private JMenuItem miHelp;
	private JMenuItem miWrittenBy;
	private final static Logger log = Logger.getLogger(Board.class .getName());
	private Handler handler;

	public static void main(String[] args) {
		new GamePanel();
	}
}
