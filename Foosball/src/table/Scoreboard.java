package table;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	Game game;

	public Scoreboard(Table mainFrame) {
		this.mainFrame = mainFrame;
		this.setPreferredSize(new Dimension(90, 32));

		human = new JLabel();
		this.humanScore = 0;
		this.aiScore = 0;
	}

	private AlphaComposite makeComposite(float alpha) {
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public void PaintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(0,100,0));

	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(new Color(0, 0, 0));
		labelText = String.valueOf(this.humanScore) + "       "
				+ String.valueOf(this.aiScore);

		//Set 0f for total transparency
		//This is the thing for transparency, it somehow makes players transparent though
		 g2d.setComposite(makeComposite(1f));
		
		human.setText(labelText);
		human.setFont(new Font("Segoe UI", Font.BOLD, 18));
		human.setBounds(490, 220, 300, 100);

		human.setOpaque(false);
		human.setBorder(null);

		this.add(human);
		human.setVisible(true);
		this.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	public void increaseScoreHuman() {
		this.humanScore++;
		
		if (this.humanScore >= 5) {

			this.mainFrame.playPanel.computerTeam.isMoving = false;
			this.mainFrame.playPanel.computerThread.stop();

			Game.endGame(0, this.mainFrame);
		}
	}

	@SuppressWarnings("deprecation")
	public void increaseScoreAI() {
		this.aiScore++;
		
		if (this.aiScore >= 5) {
			this.mainFrame.playPanel.computerTeam.isMoving = false;
			this.mainFrame.playPanel.computerThread.stop();

			Game.endGame(1, this.mainFrame);
		}
	}

}
