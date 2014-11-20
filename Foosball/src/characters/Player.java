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

public abstract class Player implements PlayingInterface{

	private Rectangle2D.Double player;
	private boolean isMoving;
	private int size, speed;
	private int dx, dy;
	private Color color;
	private PlayPanel panel;
	int starty, startx;
	int min, max;

	KeyEvent e;

	public Player(PlayPanel panel, int starty, int startx, int dy, Color color) {
		this.panel = panel;
		isMoving = true;
		size = 20;
		speed = 50; // This sleeps, so increase to decrease speed
		this.startx = startx;
		this.starty = starty;
		this.dx = 0;
		this.dy = dy;
		this.max = 720;
		this.min = 0;
		this.color = color;

		if (dx == 0 && dy == 0) {
			dy = 1;
		}

		player = new Rectangle2D.Double(startx, starty, size, size);
		//color = new Color(255, 255, 255);
		
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}
	
	public void move()
	{
		if(this.starty<=this.min || this.starty>=this.max)
		{
			this.dy = this.dy*(-1);
		}
		
		this.starty+=this.dy;
	}
	
	public void moveUp()
	{
		this.dy = -5;
		if(this.starty>this.min)
		{
			this.starty+=this.dy;
		}
		else
		{
			this.dy=0;
		}
	}
	
	public void moveDown()
	{
		this.dy = 5;
		if(this.starty<this.max)
		{
			this.starty+=this.dy;
		}
		else
		{
			this.dy=0;
		}
	}
	
	public void draw(Graphics2D g2d) {
		if (player != null) 
		{
			g2d.setColor(color);
			player.setFrame(startx, starty, size, size);
			g2d.fillRect(startx, starty, size, size);
		}
	}

	/*public void run() {
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
	}*/

	@Override
	public void kick() {
		// TODO Auto-generated method stub

	}
}
