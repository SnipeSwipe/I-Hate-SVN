package interfaces;

import javax.swing.JPanel;


public interface MenuOptions 
{
	/*Any panel providing such options to the user can implement this interface*/
	
	public void chooseTeamFormation(JPanel panel); 	//Choose from a list of different formations
	public void choosePlayLevel(JPanel panel);		//Choose level of difficulty
}
