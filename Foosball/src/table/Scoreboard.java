package table;

public class Scoreboard {
	
	int humanScore;
	int aiScore;
	//add constructor for GUI?
	
	public Scoreboard() {
		this.humanScore = 0;
		this.aiScore = 0;
	}
	
	public void increaseScoreHuman() {
		this.humanScore++;
		if (this.humanScore > 5) {
			//gameOver, print relevant message
		}
	}
	
	public void increaseScoreAI() {
		this.aiScore++;
		if (this.aiScore > 5) {
			//gameOver, print relevant message
		}
	}
	
	
}
