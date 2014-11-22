package interfaces;

import table.Table;

public interface GamingInterface 
{
	/*Any "Game" should implement this interface*/
	
	
	public int doCoinToss(); 						  //launches a "Coin Toss", returns 0 if user loses 
	  												  //and 1 if user wins.
	
	//public void endGame(int winner, Table mainFrame); //ends a "Game" based on a winner  
													  //and the "Table" it was being played on.	
}
