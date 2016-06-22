import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Bonom extends Bougeur{

										//
	//Touches
	public boolean tGPress;
	public boolean tDPress;
	public boolean tBPress;
	public boolean tHPress;
	public boolean tSPress;
	public boolean tXPress;
	public boolean tWPress;
	
	
	//Armes
	public Objet arme;
	public Objet[] inventaire;
	public int nbInvent;
	public int indexinvent;
	
	//Nom
	public String nom;
	public Image tronche;
	//
	public Bonom(int x){
	
		super("platformer/persosSprites1.png",x,100,1800,60,30);
		// super("platformer/cecile.png",100,100);
		
		this.nom= "Sophie Dina Floch";
		
		this.tGPress=false;
		this.tDPress=false;
		this.tBPress=false;
		this.tHPress=false;
		this.tSPress=false;
		this.tXPress=false;
		this.tWPress=false;
		
		inventaire=new Objet[5];
		nbInvent=1;
		indexinvent=0;
		inventaire[0]= new Objet("platformer/Pelle.png",this,"baton");
		inventaire[1]= new Objet("platformer/lanceRoquettes.png",this,"fusil");
		
		this.arme = inventaire[0];
		this.taille= 60;
		this.largeur=30;
		
		try{
			this.tronche= ImageIO.read(new File("platformer/interTronche.png"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	//Aqcuisition des touches
	public void keyReleased(int e){
		
		if(e == KeyEvent.VK_LEFT){
			this.tGPress=false;
		}
		if(e ==KeyEvent.VK_RIGHT){
			this.tDPress=false;
		}
		
		if(e ==KeyEvent.VK_DOWN){
				this.tBPress=false;
			
			
		}
		
		if(e ==KeyEvent.VK_UP){

			
			this.tHPress=false;
			this.vitesseDeSaut =20;
			this.saute = false;
		}
		if(e == KeyEvent.VK_SPACE){
			
			this.tSPress=false;
		}
		if(e == KeyEvent.VK_X){
			
			this.tXPress=false;
		}
		if(e == KeyEvent.VK_W){
			
			this.tWPress=false;
		}
		
	}
	
	public void keyPressed(int e){
		//Directions
		if(e == KeyEvent.VK_LEFT){
			if(!tBPress  && !tDPress){
				this.tGPress=true;
				
			}
		}
		if(e == KeyEvent.VK_UP){
			this.tHPress=true;
			
		}
		if(e == KeyEvent.VK_RIGHT){
			if(!tBPress  && !tGPress){
				this.tDPress=true;
				
			}
		}
		if(e == KeyEvent.VK_DOWN){
			
			this.tBPress=true;
			
			
		}
		//Taper
		if(e == KeyEvent.VK_SPACE){
			
			this.tSPress=true;
		}
		//Action
		if(e == KeyEvent.VK_X){
			
			this.tXPress=true;
		}
		//Changer d'arme
		if(e == KeyEvent.VK_W){
			
			this.tWPress=true;
		}
		
	}
	
	public void boutonner(){
		System.out.println("J'ai appuye!");
	}
	
	public void bobo(){
		if(vie>0){
			if(!bobo){
						
				this.vie -=10;
				bobo=true;
				this.compteur=20;
			}
			
		}
		else{System.out.println("La mort");
		}
	}
	
	/**
	*Change darme
	*@Author LAY
	*/
	public void changerArme(){
		if(indexinvent>=nbInvent){
			indexinvent=0;
		}
		else{
			indexinvent++;
		}
		arme.balle.clear();
		tWPress=false;
	}
	
	//Capte les mouements
	public void update(){
		if(tBPress==true && !tSPress){
			bougerEnBas();
			arme.arret();
		}
		if(tDPress==true && !tBPress ){
			if(!tSPress && this.hauteurEnLAir ==0 ){
				bougerADroite();
				arme.arret();
			}
			else if(this.hauteurEnLAir >0 ){
				bougerADroite();
				arme.arret();
			}
		}
		if(tGPress==true && !tBPress ){
			if(!tSPress && this.hauteurEnLAir ==0 ){
				bougerAGauche();
				arme.arret();
			}
			else if(this.hauteurEnLAir >0 ){
				bougerAGauche();
				arme.arret();
			}
		}
		if(tHPress==true ){
			bougerEnHaut();
			arme.arret();
		}
		if(tSPress==true && this.hauteurEnLAir ==0 ){
			taper();
			this.arme.taper();
		}
		
		if(!tHPress && !tBPress && !tDPress && !tGPress && !saute && !tSPress){
			if(this.sens=="droite"){
			arret(10);
			}
			if(this.sens=="gauche"){
			arret(9);
			}
			arme.arret();
		}
		if(tXPress==true ){
			boutonner();
		}
		
		if(tWPress && !tSPress ){
			changerArme();
		}
		
		//bobo
		if(compteur<=0){
				bobo=false;
				compteur =0;
		}
		else{
			
			compteur-=1;
		}
		if(compteur%2==1){
			
						this.numImg=42;
			}
		
		
		
		// System.out.println("B"+peuxBougerB);
		this.arme=inventaire[indexinvent];
		
		this.currentTronche = this.sprites[(int)this.numImg];
		this.arme.update();
		
	}
	
	

}