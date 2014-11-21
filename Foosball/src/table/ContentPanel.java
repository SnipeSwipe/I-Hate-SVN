package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	
	private JButton playButton = new JButton("Play");

	String[] choices = {"3-3-4", "3-4-3", "3-5-2", "3-6-1", "4-2-4", "4-3-3", "4-4-2", "4-5-1", "5-2-3", "5-3-2", "5-4-1", "6-2-2", "6-3-1"};
	String[] levelChoices = {"Noob", "Semi-Pro", "God"};	
	String formationChosen, levelChosen;
	final JComboBox<String> formationMenu = new JComboBox<String>(choices);
	final JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);

	// private Table mainFrame;
	JPanel panel = new JPanel();
	BufferedImage img;

	public ContentPanel(Table mainFrame) {
		// this.mainFrame = mainFrame;
		add(panel);
		// this.mainFrame = mainFrame;
		panel.setLayout(new BorderLayout(1, 2));
		// playButton.setLocation(100,500);
		playButton.setBounds(1000, 100, 400, 200);
		playButton.addActionListener(new ButtonListener());
		panel.add(playButton, BorderLayout.CENTER);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public class ButtonListener implements ActionListener {
		
		//String formationChosen, levelChosen;
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton clickedButton = (JButton) event.getSource();
			String buttonText = clickedButton.getText();
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
								
				//String[] choices = {"3-3-4", "3-4-3", "3-5-2", "3-6-1", "4-2-4", "4-3-3", "4-4-2", "4-5-1", "5-2-3", "5-3-2", "5-4-1", "6-2-2", "6-3-1"};
				//String[] levelChoices = {"Noob", "Semi-Pro", "God"};
				
				JLabel chooseFormation = new JLabel("Choose Team Formation", JLabel.CENTER);
				chooseFormation.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				chooseFormation.setBounds(490, 30, 300, 100);
				chooseFormation.setForeground(new Color(39, 64, 139));
				panel.add(chooseFormation);
				chooseFormation.setVisible(true);
				
				JLabel defMidAttack = new JLabel("Defenders-Midfielders-Attackers");
				defMidAttack.setFont(new Font("Segoe UI", Font.PLAIN, 15));
				defMidAttack.setBounds(531, 70, 250, 100);
				defMidAttack.setForeground(new Color(39, 64, 139));
				panel.add(defMidAttack);
				defMidAttack.setVisible(true);
				
				//final JComboBox<String> formationMenu = new JComboBox<String>(choices);
				formationMenu.setBounds(590, 150, 100, 20);
				panel.add(formationMenu);
				formationMenu.setVisible(true);
				
				JLabel chooseLevel= new JLabel("Choose Game Level", JLabel.CENTER);
				chooseLevel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				chooseLevel.setBounds(490, 220, 300, 100);
				chooseLevel.setForeground(new Color(39, 64, 139));
				panel.add(chooseLevel);
				chooseLevel.setVisible(true);
				
				//final JComboBox<String> levelMenu = new JComboBox<String>(levelChoices);
				levelMenu.setBounds(590, 300, 100, 20);
				panel.add(levelMenu);
				levelMenu.setVisible(true);
				
				JButton finalPlayButton = new JButton("Play!");
				finalPlayButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
				finalPlayButton.setForeground(new Color(39, 64, 139));
				finalPlayButton.setBounds(595, 400, 80, 30);
				panel.add(finalPlayButton);
				finalPlayButton.addActionListener(new ButtonListener());
				finalPlayButton.setVisible(true);
				
				/*formationChosen = (String)formationMenu.getSelectedItem();
				levelChosen = (String)levelMenu.getSelectedItem();
				
				System.out.println(formationChosen);*/
				
				//panel.setOpaque(true);
				//add(panel);
				//this.mainFrame.setLocationByPlatform(true);
				
				// System.out.println("in2");
				// JPanel buttonPanel = new JPanel(new BorderLayout());
				//JPanel buttonPanel1 = new JPanel(new BorderLayout());
				/*JLabel form = new JLabel(
						"             Choose A Formation for Players         ");
				panel.add(form, BorderLayout.NORTH);
				panel.setLayout(new FlowLayout());
				panel.add(option1);
				panel.add(option2);
				panel.add(option3);
				panel.add(option4);
				// panel.add(buttonPanel);
				optionButton.addActionListener(new ButtonListener());
				JLabel level = new JLabel(
						"                        Choose A Level          ");
				panel.add(level, BorderLayout.NORTH);
				panel.add(optionlevel1, BorderLayout.WEST);
				panel.add(optionlevel2, BorderLayout.EAST);
				panel.add(optionlevel3, BorderLayout.EAST);
				panel.add(buttonPanel1);
				panel.add(optionButton, BorderLayout.SOUTH);*/

				// rechange2.setBackground(Color.white);
				// rechange2.setSize(1440, 920);
				// rechange2.setBounds(50, 50, 240, 225);

				// panel.add(rechange2);
				// panel.add(optionButton, BorderLayout.CENTER);
				// optionButton.addActionListener(new ButtonListener());
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
				panel.add(new PlayPanel(formationChosen, levelChosen));
				validate();
				repaint();

			}

		}
	}
}