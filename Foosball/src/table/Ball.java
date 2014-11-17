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

public class Ball extends Thread{
	private Ellipse2D.Double ball;
	private boolean isMoving;
	private int size, speed;             
	private int dx, dy;          
	private Color color;
	private PlayPanel panel; 
	JLabel image;
	int startx=0;
	int starty=0;
	public Ball(PlayPanel panel){
		this.panel = panel;
		isMoving = true;   
		size=20;
		speed=10;
		
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
			if((newx ==0&& ((newy>234)&&(newy<=453)))|| (newx ==panel.getWidth()&& ((newy>234)&&(newy<=453))))
			{
				BufferedImage myPicture;
				try {
					myPicture = ImageIO.read(new File("resources/goal.png"));
					JLabel picLabel = new JLabel(new ImageIcon(myPicture));
					panel.add(picLabel);
					picLabel.setBounds(350,120,600,350);
					picLabel.setOpaque(true);
		         	sleep(4000);
					panel.remove(picLabel);}
					catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				ball.setFrame(startx, starty, size, size);

			}
			else ball.setFrame(newx, newy, size, size);
			panel.repaint();
			System.out.println(oldx + " " + oldy);

		}
	}
}

