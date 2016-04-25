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
