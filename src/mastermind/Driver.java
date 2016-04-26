/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind;

public class Driver {

	public static void main(String[] args) {
		Game theGame = new Game(true);
		boolean play = true;
		do{
			Bot.displayIntro();
			
			play = Bot.promptUserStart();
			
			if(!play){
				System.exit(0);
			}
			
			String guess = Bot.promptGuess(theGame.getNumGuesses(), true);
			boolean isValid = theGame.validGuess(guess);
			while(!isValid){
				guess = Bot.promptGuess(theGame.getNumGuesses(), false);
				isValid = theGame.validGuess(guess);
			}
			
			String output = theGame.evaluateGuess(guess);
			
			Bot.displayResult(guess, output);
			
		}while(play);
	}

}
