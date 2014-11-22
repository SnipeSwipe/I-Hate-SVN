package characters;

import java.awt.Color;

import table.PlayPanel;

public class Attacker extends Player {

	public Attacker(PlayPanel panel, int starty, int startx, int dy, int bound,
			Color color) {
		super(panel, starty, startx, 6, bound, color);
		minKickSpeed = 20;
		maxKickSpeed = 30;
	}
	
	public int[] kick(int dx, int dy, int dir){
		
		int[] coords = new int[2];
		int toX;
		int toY = 320;
		boolean isHuman;
		
		if(startx > 640){
			toX = 1280;
			isHuman = true;			
		}
		else{
			toX = 0;
			isHuman = false;
		}
		
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
			
			int xerror = getError(isHuman);
			int yerror = getError(isHuman);
						
			coords[0] = dir*(int)xcoor + xerror;
			coords[1] = dir*(int)ycoor + yerror;
			
			
		}
				
		return coords;
			
}
}
