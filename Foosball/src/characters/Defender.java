package characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

import table.PlayPanel;

public class Defender extends Player {

	public Defender(PlayPanel panel, int starty, int startx, int dy, int bound,
			Color color) {
		super(panel, starty, startx, 6, bound, color);
	}
	
	public int[] kick(int x, int y) {
		int[] coords = new int[2];
		if (Math.abs(x) < 20) {
			x = Math.abs(x) + 1;
		} else {
			x = Math.abs(x);
		}
		if (this.dy > 0) {
			y = y + 1;
		} else {
			y = y - 1;
		}
		coords[0] = x;
		coords[1] = y;
		return coords;
	}

}
