package table;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	  Image bgimage = null;
	  
	  ContentPanel() {
	    MediaTracker mt = new MediaTracker(this);
	    bgimage = Toolkit.getDefaultToolkit().getImage("resources/field.jpg");
	    mt.addImage(bgimage, 0);
	    try {
	      mt.waitForAll();
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }

	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
		Toolkit kit=Toolkit.getDefaultToolkit();
	    Dimension screensize=kit.getScreenSize();
	    int imwidth = 1440;
	    int imheight = 900;
	    g.drawImage(bgimage, 0, 0, imwidth, imheight, null);
	  }
	} 
