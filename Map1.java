import java.io.*;


public class Map1 extends Map{
	
	
	
	public Map1(Platformer p)throws FileNotFoundException{
		
		super("maps/map1/map3.png","maps/map1/arrierePlan.png",1800,600,"maps/map1/map1.txt",p,"maps/map1/events.txt");
		zicmu =new Music("zicmu/TwinShot.wav");
		zicmuBoss =new Music("zicmu/sonic.wav");
		
		
	}
	
	//Boutons
	public void bouton(Case c){
		System.out.println("yolo");
		if(c.getType()==3){
			p.spawnEnemmi("platformer/cecile.png");
		}
		
		if(getCase(52,14).isPlatform==true){
			getCase(52,14).isPlatform=false;
		}
		else{
			getCase(52,14).isPlatform=true;
		}
		// c.faitBobo=true;
		
	}
}