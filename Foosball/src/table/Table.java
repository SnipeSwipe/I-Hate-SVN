package table;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import question1.Goalpost;
import question1.Team;

public class Table extends JFrame{
	Team human;
	Team ai;
	Goalpost humanGoalpost, aiGoalpost;
	
	public void FullScreen() { 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);
	    setVisible(true);
	}
	
	public Table() {
		this.setTitle("Foosball");
		this.setSize(1280,720); // default size is 0,0
		this.setLocation(30,0); // default is 0,0 (top left corner)
		this.add(new ContentPanel());
		this.setVisible(true);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
	  }
	
}


