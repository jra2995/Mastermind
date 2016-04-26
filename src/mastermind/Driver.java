/**
 * Jett Anderson
 * EID: jra2995
 * Bonus Assignment - Mastermind Game
 */

package mastermind;

public class Driver {

	public static void main(String[] args) {
		boolean play = true;
		
		Bot.displayIntro();
		
		do{
			Game theGame = new Game(true);
			
			boolean customize = Bot.promptUserCustomization();
			
			if(customize){
				String option = Bot.customizationOptionMenu();
				if(option.equalsIgnoreCase("Number of Guesses")){
					int num = Bot.changeNumGuesses();
					theGame.setNumGuesses(num);
				}
				else if(option.equalsIgnoreCase("Code Length")){
					int num = Bot.changeCodeLength();
					theGame.setCodeLength(num);
				}
				else if(option.equalsIgnoreCase("Number of Colors")){
					theGame.setValidColors(Bot.moreColors(theGame.getValidColors()));
				}
			}
			
			play = Bot.promptUserStart();
			
			if(!play){
				System.exit(0);
				Bot.scan.close();
			}
			
			theGame.generateSecretCode();
			
			while(theGame.getNumGuesses() > 0){
				String guess = Bot.promptGuess(theGame.getNumGuesses(), false);
				if(Bot.checkForHistory(guess)){
					Bot.displayHistory(theGame.getHistoryOfGuesses(), theGame.getHistoryOfPegs());
					continue;
				}
				boolean isValid = theGame.validGuess(guess);
				while(!isValid){
					guess = Bot.promptGuess(theGame.getNumGuesses(), true);
					isValid = theGame.validGuess(guess);
				}
				
				String output = theGame.evaluateGuess(guess);
				
				Bot.displayResult(guess, output);
				
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
