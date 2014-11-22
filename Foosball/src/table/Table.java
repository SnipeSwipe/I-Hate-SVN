package table;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import characters.Team;

public class Table extends JFrame{
	private static final long serialVersionUID = 1L;
	Team human;
	Team ai;
	ContentPanel contentPanel;
	public PlayPanel playPanel;
	
	public void FullScreen() { 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screenSize.width, screenSize.height);
	    setVisible(true);
	}
	
	public Table() {
		setTitle("Foosball");
		setSize(1280,720); // default size is 0,0
		setLocation(30,0); // default is 0,0 (top left corner)
		//FullScreen();
		this.contentPanel = new ContentPanel(this);
		add(contentPanel);
		setVisible(true);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
	  }
	
}


