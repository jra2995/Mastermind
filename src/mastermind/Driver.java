/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind;

/**
 * Driver is the main logic for input, output, and game logic that
 * doesn't involve the actual calculation of pegs, so top-level logic
 * @author jra2995
 * @version 1.00
 */
public class Driver {

	public static void main(String[] args) {
		// Boolean to hold whether or not we're going to play again
		boolean play = true;
		
		// Display the Introduction only once
		Bot.displayIntro();
		
		// Loop to cycle through one to many games of mastermind
		do{
			// New iteration of the mastermind game
			Game theGame = new Game();
			
			// Prompt the user if they want to start another game, 
			// and close out if they don't want to
			play = Bot.promptUserStart();
			if(!play){
				System.exit(0);
				Bot.scan.close();
			}
			
			// Prompt the user if they want to customize the game at all
			boolean customize = Bot.promptUserCustomization(false);
			
			// Allow player to customize the game as many times as they want
			while(customize){
				// Get customization option
				String option = Bot.customizationOptionMenu();
				
				if(option.equalsIgnoreCase("Number of Guesses")){
					// Change number of guesses
					int num = Bot.changeNumGuesses();
					theGame.setNumGuesses(num);
				}
				else if(option.equalsIgnoreCase("Code Length")){
					// Change code length
					int num = Bot.changeCodeLength();
					theGame.setCodeLength(num);
				}
				else if(option.equalsIgnoreCase("Number of Colors")){
					// Add more colors
					theGame.setValidColors(Bot.moreColors(theGame.getValidColors()));
				}
				
				customize = Bot.promptUserCustomization(true);
			}
			
			// Generate secret code for this iteration of the game
			theGame.generateSecretCode();
			Bot.displayGeneration();
			
			// Main game logic, iterate until number of guesses are up
			while(theGame.getNumGuesses() > 0){
				// Prompt user to guess
				String guess = Bot.promptGuess(theGame.getNumGuesses(), false);
				
				// Display history is user inputs to do so
				if(Bot.checkForHistory(guess)){
					Bot.displayHistory(theGame.getHistoryOfGuesses(), theGame.getHistoryOfPegs());
					continue;
				}
				
				// Check that the guess is valid, and cycle until it is
				boolean isValid = theGame.validGuess(guess);
				while(!isValid){
					guess = Bot.promptGuess(theGame.getNumGuesses(), true);
					
					// User can still check history if their previous guess was invalid
					if(Bot.checkForHistory(guess)){
						Bot.displayHistory(theGame.getHistoryOfGuesses(), theGame.getHistoryOfPegs());
						continue;
					}
					isValid = theGame.validGuess(guess);
				}
				
				// Evaluate the guess
				String output = theGame.evaluateGuess(guess);
				
				// Display the user's guess back and the peg output
				Bot.displayResult(guess, output);
				
				// Display victory if guess is the same as the code,
				// otherwise if the last guess was just used and
				// there was no victory, then the user loses.
				if(guess.equalsIgnoreCase(theGame.getSecretCode())){
					Bot.displayVictory();
					break;
				}
				else if(theGame.getNumGuesses() == 0){
					Bot.displayLoss();
					break;
				}
			}
			
		}while(play);
	}

}
