/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind; 

import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	// Holds the secret code and code length for this game of Mastermind
	private String secretCode;
	private int codeLength;
	
	// Holds the history of guesses and black and white peg outputs based on the valid inputs 
	// from the user
	private ArrayList<String> historyOfGuesses;
	private ArrayList<String> historyOfPegs;
	
	// Holds the total number of guesses allowed for the game
	private int numGuesses;
	
	// Determines if the secret code should be revealed for debugging purposes or not
	private boolean testing;
	
	// Holds the characters representing the valid colors for the code's pegs
	private ArrayList<Character> validColors;
	
	/**
	 * Default constructor that will run a game in non-debug mode
	 */
	public Game(){
		this(false);
	}
	
	/**
	 * Based on the boolean input b, will run a game of mastermind in either debug mode
	 * or non-debug mode. 
	 * @param b is the boolean determining debug mode - true equals debug mode, false equals 
	 * non-debug mode
	 */
	public Game(boolean b){
		testing = b;
		
		// Default number of guesses is 12
		numGuesses = 12;
		
		// Default code length is 4
		codeLength = 4;
		secretCode = "";
		historyOfGuesses = new ArrayList<String>();
		historyOfPegs = new ArrayList<String>();
		validColors = new ArrayList<Character>();
		
		// Default colors are Blue, Orange, Green, Purple, Red, and Yellow
		validColors.add('B');
		validColors.add('O');
		validColors.add('G');
		validColors.add('P');
		validColors.add('R');
		validColors.add('Y');
	}
	
	/**
	 * Generates the secret code for this game using a random number generator
	 * and the valid colors for the game's code
	 */
	public void generateSecretCode(){
		Random rand = new Random();
		char[] code = new char[codeLength];
		
		// Creates a code of length code length into a character array
		for(int i = 0; i < codeLength; i++){
			code[i] = validColors.get(rand.nextInt(validColors.size()));
		}
		
		// And then converts that code to a String representation
		secretCode = new String(code);
	}
	
	/**
	 * Sets the new number of guesses
	 * @param g the new number of guesses
	 */
	public void setNumGuesses(int g){
		numGuesses = g;
	}
	
	/**
	 * Sets valid colors to the new list of valid colors
	 * @param ac the new list of valid colors
	 */
	public void setValidColors(ArrayList<Character> ac){
		validColors = new ArrayList<Character>(ac);
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Character> getValidColors(){
		return validColors;
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
	
	public boolean validGuess(String guess){
		if(guess.length() != secretCode.length()){
			return false;
		}
		
		for(int i = 0; i < guess.length(); i++){
			boolean isColor = false;
			for(int j = 0; j < validColors.size(); j++){
				if(guess.charAt(i) == validColors.get(j)){
					isColor = true;
					break;
				}
			}
			if(!isColor){
				return false;
			}
		}
		
		return true;
	}
	
	public String evaluateGuess(String guess){
		historyOfGuesses.add(guess);
		String copyCode = secretCode;
		String result1 = checkBlackPegs(guess);
		int numBlackPegs = calculateNumBlackPegs(result1);
		String result2 = checkWhitePegs(result1);
		int numWhitePegs = calculateNumWhitePegs(result2, numBlackPegs);
		String answer = "";
		
		if(testing){
			answer = answer + testOutput(copyCode) + "\nResult: ";
		}
		else{
			answer = "Result: ";
		}
		
		if(numBlackPegs == 0 && numWhitePegs == 0){
			answer = answer + "No pegs";
			historyOfPegs.add("No Pegs");
		}
		else if(numBlackPegs == 0){
			answer = answer + numWhitePegs + " white pegs";
			historyOfPegs.add(numWhitePegs + " white pegs");
		}
		else if(numWhitePegs == 0){
			answer = answer + numBlackPegs + " black pegs";
			historyOfPegs.add(numBlackPegs + " black pegs");
		}
		else{
			answer = answer + numBlackPegs + " black pegs and " + numWhitePegs + " white pegs";
			historyOfPegs.add(numBlackPegs + " black pegs and " + numWhitePegs + " white pegs");
		}
		
		secretCode = copyCode;
		numGuesses -= 1;
		return answer;
	}
	
	public String testOutput(String originalCode){
		String output = "The secret code is : " + originalCode + ". The edited code is " + secretCode;
		return output;
	}
	
	public void reset(){
		historyOfGuesses = new ArrayList<String>();
		historyOfPegs = new ArrayList<String>();
		numGuesses = 12;
	}
	
	private String checkBlackPegs(String guess){
		for(int i = 0; i < secretCode.length(); i++){
			if(secretCode.charAt(i) == guess.charAt(i) && i != guess.length() - 1){
				guess = guess.substring(0, i) + "-" + guess.substring(i + 1);
				secretCode = secretCode.substring(0, i) + "-" + secretCode.substring(i + 1);
			}
			else if(secretCode.charAt(i) == guess.charAt(i)){
				guess = guess.substring(0, i) + "-";
				secretCode = secretCode.substring(0, i) + "-";
			}
		}
		
		return guess;
	}
	
	private int calculateNumBlackPegs(String guess){
		int num = 0;
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == '-'){
				num++;
			}
		}
		
		return num;
	}
	
	private String checkWhitePegs(String guess){
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == '-'){
				continue;
			}
			else{
				for(int j = 0; j < secretCode.length(); j++){
					if(secretCode.charAt(j) == '-'){
						continue;
					}
					else{
						if(guess.charAt(i) == secretCode.charAt(j)){
							char[] tmp = secretCode.toCharArray();
							tmp[j] = '-';
							secretCode = new String(tmp);
							char[] tmp2 = guess.toCharArray();
							tmp2[i] = '-';
							guess = new String(tmp2);
							break;
						}
					}
				}
			}
		}
		
		return guess;
	}
	
	private int calculateNumWhitePegs(String guess, int black){
		int numColoredPegs = 0;
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == '-'){
				numColoredPegs++;
			}
		}
		
		int numWhite = numColoredPegs - black;
		
		return numWhite;
	}
}
