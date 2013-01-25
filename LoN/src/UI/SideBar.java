/**
 * 
 */
package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** SideBar
 * A SideBar with additional informations for the game like the number which should be searched.
 * @author rana
 *
 */
public class SideBar implements Observer {
	public SideBar(JPanel p, final Board board){
		try{
			handler = new FileHandler("Logging.txt");
		} catch (IOException e){ 
			handler = new ConsoleHandler();
		}
		log.addHandler(handler);
		handwritting = FontLoader.loadGrungeFont();
		
	
		pointsPlayerA =0;
		pointsPlayerB = 0;
		movePlayer = -1;
		this.panel = p;
		panel.setLayout(new GridLayout(3,1));
		setDesign(panel);
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		setDesign(searchNumber);
		lValidNumber = new JLabel();
		
		pPlayers = new JPanel(new GridLayout(4,1));
		setDesign(pPlayers);
		lPlayerPoints = new JLabel("Player A: " + pointsPlayerA + " | Player B: " + pointsPlayerB);
		setDesign(lPlayerPoints);
		bPlayerA = new JButton("Player A");
		setDesign(bPlayerA);
		bPlayerA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer = moveByA;
			}
		});
		bPlayerB = new JButton("Player B");
		setDesign(bPlayerB);
		bPlayerB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer = moveByB;
			}
		});
		bReset = new JButton("Reset");
		bReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				board.checkReset();
			}
		});
		setDesign(bReset);
		pPlayers.add(bPlayerA);
		pPlayers.add(bPlayerB);
		pPlayers.add(lPlayerPoints);
		pPlayers.add(bReset);
		pPlayers.setVisible(true);
		panel.add(searchNumber);
		panel.add(lValidNumber);
		panel.add(pPlayers);

		
		panel.setMinimumSize(new Dimension(150, board.getHeightGameField()*50));
		panel.setSize(150, board.getHeightGameField()*50);
		panel.setVisible(true);
	}
	/**
	 * Updates the Graphical Userinterface 
	 */
	@Override
	public void update(Observable o, Object arg) {
		searchNumber.setText(arg+"");
		
		if (board.getValidAnswer()){
			switch(movePlayer){
			case moveByA: pointsPlayerA++;
				log.finer("Player A was right!");
				break;
			case moveByB: pointsPlayerB++;
				log.finer("Player B was right!");
				break;
			}
			lValidNumber.setIcon(new ImageIcon("richtig.png"));
		}
		else{
			switch(movePlayer){
			case moveByA: pointsPlayerB++;
				log.finer("Player A was wrong!");
				break;
			case moveByB: pointsPlayerA++;
				log.finer("Player B was wrong!");
				break;
			}
			lValidNumber.setIcon(new ImageIcon("falsch.png"));
		}
		movePlayer = -1;
		lPlayerPoints.setText("Player A: " + pointsPlayerA + " | Player B: " + pointsPlayerB);
	}
	/**
	 * Set the Design of a component in the following way:
	 * handwritting font, background = dark_gray, foreground = white
	 */
	private void setDesign(Component c){
		if (handwritting != null){
			c.setFont(handwritting);
		}else{
			c.setFont(new Font("Helvetica", Font.BOLD, 26));
		}
		c.setBackground(Color.DARK_GRAY);
		c.setForeground(Color.white);
	}
	
	private JPanel panel;
	private Board board;
	private JLabel searchNumber;
	private JLabel lValidNumber;
	private int pointsPlayerA;
	private int pointsPlayerB;
	private JPanel pPlayers;
	private JLabel lPlayerPoints;
	private JButton bPlayerA;
	private JButton bPlayerB;
	private JButton bReset;
	private int movePlayer;
	private Font handwritting;
	private static final int moveByA = 0;
	private static final int moveByB = 1;
	private final static Logger log = Logger.getLogger(Board.class .getName());
	private Handler handler;
}
