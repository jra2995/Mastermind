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
	 * Brings up a customization menu based that will return the option
	 * of either changing the number of guesses, code length, and number
	 * of colors. 
	 * @return String that represents which customization option will happen
	 */
	public static String customizationOptionMenu(){
		// Prompts the user how they want to customize the game
		System.out.println("How would you like to customize the game?\nValid responses: " + 
			"<Number of Guesses>, <Code Length>, or <Number of Colors>:");
		String response = scan.nextLine();
		
		// Returns the response if it is a valid customization option
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
			// Otherwise cycle until we get a valid response for 
			// customization and print out error message
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
	
	/**
	 * Handles adding more colors to the program through input prompts
	 * and output error messages
	 * @param oldColors the colors currently used for the game
	 * @return the new colors array list
	 */
	public static ArrayList<Character> moreColors(ArrayList<Character> oldColors){
		// Variable that will determine if we can begin adding to the 
		// array list based on user input
		boolean proceed = true;
		int num = 0;
		
		// Cycle through until we get correct input
		do{
			// Catch any errors in input
			try{
				// While the number of colors added is negative or more than
				// supported, keep trying to get input
				do{
					// Prompt for number of colors to add
					System.out.println("How many colors would you like to add? Note: Up to " + 
							(26 - oldColors.size()) + " More");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					
					// Print out errors in adding colors
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
		
		// Cycle through the number of colors to add
		for(int i = 0; i < num; i++){
			// Prompt for a new color
			System.out.println("What color would you like to add? <Valid input are Strings, though " + 
				"only the first character will be used, if possible.>:");
			String color = scan.nextLine();
			char c = color.toUpperCase().charAt(0);
			
			// If the first letter of the new color hasn't been used,
			// add it to the list of colors
			if(!oldColors.contains(c)){
				oldColors.add(c);
				System.out.println(color + " will now be represented by " + c + ".");
			}
			else{
				// Otherwise cycle through the alphabet until we find an unused letter
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
	
	/**
	 * Input/output to handle changing the code length for the game
	 * @return the int number to change the code length to
	 */
	public static int changeCodeLength(){
		boolean proceed = true;
		int num = 0;
		
		// Cycle until we get correct input with no errors 
		do{
			try{
				// Cycle until we get an appropriate number for the 
				// code length 
				do{
					// Prompt for input
					System.out.println("How many long would you like the code to be? Note: Up to " + 
							"26 Letters");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					
					// Catch if the number is greater than 26 or less than 1 (unplayable)
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
	
	/**
	 * Input/Output to handle changing the number of guesses for the game
	 * @return the new number of guesses for the game
	 */
	public static int changeNumGuesses(){
		boolean proceed = true;
		int num = 0;
		
		// Cycle through until we don't have any actual errors with  
		// input
		do{
			try{
				// Cycle until we get a number that is equal to or more
				// than one. Less than one guess makes the game unplayable
				do{
					// Prompt for number of guesses
					System.out.println("How many guesses would you like to have?");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					
					// 0 or negative input will make the game unplayable, so catch it
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
	
	/**
	 * display the result for the guess to the console
	 * @param guess the current guess for the game
	 * @param output the output associated with the guess
	 */
	public static void displayResult(String guess, String output){
		System.out.println(guess + " -> " + output);
	}
	
	/**
	 * Congratulates the user after a victory
	 */
	public static void displayVictory(){
		System.out.println("\nVictory is yours, mastermind!\n");
	}
	
	/**
	 * Taunts the user after a loss
	 */
	public static void displayLoss(){
		System.out.println("\nYou lose. Guess you got outsmarted, huh?\n");
	}
	
	/**
	 * Displays the history of the current game
	 * @param guesses the valid guesses for the user's game so far
	 * @param pegs the B&W output pegs for corresponding guesses
	 */
	public static void displayHistory(ArrayList<String> guesses, ArrayList<String> pegs){
		// If there are no guesses in the list, there haven't been 
		// any valid guesses yet, so there's no history
		if(guesses.size() == 0){
			System.out.println("No History\n");
		}
		
		// Cycles through the list of guesses and displays the guess
		// and output for each combination
		for(int i = 0; i < guesses.size(); i++){
			System.out.print("Guess " + (i+1) + ": " + guesses.get(i));
			System.out.println(" -> " + pegs.get(i));
		}
		
		System.out.println("");
	}
	
	/**
	 * Display generation message for the program following 
	 * secret code generation
	 */
	public static void displayGeneration(){
		System.out.println("Generating secret code...\n");
	}
}
