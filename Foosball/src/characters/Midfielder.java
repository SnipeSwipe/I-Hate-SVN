package characters;

import java.awt.Color;
import table.PlayPanel;

public class Midfielder extends Player {

	public Midfielder(PlayPanel panel, int starty, int startx, int dy,
			int bound, Color color) {
		super(panel, starty, startx, dy, bound, color);
		minKickSpeed = 20;
		maxKickSpeed = 25;
	}
		
	public int[] kick(int dx, int dy, int dir){
		
		int[] coords = new int[2];
		int toX;
		int toY = -5 + 235 + (int)(Math.random()*(175+10)); //Error is -5 on either side
		boolean isHuman;
		
		if(startx < 640){
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
	
	public int[] pass(){
		
		int[] coords = new int[2];
		
		int toX;
		int toY;
		int dir = 1;
		boolean isHuman;
				
		if(startx < 640){

			int randomIdx = (int)(Math.random()*(panel.humanTeam.formation.noOfAttackers));
			Attacker att = panel.humanTeam.attackers[randomIdx];
			isHuman = true;
			toX = att.currentx;
			toY = att.currenty;
					
		}
		else{
			
			dir = -1;
			int randomIdx = (int)(Math.random()*(panel.computerTeam.formation.noOfAttackers));
			Attacker att = panel.computerTeam.attackers[randomIdx];
			isHuman = false;
			toX = att.currentx;
			toY = att.currenty;
			
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
		
		return coords;
		
	}
	
}
