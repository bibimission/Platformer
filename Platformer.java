
import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
*Le Jpanel du jeu.
*@Author LAY
*/
public class Platformer extends Panneau{

	
	public Map map;
	
	public Bonom mec;
	
	public ArrayList<Enemmi> cible;
	
	private int scrolling;
	
	public Image interf;
	
	private int nbEnemmi;
	public GestionnaireDEvenements ge;
	

  
	/**
	*Construit la fenetre
	*imageArpl est l'image de fond
	*imageMap est le decor principal
	*dimX et dimY sont les dimensions de la carte
	*txtMap est le texte contenant les infos de cases
	*@Author LAY
	*/
	public Platformer(){//String imageMap,String imageArpl, int dimX,int dimY,String txtMap
		
			try{
			this.map = new Map1(this); //imageMap,imageArpl,dimX,dimY,txtMap
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			try {
			this.interf= ImageIO.read(new File("platformer/interface.png"));
		 
			} 
			catch (IOException e) {
			  e.printStackTrace();
			}
		
		
		this.mec = new Bonom(100);
		
		this.cible = new ArrayList<Enemmi>();
		int i =0;
		nbEnemmi=0;
		
		// while(i< 5){
			// spawnEnemmi("platformer/cecile.png");
			
			// i++;
		// }
		
		this.scrolling=0;
		
		//events
		ge= new GestionnaireDEvenements(this,map.events);
		
		m=map.zicmu;
		
	}
	/**
	*Vie
	*@Author LAY
	*/
	private void dessineVie(Graphics g){
				g.setColor(Color.red);
				
				g.drawLine(24,128,21+3*(int)mec.vie,128);
				g.drawLine(24,129,21+3*(int)mec.vie,129);
				
				
				g.setColor(Color.white);
				g.drawLine(24,130,21+3*(int)mec.vie,130);
				g.setColor(new Color(255,128,119));
				g.drawLine(24,131,21+3*(int)mec.vie,131);
				g.setColor(Color.red);
				g.drawLine(24,132,21+3*(int)mec.vie,132);
				g.drawLine(24,133,21+3*(int)mec.vie,133);
				
				g.drawLine(24,134,21+3*(int)mec.vie,134);
				g.drawLine(24,135,21+3*(int)mec.vie,135);
				g.setColor(new Color(255,128,119));
				g.drawLine(24,136,21+3*(int)mec.vie,136);
				g.setColor(Color.red);
				g.drawLine(24,137,21+3*(int)mec.vie,137);
				g.drawLine(24,138,21+3*(int)mec.vie,138);
		
			}
	/**
	*Peint la fenêtre.
	*@Author LAY
	*/
	public void paintComponent(Graphics g) {
		//Decor en arriere plan
		g.drawImage(this.map.arplan,0+(int)(scrolling*0.7),0,this);
		
		g.drawImage(this.map.getFond(), 0+scrolling, 0, this);
		update();
		//interface
		g.drawImage(this.interf, 0, 0, this);
		g.setColor(Color.white);
		g.drawString(this.mec.nom,25,107);
		g.drawString(" "+this.mec.vie,25,140);
		g.drawImage(this.mec.arme.holo,100,10,this);
		
		g.drawImage(mec.tronche,13,15,this);
			//vie
			dessineVie(g);
		
		
		//Bonom
		g.drawImage(this.mec.currentTronche, this.mec.positionBonomX+scrolling, this.mec.positionBonomY, this);
		// System.out.println("X"+this.mec.positionBonomX/30+"Y"+this.mec.positionBonomY/30);
		
		// et son Arme
		g.drawImage(this.mec.arme.currentTronche, this.mec.arme.posx+scrolling, this.mec.arme.posy, this);
		if(this.mec.arme.type=="fusil"){
			for(Projectile pan : mec.arme.balle){
				
					g.drawImage(pan.tronche,pan.x+scrolling,pan.y,this);
				
			}
		}
		
		//Mechant
		int i =0;
		for(Enemmi gens : cible){
				
					
			g.drawImage(gens.currentTronche, gens.positionBonomX+scrolling, gens.positionBonomY, this);
			
			g.drawString(" "+gens.vie, gens.positionBonomX+scrolling, gens.positionBonomY);
			
		}
	}
	
	
	/**
	*Hyper Pratique!
	*Verifie les possibilités de déplacement dans les case aux alentours.
	*
	*Actualise les parametres du Bonom: estSurUnePlatform-hauteurEnLAir-coorDuSol et peuxBouger[?]
	*
	*/
	public void calculDesDeplacementsAncienneVersion(Bougeur b){
		int x = b.positionBonomX;
		int y = b.positionBonomY;
		int distanceDuSol;
		
		//cases à gauche du bonom
		Case c1 =this.map.getCase((int)(x)/30,(int)(y+50)/30);
		Case c5 =this.map.getCase((int)(x)/30,(int)(y)/30);
		
		//cases à droite du bonom
		Case c2 =this.map.getCase((int)(x+30)/30,(int)(y+50)/30);
		Case c6 =this.map.getCase((int)(x+30)/30,(int)(y)/30);
		
		//case au dessus du bonom
		Case c3 =this.map.getCase((int)(x+15)/30,(int)(y-b.vitesseDeSaut)/30);
		
		//cases en bas du bonom
		Case c4 =this.map.getCase((int)(x+2)/30,(int)(y+60)/30);
		Case c7 =this.map.getCase((int)(x+29)/30,(int)(y+60)/30);
		
		
		
		
		//Calcule si on peut aller a Gauche
		if(c1.isFranchissable && c5.isFranchissable && x>0){
			b.peuxBougerG=true;
		}
		else b.peuxBougerG=false;
		
		//Calcule si on peut aller a Droite
		if(c2.isFranchissable && c6.isFranchissable && x<map.largeur*30-40){
			b.peuxBougerD=true;
		}
		else b.peuxBougerD=false;
		
		//Calcule si on peut aller en Haut
		if(c3.isFranchissable == true){
			b.peuxBougerH=true;
		}
		else b.peuxBougerH=false;
		
		//Calcule si on peut tomber
		if(c4.isFranchissable && c7.isFranchissable && !c4.isPlatform && !c7.isPlatform ){  //
			b.peuxBougerB=true;
		}
		else if(c4.isPlatform){
			b.estSurUnePlatform =true;
			b.peuxBougerB=false;
		}
		else {b.peuxBougerB=false;}
		
		// if( c4.isPlatform ||  c7.isPlatform){
			// if(c4.getY()<=y+60 || c7.getY()<=y+60 ){
				// this.mec.peuxBougerB=true;
			// y+=1;
			// }
		// }
			
			while(  this.map.getCase((int)x/30,(int)(y+60)/30).isFranchissable  && this.map.getCase((int)(x+30)/30,(int)(y+60)/30).isFranchissable
				&& !this.map.getCase((int)x/30,(int)(y+60)/30).isPlatform && !this.map.getCase((int)(x+30)/30,(int)(y+60)/30).isPlatform 
				){  //|| this.map.getCase((int)x/30,(int)(y+60)/30)==c4 || this.map.getCase((int)(x+29)/30,(int)(y+60)/30)==c7
					
				//c4.getY()+1!=y+60
					y+=1;
				
			}
			distanceDuSol = y -this.mec.positionBonomY;
			b.hauteurEnLAir=distanceDuSol;
			
			b.coorDuSol = y;
			// System.out.println(y);
		
		if(mec.tXPress){
			map.bouton(c1);
			mec.tXPress=false;
		}
		if(c1.faitBobo || c2.faitBobo || c5.faitBobo || c6.faitBobo){
			b.bobo();
		}
		
	}
	
	public void calculDesDeplacements(Bougeur b){
		int x= b.positionBonomX;
		int y= b.positionBonomY;
		
		int i=0;
		boolean bloque =false;
		Case c1;
		
		//Gauche
		while(i<b.taille-2 && !bloque){
			c1=map.getCase((int)(x-1)/30,(int)(y+i)/30);
			if(x<=0 || !c1.isFranchissable){
				b.peuxBougerG=false;
				bloque=true;
			}
			else{
				b.peuxBougerG=true;
			}
			i++;
			
		}
		//Droite
		bloque =false;
		i=0;
		
		while(i<b.taille-2 && !bloque){
			c1=map.getCase((int)(x+b.largeur)/30,(int)(y+i)/30);
			if(x>map.largeur*30-40 || !c1.isFranchissable){
				b.peuxBougerD=false;
				bloque=true;
			}
			else{
				b.peuxBougerD=true;
			}
			i++;
			
		}
		
		
		
		
		//Haut
		bloque =false;
		i=0;
		
		while(i<b.largeur && !bloque){
			c1=map.getCase((int)(x+i)/30,(int)(y)/30);
			if(!c1.isFranchissable || c1.isPlatform){
				b.peuxBougerH=false;
				bloque=true;
			}
			else{
				b.peuxBougerH=true;
			}
			i++;
			
		}
		
		//Evenements et autres
		i=0;
		int j=0;
		
		while(i<b.taille){
			j=0;
			while(j<b.largeur){
				c1=map.getCase((int)(x+j)/30,(int)(y+i)/30);
				
				if(mec.tXPress){
					map.bouton(c1);
					mec.tXPress=false;
				}
				if(c1.faitBobo){
					b.bobo();
				}
				
				j+=29;
			}
			i+=29;
		}
		
		//Gravite
		i=-1;
		bloque =false;
		j=0;
		
		while(i<map.hauteur*30-40 && !bloque){
			j=0;
			while(j<b.largeur && !bloque){
				c1=map.getCase((int)(x+j)/30,(int)(y+b.taille+i+1)/30);
				
				
				if(c1.isPlatform || !c1.isFranchissable){
					bloque=true;
				}
				j+=29;
			}
			
			i++;
		}
		if(i<=4)i=0;
		
			
			int distanceDuSol = i;
			
			b.hauteurEnLAir=distanceDuSol;
			
			b.coorDuSol = i+y;
			
		
		// Bas
		bloque =false;
		i=0;
		
		while(i<b.largeur && !bloque){
			c1=map.getCase((int)(x+i)/30,(int)(y+b.taille+1)/30);
			
			
			if(!c1.isFranchissable || c1.isPlatform){
				b.peuxBougerB=false;
				bloque=true;
			}
			else{
				b.peuxBougerB=true;
			}
			i+=20;
			
		}
		
		
		
		
	}
	//Calcul des collisions
	
	public boolean seRentreDedans(Bougeur b1, Bougeur b2){
		
		Rectangle r1 = new Rectangle(b1.positionBonomX, b1.positionBonomY, b1.largeur, b1.taille);
		Rectangle r2 = new Rectangle(b2.positionBonomX, b2.positionBonomY, b2.largeur, b2.taille);
		
		boolean ret = r1.intersects(r2);
		
		return ret;
	}
	
	//Calcule le décalage des Images
	public void scrollingeur(){
		if(this.mec.positionBonomX>400 && this.mec.positionBonomX<=map.largeur*30-500){
			this.scrolling= -this.mec.positionBonomX+400;
		}
		else if(this.mec.positionBonomX>map.largeur*30-500){
			this.scrolling=-(map.largeur*30-900);
		}
		else scrolling=0;
	}
	
	//Met a jour le Jpanel avant de l'envoyer a paintcomponent
	public void update(){
		ge.update();
		calculDesDeplacements(mec);
		scrollingeur();
		this.mec.update();
		this.mec.gravite();
		
		int i=0;
		for(Enemmi gens : cible){
				
					
				
			
			calculDesDeplacements(gens);
			gens.update();
			gens.gravite();
			if(mec.arme.type=="fusil"){
				for(Projectile pan : mec.arme.balle){
				
					if(balleTouche(gens,pan)){
						gens.bobo();
					}
				}
			}
			
			if(seRentreDedans(mec,gens)&& this.mec.blesseLesGens && !gens.baisse){ //
				System.out.println("ouch");
				gens.bobo();
				
				// Music m =new Music("zicmu/paf.wav");
				// m.jouer();
				// cible[i].baisse=true;
			}
			else if(seRentreDedans(mec,gens) && !this.mec.tSPress && !gens.baisse){
				mec.bobo();
				
			}
			i++;
		}
	
		
		
		
	}
	// public void bigEvent(){
		// map.bigEvent;
	// }
	
	/**
	*Retourne vrai si la balle touche le bougeur
	*@Author LAY
	*/
	public boolean balleTouche(Bougeur b, Projectile p){
		
		Rectangle r1 = new Rectangle(b.positionBonomX, b.positionBonomY, b.largeur, b.taille);
		Rectangle r2 = new Rectangle(p.x, p.y, p.largeur, p.taille);
		
		boolean ret = r1.intersects(r2);
		
		return ret;
	}
	/**
	*Fait apparaitre un enemi sur la carte
	*@Author LAY
	*/
	public void spawnEnemmi(String type){
		
		if(type.equals("platformer/cecile.png")){
			cible.add(new Enemmi(2*2+400,type,60,30,1200,50));
		}
		if(type.equals("platformer/Basilic.png")){
			cible.add(new Enemmi(2*2+400,type,100,80,3600,25));
		}
		if(type.equals("platformer/Basilicosaure.png")){
			cible.add(new Enemmi(2*2+400,type,300,240,10800,5));
		}
		nbEnemmi++;
		
	}
	
	/**
	*Reinitialise le Bonom
	*@Author LAY
	*/
	public void respawn(){
			
			this.ge.spawn("joueur");
		
	}
	
	//Si une touche est pressee, transmet au Bonom
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_R){
			respawn();
		}
		this.mec.keyPressed(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e){
		this.mec.keyReleased(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e){
		
		
	}
	
	/**
	*Rend le nb d'enemmis dans le tableau d'enemmis.
	*@Author LAY
	*/
	public int getNbEnemmis(){
		return this.nbEnemmi;
	}	
	
	public void changeZicmu(Music m){
		this.m.arreter();
		this.m=m;
		this.m.jouerEnBoucle();
	}
}
