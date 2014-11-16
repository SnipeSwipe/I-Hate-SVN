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

public class PlayPanel extends JPanel{
	
	//BufferedImage img;
		Ball b ;
		BufferedImage img;
		 

	// private JButton playButton = new JButton("Hellloooo");
	   // private Table mainFrame;
    
	    public PlayPanel(){
	    	setPreferredSize(new Dimension(1280,720));
	    	/*try {
				img = ImageIO.read(new File("resources/field.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}*/
	    	
	    	//System.out.println("helloanmoooollll");
	    	 b=new Ball(this);
			 b.start();
		
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
		}
	    	
}
