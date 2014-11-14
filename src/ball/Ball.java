package ball;

import table;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Thread {
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	private Color color;

	public Ball() {
		isMoving = true;
		size = 10 + (int) (Math.random() * 60);
		speed = 10 + (int) (Math.random() * 100);
		int startx = (int) (Math.random() * 350);
		int starty = (int) (Math.random() * 335);
		dx = -10 + (int) (Math.random() * 21);
		dy = -10 + (int) (Math.random() * 21);
		if (dx == 0 && dy == 0)
			dy = 1;
		ball = new Ellipse2D.Double(startx, starty, size, size);
		Random rand = new Random();
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
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
			int oldx = (int) ball.getX();
			int oldy = (int) ball.getY();
			int newx = oldx + dx;
			if (newx + size > panel.getWidth() || newx < 0)
				dx = -dx;
			int newy = oldy + dy;
			if (newy + size > panel.getHeight() || newy < 0)
				dy = -dy;
			ball.setFrame(newx, newy, size, size);
			panel.repaint();
		}

	}
}