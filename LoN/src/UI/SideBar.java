/**
 * 
 */
package UI;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author rana
 *
 */
public class SideBar {
	public SideBar(JPanel panel, Board board){
		this.panel = panel;
		this.board = board;
		searchNumber= new JLabel(board.getActualSearchedNumber() + "");
		
		panel.add(searchNumber);
	}
	
	
	private JPanel panel;
	private Board board;
	private JLabel searchNumber;
}
