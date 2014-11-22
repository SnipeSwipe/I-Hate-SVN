package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;
import javax.swing.JLabel;
import characters.Game;

public class Ball implements Runnable { // singleton class

	int lastTeamContact; //0 if nothing, 1 if AI, 2 if Human
	private static Ball instance = null; // singleton instance field
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speedInverse;
	private int dx, dy;
	int startx, starty;
	public static int newx;
	public static int newy;
	public boolean computerScored;
	boolean humanScored;
	int xBound, yBound; // width and height of field in pixels (Hard-code or
						// pass as parameters)

	private Color color;
	private PlayPanel panel;
	// private Scoreboard board;
	Random rand;
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

	public Ball(PlayPanel panel, int tossResult) { // private constructor to
													// prevent instantiation
													// from outside

		this.panel = panel;
		this.lastTeamContact = (tossResult+1); //1 if AI won and 2 if Human won
		isMoving = true;
		size = 20;
		speedInverse = 20;
		dx = dy = 10;
		newx = newy = startx = starty = 0;
		xBound = 1280;
		yBound = 670;
		computerScored = false;
		humanScored = false;

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
				Thread.sleep(speedInverse);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			rand = new Random();

			// calculate new position and move ball
			moveBall();

			// detecting collisions by checking intersection
			// Refer to Playing interface for explanation of kick function

			for (int i = 0; i < this.panel.humanTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.humanTeam.attackers[i]
						.getPlayerRect())) {

					int[] temp = this.panel.humanTeam.attackers[i].kick(dx, dy,
							1);
					dx = temp[0];
					dy = temp[1];

				}
			}

			for (int i = 0; i < this.panel.humanTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.defenders[i]
						.getPlayerRect())) {

					int[] temp;

					boolean shoot = rand.nextBoolean();

					if (shoot) {
						// temp = this.panel.humanTeam.defenders[i].pass();

						temp = this.panel.humanTeam.defenders[i]
								.kick(dx, dy, 1);
					} else
						temp = this.panel.humanTeam.defenders[i].pass();

					dx = temp[0];
					dy = temp[1];

				}
			}

			for (int i = 0; i < this.panel.humanTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.humanTeam.midfielders[i]
						.getPlayerRect())) {

					int[] temp;

					boolean shoot = rand.nextBoolean();

					if (shoot) {
						temp = this.panel.humanTeam.midfielders[i].kick(dx, dy,
								1);
						// temp = this.panel.humanTeam.midfielders[i].pass();

					} else
						temp = this.panel.humanTeam.midfielders[i].pass();

					dx = temp[0];
					dy = temp[1];

				}
			}

			if (this.ball.intersects(this.panel.humanTeam.goalkeeper
					.getPlayerRect())) {

				int[] temp = this.panel.humanTeam.goalkeeper.pass();

				dx = temp[0];
				dy = temp[1];

			}

			for (int i = 0; i < this.panel.computerTeam.formation.noOfAttackers; i++) {
				if (this.ball.intersects(this.panel.computerTeam.attackers[i]
						.getPlayerRect())) {

					int[] temp = this.panel.computerTeam.attackers[i].kick(dx,
							dy, -1);
					dx = temp[0];
					dy = temp[1];
				}
			}

			for (int i = 0; i < this.panel.computerTeam.formation.noOfDefenders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.defenders[i]
						.getPlayerRect())) {

					int[] temp;

					boolean shoot = rand.nextBoolean();

					if (shoot) {
						temp = this.panel.computerTeam.defenders[i].kick(dx,
								dy, -1);

					} else {

						temp = this.panel.computerTeam.defenders[i].pass();
					}

					dx = temp[0];
					dy = temp[1];

				}
			}

			for (int i = 0; i < this.panel.computerTeam.formation.noOfMidfielders; i++) {
				if (this.ball.intersects(this.panel.computerTeam.midfielders[i]
						.getPlayerRect())) {

					int[] temp;

					boolean shoot = rand.nextBoolean();

					if (shoot) {
						temp = this.panel.computerTeam.midfielders[i].kick(dx,
								dy, -1);
						// temp = this.panel.computerTeam.midfielders[i].pass();

					} else
						temp = this.panel.computerTeam.midfielders[i].pass();

					dx = temp[0];
					dy = temp[1];
				}
			}

			if (this.ball.intersects(this.panel.computerTeam.goalkeeper
					.getPlayerRect())) {

				int[] temp = this.panel.computerTeam.goalkeeper.pass();
				dx = temp[0];
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
		if (humanScored) {
			dx = -Math.abs(dx);
			humanScored = false;
		}
		if (computerScored) {
			dx = Math.abs(dx);
			computerScored = false;
		}

		/*
		 * if (Math.abs(dx) > 1) { if (dx > 0) { newx = oldx + dx - 1; } else if
		 * (dx < 0) { newx = oldx + dx + 1; } } else{ newx = oldx + dx; }
		 */
		newx = oldx + dx;

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
			this.panel.game.scoreBoard.increaseScoreHuman();
			try {
				// JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.setLayout(null);
				JLabel picLabel = new JLabel("GOAL!", JLabel.CENTER);
				picLabel.setFont(new Font("Segoe UI", Font.BOLD, 150));
				picLabel.setBounds(400, 250, 500, 130);
				panel.add(picLabel);
				picLabel.setBounds(250, 120, 800, 400);

				Thread.sleep(1000);

				panel.remove(picLabel);
				panel.setLayout(new BorderLayout());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			humanScored = true;

			speedInverse = 20;
			dx = dy = 10;
			ball.setFrame(1221, this.panel.computerTeam.goalkeeper.currenty,
					size, size);

		} else if (((newy >= 250 - 15) && (newy <= 425 - 15)) && (newx < 0)) {
			this.panel.game.scoreBoard.increaseScoreAI();

			try {
				panel.setLayout(null);
				JLabel picLabel = new JLabel("GOAL!", JLabel.CENTER);
				picLabel.setFont(new Font("Segoe UI", Font.BOLD, 150));
				picLabel.setBounds(400, 250, 500, 130);
				panel.add(picLabel);

				// picLabel.setOpaque(true);
				Thread.sleep(1000);
				panel.remove(picLabel);
				panel.setLayout(new BorderLayout());
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			computerScored = true;

			speedInverse = 20;
			dx = dy = 10;

			ball.setFrame(59, this.panel.humanTeam.goalkeeper.currenty, size,
					size);
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
