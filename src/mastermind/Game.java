/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind; 

import java.util.ArrayList;
import java.util.Random;

/**
 * Class to create the game mechanics for the Game known as Mastermind
 * @author jra2995
 * @version 1.00
 */
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
	 * Gets the valid colors for the game's pegs
	 * @return all valid colors as ArrayList of Character objects
	 */
	public ArrayList<Character> getValidColors(){
		return validColors;
	}
	
	/**
	 * Adds the color passed to this method to the list of valid colors
	 * @param c the new color to be added
	 */
	public void addColor(char c){
		validColors.add(c);
	}
	
	/**
	 * Sets the code length to the passed integer
	 * @param l the new code length for the game
	 */
	public void setCodeLength(int l){
		codeLength = l;
	}
	
	/**
	 * Gets the secret code for the Mastermind game
	 * @return the code to be guessed by the user
	 */
	public String getSecretCode(){
		return secretCode;
	}
	
	/**
	 * Gets the length of the code to be guessed
	 * @return the length of the secret code
	 */
	public int getCodeLength(){
		return codeLength;
	}
	
	/**
	 * Gets the history of guesses as strings that were evaluated as
	 * guesses by the game (only valid guesses)
	 * @return the list of previous guesses
	 */
	public ArrayList<String> getHistoryOfGuesses(){
		return historyOfGuesses;
	}
	
	/**
	 * Gets the history of black and white peg outputs for evaluated
	 * guesses
	 * @return the history of black and white pegs as String outputs
	 */
	public ArrayList<String> getHistoryOfPegs(){
		return historyOfPegs;
	}
	
	/**
	 * Gets the number of guesses left in this iteration of the game
	 * @return gets the number of guesses left in the game
	 */
	public int getNumGuesses(){
		return numGuesses;
	}
	
	/**
	 * Checks whether or not the guess passed into this method is valid
	 * i.e. each letter must be one of the valid colors, and the length
	 * must match the length of the secret code
	 * @param guess the guess to be checked for validity
	 * @return true if the guess is a valid guess, false otherwise
	 */
	public boolean validGuess(String guess){
		
		// Checks that the guess length is the same as the secret code's
		// length
		if(guess.length() != secretCode.length()){
			return false;
		}
		
		// Cycles through the letters of guess to check them individually
		// as part of the validity test
		for(int i = 0; i < guess.length(); i++){
			boolean isColor = false;
			
			// Cycles through all the colors to make sure each letter
			// in the guess is at least one valid color
			for(int j = 0; j < validColors.size(); j++){
				
				// If the character in the guess is a valid color,
				// will break out of the loop and we've found a match
				if(guess.charAt(i) == validColors.get(j)){
					isColor = true;
					break;
				}
			}
			
			// If the character in the guess was not valid, then the 
			// whole guess is invalid, so return false
			if(!isColor){
				return false;
			}
		}
		
		// If we've checked every character and haven't returned,
		// then the code is a valid guess
		return true;
	}
	
	/**
	 * Evaluate the guess and determine what the output of black and 
	 * white pegs
	 * Precondition: Guess must be validated first, otherwise output could be
	 * unforseen
	 * @param guess the guess to compared against the secret code
	 * @return a String representing the output message of black and white pegs
	 */
	public String evaluateGuess(String guess){
		// Since the guess is valid, add it to the history of guesses evaluated
		historyOfGuesses.add(guess);
		
		// Make sure we keep track of the original secret code
		String copyCode = secretCode;
		
		// Find out how many black pegs are generated by the guess
		String result1 = checkBlackPegs(guess);
		
		// Get the number of black pegs from the modified guess
		int numBlackPegs = calculateNumBlackPegs(result1);
		
		// Find out how many white pegs are generated by the guess
		String result2 = checkWhitePegs(result1);
		
		// Get the number of white pegs from the modified guess
		int numWhitePegs = calculateNumWhitePegs(result2, numBlackPegs);
		
		String answer = "";
		
		// If we're in testing mode, output the secret code and modified code,
		// or keeps the code a secret
		if(testing){
			answer = answer + testOutput(copyCode) + "\nResult: ";
		}
		else{
			answer = "Result: ";
		}
		
		// Based on the number of black and white pegs, save an appropriate answer 
		// in a  string form and add it to the history of pegs list
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
		
		// Get the secret code back to its original state
		secretCode = copyCode;
		
		// Take one away from the number of guesses
		numGuesses -= 1;
		
		// Return the output to be printed as a result of the guess
		return answer;
	}
	
	/**
	 * Returns the passed original code and modified code into a 
	 * convenient debugging string
	 * @param originalCode the code to also be output to the string
	 * @return the debug string with the secret code (modified) and the original code
	 */
	public String testOutput(String originalCode){
		String output = "The secret code is : " + originalCode + ". The edited code is " + secretCode;
		return output;
	}
	
	/**
	 * Resets the game by emptying the history lists and the number
	 * of guesses back to the default (12) number
	 */
	public void reset(){
		historyOfGuesses = new ArrayList<String>();
		historyOfPegs = new ArrayList<String>();
		numGuesses = 12;
	}
	
	/**
	 * Checks for any black pegs from the passed guess against the secret code
	 * @param guess the guess to be checked for any black pegs
	 * @return the modified guess with -'s to represent black pegs found
	 */
	private String checkBlackPegs(String guess){
		// Cycles through each letter in the secret code to check against the guess
		for(int i = 0; i < secretCode.length(); i++){
			// If the letter in the secret code matches the guess, then replace the guess
			// with -'s at the matching letter index in both secret code and guess
			// The condition is to handle the substring process, based on if the letter index
			// is the last index or not
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
	
	/**
	 * Based on the number of -'s in the guess, return the number of
	 * -'s as the number of black pegs
	 * @param guess the modified guess after black pegs are checked
	 * @return the number of -'s (representing black pegs) in the modified guess
	 */
	private int calculateNumBlackPegs(String guess){
		int num = 0;
		
		// For each guess's character, if that character is a -, then
		// increase the number of black pegs found
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == '-'){
				num++;
			}
		}
		
		return num;
	}
	
	/**
	 * Check for white pegs based on a modified guess (after black pegs) are checked
	 * @param guess the guess that is modified by the black pegs method
	 * @return the modified guess after white pegs are checked for
	 */
	private String checkWhitePegs(String guess){
		// For each character in the guess, check for white pegs
		for(int i = 0; i < guess.length(); i++){
			// If the character is a -, it's already been checked for a black peg, 
			// so move on
			if(guess.charAt(i) == '-'){
				continue;
			}
			else{
				// Otherwise, you have to check if the character is in the secret code
				for(int j = 0; j < secretCode.length(); j++){
					// If the secret code character is a dash, move on
					if(secretCode.charAt(j) == '-'){
						continue;
					}
					else{
						// Otherwise, if the character matches up with a secret code character,
						// mark that character in the guess and secret code with a -
						if(guess.charAt(i) == secretCode.charAt(j)){
							// Mark the character in the secretCode is a -
							char[] tmp = secretCode.toCharArray();
							tmp[j] = '-';
							secretCode = new String(tmp);
							
							// Mark the character in the guess as a -
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
	
	/**
	 * Calculate the number of white pegs based on the number of black pegs
	 * and the new modified guess once white pegs have been found
	 * @param guess the modified guess to calculate white pegs
	 * @param black the number of black pegs already found
	 * @return the number of white pegs
	 */
	private int calculateNumWhitePegs(String guess, int black){
		int numColoredPegs = 0;
		
		// For each character in the guess, the number of -'s
		// indicate the number of pegs output
		for(int i = 0; i < guess.length(); i++){
			if(guess.charAt(i) == '-'){
				numColoredPegs++;
			}
		}
		
		// The number of white pegs is the number of B&W pegs 
		// subtracted with the black pegs
		int numWhite = numColoredPegs - black;
		
		return numWhite;
	}
}
