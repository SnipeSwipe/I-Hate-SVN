package table;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import characters.*;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {

	Random ran;
	int ranNum;

	Ball b;
	BufferedImage img;
	Team humanTeam, computerTeam;
	Thread humanThread, computerThread;
	Timer timer;
	Scoreboard scoreBoard;

	public PlayPanel(String formationChosen, String levelChosen) {
		setPreferredSize(new Dimension(1280, 670));
		try {
			img = ImageIO.read(new File("resources/field.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		scoreBoard = new Scoreboard();
		int result = Game.doCoinToss();
		humanTeam = new Team(new Formation(3, 3, 4), this, TeamMode.HUMAN);
		computerTeam = new Team(new Formation(3, 4, 3), this, TeamMode.COMPUTER);

		b = Ball.getInstance(this, result);
		b.start();
		
		computerThread = new Thread(computerTeam);
		computerThread.start();
		computerTeam.setBally(b.newy);

		timer = new Timer(100, this);
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		setFocusTraversalKeysEnabled(false);
		putTAB();

		setVisible(true);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon("resources/field.jpg").getImage();

		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);

		Graphics2D g2d = (Graphics2D) g;
		b.draw(g2d);
		humanTeam.draw(g2d);
		computerTeam.draw(g2d);
		humanTeam.rodDraw(g2d);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public void checkScoring() {

		// Goal scoring logic
		// End of goal logic
	}

	public void putTAB() {
		try {
			Robot robot = new Robot();

			// Simulate a key press
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			System.out.println("Tab Pressed");
		}

		/*if (e.getKeyCode() == KeyEvent.VK_UP) {
			humanTeam.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			humanTeam.moveDown();
		}*/

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			humanTeam.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			humanTeam.moveDown();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			//humanTeam.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			//humanTeam.moveDown();
		}

	}
}
