package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Random;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


import GameField.GameField;
import MathematicalLogik.MultDivCheck;
import MathematicalLogik.MultDivPlusMinusCheck;
import MathematicalLogik.PlusMinusCheck;

/** Board represents the Graphical Userinterface of the GameField.
 * Implements Observable so that an other graphical userinterface can get the updates of the board.
 * The class implements the creation of a new random number for playing Legaue of Numbers.
 * 
 * @author rana
 *
 */

public class Board extends Observable{
	public final static int PLUSMINUS = 1;
	public final static int MULITDIV = 2;
	public final static int PLUSMINUSMULTDIV = 3;
	private Font handwritting;
	
	/**
	 * 
	 * @param panel A JPanel for the JButtons of the GameField
	 * @param gameValue must be a static final int of the class. Determine which mathematical operations are allowed.
	 * @param maxNumber specifies the maximum of the values on a Field (the Buttons). 
	 */
	
	public Board( JPanel	panel, int gameValue, int maxNumber){
		try{
			handler = new FileHandler("Logging.txt");
		} catch (IOException e){ 
			handler = new ConsoleHandler();
		}
		log.addHandler(handler);
		
		this.panel = panel;
		this.gameValue = gameValue;
		this.maxNumber = maxNumber;
		handwritting = null;
		

		
		this.handwritting = FontLoader.loadGrungeFont();

		//Schriftart laden
		InputStream myStream = null;
		try {
			Path Pat = Paths.get(Board.class.getResource("GrungeHandwriting.ttf").toString());
			String ParsedPat = Pat.toString();
			myStream = new BufferedInputStream(new FileInputStream(ParsedPat)); 	 
			/*InputStream is = this.getClass().getResourceAsStream("GrungeHandwriting.ttf");
			//File fontFile = new File(this.getClass().getResource("../../../Fonts/Handwritting.ttf").toURI());
			this.handwritting = Font.createFont(Font.PLAIN, is);//.deriveFont(Font.PLAIN, 20f);*/
		} catch (IOException e) {
			System.out.println("Probleme beim I/O");
		} catch (NullPointerException e){
			try {
				myStream = new BufferedInputStream(new FileInputStream("GrungeHandwriting.ttf"));
			} catch (FileNotFoundException e1) {
				System.out.println("Font-File konnte nicht gefunden werden.");
			}
		}
		try {
			handwritting = Font.createFont(Font.TRUETYPE_FONT, myStream).deriveFont(Font.PLAIN, 30);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Font Format Exception");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Probleme beim Laden der Schrift");
		}
		//Schriftart verwenden
		handwritting = FontLoader.loadGrungeFont();
		setDesign(panel);
		reset();
		panel.setSize(model.getWidth()*50, model.getHeight()*50);
		panel.setLayout(new GridLayout(model.getWidth(), model.getHeight()));
	}
	/** Creates a new GameField.
	 * An 2-dimensional JButtonArray of the size 10*10 and fill it with the values of the GameField.
	 * Every Button has an overwritten ActionListener which count how many buttons are clicked. If 3 Buttons are clicked, it will controll whether it is a valid game move for the gameMode (gameValue in constructor).
	 * If the move is valid, it will create a new random number. 
	 * This method notify the observers.
	 */
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
				setDesign(buttons[i][j]);
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
								boolean checked = false;
								if (gameValue == PLUSMINUS){
									if (PlusMinusCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										validAnswer = true;
										striking();
										checked = true;
									}
								}
								if (gameValue == MULITDIV){
									if (MultDivCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										validAnswer = true;
										striking();
										checked = true;
									}
								}
								if (gameValue == PLUSMINUSMULTDIV){
									if (MultDivPlusMinusCheck.check(intValues[0], intValues[1], intValues[2], actualSearchedNumber)){
										validAnswer = true;
										striking();
										checked = true;
									}
								}
								if (!checked){
									validAnswer = false;
									log.finest("Wrong answer!");
								}
								else{
									log.finest("Right answer!");
									createNewNumber();
								}
								
								update();
								setChanged();
								notifyObservers(actualSearchedNumber);
							}
						}
				});
			}
		}
	}
	/** Updates the GUI of the Board (Enabling of the Buttons)
	 *  
	 */
	
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
	
	/**
	 * Enable all buttons around the clicked button
	 * @param x int value for the width
	 * @param y int value for the height
	 * 
	 */
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
	/**
	 * 
	 * @return the height of the GameField
	 */
	public int getHeightGameField(){
		return model.getHeight();
	}
	/**
	 * 
	 * @return the width of the GameField
	 */
	public int getWidthGameField(){
		return model.getWidth();
	}
	/**
	 * 
	 * @return the actual Number wich should be searched
	 */
	public int getActualSearchedNumber(){
		return actualSearchedNumber;
	}
	/**
	 * Create a new number by calling method randomNumber();
	 */
	public boolean getValidAnswer(){
		return validAnswer;
	}
	
	public boolean checkReset(){
		int counter = 0;
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				if (buttons[i][j].isEnabled()){
					counter++;
				}
			}
		}
		log.fine("Resetting of the gamefield is " + ((counter == 0) ? "valid" : "invalid" )+ ".");
		System.out.println(counter);
		if (counter == 0){
			update();
			counterForValue = 0;
			return true;
		}
		return false;
	}
	private void createNewNumber(){
		log.fine("Created the new Number " + randomNumber());
		System.out.println("New generated number " + randomNumber());
	}
	/**
	 * 
	 * @param i width
	 * @param j height
	 * @return the value of the model
	 */
	private int getValue(int i, int j){
		return model.getValue(j, i);
	}
	/**
	 * returns whether the field is striked
	 * @param i width
	 * @param j height
	 */
	private boolean getStriked(int i, int j){
		return model.getStriked(j, i);
	}
	/**
	 * Set the field striked.
	 * @param i width
	 * @param j height
	 */
	private void setStriked(int i, int j){
		model.setStriked(i, j);
	}
	/**
	 * Could change the value of a JButton
	 * @param the button which should be checked
	 * @return counterForValue < 2 -> true else false
	 */
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
	/**
	 * Checks whether an JButton is choosen an second time
	 * @param b JButton which should controlled
	 * @return true if the JButton is already choosen else false.
	 */
	private boolean fieldAlreadyChoosen(JButton b){
		for (int i=0; i < counterForValue; i++){
			if (values[i].equals(b)){
				return true;
			}
		}
		return false;
	}
	/** Set the clicked values as striked
	 * 
	 */
	private void striking(){
		for (int i=0; i < 3; i++){
			int buttonString = Integer.parseInt(values[i].getActionCommand());
			int column = buttonString % getWidthGameField();
			int row = buttonString / getWidthGameField();
			setStriked(column, row);
		}
	}
	/**
	 * Creates a new random number. The maximum is the possible maximum number with the game config.
	 * e.g. MaxNumber is ten and only plus and minus is allowed: 10 + 10 + 10 = 30.
	 * @return the new random number, which can be build on the game Board.
	 */
	
	private int randomNumber(){
		Random r = new Random();
		int ret = 0;
		int max = 0;
		if (gameValue == PLUSMINUS){
			max = 3 * (maxNumber-1);
		}
		if (gameValue == MULITDIV || gameValue == PLUSMINUSMULTDIV){
			max = (maxNumber-1) * (maxNumber-1) * (maxNumber-1);
		}
		for (int i=0; i < 10;  i++){
			ret = r.nextInt(max);
			log.finest("Created the new random number " + ret);
			if (controllRandomNumber(ret)){
				actualSearchedNumber = ret;
				log.finer("New randomnumber is: " + ret);
				return ret;
			}
		}
		JDialog dialog = new JDialog();
		dialog.add(new JLabel("Time for a new Game!"));
		dialog.setSize(200,200);
		dialog.setVisible(true);
		
		return ret;
	}
	/**
	 * 
	 * @param random an potential value for checking whether the value is valid for the game.
	 * @return whether the random number can be computate on the build.
	 */
	private boolean controllRandomNumber(int random){
		for (int i=0; i < model.getWidth(); i++){
			for (int j=0; j < model.getHeight(); j++){
				if (i+2 < model.getWidth()){
					if (gameValue == PLUSMINUS){
						if (!(getStriked(i, j) || getStriked(i+1, j ) || getStriked(i+2, j))){
							switch (gameValue){
							case PLUSMINUS:
								if (PlusMinusCheck.check(getValue(i,j),getValue(i+1,j),getValue(i+2,j), random)){
									log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" 
											+ i+1 + "|" + j + "],[" + i+2 + "|" + j + "]");
									return true;
								}
								break;
							case MULITDIV:
								if (MultDivCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+2,j), random)){
									log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" 
											+ i+1 +"],[" +j + "],[" + i+2 + "],[" + j + "]");

									return true;
								}
								break;
							case PLUSMINUSMULTDIV:
								if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+2, j), random)){
									log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" +
											i+1 + "],[" + j + "],[" + i+2 + "],[" + j +"]");

									return true;
								}
								break;
							}
					}
					
				}
				if (j+2 < model.getHeight()){
					
					if (!(getStriked(i, j) || getStriked(i, j+1) || getStriked(i, j+2))){
						switch (gameValue){
						case PLUSMINUS: if (PlusMinusCheck.check(getValue(i,j), getValue(i,j+1), getValue(i, j+2), random)){
							log.finest("Random number can be found at [i|j] [" + i + "],[" + j + "],["+
									i + "|" + j + "],[" + i + "|" + j+2 + "]");

							return true;
						}
							break;
						case MULITDIV: if (MultDivCheck.check(getValue(i,j), getValue(i,j+1), getValue(i, j+2), random)){
							log.finest("Random number can be found at [i|j] [" + i + "],[" + j + "],["+
									i + "|" + j + "],[" + i + "|" + j+2 + "]");
							return true;
						}
							break;
						case PLUSMINUSMULTDIV: if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i, j+1), getValue(i, j+2), random)){
							log.finest("Random number can be found at [i|j] [" + i + "],[" + j + "],["+
									i + "|" + j + "],[" + i + "|" + j+2 + "]");
							return true;
						}
							break;
						}
					}
				}
				if (j+1 < model.getHeight() && i+1 < model.getWidth()){
					if (! (getStriked(i, j) || getStriked(i+1, j) || getStriked(i+1, j+1))){
						switch (gameValue){
						case PLUSMINUS: if (PlusMinusCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1,j+1), random)){
							log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" +
									i+1 + "|" + j + "],[" + i+1 + "|" + j+1 + "]");
							return true;
						}
							break;
						case MULITDIV: if (MultDivCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1, j+1), random)){
							log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" +
									i+1 + "|" + j + "],[" + i+1 + "|" + j+1 + "]");
							return true;
						}
							break;
						case PLUSMINUSMULTDIV: if (MultDivPlusMinusCheck.check(getValue(i,j),getValue(i+1, j), getValue(i+1, j+1), random)){
							log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" +
									i+1 + "|" + j + "],[" + i+1 + "|" + j+1 + "]");
							return true;
						}
							break;
						}
					}
					if (! (getStriked(i, j) || getStriked(i+1, j) ||getStriked(i+1, j+1))){
						switch(gameValue){
						case PLUSMINUS: 
							if (PlusMinusCheck.check(getValue(i,j), getValue(i, j+1), getValue(i+1, j), random)){
								log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" 
										+ i + "|" + j+1 + "],[" + i+1 + "|" + j + "]");
								return true;
							}
							break;
						case MULITDIV:
							if (MultDivCheck.check(getValue(i,j), getValue(i+1, j), getValue(i+1, j+1), random)){
								log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" 
										+ i + "|" + j+1 + "],[" + i+1 + "|" + j + "]");
								return true;
							}
							break;
						case PLUSMINUSMULTDIV:
							if (MultDivPlusMinusCheck.check(getValue(i,j), getValue(i+1,j), getValue(i+1, j+1), random)){
								log.finest("Random number can be found at [i|j] [" + i + "|" + j + "],[" 
										+ i + "|" + j+1 + "],[" + i+1 + "|" + j + "]");
								return true;
							}
							break;
						}
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Set the Design of a component in the following way:
	 * handwritting font, background = dark_gray, foreground = white
	 */
	private void setDesign(Component c){
		if (handwritting != null){
			c.setFont(handwritting);
		}else{
			c.setFont(new Font("Helvetica", Font.BOLD, 26));
		}
		c.setBackground(Color.DARK_GRAY);
		c.setForeground(Color.white);
	}
	private boolean validAnswer;
	private boolean check; 
	private int counterForValue;
	private int gameValue;
	private int maxNumber;
	private int actualSearchedNumber;
	private JButton[] values;
	private JButton[][] buttons;
	private GameField model;
	private JPanel panel;
	private final static Logger log = Logger.getLogger(Board.class .getName());
	private Handler handler;
}
