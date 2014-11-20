package characters;

import java.awt.Color;
import java.awt.Graphics2D;

import table.Formation;
import table.PlayPanel;
//import table.PlayPanel.Attacker;
//import table.PlayPanel.Defender;
//import table.PlayPanel.Goalkeeper;
//import table.PlayPanel.Midfielder;
	
public class Team implements Runnable
{
	TeamMode teamMode;
	Formation formation;
	Defender[] defenders;
	Midfielder[] midfielders;
	Attacker[] attackers;
	Goalkeeper goalkeeper;
	PlayPanel panel;
	Color color;
	boolean isMoving;
	
	
	public void run() 
	{
		while(isMoving) 
		{
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			this.move();
			this.panel.repaint();
		}
	}
	
	public Team(Formation formation, PlayPanel panel, TeamMode teamMode)
	{	
		this.panel = panel;
		this.formation = formation;
		this.defenders = new Defender[this.formation.noOfDefenders];
		this.midfielders = new Midfielder[this.formation.noOfMidfielders];
		this.attackers = new Attacker[this.formation.noOfAttackers];
		
		int gkY, defY, midY, attackY;
		defY = convertNumberToCoordinate(this.formation.noOfDefenders);
		midY = convertNumberToCoordinate(this.formation.noOfMidfielders);
		attackY = convertNumberToCoordinate(this.formation.noOfAttackers);
		gkY = 360;
		
		int gkX, defX, midX, attackX;
		gkX=defX=midX=attackX=0;
		
		if(teamMode == TeamMode.HUMAN)
		{
			gkX = 59; 
			defX = 185;
			midX = 549;
			attackX = 913;
			this.color = new Color(255, 255, 255);
		}
		else if(teamMode == TeamMode.COMPUTER)
		{
			gkX = 1221; 
			defX = 1095;
			midX = 731;
			attackX = 367;
			this.color = new Color(0, 0, 0);
			this.isMoving = true;
		}
		
		this.goalkeeper = new Goalkeeper(this.panel, gkY, gkX, 5, this.color);
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i] = new Defender(this.panel, defY, defX, 5, this.color);
			defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i] = new Midfielder(this.panel, midY, midX, 5, this.color);
			midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i] = new Attacker(this.panel, attackY, attackX, 5, this.color);
			attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
		}
		
	}
	
	public void move()
	{
		this.goalkeeper.move();
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i].move();
			//defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i].move();
			//midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i].move();
			//attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
		}
	}
	
	public void moveUp()
	{
		this.goalkeeper.moveUp();
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i].moveUp();
			//defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i].moveUp();
			//midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i].moveUp();
			//attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
		}
	}
	
	public void moveDown()
	{
		this.goalkeeper.moveDown();
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i].moveDown();
			//defY+=convertNumberToCoordinateGap(this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i].moveDown();
			//midY+=convertNumberToCoordinateGap(this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i].moveDown();
			//attackY+=convertNumberToCoordinateGap(this.formation.noOfAttackers);
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
