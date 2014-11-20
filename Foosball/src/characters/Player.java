package characters;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.*;

import table.PlayPanel;

public abstract class Player implements PlayingInterface, Runnable, KeyListener{

	private Rectangle2D.Double player;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	private Color color;
	private PlayPanel panel;
	int starty, startx;

	KeyEvent e;

	public Player(PlayPanel panel, int starty, int startx, int dy) {
		this.panel = panel;
		isMoving = true;
		size = 20;
		speed = 50; // This sleeps, so increase to decrease speed
		this.startx = startx;
		this.starty = starty;
		this.dx = 0;
		this.dy = dy;

		if (dx == 0 && dy == 0) {
			dy = 1;
		}

		player = new Rectangle2D.Double(startx, starty, size, size);
		color = new Color(255, 255, 255);
		
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}

	public void draw(Graphics2D g2d) {
		if (player != null) {
			g2d.setColor(color);
			g2d.fill(player);
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
			int oldx = (int) player.getX();
			int oldy = (int) player.getY();
			// Redundant
			// int newx = oldx + dx;
			System.out.println(panel.getWidth() + " " + panel.getHeight());


			int newy = oldy + dy;
			if (newy + size > panel.getHeight() || newy < 0)
				dy = -dy;

			player.setFrame(startx, newy, size, size);
			panel.repaint();

			System.out.println(oldx + " " + oldy);

		}
	}

	@Override
	public void kick() {
		// TODO Auto-generated method stub

	}
	

	@Override
	public void keyTyped(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key typed");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key typed");
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key pressed");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key pressed");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			System.out.println("Up key Released");
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			System.out.println("Down key Released");
		}
	}

}
