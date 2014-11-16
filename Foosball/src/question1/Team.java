package question1;

public class Team implements Runnable {
	
	Attacker[] attackers;
	Defender[] defenders;
	Midfielder[] midfielders;
	
	public void run() {
		
	}
	
	public Team(int numberOfDefenders, int numberOfMidfielders, int numberOfAttackers) {
		this.defenders = new Defender[numberOfDefenders];
		this.midfielders = new Midfielder[numberOfMidfielders];
		this.attackers = new Attacker[numberOfAttackers];
	}

}
