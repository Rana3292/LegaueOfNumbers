package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import GameField.GameField;



public class Board {
	public Board( JPanel	panel ){
		this.panel = panel;
		reset();
		panel.setSize(model.getWidth()*50, model.getHeight()*50);
		panel.setLayout(new GridLayout(model.getWidth(), model.getHeight()));
	}
	
	public void reset(){
		model = new GameField();
		buttons = new JButton[model.getWidth()][model.getHeight()];
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				buttons[i][j] = new JButton(model.getValue(j, i) + "");
				buttons[i][j].setSize(50, 50);
				panel.add(buttons[i][j]);
			}
		}
	}
	
	public void update(){
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				if (model.getStriked(i,j)){
					buttons[i][j].setEnabled(true);
				}
			}
		}
	}
	private JButton[][] buttons;
	private GameField model;
	private JPanel panel;
}
