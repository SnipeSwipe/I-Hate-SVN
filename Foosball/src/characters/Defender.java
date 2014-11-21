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

}
