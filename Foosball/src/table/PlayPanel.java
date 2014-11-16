package table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import question1.*;

public class PlayPanel extends JPanel{
	
	//BufferedImage img;
		Ball b;
		BufferedImage img;
		Midfielder midfielders[];
		Thread midThread[];
		
		Attacker attackfielders[];
		Thread attackThread[];
		
		Defender defendfielders[];
		Thread defendThread[];
		
		Goalkeeper GKfielders[];
		Thread GKThread[];
	// private JButton playButton = new JButton("Hellloooo");
	   // private Table mainFrame;
    
	    public PlayPanel(){
	    	setPreferredSize(new Dimension(1280,670));
	    	try {
				img = ImageIO.read(new File("resources/field.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	
	    	//System.out.println("helloanmoooollll");
	    	 b=new Ball(this);
			 b.start();
			 midfielders=new Midfielder[4];
			 midThread=new Thread[4];
			 
			 attackfielders=new Attacker[4];
			 attackThread=new Thread[4];
			 
			 GKfielders=new Goalkeeper[4];
			 GKThread=new Thread[4];
			 
			 defendfielders=new Defender[4];
			 defendThread=new Thread[4];
				
			 int mid=180;//4
			 int attack=200;//3
			 int defend=240;//2
			 int GK=360;//1 player
			 for(int i=0;i<4;i++)
			 {
				 midfielders[i]=new Midfielder(this,mid,549);

				 mid+=120;
				midThread[i]=new Thread(midfielders[i]); 
				midThread[i].start();
			 }
			 for(int i=0;i<3;i++)
			 {
				 attackfielders[i]=new Attacker(this,mid,913);
				 attack+=160;
				 attackThread[i]=new Thread(attackfielders[i]); 
					attackThread[i].start();
			 }
			 for(int i=0;i<1;i++)
			 {
				 GKfielders[i]=new Goalkeeper(this,mid,59);
				 GK+=360;
				 GKThread[i]=new Thread(GKfielders[i]); 
					GKThread[i].start();

			 }
			 for(int i=0;i<2;i++)
			 {
				 defendfielders[i]=new Defender(this,mid,185);
				 defend+=240;
				 defendThread[i]=new Thread(defendfielders[i]); 
					defendThread[i].start();
			 }


			 /*pp=new Midfielder(this,300,549);
			 p2=new Thread(pp);
			 ppp=new Midfielder(this,420,549);
			 p3=new Thread(ppp);
			 pppp=new Midfielder(this,540,549);
			 p4=new Thread(pppp);*/
			 
			//System.out.println("fat");
			//this.validate();
			 
			setVisible(true);

			
	    }
	    
	    @Override
		
		public void paintComponent (Graphics g){
			super.paintComponent(g);
	        Image image = new ImageIcon("resources/field.jpg").getImage();  

			g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

			Graphics2D g2d = (Graphics2D)g;
			b.draw(g2d);
			for(int i=0;i<4;i++)
			 {
				midfielders[i].draw(g2d);
			 }
			for(int i=0;i<3;i++)
			 {
				attackfielders[i].draw(g2d);
			 }
			for(int i=0;i<2;i++)
			 {
				defendfielders[i].draw(g2d);
			 }
			for(int i=0;i<1;i++)
			 {
				GKfielders[i].draw(g2d);
			 }
			
		}
	    	
}
