/**
 * 
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		pointsPlayerA =0;
		pointsPlayerB = 0;
		movePlayer = -1;
		this.panel = panel;
		panel.setLayout(new GridLayout(3,1));
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		lValidNumber = new JLabel();
		lValidNumber.setIcon(new ImageIcon("richtig.jpg"));
		
		pPlayers = new JPanel(new GridLayout(3,1));
		lPlayerPoints = new JLabel("Player A: " + pointsPlayerA + " | Player B: " + pointsPlayerB);
		bPlayerA = new JButton("Player A");
		bPlayerA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				movePlayer = moveByA;
			}
		});
		bPlayerB = new JButton("Player B");
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
	private static final int moveByA = 0;
	private static final int moveByB = 1;
}
