package table;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import characters.Game;

public class Ball extends Thread {

	private static Ball instance = null; // singleton instance field
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	int startx, starty;
	public static int newx, newy;
	boolean computerScored;
	int xBound, yBound; // width and height of field in pixels (Hard-code or
						// pass as parameters)

	private Color color;
	private PlayPanel panel;
	JLabel image;
	Game game;

	public static Ball getInstance(PlayPanel panel, int tossResult) { // a
																		// unique
																		// ball
																		// is
																		// only
																		// instantiated
																		// once

		if (instance == null) {
			instance = new Ball(panel, tossResult);
		}
		return instance;
	}

	private Ball(PlayPanel panel, int tossResult) { // private constructor to
													// prevent instantiation
													// from outside

		this.panel = panel;
		isMoving = true;
		size = 20;
		speed = 20;
		dx = dy = 10;
		newx = newy = startx = starty = 0;
		xBound = 1280;
		yBound = 670;
		computerScored = false;

		if (tossResult == 1) {
			startx = 61;
			starty = 335;
		} else {
			startx = 1150;
			starty = 335;
			computerScored = true;
		}
		ball = new Ellipse2D.Double(startx, starty, size, size);
		color = new Color(204, 0, 0);
		game = new Game();

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

			// detecting collisions
			//See this, refer for each collision
			for (int i = 0; i < this.panel.humanTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.humanTeam.attackers[i].getPlayerRect())) {
					int[] temp = this.panel.humanTeam.attackers[i].kick(dx, dy);
					dx = temp[0];
					dy = temp[1];
				}
			}

			for (int i = 0; i < this.panel.humanTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.defenders[i].getPlayerRect())) {
					int[] temp = this.panel.humanTeam.defenders[i].kick(dx, dy);
					dx = temp[0];
					dy = temp[1];
				}
			}

			for (int i = 0; i < this.panel.humanTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.midfielders[i].getPlayerRect())) {
					int[] temp = this.panel.humanTeam.midfielders[i].kick(dx, dy);
					dx = temp[0];
					dy = temp[1];
				}
			}

			if (this.ball.intersects(this.panel.humanTeam.goalkeeper.getPlayerRect())) {
				int[] temp = new int[2];
				temp = this.panel.humanTeam.goalkeeper.kick(dx, dy);
				dx = temp[0];
				dy = temp[1];

			}
			for (int i = 0; i < this.panel.computerTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.computerTeam.attackers[i].getPlayerRect())) {
					int[] temp = this.panel.computerTeam.attackers[i].kick(dx, dy);
					dx = (-1) * temp[0];
					dy = temp[1];
				}
			}

			for (int i = 0; i < this.panel.computerTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.defenders[i].getPlayerRect())) {
					int[] temp = this.panel.computerTeam.defenders[i].kick(dx, dy);
					dx = (-1) * temp[0];
					dy = temp[1];
				}
			}

			for (int i = 0; i < this.panel.computerTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.midfielders[i].getPlayerRect())) {
					int[] temp = this.panel.computerTeam.midfielders[i].kick(dx, dy);
					dx = (-1) * temp[0];
					dy = temp[1];
				}
			}

			if (this.ball.intersects(this.panel.computerTeam.goalkeeper.getPlayerRect())) {
				int[] temp = this.panel.computerTeam.goalkeeper.kick(dx, dy);
				dx = (-1) * temp[0];
				dy = temp[1];
			}
			// end of detecting collisions

			checkGoal(); // method to check for goals

		}
	}

	public void moveBall() {

		int oldx = (int) ball.getX();
		int oldy = (int) ball.getY();

		if (newx + size > xBound || newx < 0) {
			dx = -dx;
		} else
			dx = +dx;
		if (computerScored) {
			dx = -Math.abs(dx);
			computerScored = false;
		}

		if (Math.abs(dx) > 1) {
			if (dx > 0) {
				newx = oldx + dx - 1;
			} else if (dx < 0) {
				newx = oldx + dx + 1;
			}
		} else {
			newx = oldx + dx;
		}
		// newx = oldx + dx;

		newy = oldy + dy;
		if (newy + size > yBound || newy < 0) {
			dy = -dy;
		} else {
			dy = +dy;
		}

		// System.out.println(newx+" "+newy);
		ball.setFrame(newx, newy, size, size);
	}

	public void setPosition(int x, int y) { // reset the ball at a particular
											// position

		ball.setFrame(x, y, size, size);

	}

	public void checkGoal() {

		if (((newy >= 250 - 15) && (newy <= 425 - 15))
				&& (newx >= panel.getWidth() - 20)) {
			BufferedImage myPicture;
			this.panel.scoreBoard.increaseScoreHuman();
			try {
				myPicture = ImageIO.read(new File("resources/goal.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
				picLabel.setBounds(250, 220, 800, 400);
				panel.repaint();
				sleep(1000);
				panel.remove(picLabel);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
			computerScored = true;

			speed = 20;
			dx = dy = 10;
			ball.setFrame(1150, 360, size, size);

		} else if (((newy >= 250 - 15) && (newy <= 425 - 15)) && (newx < 0)) {
			BufferedImage myPicture;
			this.panel.scoreBoard.increaseScoreAI();

			try {
				myPicture = ImageIO.read(new File("resources/goal.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
				picLabel.setBounds(250, 220, 800, 400);
				panel.repaint();
				//panel.scoreBoard.draw(g2d);
				// picLabel.setOpaque(true);
				sleep(1000);
				panel.remove(picLabel);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}

			speed = 20;
			dx = dy = 10;

			ball.setFrame(59, 360, size, size);
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
