package characters;

import java.awt.Graphics2D;

import table.Formation;
import table.PlayPanel;
//import table.PlayPanel.Attacker;
//import table.PlayPanel.Defender;
//import table.PlayPanel.Goalkeeper;
//import table.PlayPanel.Midfielder;

public class Team  
{
	Formation formation;
	Defender[] defenders;
	Midfielder[] midfielders;
	Attacker[] attackers;
	Goalkeeper goalkeeper;
	PlayPanel panel;
	
	
	public void run() 
	{
		
	}
	
	public Team(Formation formation, PlayPanel panel)
	{	
		this.panel = panel;
		this.formation = formation;
		this.defenders = new Defender[this.formation.noOfDefenders];
		this.midfielders = new Midfielder[this.formation.noOfMidfielders];
		this.attackers = new Attacker[this.formation.noOfAttackers];
		
		int defY, midY, attackY, gkY;
		defY = convertNumberToCoordinate(this.formation.noOfDefenders);
		midY = convertNumberToCoordinate(this.formation.noOfMidfielders);
		attackY = convertNumberToCoordinate(this.formation.noOfAttackers);
		gkY = 360;
		
		this.goalkeeper = new Goalkeeper(this.panel, gkY, 59, 5);
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i] = new Defender(this.panel, defY, 185, 5);
			defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i] = new Midfielder(this.panel, midY, 549, 5);
			midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i] = new Attacker(this.panel, attackY, 913, 5);
			attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
		}
		
		
		
	}
	public void draw(Graphics2D g2d)
	{
		this.goalkeeper.draw(g2d);
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i].draw(g2d);
			//defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i].draw(g2d);
			//midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i].draw(g2d);
			//attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
		}
	}
	
	static int convertNumberToCoordinate(int number)
	{
		int coordinate=0;
		switch(number)
		{
			case 2: coordinate = 240; break;
			case 3: coordinate = 200; break;
			case 4: coordinate = 180; break;
			case 5: coordinate = 140; break;
		}
		
		return coordinate;
	}
	
	static int convertNumberToCoordinateGap(int number)
	{
		int coordinateGap=0;
		switch(number)
		{
			case 2: coordinateGap = 240; break;
			case 3: coordinateGap = 160; break;
			case 4: coordinateGap = 120; break;
			case 5: coordinateGap = 110; break;
		}
		
		return coordinateGap;
	}
	
	

}
