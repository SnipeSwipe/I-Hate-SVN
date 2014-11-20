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
	public Formation formation;
	public Defender[] defenders;
	public Midfielder[] midfielders;
	public Attacker[] attackers;
	public Goalkeeper goalkeeper;
	PlayPanel panel;
	Color color;
	boolean isMoving;
	
	
	public void run() 
	{
		while(isMoving) 
		{
			try 
			{
				Thread.sleep(20);
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
		
		int panelHeight = 670; //this is the width of the field. 
		int gkY, defY, midY, attackY;
		int gkBound, defBound, midBound, attackBound; //movement bounds for players
		
		//bounds and starting positions set according to formation
		defY = defBound = convertNumberToCoordinateGap(panelHeight, this.formation.noOfDefenders);
		midY = midBound =  convertNumberToCoordinateGap(panelHeight, this.formation.noOfMidfielders);
		attackY = attackBound = convertNumberToCoordinateGap(panelHeight, this.formation.noOfAttackers);
		gkY = panelHeight/2;;
		gkBound = 150; //bound set to cover just the goal area
		
		
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
		
		this.goalkeeper = new Goalkeeper(this.panel, gkY, gkX, 5, gkBound, this.color);
		
		for(int i=0; i<this.formation.noOfDefenders; i++)
		{
			this.defenders[i] = new Defender(this.panel, defY, defX, 5, defBound, this.color);
			defY+=convertNumberToCoordinateGap(panelHeight, this.formation.noOfDefenders);
		}
		
		for(int i=0; i<this.formation.noOfMidfielders; i++)
		{
			this.midfielders[i] = new Midfielder(this.panel, midY, midX, 5, midBound, this.color);
			midY+=convertNumberToCoordinateGap(panelHeight, this.formation.noOfMidfielders);
		}
		
		for(int i=0; i<this.formation.noOfAttackers; i++)
		{
			this.attackers[i] = new Attacker(this.panel, attackY, attackX, 5, attackBound, this.color);
			attackY+=convertNumberToCoordinateGap(panelHeight, this.formation.noOfAttackers);
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
	
	public 
	
	static int convertNumberToCoordinate(int height, int number)
	{
		int coordinate=0;
		switch(number)
		{
			case 2: coordinate = height/3; break;
			case 3: coordinate = height/4; break;
			case 4: coordinate = height/5; break;
			case 5: coordinate = height/6; break;
		}
		
		return coordinate;
	}
	
	static int convertNumberToCoordinateGap(int height, int number)
	{
		int coordinateGap=0;
		switch(number)
		{
			case 2: coordinateGap = height/3; break;
			case 3: coordinateGap = height/4; break;
			case 4: coordinateGap = height/5; break;
			case 5: coordinateGap = height/6; break;
		}
		
		return coordinateGap;
	}
	
	

}
