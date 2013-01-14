package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameField.GameField;



public class Board {
	public Board( JPanel	panel){
		this.panel = panel;
		reset();
		panel.setSize(model.getWidth()*50, model.getHeight()*50);
		panel.setLayout(new GridLayout(model.getWidth(), model.getHeight()));
	}
	
	public void reset(){
		model = new GameField();
		values = new JButton[3];
		counterForValue = 0;
		buttons = new JButton[model.getWidth()][model.getHeight()];
		int i, j;
		for (i=0; i < model.getWidth(); i++){
			for (j=0; j < model.getHeight(); j++){
				buttons[i][j] = new JButton(model.getValue(j, i) + "");
				buttons[i][j].setSize(50, 50);
				/* HowTo identify a Button: 
				 * int Value / model.getWidth() = row
				 * int Value % model.getWidth() = column 
				 */
				buttons[i][j].setActionCommand(i*model.getWidth() + j + ""); 
				panel.add(buttons[i][j]);
				buttons[i][j].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String s = e.getActionCommand();
						int buttonid = Integer.parseInt(s);
						int column = buttonid % getWidthGameField();
						int row = buttonid / getWidthGameField();
						enableButtons(row, column);
						if (!setValue(buttons[row][column])){
							JDialog dialog = new JDialog();
							dialog.add(new JLabel("You have choosen three values :)"));
							dialog.setSize(200, 200);
							dialog.setVisible(true);
						}
					}
				});
			}
		}
	}
	
	public void update(){
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				if (model.getStriked(j,i)){
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}
	
	public void enableButtons(int x, int y){
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				buttons[i][j].setEnabled(false);
				//Setting the Buttons around not enabled!
				if (i == x-1 || i == x+1){ 
					if ( j==y){
						if (! model.getStriked(j, i) && !fieldAlreadyChoosen(buttons[i][j])){
							buttons[i][j].setEnabled(true);
						}
					}
				}
				if (x == i){
					if (j == y-1 || j == y+1){
						if (! model.getStriked(j, i) && !fieldAlreadyChoosen(buttons[i][j])){
							buttons[i][j].setEnabled(true);
						}
					}
				}
			}
		}
	}
	
	public int getHeightGameField(){
		return model.getHeight();
	}
	public int getWidthGameField(){
		return model.getWidth();
	}
	
	private int getValue(int i, int j){
		return model.getValue(j, i);
	}
	
	private boolean setValue(JButton value){
		if (counterForValue < 2){
			values[counterForValue] = value;
			counterForValue++;
			return true;
		}
		counterForValue = 0;
		return false;
	}
	
	private boolean fieldAlreadyChoosen(JButton b){
		for (int i=0; i < counterForValue; i++){
			if (values[i].equals(b)){
				return true;
			}
		}
		return false;
	}
	
	private int counterForValue;
	private JButton[] values;
	private JButton[][] buttons;
	private GameField model;
	private JPanel panel;
}
