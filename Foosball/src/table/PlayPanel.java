package table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import characters.*;

public class PlayPanel extends JPanel implements ActionListener, KeyListener {

	// BufferedImage img;
	// Table table;

	Ball b;
	BufferedImage img;
	Team humanTeam, computerTeam;
	Thread humanThread, computerThread;
	Timer timer;

	public PlayPanel() {
		setPreferredSize(new Dimension(1280, 670));
		try {
			img = ImageIO.read(new File("resources/field.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Game.doCoinToss();
		humanTeam = new Team(new Formation(3, 3, 4), this, TeamMode.HUMAN);
		computerTeam = new Team(new Formation(3, 4, 3), this, TeamMode.COMPUTER);

		computerThread = new Thread(computerTeam);
		computerThread.start();
		
		b = new Ball(this);
		b.start();

		// humanThread = new Thread(humanTeam);
		// humanThread.start();

		timer = new Timer(5, this);
		addKeyListener(this);
		// setFocusable(true);
		setFocusTraversalKeysEnabled(false);

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

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//b.moveBall();
		repaint();
		computerTeam.move();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_TAB) {
			System.out.println("Tab Pressed");
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			humanTeam.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			humanTeam.moveDown();
		}

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
			humanTeam.moveUp();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			humanTeam.moveDown();
		}

	}
}
