/**
 * 
 */
package UI;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rana
 *
 */
public class SideBar implements Observer {
	public SideBar(JPanel panel, Board board){
		this.panel = panel;
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		
		panel.add(searchNumber);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		searchNumber.setText(arg+"");
	}
	
	private JPanel panel;
	private Board board;
	private JLabel searchNumber;
}
