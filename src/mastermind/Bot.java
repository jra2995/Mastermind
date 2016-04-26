/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind;

import java.util.Scanner;
import java.util.ArrayList;

public class Bot {
	public static void displayIntro(){
		System.out.println("Welcome to Mastermind.  Here are the rules." + 
			"This is a text version of the classic board game " + 
			"Mastermind. The computer will think of a secret " + 
			"code. The code consists of 4 colored pegs. The pegs " + 
			"may be one of six colors: Blue, Green, Orange, Purple, " + 
			"Red, or Yellow. A color may appear more than once in " + 
			"the code. You try to guess what colored pegs are in " + 
			"the code and what order they are in.   After you make " + 
			"a valid guess the result (feedback) will be " + 
			"displayed. The result consists of a black peg for " + 
			"each peg you have guessed exactly correct (color and " + 
			"position) in your guess.  For each peg in the guess " + 
			"that is the correct color, but is out of position, " + 
			"you get a white peg.  For each peg, which is fully " + 
			"incorrect, you get no feedback. Only the first letter " + 
			"of the color is displayed. B for Blue, R for Red, and " + 
			"so forth. When entering guesses you only need to " + 
			"enter the first character of each color as a capital " + 
			"letter. You have 12 guesses to figure out the secret " + 
			"code or you lose the game.");
	}
	
	public static boolean promptUserStart(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Are you ready to play? (Y/N)\n");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Y")){
			System.out.println("Generating secret code....\n");
			scan.close();
			return true;
		}
		else if(response.equalsIgnoreCase("N")){
			System.out.println("Goodbye!");
			scan.close();
			return false;
		}
		else{
			while(!response.equalsIgnoreCase("Y") || !response.equalsIgnoreCase("N")){
				System.out.println("Error - Invalid Input. Please try again.\n" + 
					"Are you ready to play? (Y/N)\n");
				response = scan.nextLine();
				if(response.equalsIgnoreCase("Y")){
					System.out.println("Generating secret code....\n");
					scan.close();
					return true;
				}
				else if(response.equalsIgnoreCase("N")){
					System.out.println("Goodbye!");
					scan.close();
					return false;
				}
			}
		}
		scan.close();
		return false;
	}
	
	public static String promptGuess(int numGuesses, boolean repeat){
		Scanner scan = new Scanner(System.in);
		String response;
		if(!repeat){
			System.out.println("You have " + numGuesses + " left.\n");
			System.out.println("What is your next guess?\nType in the " + 
				"characters for your guess and press enter");
			System.out.println("Enter guess: ");
			response = scan.nextLine();
		}
		else{
			System.out.println("Error - Your previous guess was invalid. Please try again.\n");
			System.out.println("You have " + numGuesses + " left.\n");
			System.out.println("What is your next guess?\nType in the " + 
				"characters for your guess and press enter");
			System.out.println("Enter guess: ");
			response = scan.nextLine();
		}
		
		scan.close();
		return response;
	}
	
	public static boolean promptUserCustomization(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to customize the game now (Y/N)\n");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Y")){
			scan.close();
			return true;
		}
		else if(response.equalsIgnoreCase("N")){
			scan.close();
			return false;
		}
		else{
			while(!response.equalsIgnoreCase("Y") || !response.equalsIgnoreCase("N")){
				System.out.println("Error - Invalid Input. Please try again.\n" + 
					"Would you like to customize the game now? (Y/N)\n");
				response = scan.nextLine();
				if(response.equalsIgnoreCase("Y")){
					scan.close();
					return true;
				}
				else if(response.equalsIgnoreCase("N")){
					scan.close();
					return false;
				}
			}
		}
		scan.close();
		return false;
	}
	
	public static String customizationOptionMenu(){
		Scanner scan = new Scanner(System.in);
		System.out.println("How would you like to customize the game?\nValid responses: " + 
			"<Number of Guesses>, <Code Length>, or <Number of Colors>:\n");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Number of Guesses")){
			scan.close();
			return response;
		}
		else if(response.equalsIgnoreCase("Code Length")){
			scan.close();
			return response;
		}
		else if(response.equalsIgnoreCase("Number of Colors")){
			scan.close();
			return response;
		}
		else{
			while(!response.equalsIgnoreCase("Number of Guesses") || 
				!response.equalsIgnoreCase("Code Length") || !response.equalsIgnoreCase("Number of Colors")){
				System.out.println("Error - Invalid Input. Please try again.\n");
				System.out.println("How would you like to customize the game?\nValid responses: " + 
						"<Number of Guesses>, <Code Length>, or <Number of Colors>:\n");
				response = scan.nextLine();
				if(response.equalsIgnoreCase("Number of Guesses")){
					scan.close();
					return response;
				}
				else if(response.equalsIgnoreCase("Code Length")){
					scan.close();
					return response;
				}
				else if(response.equalsIgnoreCase("Number of Colors")){
					scan.close();
					return response;
				}
			}
		}
		scan.close();
		return "";
	}
	
	public static ArrayList<Character> moreColors(ArrayList<Character> oldColors){
		Scanner scan = new Scanner(System.in);
		boolean proceed = true;
		int num = 0;
		do{
			try{
				do{
					System.out.println("How many colors would you like to add? Note: Up to " + 
							(26 - oldColors.size()) + " More\n");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					if(num > 26 - oldColors.size()){
						System.out.println("Error - Number can only be up to " + (26 - oldColors.size() + " more"));
					}
				}while(num > 26 - oldColors.size());
			}
			catch(NumberFormatException nfe){
				System.out.println("Error - Invalid Input. Only integers are acceptable.");
				proceed = false;
			}
		}while(!proceed);
		
		for(int i = 0; i < num; i++){
			System.out.println("What color would you like to add? <Valid input are Strings, though " + 
				"only the first character will be used, if possible.>:\n");
			String color = scan.nextLine();
			char c = color.toUpperCase().charAt(0);
			if(!oldColors.contains(c)){
				oldColors.add(c);
				System.out.println(color + " will now be represented by " + c + ".");
			}
			else{
				char start = 'A';
				for(int j = 0; j < 26; j++){
					if(!oldColors.contains(start + j)){
						oldColors.add(c);
						System.out.println(color + " will now be represented by " + ((char)(start + j)) + ", as " + 
							c + " is taken already.");
						break;
					}
				}
			}
		}
		scan.close();
		return oldColors;
	}
	
	public static int changeCodeLength(){
		Scanner scan = new Scanner(System.in);
		System.out.println("What new length would you like to change the code to?: ");
		boolean proceed = true;
		int num = 0;
		do{
			try{
				do{
					System.out.println("How many long would you like the code to be? Note: Up to " + 
							"26 Letters\n");
					String response = scan.nextLine();
					num = Integer.parseInt(response);
					proceed = true;
					if(num > 26){
						System.out.println("Error - Number can only be up to 26 more");
					}
				}while(num > 26);
			}
			catch(NumberFormatException nfe){
				System.out.println("Error - Invalid Input. Only integers are acceptable.");
				proceed = false;
			}
		}while(!proceed);
		scan.close();
		return num;
	}
	
	public static int changeNumGuesses(){
		Scanner scan = new Scanner(System.in);
		System.out.println("How many guesses would you like to have?: ");
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
		scan.close();
		return num;
	}
	
	public static void displayResult(String guess, String output){
		System.out.println(guess + " -> " + output);
	}
}
