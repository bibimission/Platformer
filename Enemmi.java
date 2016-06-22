public class Enemmi extends Bougeur{

	public boolean bd=false;
	public boolean bg=true;
	public boolean baisse=false;
	public double coefficientBobo;
	
	
	public Enemmi(int posx,String fichierTronche,int taille,int largeur,int tailleFichierSprites,double coefficientBobo){
		super(fichierTronche,posx,100,tailleFichierSprites,taille,largeur);
		this.numImg =10;
		this.currentTronche = sprites[(int)numImg];
		this.taille=taille;
		this.largeur =largeur;
		this.coefficientBobo=coefficientBobo;
		
	}
	
	public void bobo(){
		if(vie>0 && compteur==0){
			vie-=coefficientBobo;
			compteur=20;
		}
		
	}
	
	public void bouger(){
		if(peuxBougerG && bg && !baisse){
			bougerAGauche();
			// bd = false;
			
		}
		else{
			bg=false;
			bd =true;
			
		}
		if(peuxBougerD && bd && !baisse){
			bougerADroite();
			// bd = false;
			
		}
		else{
			bg=true;
			bd =false;
			
		}
		if(baisse){
			bougerEnBas();
		}
		// else{
			// bd=true;
			// bg =false;
		// }
		// if(bd == true){
			// bougerADroite();
			// bd =true;
			// bg =false;
		// }
		// else{
			// bd=false;
			// bg =true;
		// }
	}
	
	public void update(){
		bouger();
		this.currentTronche = this.sprites[(int)this.numImg];
		
		if(vie<=0){
			baisse=true;
		}
		if(compteur!=0){
			compteur--;
			}
		
	}
}