import java.io.*;

public class Map2 extends Map{
	
	
	
	
	public Map2(Platformer p)throws FileNotFoundException{
		
		super("maps/map3/map3.png","maps/map3/arrierePlan.png",2700,600,"maps/map3/map3.txt",p,"maps/map3/events.txt");
		zicmu =new Music("zicmu/boomerang.wav");
		zicmuBoss =new Music("zicmu/sonic.wav");
		
	}
	
	public void bouton(Case c){
		System.out.println("yolo");
		if(c.getType()==3){
			p.spawnEnemmi("platformer/cecile.png");
		}
	}
}