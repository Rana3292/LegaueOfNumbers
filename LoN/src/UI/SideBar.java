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
import java.io.IOException;
import java.io.InputStream;
import java.util.Observable;
import java.util.Observer;

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
	public SideBar(JPanel panel, Board board){
				//Schriftart laden
				try {
					InputStream myStream = new BufferedInputStream(new FileInputStream("GrungeHandwriting.ttf"));
					handwritting = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 30);
					/*InputStream is = this.getClass().getResourceAsStream("GrungeHandwriting.ttf");
					//File fontFile = new File(this.getClass().getResource("../../../Fonts/Handwritting.ttf").toURI());
					this.handwritting = Font.createFont(Font.PLAIN, is);//.deriveFont(Font.PLAIN, 20f);*/
				} catch (FontFormatException e) {
					System.out.println("Probleme mit dem Lesen der Schriftart");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Probleme beim I/O");
					e.printStackTrace();
				} 
				//Schriftart verwenden
	
		pointsPlayerA =0;
		pointsPlayerB = 0;
		movePlayer = -1;
		this.panel = panel;
		panel.setLayout(new GridLayout(3,1));
		setDesign(panel);
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		setDesign(searchNumber);
		lValidNumber = new JLabel();
		//lValidNumber.setIcon(new ImageIcon("richtig.png"));
		
		pPlayers = new JPanel(new GridLayout(3,1));
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
		pPlayers.add(bPlayerA);
		pPlayers.add(bPlayerB);
		pPlayers.add(lPlayerPoints);
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
				break;
			case moveByB: pointsPlayerB++;
				break;
			}
			lValidNumber.setIcon(new ImageIcon("richtig.png"));
		}
		else{
			switch(movePlayer){
			case moveByA: pointsPlayerB++;
				break;
			case moveByB: pointsPlayerA++;
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
		c.setFont(handwritting);
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
	private int movePlayer;
	private Font handwritting;
	private static final int moveByA = 0;
	private static final int moveByB = 1;
}
