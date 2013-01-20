/**
 * 
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** SideBar
 * A SideBar with additional informations for the game like the number which should be searched.
 * @author rana
 *
 */
public class SideBar implements Observer {
	public SideBar(JPanel panel, Board board){
		this.panel = panel;
		panel.setLayout(new GridLayout(2,1));
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		lValidNumber = new JLabel();
		lValidNumber.setIcon(new ImageIcon("richtig.jpg"));
		

		panel.add(searchNumber);
		panel.add(lValidNumber);

		
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
			lValidNumber.setIcon(new ImageIcon("richtig.jpg"));
		}
		else{
			lValidNumber.setIcon(new ImageIcon("falsch.jpg"));
		}
	}
	
	private JPanel panel;
	private Board board;
	private JLabel searchNumber;
	private JLabel lValidNumber;

}
