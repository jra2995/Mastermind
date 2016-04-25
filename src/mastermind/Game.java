/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind; 

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private String secretCode;
	private int codeLength;
	private ArrayList<String> historyOfGuesses;
	private ArrayList<String> historyOfPegs;
	private int numGuesses;
	private boolean testing;
	private ArrayList<Character> validColors;
	
	public Game(){
		this(false);
	}
	
	public Game(boolean b){
		testing = b;
		numGuesses = 12;
		codeLength = 4;
		secretCode = "";
		historyOfGuesses = new ArrayList<String>();
		historyOfPegs = new ArrayList<String>();
		validColors = new ArrayList<Character>();
		validColors.add('B');
		validColors.add('O');
		validColors.add('G');
		validColors.add('P');
		validColors.add('R');
		validColors.add('Y');
		Random rand = new Random();
		for(int i = 0; i < codeLength; i++){
			secretCode.concat(validColors.get(rand.nextInt(validColors.size())).toString());
		}
	}
	
	public void setNumGuesses(int g){
		numGuesses = g;
	}
	
	public void addColor(char c){
		validColors.add(c);
	}
	
	public void setCodeLength(int l){
		codeLength = l;
	}
	
	public String getSecretCode(){
		return secretCode;
	}
	
	public int getCodeLength(){
		return codeLength;
	}
	
	public ArrayList<String> getHistoryOfGuesses(){
		return historyOfGuesses;
	}
	
	public ArrayList<String> getHistoryOfPegs(){
		return historyOfPegs;
	}
	
	public int getNumGuesses(){
		return numGuesses;
	}
	
	public String evaluateGuess(String guess){
		
		return "";
	}
}
