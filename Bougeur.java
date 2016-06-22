import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public abstract class Bougeur{
	public Image currentTronche;
	private BufferedImage gros;
	
	//Valeurs importantes
	public int positionBonomX ;
	public int positionBonomY ;
	
	public int largeur;
	public int taille;
	//Vie
	public boolean bobo=false;
	public boolean blesseLesGens=false;
	public double vie;
	public double compteur;
	//Tronche
	public Image[] sprites;				// 0 à 9 -Tourné à Gauche   |  10 à 19 -Tourné à Droite  |  20 à 27 -Se Baisser
	public double numImg;
	
	//Sens du bonom
	public String sens;
	
	//Variables du Saut
	public double hauteurEnLAir;
	public double vitesseDeSaut;
	private double vitesseDeChute;
	public boolean saute =false;
	
	public int coorDuSol;
	public boolean estSurUnePlatform=false;
	
	//Mouvement
	public int vitesseDeMarche =4;
	
	public boolean peuxBougerD;
	public boolean peuxBougerB;
	public boolean peuxBougerG;
	public boolean peuxBougerH;
	
	
	
	//Constructeur
	public Bougeur(String nomFichierDesSprites,int posx,int posy,int taille,int hauteur,int largeur){
		this.positionBonomX=posx;
		this.positionBonomY=posy;
		
		this.taille =hauteur;
		
		this.vie =50;
		
		try {
			this.gros= ImageIO.read(new File(nomFichierDesSprites));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		
		this.sprites = new Image[60];
		int x =0;
		int y =0;
		int i =0;
		while(x<taille){
			this.sprites[i] = this.gros.getSubimage(x,y,largeur,hauteur);
			x =x+largeur;
			i++;
		}
		this.numImg =10;			//Image 10 est l'image lorsque le bonom est immobile et regarde a droite
		
		this.currentTronche = sprites[(int)numImg];
		
		this.sens = "droite";
		
		this.vitesseDeSaut = 20;
		
		this.vitesseDeChute = 1;
		
		peuxBougerD = true;
		peuxBougerB = true;
		peuxBougerG = true;
		peuxBougerH = true;
		
		// perchement(451); //451 c'est le sol par défaut
	}
	
	//Prend des degats
	public abstract void bobo();
	
	//Marcher a droite
	public void bougerADroite(){
		if(peuxBougerD == true){
			if(this.numImg==18 || this.numImg<10 || this.numImg > 19)this.numImg = 11;
			else this.currentTronche = this.sprites[(int)this.numImg];
			this.numImg+= 0.5;
			this.positionBonomX+=vitesseDeMarche;
			
			this.sens = "droite";
		}
	}
	
	//marcher a gauche
	public void bougerAGauche(){
		if(peuxBougerG == true){
			if(this.numImg<=1 || this.numImg>9)this.numImg = 9;
			else this.currentTronche = this.sprites[(int)this.numImg];
			this.numImg-= 0.5;
			
			this.positionBonomX-=vitesseDeMarche;
			
			this.sens = "gauche";
		}
	}
	
	//sauter
	public void bougerEnHaut(){
	
			
				if(this.hauteurEnLAir==0){
					this.vitesseDeSaut=20;
					this.saute =true;
				}
				if( this.sens =="gauche")this.numImg = 0;
				if( this.sens =="droite")this.numImg = 19;
				if(saute==true || this.hauteurEnLAir==0){
					
				
				
						this.positionBonomY-=this.vitesseDeSaut;
						this.vitesseDeSaut = this.vitesseDeSaut*0.8;
						
					
						
						
						if(positionBonomY-this.vitesseDeSaut <coorDuSol-90){
							saute=false;
						}
						else saute =true;
					
				}
				else{
					this.vitesseDeSaut=20;
					saute=false;
				}
		
	
		
		
	}
	
	//Se baisser
	public void bougerEnBas(){
		
		if(this.peuxBougerB){
			System.out.println("Ne peut pas se baisser en l'air");
		}
		/**
		Pour descendre des plateformes avec Bas
		
		else if(this.estSurUnePlatform==true){
			this.positionBonomY +=5;
			this.estSurUnePlatform=false;
		}
		
		*/
		
		
		else{
			if(this.numImg !=19 && this.numImg !=0 && this.numImg <20 && this.numImg >9){
				this.numImg =20;
				// Music m =new Music("pet.wav");
				// m.jouer();
			}
			if(this.numImg !=19 && this.numImg !=0 && this.numImg <20 && this.numImg <10){
				this.numImg =27;
				// Music m =new Music("pet.wav");
				// m.jouer();
			}
			
			
			if (this.numImg!=23 && this.sens == "droite"){
			
				this.currentTronche = this.sprites[(int)this.numImg];
				this.numImg+= 0.5;
			}
			if (this.numImg!=24 && this.sens == "gauche"){
			
				this.currentTronche = this.sprites[(int)this.numImg];
				this.numImg-= 0.5;
			}
			
		}
	}
	
	public void taper(){
		if(this.sens =="droite"){
			if(this.numImg <28 || this.numImg >=34 ){
				this.numImg =28;
				}
				this.numImg+= 0.5;
		}
		else if(this.sens=="gauche"){
			if(this.numImg <=35 || this.numImg >41 ){
				this.numImg =41;
				}
				this.numImg-= 0.5;
		}
		
		if(this.numImg==37 || this.numImg==34){
			blesseLesGens=true;
		}
		else{
			blesseLesGens=false;
		}
	}
	
	//affiche le bonome en position d'arret par rapport au sens ou il était.
	public void arret(int pos){
		
		this.currentTronche = this.sprites[pos];
		this.numImg =pos;
	}
	
	
	
	//fait tomber le joueur si il est au dessus du sol.
	public void gravite(){
		if(peuxBougerB == true){
			 
			if(saute==false){
				 if(this.positionBonomY<this.coorDuSol){
					
					positionBonomY+= (int)vitesseDeChute;
					vitesseDeChute = vitesseDeChute*1.4;
					if(positionBonomY>coorDuSol)positionBonomY=coorDuSol;
					
				 }
				
				else vitesseDeChute = 1;
				//this.positionBonomY =this.coorDuSol+60;
				
			
			}
			else vitesseDeChute = 1;
		}
		else vitesseDeChute = 1;
		 
	}
}