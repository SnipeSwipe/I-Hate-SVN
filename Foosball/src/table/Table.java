package table;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;

import question1.Goalpost;
import question1.Team;
import ball.Ball;

public class Table extends JFrame{
	Team human;
	Team ai;
	Ball ball;
	Goalpost humanGoalpost, aiGoalpost;
	
	public void FullScreen() { 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);
	    setVisible(true);
	}
	
	public Table() {
		setTitle("Foosball");
		setSize(1000,500); // default size is 0,0
		setLocation(200,100); // default is 0,0 (top left corner)
		//FullScreen();
		add(new ContentPanel());
		setVisible(true);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
	  }
	
}


