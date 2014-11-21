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

public abstract class Player implements PlayingInterface {

	private Rectangle2D.Double player;
	private int size, speed;
	private int dy;
	private Color color;
	private PlayPanel panel;
	int starty, startx;
	int currentx, currenty;
	int min, max;
	int bound;

	KeyEvent e;

	public Player(PlayPanel panel, int starty, int startx, int dy, int bound,
			Color color) {
		this.panel = panel;
		size = 20;
		speed = 50; // This sleeps, so increase to decrease speed
		this.startx = startx;
		this.starty = starty;
		this.currentx = startx;
		this.currenty = starty;
		this.dy = dy;
		this.bound = bound; // defines the upper and lower bound for movement
		this.max = starty + bound;
		this.min = starty - bound;
		this.color = color;

		player = new Rectangle2D.Double(startx, starty, size, size);

		// color = new Color(255, 255, 255);

		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}

	public Rectangle2D.Double getPlayerRect() {
		return this.player;
	}

	public void move() {
		if (this.currenty <= this.min || this.currenty >= this.max) {
			this.dy = this.dy * (-1);
		}

		this.currenty += this.dy;
	}

	public void move(int bally) {
		if (this.currenty <= this.min || this.currenty >= this.max) {
			if (this.currenty < bally) {
				if (this.dy < 0) {
					this.dy = this.dy * (-1);
					this.currenty += this.dy;
				}
			} else if (this.currenty > bally) {
				if (this.dy > 0) {
					this.dy = this.dy * (-1);
					this.currenty += this.dy;
				}
			}
		}
	}

	public void moveUp() {
		this.dy = -10;
		if (this.currenty > this.min) {
			this.currenty += this.dy;
		} else {
			this.dy = 0;
		}
	}

	public void moveDown() {
		this.dy = 10;
		if (this.currenty < this.max) {
			this.currenty += this.dy;
		} else {
			this.dy = 0;
		}
	}

	public void draw(Graphics2D g2d) {
		if (player != null) {
			g2d.setColor(color);
			player.setFrame(currentx, currenty, size, size);
			g2d.fillRect(currentx, currenty, size, size);
		}
	}

	@Override
	public int kick() {
		return 0;
	}

}
