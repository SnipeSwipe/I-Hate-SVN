package table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import characters.Game;

public class Scoreboard extends JPanel {
	
	Table mainFrame;
	private static final long serialVersionUID = 1L;
	int humanScore;
	int aiScore;
	JLabel human;
	String labelText;
	//add constructor for GUI?
	
	public Scoreboard(Table mainFrame) 
	{	
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(1280, 32));
		//this.setBackground(new Color(0, 0, 0, 0));
		
        human = new JLabel();
		this.humanScore = 0;
		this.aiScore = 0;
	}
	
	public void PaintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawString(Integer.toString(this.humanScore), 100, 100);
		human.setText(labelText);
        human.setFont(new Font("Segoe UI", Font.BOLD, 18));
        human.setBounds(490, 220, 300, 100);
        //human.setForeground(new Color(39, 64, 139));
        this.add(human);
        human.setVisible(true);
		this.setVisible(true);
	}
	
	public void draw(Graphics g2d)
	{
		g2d.setColor(new Color(0,0,0));
		//labelText = "Your Score: " + String.valueOf(this.humanScore) + " Computer Score: " + String.valueOf(this.aiScore);
		labelText = String.valueOf(this.humanScore) + "       " + String.valueOf(this.aiScore);
		human.setText(labelText);
        human.setFont(new Font("Segoe UI", Font.BOLD, 18));
        human.setBounds(490, 220, 300, 100);
        //human.setForeground(new Color(39, 64, 139));
        human.setOpaque(false);
		human.setBorder(null);
        this.add(human);
        human.setVisible(true);
		this.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void increaseScoreHuman() {
		this.humanScore++;
		if (this.humanScore >= 5) 
		{
			//this.mainFrame.playPanel.b.sleep();
			this.mainFrame.playPanel.computerTeam.isMoving = false;
			this.mainFrame.playPanel.computerThread.stop();
			
			Game.gameOver(0, this.mainFrame);
			
		}
		System.out.println("Human Scores!");
		System.out.println(this.humanScore);
	}
	
	public void increaseScoreAI() {
		this.aiScore++;
		if (this.aiScore >= 5) 
		{
			//this.mainFrame.playPanel.b.stop();
			this.mainFrame.playPanel.computerTeam.isMoving = false;
			this.mainFrame.playPanel.computerThread.stop();
			
			Game.gameOver(1, this.mainFrame);
		}
		System.out.println("Computer Scores!");
		System.out.println(this.aiScore);
	}
	
	
}
