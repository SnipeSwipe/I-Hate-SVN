package table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BallA extends Thread{
	public JPanel box;
	public static final int XSIZE = 10;
	public static final int YSIZE = 10;
	public int x = 10;
	public int y = 10;
	public int dx = 5;
	public int dy = 5;
	DrawPanel drawPanel;

	class DrawPanel extends JPanel {
		
		public void paintComponent(Graphics g){
			g.setColor(Color.WHITE);
			g.fillOval(x,y,40,40);
		}
	}
	
	public BallA(JPanel b) {
		box = b;
	}

	public void draw() {
		
		drawPanel = new DrawPanel();
		//g.dispose();
	}

	public void move() {
		
		System.out.println("YO MAMMA");
		if (!box.isVisible())
			return;
		Graphics g = box.getGraphics();
		//g.setXORMode(box.getBackground());
		//g.fillOval(x, y, XSIZE, YSIZE);
		x += dx;
		y += dy;
		Dimension d = box.getSize();
		if (x < 0) {
			x = 0;
			dx = -dx;
		}
		if (x + XSIZE >= d.width) {
			x = d.width - XSIZE;
			dx = -dx;
		}
		if (y < 0) {
			y = 0;
			dy = -dy;
		}
		if (y + YSIZE >= d.height) {
			y = d.height - YSIZE;
			dy = -dy;
		}
		System.out.println(x);
		System.out.println(y);

		//g.fillOval(x, y, XSIZE, YSIZE);
		drawPanel.repaint();
		//g.dispose();
	}

	public void run() {
		try {
			draw();

			while (true) {
				move();
				sleep(100);
			}
		} catch (InterruptedException e) {
		}
	}

}