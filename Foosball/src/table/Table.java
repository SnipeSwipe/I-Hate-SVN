package table;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import characters.Goalpost;
import characters.Team;

public class Table extends JFrame{
	private static final long serialVersionUID = 1L;
	Team human;
	Team ai;
	Goalpost humanGoalpost, aiGoalpost;
	
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
		add(new ContentPanel(this));
		setVisible(true);
		
		// Window Listeners
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} //windowClosing
		} );
	  }
	
	public void playSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/sound.wav"));
	        clip.open(ais);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			e.printStackTrace();
		}
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                // A GUI element to prevent the Clip's daemon Thread
	                // from terminating at the end of the main()
	                //JOptionPane.showMessageDialog(null, "Close to exit!");
	            }
	        });  
    }  
	
}


