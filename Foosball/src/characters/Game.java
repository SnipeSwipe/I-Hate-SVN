package characters;

import interfaces.GamingInterface;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import table.Scoreboard;
import table.Table;

public class Game implements GamingInterface 
{
	Table mainFrame;
	public Scoreboard scoreBoard;
	static String titleMessage = null;
	static String dialogMessage = null;
	static String whoStartsMessage = null;

	public static void endGame(int winner, Table mainFrame) //0: Human, 1:AI
	{	
		//mainFrame.playPanel.b.stop();
		int x = launchGameOverDialog(winner);
		
		if(x == -1)
		{
			endGame(winner, mainFrame);
		}
		else if(x == 0)
		{
			/*System.out.println("Working");
			mainFrame.setVisible(false);
			mainFrame.playPanel.b.stop();
			JFrame f = new Table();
			f.setVisible(true);
			f.display();*/
			System.exit(0);
		}
		else if(x == 1)
		{
			//mainFrame.playPanel.b.stop();
			System.exit(0);
		}
		
	}
	
	public static int launchGameOverDialog(int winner)
	{
		Object[] options = { "Play Again", "Exit" };
		String winnerDeclare = null;
		int icon = 0;
		if(winner == 0)
		{
			icon = JOptionPane.INFORMATION_MESSAGE;
			winnerDeclare = "Congrats, you just earned some respect.";
		}
		else if(winner == 1)
		{
			icon = JOptionPane.ERROR_MESSAGE;
			winnerDeclare = "Sorry, you suck.";
		}
		
		int n = JOptionPane.showOptionDialog(null, winnerDeclare, "Game Over!", 
				JOptionPane.YES_NO_OPTION, icon, null, options, null);
		
		return n;
	}

	public int doCoinToss() {
		int userChoice = showCoinTossDialog();
		if (userChoice == -1) {
			this.doCoinToss();
		} else if (userChoice != -1) {
			showResultDialog(userChoice);
		}
		return result();
	}

	public static int showCoinTossDialog() {
		Object[] options = { "Heads", "Tails" };
		int n = JOptionPane.showOptionDialog(null, "Choose Heads or Tails",
				"Coin Toss", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, options, null);
		// System.out.println(userChoice);
		return n;
	}

	public static void showResultDialog(int userChoice) {

		int headsOrTails = (int) (Math.random() * 2);
		Object[] options = { "Play!" };

		if (headsOrTails == userChoice) {
			titleMessage = "You win the Toss";
			whoStartsMessage = "You start the game.";
		} else {
			titleMessage = "You lose the Toss";
			whoStartsMessage = "Computer starts the game.";
		}

		dialogMessage = "You chose: " + convert(userChoice)
				+ "\nCoin Toss Result: " + convert(headsOrTails) + "\n"
				+ whoStartsMessage;

		@SuppressWarnings("unused")
		int n = JOptionPane.showOptionDialog(null, dialogMessage, titleMessage,
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
				null, options, null);
		// System.out.println(userChoice);
	}

	public static int result() {
		if(titleMessage.equals("You win the Toss"))
			return 1;
		else
			return 0;
	}

	public static String convert(int x) {
		String result = null;

		if (x == 0) {
			result = "Heads";
		} else if (x == 1) {
			result = "Tails";
		}
		return result;
	}

}
