package mainclasses;

import javax.swing.JFrame;

import characters.Game;

import table.Table;

public class Main {

	@SuppressWarnings("unused")
	private static JFrame f;

	public static void main(String[] args) 
	{
		Game game = new Game();
		game.startGame();
	} //main 
	
	
}
