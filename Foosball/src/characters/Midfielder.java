package characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

import table.PlayPanel;

public class Midfielder extends Player {

	public Midfielder(PlayPanel panel, int starty, int startx, int dy,
			int bound, Color color) {
		super(panel, starty, startx, 4, bound, color);
		minKickSpeed = 20;
		maxKickSpeed = 25;
	}
	
	public int[] kick(int dx, int dy, int dir) {
		int[] coords = new int[2];
				
		dir = dir/(Math.abs(dir)); //Get only unit vector direction in x axis
		if(dx*dir > 0)  //i.e. if dx and dir have same sign (While defending)
			dx = dir*Math.abs(dx);
		else{		//Else dx and dir have different signs(While attacking)
			int newSpeed = minKickSpeed + (int)(Math.random()*(maxKickSpeed-minKickSpeed));
			dx = dir*(newSpeed);
		}
		
		// if going downwards, reduce ball angle(I dunno how to
		// put it, just change ball angle)
		if (this.dy > 0) {
			dy = dy + 1;
		} else {
			dy = dy - 1;
		}
		coords[0] = dx;
		coords[1] = dy;
		return coords;
	}
	

}
