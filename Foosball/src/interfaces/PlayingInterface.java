package interfaces;

public interface PlayingInterface {
	
	/* Method Kick for Players 
	// Parameters - takes in x-axis speed, y-axis speed
	// and direction where the player wants to kick the ball
	// 
	// Return values - Arr[0], Arr[1] are new x,y axis speeds 
	 * While defending- Simply mirrors the velocity in opposite direction
	 * While attacking- Gives it with speed in constant range*/
	public int[] kick(int dx, int dy, int dir);  
}
