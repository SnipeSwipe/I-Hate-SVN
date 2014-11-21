package table;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Scoreboard extends JPanel {
	
	private static final long serialVersionUID = 1L;
	int humanScore;
	int aiScore;
	//add constructor for GUI?
	
	public Scoreboard() {
		this.humanScore = 0;
		this.aiScore = 0;
		this.setVisible(true);
	}
	
	public void PaintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(Integer.toString(this.humanScore), 100, 100);
		this.setVisible(true);
	}
	
	public void increaseScoreHuman() {
		this.humanScore++;
		if (this.humanScore > 5) {
			//gameOver, print relevant message
		}
		System.out.println("Human Scores!");
	}
	
	public void increaseScoreAI() {
		this.aiScore++;
		if (this.aiScore > 5) {
			//gameOver, print relevant message
		}
		System.out.println("Computer Scores!");
	}
	
	
}
