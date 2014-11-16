package question1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

import table.PlayPanel;

public class Attacker extends Player implements PlayingInterface,Runnable {

	private Rectangle2D.Double player;
	private boolean isMoving;
	private int size, speed;             
	private int dx, dy;          
	private Color color;
	private PlayPanel panel; 
	int starty ,startx;
	public Attacker(PlayPanel panel,int starty ,int startx){
		this.panel=panel;
		isMoving = true;   
		size=20;
		speed=50;
		this.startx=startx;
		this.starty=starty;
		dx=10;
		dy=10;
		if(dx==0 && dy==0)  
			dy=1;
		player=new Rectangle2D.Double(startx, starty, size, size);
		Random rand = new Random();
		color=new Color(255,255,255);
	}


	public void draw(Graphics2D g2d){
		if (player!= null){
			g2d.setColor(color);
			g2d.fill(player);
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
			int oldx=(int) player.getX();
			int oldy=(int) player.getY();
			int newx=oldx + dx;
			System.out.println(panel.getWidth() + " " + panel.getHeight());
			
			/*if(newx+size>panel.getWidth()||newx<0){
		
				System.out.println("NOT HERE");
				dx=-dx;
			}
			else dx=+dx;*/
			int newy=oldy+dy;
			if(newy+size>panel.getHeight()||newy<0) 
				dy=-dy; 
			
			player.setFrame(startx, newy, size, size);
			panel.repaint();
			System.out.println("Yo ma is nice");
			System.out.println(oldx + " " + oldy);

		}
	}


	@Override
	public void kick() {
		// TODO Auto-generated method stub
		
	}

}
