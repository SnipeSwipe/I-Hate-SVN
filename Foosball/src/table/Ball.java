package table;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Ball extends Thread{
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;             
	private int dx, dy;          
	private Color color;
	private PlayPanel panel; 
	
	public Ball(PlayPanel panel){
		this.panel = panel;
		isMoving = true;   
		size=20;
		speed=50;
		int startx=0;
		int starty=0;
		dx=10;
		dy=10;
		if(dx==0 && dy==0)  
			dy=1;
		ball=new Ellipse2D.Double(startx, starty, size, size);
		Random rand = new Random();
		color=new Color(0,0,0);
	}


	public void draw(Graphics2D g2d){
		if (ball!= null){
			g2d.setColor(color);
			g2d.fill(ball);
		}
	}

	public void run(){
		while(isMoving){
			try {
				Thread.sleep(speed);
			}
			catch (InterruptedException e){
				e.printStackTrace();}

			// calculate new position and move ball
			int oldx=(int) ball.getX();
			int oldy=(int) ball.getY();
			//let's check if the goal has been reached, man. Is there a point in life otherwise?
			//if (oldx)
			int newx=oldx + dx;
			System.out.println(panel.getWidth() + " " + panel.getHeight());
			
			if(newx+size>panel.getWidth()||newx<0){
		
				System.out.println("NOT HERE");
				dx=-dx;
			}
			else dx=+dx;
			int newy=oldy+dy;
			if(newy+size>panel.getHeight()||newy<0) 
				dy=-dy; 
			else dy=+dy; 
			
			ball.setFrame(newx, newy, size, size);
			panel.repaint();
			System.out.println("Yo ma is nice");
			System.out.println(oldx + " " + oldy);

		}
	}
}

