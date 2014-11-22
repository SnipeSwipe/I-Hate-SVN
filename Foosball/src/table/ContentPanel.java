package table;

import interfaces.MenuOptions;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.*;

@SuppressWarnings("serial")
public class ContentPanel extends JPanel implements MenuOptions {
	
	private JButton playButton = new JButton("Play");
	Table mainFrame;
	String[] choices = {"3-3-4", "3-4-3", "3-5-2", "3-6-1", "4-2-4", "4-3-3", 
			"4-4-2", "4-5-1", "5-2-3", "5-3-2", "5-4-1", "6-2-2", "6-3-1"};
	String[] levelChoices = {"Novice", "Intermediate", "Pro"};	
	String formationChosen, levelChosen;
	final JComboBox<String> formationMenu = new JComboBox<String>(choices);
	final JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);

	JPanel panel = new JPanel();
	BufferedImage img;
	
	CustomButton musicButton;
	ImageIcon musicButtonImage;

	public ContentPanel(Table mainFrame) 
	{
		this.mainFrame = mainFrame;
		this.setBackground(new Color(255, 255, 255));
		panel.setPreferredSize(new Dimension(1280, 675));
		panel.setBounds(0, 0, 1280, 720);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		
		try 
		{
			BufferedImage foosballFront;
			foosballFront = ImageIO.read(new File("resources/foosballFront.png"));
			
			
			JLabel foosball = new JLabel(new ImageIcon(foosballFront));
			foosball.setBounds(450, 100, 400, 179);
			foosball.setVisible(true);
			panel.add(foosball);
		} 
		
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		
		playButton.setBounds(600, 350, 80, 30);
		playButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		playButton.setVisible(true);
		playButton.addActionListener(new ButtonListener());
		panel.add(playButton);
		panel.setVisible(true);
		add(panel);
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void chooseTeamFormation(JPanel panel) //function to print formation menu
	{
		JLabel chooseFormation = new JLabel("Choose Team Formation", JLabel.CENTER);
		chooseFormation.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		chooseFormation.setBounds(490, 30, 300, 100);
		chooseFormation.setForeground(new Color(112, 48, 160));
		panel.add(chooseFormation);
		chooseFormation.setVisible(true);
		
		JLabel defMidAttack = new JLabel("Defenders-Midfielders-Attackers");
		defMidAttack.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		defMidAttack.setBounds(531, 70, 250, 100);
		defMidAttack.setForeground(new Color(112, 48, 160));
		panel.add(defMidAttack);
		defMidAttack.setVisible(true);
		
		try {
			this.musicButtonImage = new ImageIcon("resources/buttons/music.png");
			this.musicButton = new CustomButton(this.musicButtonImage, "PLAY", 200, 0, 200, 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		panel.add(musicButton);
		musicButton.addActionListener(new ButtonListener());
		formationMenu.setBounds(590, 150, 100, 20);
		panel.add(formationMenu);
		formationMenu.setVisible(true);
	}
	
	public void choosePlayLevel(JPanel panel) //function to print level menu
	{
		JLabel chooseLevel= new JLabel("Choose Game Level", JLabel.CENTER);
		chooseLevel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		chooseLevel.setBounds(490, 220, 300, 100);
		chooseLevel.setForeground(new Color(112, 48, 160));
		panel.add(chooseLevel);
		chooseLevel.setVisible(true);
		
		levelMenu.setBounds(590, 300, 100, 20);
		panel.add(levelMenu);
		levelMenu.setVisible(true);
		
		JButton finalPlayButton = new JButton("Play!");
		finalPlayButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		finalPlayButton.setForeground(new Color(112, 48, 160));
		finalPlayButton.setBounds(595, 400, 80, 30);
		panel.add(finalPlayButton);
		finalPlayButton.addActionListener(new ButtonListener());
		finalPlayButton.setVisible(true);
	}
	
	public static void playSound() {
		try {
	        Clip clip = AudioSystem.getClip();
	        // getAudioInputStream() also accepts a File or InputStream
	        AudioInputStream ais = AudioSystem.getAudioInputStream(new File("resources/sound.wav"));
	        clip.open(ais);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception e) {
			System.out.println("");
		}
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                // A GUI element to prevent the Clip's daemon Thread
	                // from terminating at the end of the main()
	                //JOptionPane.showMessageDialog(null, "Close to exit!");
	            }
	        });  
    }  
	
	public class ButtonListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton clickedButton = (JButton) event.getSource();
			String buttonText = clickedButton.getText();
			if (buttonText.equals("")) {
				ContentPanel.playSound();
			}
			if (buttonText.equals("Play")) {
				try {
					img = ImageIO.read(new File("resources/7.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				//setBackground(Color.BLACK);
				//setOpaque(true);

				panel.removeAll();
				
				int panelHeight = 675;
				int panelWidth = 1280;
				panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
				panel.setLayout(null);
				
				mainFrame.contentPanel.chooseTeamFormation(panel);
				mainFrame.contentPanel.choosePlayLevel(panel);
				
				validate();
				repaint();

				setVisible(true);
			}
			if (buttonText.equals("Play!")) {
				
				formationChosen = (String)formationMenu.getSelectedItem();
				levelChosen = (String)levelMenu.getSelectedItem();
				
				panel.removeAll();
				panel.setLayout(new BorderLayout());
				//panel.setVisible(false);
				setOpaque(true);
				setVisible(true);
				mainFrame.playPanel = new PlayPanel(formationChosen, levelChosen, mainFrame);
				panel.add(mainFrame.playPanel);
				validate();
				repaint();

			}
			

		}
	}
}