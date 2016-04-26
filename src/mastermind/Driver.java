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
			Game theGame = new Game();
			
			play = Bot.promptUserStart();
			
			if(!play){
				System.exit(0);
				Bot.scan.close();
			}
			
			boolean customize = Bot.promptUserCustomization(false);
			
			while(customize){
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
				
				customize = Bot.promptUserCustomization(true);
			}
			
			theGame.generateSecretCode();
			Bot.displayGeneration();
			
			while(theGame.getNumGuesses() > 0){
				String guess = Bot.promptGuess(theGame.getNumGuesses(), false);
				if(Bot.checkForHistory(guess)){
					Bot.displayHistory(theGame.getHistoryOfGuesses(), theGame.getHistoryOfPegs());
					continue;
				}
				boolean isValid = theGame.validGuess(guess);
				while(!isValid){
					guess = Bot.promptGuess(theGame.getNumGuesses(), true);
					if(Bot.checkForHistory(guess)){
						Bot.displayHistory(theGame.getHistoryOfGuesses(), theGame.getHistoryOfPegs());
						continue;
					}
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
