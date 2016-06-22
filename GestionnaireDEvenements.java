import java.util.*;

/**
*Gere les evenements sur une Map
*@Author LAY
*/
public class GestionnaireDEvenements{

	private Platformer p;
	private Long temps;
	private HashMap<Long,String> events;
	
	/**
	*pl est le platformer sur lequel les events vont avoir lieu
	*events est une liste d'evenements aparaissant a un temps precis, en Long
	*@Author LAY
	*/
	public GestionnaireDEvenements(Platformer pl,HashMap<Long,String> events){
		this.p = pl;
		temps=new Long(0); 
		this.events=events;
	}
	
	public void spawn(String nom){
		if(nom=="joueur"){
			p.mec=new Bonom(100);
		}
		
		else{
			if(nom=="cec"){
				p.spawnEnemmi("platformer/cecile.png");
			}
			if(nom=="Basilic"){
				p.spawnEnemmi("platformer/Basilic.png");
			}
			if(nom=="Basilicosaure"){
				p.spawnEnemmi("platformer/Basilicosaure.png");
			}
		}
	}
	
	//Creer un hashmap avec le temps à gauche et l'event à droite
	/**
	*Met a jour les events
	*@Author LAY
	*/
	public void update(){
		
		String s = events.get(temps);
		// System.out.println(s);
		// System.out.println(temps);
		
		if(s!=null){
			System.out.println(s);
			if(s.equals("spCec")){
				spawn("cec");
				System.out.println("fait");
			}
			if(s.equals("dj")){
				p.changeZicmu(p.map.zicmuBoss);
			}
			if(s.equals("spBas")){
				spawn("Basilic");
				System.out.println("fait");
			}
			if(s.equals("spBasor")){
				spawn("Basilicosaure");
				System.out.println("fait");
			}
			
			
		}
		temps++;
	}
}