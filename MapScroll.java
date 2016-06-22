import java.io.*;


public class MapScroll extends Map{
	
	
	
	public MapScroll(String imageDefond, String lesEvents, String leTxt)throws FileNotFoundException{
		
		super(imageDefond,imageDefond,900,600,leTxt,lesEvents);
		zicmu =new Music("zicmu/TwinShot.wav");
		zicmuBoss =new Music("zicmu/sonic.wav");
		
		
	}
	
	//Boutons
	public void bouton(Case c){
		
		
	}
}