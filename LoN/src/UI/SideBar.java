/**
 * 
 */
package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

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
		
		panel.add(searchNumber);
		panel.add(lValidNumber);
		
		panel.setVisible(true);
	}
	/**
	 * Updates the Graphical Userinterface 
	 */
	@Override
	public void update(Observable o, Object arg) {
		searchNumber.setText(arg+"");
		if (board.getValidAnswer()){
			lValidNumber.setText("Gut gemacht!");
		}
		else{
			lValidNumber.setText("Nochmal probieren!");
		}
	}
	
	private JPanel panel;
	private Board board;
	private JLabel searchNumber;
	private JLabel lValidNumber;
}
