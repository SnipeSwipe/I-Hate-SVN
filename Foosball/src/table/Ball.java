package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import characters.Game;

public class Ball extends Thread {
		
	private static Ball instance = null;  //singleton instance field
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	int startx, starty;
	int newx, newy;
	int flagHitComputerGoal=0;
	int xBound, yBound; //width and height of field in pixels (Hard-code or pass as parameters)
	
	private Color color;
	private PlayPanel panel;
	private Scoreboard board;
	JLabel image;
	Game game;
		
	public static Ball getInstance(PlayPanel panel){  //a unique ball is only instantiated once
		
		if(instance == null){
			instance = new Ball(panel);
		}		
		return instance;		
	}

	private Ball(PlayPanel panel) {  //private constructor to prevent instantiation from outside
		
		this.panel = panel;
		isMoving = true;
		size = 20;
		speed = 20;
		dx = dy = 10;
		newx = newy = startx = starty = 0;
		xBound = 1280;
		yBound = 670;
		
		ball = new Ellipse2D.Double(startx, starty, size, size);
		color = new Color(204, 0, 0);
		game = new Game();
		board = new Scoreboard();
		
	}

	public void draw(Graphics2D g2d) {
		if (ball != null) {
			g2d.setColor(color);
			g2d.fill(ball);
		}
	}

	public void run() {
		
		while (isMoving) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// calculate new position and move ball
			moveBall();
			
			//detecting collisions
			for(int i=0; i<this.panel.humanTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.humanTeam.attackers[i].getPlayerRect())) {
					//whoever fixes this code, this should be in the kick() method
					this.dx = Math.abs(this.dx);
				}
			}
			
			for(int i=0; i<this.panel.humanTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.defenders[i].getPlayerRect())) {
					this.dx = Math.abs(this.dx);
				}
			}
			
			for(int i=0; i<this.panel.humanTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.midfielders[i].getPlayerRect())) {
					this.dx = Math.abs(this.dx);
				}
			}
			
			if (this.ball.intersects(this.panel.humanTeam.goalkeeper.getPlayerRect())) {
				this.dx = Math.abs(this.dx);
			}
			 //ai
			for(int i=0; i<this.panel.computerTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.computerTeam.attackers[i].getPlayerRect())) {
					//whoever fixes this code, this should be in the kick() method
					this.dx = (-1) * Math.abs(this.dx);
				}
			}
			
			for(int i=0; i<this.panel.computerTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.defenders[i].getPlayerRect())) {
					this.dx =  (-1) * Math.abs(this.dx);
				}
			}
			
			for(int i=0; i<this.panel.humanTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.midfielders[i].getPlayerRect())) {
					this.dx =  (-1) * Math.abs(this.dx);
				}
			}
			
			if (this.ball.intersects(this.panel.computerTeam.goalkeeper.getPlayerRect())) {
				this.dx =  (-1) * Math.abs(this.dx);
			}
			//end of detecting collisions
			
			checkGoal(); //method to check for goals

		}
	}
	
	public void moveBall(){
				
		int oldx = (int) ball.getX();
		int oldy = (int) ball.getY();
			
		newx = oldx + dx;		
		if ((newx + size > xBound || newx < 0)||((newx + size >=214 || newx<=433)&& (flagHitComputerGoal==1))) {
			flagHitComputerGoal=0;
			dx = -dx;
		} else
			dx = +dx;
		
		newy = oldy + dy;
		if ((newy + size > yBound || newy< 0)||((newx + size >=214 || newx<=433)&& (flagHitComputerGoal==1))){
			dy = -dy;
		} else {
			dy = +dy;
		}
			
		System.out.println(newx+" "+newy);
		ball.setFrame(newx, newy, size, size);
	}
	
	public void setPosition(int x, int y){ //reset the ball at a particular position
		
		ball.setFrame(x , y, size, size);
		
	}
	
	public void checkGoal(){
				
		if (((newy >= 234-20) && (newy <= 453-20))
				&& (newx >= panel.getWidth() - 20)) {
			BufferedImage myPicture;
			board.increaseScoreHuman();
			try {
				myPicture = ImageIO.read(new File("resources/goal.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
				picLabel.setBounds(350, 120, 600, 350);
				picLabel.setOpaque(true);
				sleep(1000);
				panel.remove(picLabel);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}

			ball.setFrame(59, 360, size, size);
		} else if (((newy >= 234-20) && (newy <= 453-20)) && (newx <0)) {
			BufferedImage myPicture;
			board.increaseScoreAI();

			try {
				myPicture = ImageIO.read(new File("resources/goal.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
				picLabel.setBounds(350, 120, 600, 350);
				picLabel.setOpaque(true);
				sleep(1000);
				panel.remove(picLabel);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
			flagHitComputerGoal=1;
			ball.setFrame(1200, 360, size, size);
		}
		
		
	}

	public int getBallY() {
		// TODO Auto-generated method stub
		return newy;
	}

	public int getBallX() {
		// TODO Auto-generated method stub
		return newx;
	}

}
