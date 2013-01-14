package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import GameField.GameField;



public class Board {
	public Board( JPanel	panel){
		this.panel = panel;
		this.eventHandler = eventHandler;
		reset();
		panel.setSize(model.getWidth()*50, model.getHeight()*50);
		panel.setLayout(new GridLayout(model.getWidth(), model.getHeight()));
	}
	
	public void reset(){
		model = new GameField();
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
					if ( j == y-1 || (j==y) || j == y+1){
						if (! model.getStriked(j, i)){
							buttons[i][j].setEnabled(true);
						}
					}
				}
				if (x == i){
					if (j == y-1 || j == y+1){
						if (! model.getStriked(j, i)){
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
	
	private JButton[][] buttons;
	private GameField model;
	private JPanel panel;
	private EventHandleBoard eventHandler;
}
