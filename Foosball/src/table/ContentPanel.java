package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	private JButton playButton = new JButton("Play");
	private JButton optionButton = new JButton("Submit");

	private JButton option1 = new JButton("3 4 3");
	private JButton option2 = new JButton("4 4 2");
	private JButton option3 = new JButton("4 3 3");
	private JButton option4 = new JButton("3 5 2");
	private JButton optionlevel1 = new JButton("Novice");
	private JButton optionlevel2 = new JButton("Intermediate");
	private JButton optionlevel3 = new JButton("Pro");

	private Table mainFrame;
	JPanel panel = new JPanel();
	BufferedImage img;
	
	

	public ContentPanel() {
		add(panel);
		// this.mainFrame = mainFrame;
		panel.setLayout(new BorderLayout(1, 2));
		// playButton.setLocation(100,500);
		playButton.setBounds(1000, 100, 400, 200);
		playButton.addActionListener(new ButtonListener());
		panel.add(playButton, BorderLayout.CENTER);
		/*
		 * startButton.setBounds(BUTTON_LOCATION_X , BUTTON_LOCATION_Y,
		 * BUTTON_SIZE_X, BUTTON_SIZE_Y );
		 */

		try {
			img = ImageIO.read(new File("resources/7.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// paint the background image and scale it to fill the entire space
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}

	public class ButtonListener implements ActionListener {
		JPanel rechange2;

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
				System.out.println("hello");// ContentPanel.this.add(gameGrid,
											// BorderLayout.CENTER);
				panel.removeAll();
				// System.out.println("in2");
				// JPanel buttonPanel = new JPanel(new BorderLayout());
				JPanel buttonPanel1 = new JPanel(new BorderLayout());
				JLabel form = new JLabel(
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
				panel.add(optionButton, BorderLayout.SOUTH);

				// rechange2 = new JPanel(null);
				// rechange2.setBackground(Color.white);
				// rechange2.setSize(1440, 920);
				// rechange2.setBounds(50, 50, 240, 225);
				// System.out.println("in3");
				// panel.add(rechange2);
				// panel.add(optionButton, BorderLayout.CENTER);
				// optionButton.addActionListener(new ButtonListener());
				validate();
				repaint();
				// System.out.println("in4");
				setVisible(true);
			}
			if (buttonText.equals("Submit")) {
				System.out.println("hello111");
				// ContentPanel.this.add(gameGrid, BorderLayout.CENTER);
				panel.removeAll();
				// System.out.println("in2");
				rechange2 = new JPanel(null);
				try {
					img = ImageIO.read(new File("resources/field.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("test");
				// rechange2.setBackground(Color.white);
				// rechange2.setSize(1440, 920);
				// rechange2.setBounds(50, 50, 240, 225);
				// System.out.println("in3");
				// panel.add(rechange2);
				// panel.add(new Table());//new frame());
				// new PlayPanel();
				validate();
				repaint();
				// Create an instance of a ball
				Ball b = new Ball(rechange2);
				b.start();

				

				// System.out.println("in4");
				setVisible(true);
			}

			/*
			 * mainFrame.remove(mainFrame.panel); mainFrame.validate();
			 */}
	}
}