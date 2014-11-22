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
	
	/*public int[] kicke(int dx, int dy, int dir) {
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
	}*/
	
	public int[] kick(int dx, int dy, int dir){
		
		int[] coords = new int[2];
		int toX;
		int toY = 320;
		
		if(startx < 640)
			toX = 1280;
		else
			toX = 0;
		
		dir = dir/(Math.abs(dir)); //Get only unit vector direction in x axis
		if(dx*dir > 0){  //i.e. if dx and dir have same sign (While defending)
			dx = dir*Math.abs(dx);
			coords[0] = dx;
			coords[1] = dy;
		}
		
		else{		//Else dx and dir have different signs(While attacking)
			int newSpeed = minKickSpeed + (int)(Math.random()*(maxKickSpeed-minKickSpeed));
			
			double xcoor = toX - this.currentx;
			double ycoor = toY - this.currenty;
			
			ycoor = (ycoor*newSpeed)/xcoor;
			xcoor = newSpeed;
			
			coords[0] = dir*(int)xcoor;
			coords[1] = dir*(int)ycoor;
			
			
		}
				
		return coords;
	
	}
	
	public int[] pass(){
		
		int[] coords = new int[2];
		
		int toX;
		int toY;
		int dir = 1;
				
		if(startx < 640){

			int randomIdx = (int)(Math.random()*(panel.humanTeam.formation.noOfAttackers));
			Attacker att = panel.humanTeam.attackers[randomIdx];
			toX = att.currentx;
			toY = att.currenty;
					
		}
		else{
			
			dir = -1;
			int randomIdx = (int)(Math.random()*(panel.computerTeam.formation.noOfAttackers));
			Attacker att = panel.computerTeam.attackers[randomIdx];
			toX = att.currentx;
			toY = att.currenty;
			
		}
		
		double xcoor = toX - this.currentx;
		double ycoor = toY - this.currenty;
		
		int newSpeed = minKickSpeed + (int)(Math.random()*(maxKickSpeed-minKickSpeed));
		
		ycoor = (ycoor*newSpeed)/xcoor;
		xcoor = newSpeed;
		
		coords[0] = dir*(int)xcoor;
		coords[1] = dir*(int)ycoor;
		
		//System.out.println(xcoor + " " + ycoor);
		
		return coords;
		
	}
	

}
