package characters;

import java.awt.Color;
import table.PlayPanel;

public class Goalkeeper extends Player {
	

	public Goalkeeper(PlayPanel panel, int starty, int startx, int dy,
			int bound, Color color) {
		super(panel, starty, startx, 5, bound, color);
		minKickSpeed = 15;
		maxKickSpeed = 20;
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
	
	public int[] pass(){
		
		int[] coords = new int[2];
		
		int toX;
		int toY;
		int dir = 1;
		boolean isHuman;
				
		if(startx < 640){

			int randomIdx = (int)(Math.random()*(panel.humanTeam.formation.noOfDefenders));
			Defender defender = panel.humanTeam.defenders[randomIdx];
			isHuman = true;
			toX = defender.currentx;
			toY = defender.currenty;
					
		}
		else{
			dir = -1;
			int randomIdx = (int)(Math.random()*(panel.computerTeam.formation.noOfDefenders));
			Defender defender = panel.computerTeam.defenders[randomIdx];
			isHuman = false;
			toX = defender.currentx;
			toY = defender.currenty;
			
		}
		
		double xcoor = toX - this.currentx;
		double ycoor = toY - this.currenty;
		
		int newSpeed = minKickSpeed + (int)(Math.random()*(maxKickSpeed-minKickSpeed));
		
		ycoor = (ycoor*newSpeed)/xcoor;
		xcoor = newSpeed;
		
		int xerror = getError(isHuman);
		int yerror = getError(isHuman);
					
		coords[0] = dir*(int)xcoor + xerror;
		coords[1] = dir*(int)ycoor + yerror;
		
		//System.out.println(xcoor + " " + ycoor);
		
		return coords;
		
	}
		
}
