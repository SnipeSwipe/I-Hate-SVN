package testing;

import java.awt.Color;

import javax.swing.JFrame;

import org.testng.annotations.Test;

import characters.Game;
import characters.Goalkeeper;

import table.Ball;
import table.PlayPanel;
import table.Table;
import junit.framework.TestCase;

public class GameTest extends TestCase 
{

	  	Table table = new Table();
	  	PlayPanel panel = new PlayPanel("formation", "level", table);
	  	Game g=new Game();
	  	Ball ball=new Ball(panel, 0);
		int result=g.doCoinToss();

	/*@Test	
	public void testFormationSum() {
		//	Formation f=new Formation(computerTeam.goalkeeper);
		//String str = "TestNG is working fine";
		assertEquals("TestNG is working fine", 11);
	}*/
@Test	
	public void checkGoalsum() throws InterruptedException{
	for(int i=0;i<4;i++)
	{
		
		g.scoreBoard.increaseScoreAI();
	}
		System.out.println("ans is :"+g.scoreBoard.aiScore);	
		assertEquals(g.scoreBoard.aiScore, 4);
		
	}
	
@Test
	public void checkCompBallpositionOnCoinToss(){	
		
		if(result==0){
			assertEquals(ball.newx,1141	);
			System.out.println("ball x computer	:"+ ball.newx);	
		}
		
		
	}
@Test	
public void checkHumanBallpositionOnCoinToss(){	

	if(result==1	){
		assertEquals(ball.newx,61	);
		System.out.println("ball x computer	:"+ ball.newx);	
	}
}
@Test		
public void checkBallPosOnCompGoal()
	{	

	if(ball.computerScored ==true){
		assertEquals(ball.newx,61);
		}
	
	}
	
@Test
public void playerShouldMoveUpIfBottomReached()
	{
		  Goalkeeper gk = new Goalkeeper(panel, 50, 50, 20, 130, new Color(0,0,0));  //max becomes 155
		  gk.currenty = 160; //y is set more than max thus player should move up
		  gk.move();
		  assert(gk.currenty < 160);	
	}		
}

