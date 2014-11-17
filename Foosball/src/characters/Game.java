package characters;

import javax.swing.JOptionPane;

public class Game {
	static String titleMessage = null;
	static String dialogMessage = null;
	static String whoStartsMessage = null;
	public void GameOver(){
		
	}
	
	public static void doCoinToss(){
		int userChoice = showCoinTossDialog();
		if(userChoice == -1)
		{
			doCoinToss();
		}
		else if(userChoice != -1)
		{
			showResultDialog(userChoice);
		}
	}
	
	public static int showCoinTossDialog(){
		Object[] options = {"Heads", "Tails"};
		int n = JOptionPane.showOptionDialog(null, "Choose Heads or Tails", "Coin Toss", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
		//System.out.println(userChoice);
		return n;
	}
	
	public static void showResultDialog(int userChoice){
		
		int headsOrTails = (int)(Math.random()*2);
		Object[] options = {"Play!"};
		
		if(headsOrTails == userChoice)
		{
			titleMessage = "You win the Toss";
			whoStartsMessage = "You start the game.";
		}
		else
		{
			titleMessage = "You lose the Toss";
			whoStartsMessage = "Computer starts the game.";
		}
		
		dialogMessage = "You chose: "+convert(userChoice)+"\nCoin Toss Result: "+convert(headsOrTails)+"\n"+whoStartsMessage;
		
		@SuppressWarnings("unused")
		int n = JOptionPane.showOptionDialog(null, dialogMessage, titleMessage, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
		//System.out.println(userChoice);
	}
	public String result()
	{
			return titleMessage;
		
		
	}
	public static String convert(int x){
		String result = null;
		
		if(x == 0)
		{
			result = "Heads";
		}
		else if(x == 1)
		{
			result = "Tails";
		}
		return result;
	}

	
}
