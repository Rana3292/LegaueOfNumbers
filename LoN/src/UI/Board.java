package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GameField.GameField;
import MathematicalLogik.MultDivCheck;
import MathematicalLogik.MultDivPlusMinusCheck;
import MathematicalLogik.PlusMinusCheck;



public class Board extends Observable{
	public final static int PLUSMINUS = 1;
	public final static int MULITDIV = 2;
	public final static int PLUSMINUSMULTDIV = 3;
	
	public Board( JPanel	panel, int gameValue, int maxNumber){
		this.panel = panel;
		this.gameValue = gameValue;
		this.maxNumber = maxNumber;
		reset();
		panel.setSize(model.getWidth()*50, model.getHeight()*50);
		panel.setLayout(new GridLayout(model.getWidth(), model.getHeight()));
	}
	
	public void reset(){
		model = new GameField();
		values = new JButton[3];
		counterForValue = 0;
		createNewNumber();
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
							/*JDialog dialog = new JDialog();
							dialog.add(new JLabel("You have choosen three values :)"));
							dialog.setSize(200, 200);
							dialog.setVisible(true);*/
							System.out.println("3 Values has been chosen");
						}
					
						if (check){
								int[] intValues = new int[3];
								for (int i=0; i < 3; i++){
									int buttonString = Integer.parseInt(values[i].getActionCommand());
									column = buttonString % getWidthGameField();
									row = buttonString / getWidthGameField();
									intValues[i] = getValue(row, column);
								}
								JDialog dialog = new JDialog();
								dialog.setSize(200, 200);
								boolean checked = false;
								if (gameValue == PLUSMINUS){
									if (PlusMinusCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										dialog.add(new JLabel("Right!"));
										striking();
										checked = true;
									}
								}
								if (gameValue == MULITDIV){
									if (MultDivCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										dialog.add(new JLabel("Right!"));
										striking();
										checked = true;
									}
								}
								if (gameValue == PLUSMINUSMULTDIV){
									if (MultDivPlusMinusCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										dialog.add(new JLabel("Right!"));
										striking();
										checked = true;
									}
								}
								if (!checked){
									dialog.add(new JLabel("False!"));
								}
								else{
									createNewNumber();
								}
								dialog.setVisible(true);
								
								update();
								setChanged();
								notifyObservers(actualSearchedNumber);
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
					buttons[i][j].setEnabled(false);
				}
				else{
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
	public int getActualSearchedNumber(){
		return actualSearchedNumber;
	}
	public void createNewNumber(){
		System.out.println("New generated number " + randomNumber());
	}
	
	private int getValue(int i, int j){
		return model.getValue(j, i);
	}
	private void setStriked(int i, int j){
		model.setStriked(i, j);
	}
	private boolean setValue(JButton value){
		if (counterForValue < 2){
			check = false;
			values[counterForValue] = value;
			counterForValue++;
			return true;
		}
		if (counterForValue == 2){
			values[2] = value;
		}
		counterForValue = 0;
		check = true;
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
	
	private void striking(){
		for (int i=0; i < 3; i++){
			int buttonString = Integer.parseInt(values[i].getActionCommand());
			int column = buttonString % getWidthGameField();
			int row = buttonString / getWidthGameField();
			setStriked(column, row);
		}
	}
	
	private int randomNumber(){
		Random r = new Random();
		int ret = 0;
		int max = 0;
		if (gameValue == PLUSMINUS){
			max = 3 * maxNumber;
		}
		if (gameValue == MULITDIV || gameValue == PLUSMINUSMULTDIV){
			max = maxNumber * maxNumber * maxNumber;
		}
		ret = r.nextInt(max);
		while (! controllRandomNumber(ret)){
			ret = r.nextInt(max);
		}
		actualSearchedNumber = ret;
		return ret;
	}
	private boolean controllRandomNumber(int random){
		for (int i=0; i < model.getHeight(); i++){
			for (int j=0; j < model.getWidth(); j++){
				if (i+2 < model.getWidth()){
					if (gameValue == PLUSMINUS){
						if (PlusMinusCheck.check(getValue(i,j),getValue(i+1,j),getValue(i+2,j), random)){
							return true;
						}
					}
					if (gameValue == MULITDIV){
						if (MultDivCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+2,j), random))
							return true;
					}
					if (gameValue == PLUSMINUSMULTDIV){
						if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+2, j), random))
							return true;
					}
				}
				if (j+2 < model.getHeight()){
					switch (gameValue){
					case PLUSMINUS: if (PlusMinusCheck.check(getValue(i,j), getValue(i,j+1), getValue(i, j+2), random))
						return true;
						break;
					case MULITDIV: if (MultDivCheck.check(getValue(i,j), getValue(i,j+1), getValue(i, j+2), random))
						return true;
						break;
					case PLUSMINUSMULTDIV: if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i, j+1), getValue(i, j+2), random))
						return true;
						break;
					}
				}
				if (j+1 < model.getHeight() && i+1 < model.getWidth()){
					switch (gameValue){
					case PLUSMINUS: if (PlusMinusCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1,j+1), random))
						return true;
						if (PlusMinusCheck.check(getValue(i,j), getValue(i, j+1), getValue(i+1, j), random))
							return true;
						break;
					case MULITDIV: if (MultDivCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1, j+1), random))
						return true;
						if (MultDivCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1, j+1), random))
							return true;
						break;
					case PLUSMINUSMULTDIV: if (MultDivPlusMinusCheck.check(getValue(i,j),getValue(i+1, j), getValue(i+1, j+1), random))
							return true;
						if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+1, j+1), random))
							return true;
						break;
					}
				}
			}
		}
		return false;
	}
	
	
	private boolean check; 
	private int counterForValue;
	private int gameValue;
	private int maxNumber;
	private int actualSearchedNumber;
	private JButton[] values;
	private JButton[][] buttons;
	private GameField model;
	private JPanel panel;
}
