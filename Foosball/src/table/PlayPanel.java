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
	Table mainFrame;
	public Ball b;
	BufferedImage img;
	public Team humanTeam;
	public Team computerTeam;
	Thread humanThread, computerThread, ballThread;
	Timer timer;
	public int diffLevel;
	//Scoreboard scoreBoard;
	Game game;

	public PlayPanel(String formationChosen, String levelChosen, Table mainFrame) {
		
		this.mainFrame = mainFrame;
		this.game = new Game();
		String[] compChoices = {"3-3-4", "3-4-3", "3-5-2", "3-6-1", "4-2-4", "4-3-3", "4-4-2", "4-5-1", "5-2-3", "5-3-2", "5-4-1", "6-2-2", "6-3-1"};		

		if(levelChosen.equals("Novice")){
			this.diffLevel = 1;
		}
		else if(levelChosen.equals("Novice")){
			this.diffLevel = 2;
		}
		else if(levelChosen.equals("Novice")){
			this.diffLevel = 3;
		}
		
		setPreferredSize(new Dimension(1280, 670));
		try {
			img = ImageIO.read(new File("resources/field.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int humanForm[] = new int[3];
		int compForm[] = new int[3];
		
		int randomIdx = (int)(Math.random()*13); //random index b/w 0-12
		String randomForm = compChoices[randomIdx];  //choose random formation
		
		//split formation string into int array
		int i = 0;	
		for(String retVal: formationChosen.split("-", 3)){			
			humanForm[i] = Integer.parseInt(retVal);
			i++;			
		}
		i = 0;		
		for(String retVal: randomForm.split("-", 3)){			
			compForm[i] = Integer.parseInt(retVal);
			i++;			
		}
		//splitting ends
		
		int result = this.game.doCoinToss();
		
		
		humanTeam = new Team(new Formation(humanForm[0], humanForm[1], humanForm[2]), this, TeamMode.HUMAN);
		computerTeam = new Team(new Formation(compForm[0], compForm[1], compForm[2]), this, TeamMode.COMPUTER);

		b = Ball.getInstance(this, result);
		ballThread = new Thread(b);
		ballThread.start();
		
		computerThread = new Thread(computerTeam);
		computerThread.start();
		computerTeam.setBally(b.newy);
		this.game.scoreBoard = new Scoreboard(mainFrame);
		this.add(this.game.scoreBoard);
		this.game.scoreBoard.setBounds(250, 120, 800, 400);
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
		this.game.scoreBoard.draw(g2d);
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
