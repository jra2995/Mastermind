/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Bot handles the input and output logic for the game (short for robot)
 * @author jra2995
 * @version 1.00
 */
public class Bot {
	// Scanner for user input
	public static Scanner scan = new Scanner(System.in);
	
	/**
	 * Displays the intro message for the game
	 */
	public static void displayIntro(){
		System.out.println("Welcome to Mastermind.  Here are the rules.\n" + 
			"This is a text version of the classic board game\n" + 
			"Mastermind. The computer will think of a secret\n" + 
			"code. The code consists of 4 colored pegs. The pegs\n" + 
			"may be one of six colors: Blue, Green, Orange, Purple,\n" + 
			"Red, or Yellow. A color may appear more than once in\n" + 
			"the code. You try to guess what colored pegs are in\n" + 
			"the code and what order they are in. After you make\n" + 
			"a valid guess the result (feedback) will be\n" + 
			"displayed. The result consists of a black peg for\n" + 
			"each peg you have guessed exactly correct (color and\n" + 
			"position) in your guess. For each peg in the guess\n" + 
			"that is the correct color, but is out of position,\n" + 
			"you get a white peg. For each peg, which is fully\n" + 
			"incorrect, you get no feedback. Only the first letter\n" + 
			"of the color is displayed. B for Blue, R for Red, and\n" + 
			"so forth. When entering guesses you only need to\n" + 
			"enter the first character of each color as a capital\n" + 
			"letter. You have 12 guesses to figure out the secret\n" + 
			"code or you lose the game.\n");
	}
	
	/**
	 * Prompts the user if they are ready to play or not
	 * @return true if they are ready to play (yes) or false if not (no)
	 */
	public static boolean promptUserStart(){
		// Prompts the user for their response, Y/N are valid
		System.out.println("Are you ready to play? (Y/N)");
		String response = scan.nextLine();
		
		// If Y, return true, else N return false and wave goodbye
		if(response.equalsIgnoreCase("Y")){
			return true;
		}
		else if(response.equalsIgnoreCase("N")){
			System.out.println("Goodbye!");
			return false;
		}
		else{
			// Or if the input is invalid, cycle through until we get
			// the correct input, either Y or N
			while(!response.equalsIgnoreCase("Y") || !response.equalsIgnoreCase("N")){
				System.out.println("Error - Invalid Input. Please try again.\n" + 
					"Are you ready to play? (Y/N)");
				response = scan.nextLine();
				System.out.println("");
				if(response.equalsIgnoreCase("Y")){
					return true;
				}
				else if(response.equalsIgnoreCase("N")){
					System.out.println("Goodbye!");
					
					return false;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Prompt the user for a guess
	 * @param numGuesses the number of guesses in the game
	 * @param repeat if the guess was invalid, display a different prompt
	 * @return the guess the user has entered, invalid or otherwise
	 */
	public static String promptGuess(int numGuesses, boolean repeat){
		String response;
		
		// If repeated prompt, display different prompt message
		if(!repeat){
			// Prompt for next guess
			System.out.println("You have " + numGuesses + " guesses left.");
			System.out.println("What is your next guess?\nType in the " + 
				"characters for your guess and press enter (or type <history>)");
			System.out.print("Enter guess: ");
			response = scan.nextLine();
			System.out.println("");
		}
		else{
			// Guess was invalid, so prompt for another guess
			System.out.println("Error - Your previous guess was invalid. Please try again.\n");
			System.out.println("You have " + numGuesses + " guesses left.");
			System.out.println("What is your next guess?\nType in the " + 
				"characters for your guess and press enter (or type <history>)");
			System.out.println("Enter guess: ");
			response = scan.nextLine();
			System.out.println("");
		}
		
		
		return response;
	}
	
	/**
	 * Based on the guess, if it equals "history", then return a boolean
	 * appropriately
	 * @param guess the guess entered by the user
	 * @return true if the guess was actually "history" command, false otherwise
	 */
	public static boolean checkForHistory(String guess){
		if(guess.equalsIgnoreCase("history")){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Prompt the user if they want to make any customization options
	 * @param repeat if one customization has already been made, repeat is true
	 * otherwise it's false
	 * @return true if any customization should be made, false if not
	 */
	public static boolean promptUserCustomization(boolean repeat){
		// Prompt for customization, change the prompt based on multiple
		// prompts
		System.out.print("Would you like to customize the game");
		if(repeat){
			System.out.println(" again (Y/N): ");
		}
		else{
			System.out.println(" now (Y/N): ");
		}
		
		// Get the user input, and check if it's valid Y/N
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Y")){
			return true;
		}
		else if(response.equalsIgnoreCase("N")){
			return false;
		}
		else{
			// Otherwise cycle until we get valid input Y/N to the
			// prompt
			while(!response.equalsIgnoreCase("Y") || !response.equalsIgnoreCase("N")){
				System.out.print("Error - Invalid Input. Please try again.\n" + 
					"Would you like to customize the game");
				if(repeat){
					System.out.println(" again (Y/N): ");
				}
				else{
					System.out.println(" now (Y/N): ");
				}
				response = scan.nextLine();
				if(response.equalsIgnoreCase("Y")){
					return true;
				}
				else if(response.equalsIgnoreCase("N")){
					return false;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String customizationOptionMenu(){
		System.out.println("How would you like to customize the game?\nValid responses: " + 
			"<Number of Guesses>, <Code Length>, or <Number of Colors>:");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Number of Guesses")){
			
			return response;
		}
		else if(response.equalsIgnoreCase("Code Length")){
			
			return response;
		}
		else if(response.equalsIgnoreCase("Number of Colors")){
			
			return response;
		}
		else{
			while(!response.equalsIgnoreCase("Number of Guesses") || 
				!response.equalsIgnoreCase("Code Length") || !response.equalsIgnoreCase("Number of Colors")){
				System.out.println("Error - Invalid Input. Please try again.\n");
				System.out.println("How would you like to customize the game?\nValid responses: " + 
						"<Number of Guesses>, <Code Length>, or <Number of Colors>:");
				response = scan.nextLine();
				if(response.equalsIgnoreCase("Number of Guesses")){
					
					return response;
				}
				else if(response.equalsIgnoreCase("Code Length")){
					
					return response;
				}
				else if(response.equalsIgnoreCase("Number of Colors")){
					
					return response;
				}
			}
		}
		
		return "";
	}
	
	public static ArrayList<Character> moreColors(ArrayList<Character> oldColors){
		
		boolean proceed = true;
		int num = 0;
		do{
			try{
				do{
					System.out.println("How many colors would you like to add? Note: Up to " + 
							(26 - oldColors.size()) + " More");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					if(num > 26 - oldColors.size()){
						System.out.println("Error - Number can only be up to " + (26 - oldColors.size() + " more"));
					}
					else if(num < 0){
						System.out.println("Error - Can't be negative answer");
					}
				}while(num > 26 - oldColors.size() || num < 0);
			}
			catch(NumberFormatException nfe){
				System.out.println("Error - Invalid Input. Only integers are acceptable.");
				proceed = false;
			}
		}while(!proceed);
		
		for(int i = 0; i < num; i++){
			System.out.println("What color would you like to add? <Valid input are Strings, though " + 
				"only the first character will be used, if possible.>:");
			String color = scan.nextLine();
			char c = color.toUpperCase().charAt(0);
			if(!oldColors.contains(c)){
				oldColors.add(c);
				System.out.println(color + " will now be represented by " + c + ".");
			}
			else{
				char start = 'A';
				for(char j = start; j < start + 26; j++){
					if(!oldColors.contains(j)){
						oldColors.add(j);
						System.out.println(color + " will now be represented by " + j + ", as " + 
							c + " is taken already.");
						break;
					}
				}
			}
		}
		
		return oldColors;
	}
	
	public static int changeCodeLength(){
		
		//System.out.println("What new length would you like to change the code to?: ");
		boolean proceed = true;
		int num = 0;
		do{
			try{
				do{
					System.out.println("How many long would you like the code to be? Note: Up to " + 
							"26 Letters");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					if(num > 26){
						System.out.println("Error - Number can only be up to 26");
					}
					else if(num < 1){
						System.out.println("Error - Code has to be at least one letter long.");
					}
				}while(num > 26 || num < 1);
			}
			catch(NumberFormatException nfe){
				System.out.println("Error - Invalid Input. Only integers are acceptable.");
				proceed = false;
			}
		}while(!proceed);
		
		return num;
	}
	
	public static int changeNumGuesses(){
		
		//System.out.println("How many guesses would you like to have?: ");
		boolean proceed = true;
		int num = 0;
		do{
			try{
				do{
					System.out.println("How many guesses would you like to have?");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					if(num < 1){
						System.out.println("Error - You Must At Least Have One Guess");
					}
				}while(num < 1);
			}
			catch(NumberFormatException nfe){
				System.out.println("Error - Invalid Input. Only integers are acceptable.");
				proceed = false;
			}
		}while(!proceed);
		
		return num;
	}
	
	public static void displayResult(String guess, String output){
		System.out.println(guess + " -> " + output);
	}
	
	public static void displayVictory(){
		System.out.println("\nVictory is yours, mastermind!\n");
	}
	
	public static void displayLoss(){
		System.out.println("\nYou lose. Guess you got outsmarted, huh?\n");
	}
	
	public static void displayHistory(ArrayList<String> guesses, ArrayList<String> pegs){
		if(guesses.size() == 0){
			System.out.println("No History\n");
		}
		
		for(int i = 0; i < guesses.size(); i++){
			System.out.print("Guess " + (i+1) + ": " + guesses.get(i));
			System.out.println(" -> " + pegs.get(i));
		}
		
		System.out.println("");
	}
	
	public static void displayGeneration(){
		System.out.println("Generating secret code...\n");
	}
}
