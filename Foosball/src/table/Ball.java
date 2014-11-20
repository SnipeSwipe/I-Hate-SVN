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
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	private Color color;
	private PlayPanel panel;
	Scoreboard board = new Scoreboard();
	JLabel image;
	int startx = 0;
	int starty = 0;
	Game game = new Game();
	int newy = 0;
	int newx = 0;

	public Ball(PlayPanel panel) {
		this.panel = panel;
		isMoving = true;
		size = 20;
		speed = 20;

		dx = 10;
		dy = 10;
		if (dx == 0 && dy == 0)
			dy = 1;

		ball = new Ellipse2D.Double(startx, starty, size, size);

		Random rand = new Random();
		color = new Color(204, 0, 0);
	}

	public void draw(Graphics2D g2d) {
		if (ball != null) {
			g2d.setColor(color);
			g2d.fill(ball);
		}
	}

	public boolean collision() {
		// return ball.getBounds2D().intersects(player.getBounnds2D);
		return false;
	}

	public void run() {
		while (isMoving) {
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// calculate new position and move ball
			int oldx = (int) ball.getX();
			int oldy = (int) ball.getY();
			newx = oldx + dx;
			// System.out.println(panel.getWidth() + " " + panel.getHeight());

			if (newx + size > panel.getWidth() || newx < 0) {
				dx = -dx;
			} else
				dx = +dx;
			newy = oldy + dy;
			if (newy + size > panel.getHeight() || newy < 0) {
				dy = -dy;
			} else {
				dy = +dy;
			}
			
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
				this.dx = (-1) * Math.abs(this.dx);
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
			
			//System.out.println(newx + "  " + newy);
			if (((newy >= 234) && (newy <= 453))
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
			} else if (((newy >= 234) && (newy <= 453)) && (newx == 0)) {
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

				ball.setFrame(1221, 360, size, size);
			}

			else {
				ball.setFrame(newx, newy, size, size);
			}
			panel.repaint();
		}
	}
}
