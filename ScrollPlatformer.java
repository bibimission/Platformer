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

public class ScrollPlatformer extends Panneau{

	private Map[] maps;
	
	
	private Bonom mec;
	private Enemmi boss;
	
	private int xMapActuelle;
	private int xMapSuivante;
	
	private int indexMap;
	private int scrolling;
	private int scrollingJ;
	private int distance;
	
	private int defilement;
	
	public ScrollPlatformer(){
	
		this.mec = new Bonom(100);
		 boss=new Enemmi(20,"platformer/Basilicosaure.png",300,240,10800,5);
		maps= new Map[4];
		try{
			maps[0]=new MapScroll("maps/mapScroll/mapScroll1.png","maps/mapScroll/events.txt","maps/mapScroll/mapScroll1.txt");
			maps[1]=new MapScroll("maps/mapScroll/mapScroll2.png","maps/mapScroll/events.txt","maps/mapScroll/mapScroll2.txt");
			maps[2]=new MapScroll("maps/mapScroll/mapScroll3.png","maps/mapScroll/events.txt","maps/mapScroll/mapScroll1.txt");
			maps[3]=new MapScroll("maps/mapScroll/mapScroll4.png","maps/mapScroll/events.txt","maps/mapScroll/mapScroll2.txt");
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		
		indexMap=0;
		xMapActuelle=0;
		xMapSuivante=900;
		scrolling =0;
		scrollingJ =0;
		distance = 0;
		
		defilement=10;
		
		mec.vitesseDeMarche=12;
		boss.vitesseDeMarche=11;
		
		m= new Music("zicmu/sonic.wav");
	}
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_R){
			// respawn();
		}
		this.mec.keyPressed(e.getKeyCode());
	}
	public void keyReleased(KeyEvent e){
		this.mec.keyReleased(e.getKeyCode());
	}
	public void keyTyped(KeyEvent e){
		
		
	}
	public void paintComponent(Graphics g) {
		g.drawImage(this.maps[indexMap].getFond(),xMapActuelle+scrolling, 0, this);
		g.drawImage(this.maps[indexNext()].getFond(),xMapSuivante+scrolling, 0, this);
		g.drawImage(this.mec.currentTronche, this.mec.positionBonomX+scrollingJ, this.mec.positionBonomY, this);
		g.drawImage(this.boss.currentTronche, this.boss.positionBonomX+scrollingJ, this.boss.positionBonomY, this);
		update();
	}
	
	public void update(){
		// ge.update();
		calculDesDeplacements(mec);
		calculDesDeplacements(boss);
		boss.peuxBougerG=false;
		this.mec.update();
		this.mec.gravite();
		this.boss.update();
		this.boss.gravite();
		
		scrollingeur();
		distance+=defilement;
		// System.out.println(indexMap);
		// System.out.println(mec.positionBonomX);
		if(distance>=900){
			changeMap();
			distance=0;
			scrolling=0;
			
		}
		
		if(seRentreDedans(mec,boss)&& this.mec.blesseLesGens && !boss.baisse){ //
				System.out.println("ouch");
				boss.bobo();
				
		}
		else if(seRentreDedans(mec,boss) && !this.mec.tSPress && !boss.baisse){
				mec.bobo();
				
		}
	}
	
	
	public void scrollingeur(){
		// if(this.mec.positionBonomX>400 ){ //&& this.mec.positionBonomX<=map.largeur*30-500
			// this.scrolling= -this.mec.positionBonomX+400;
		// }
		// else if(this.mec.positionBonomX>map.largeur*30-500){
			// this.scrolling=-(map.largeur*30-900);
		// }
		// else scrolling=0;
		scrolling-=defilement;
		scrollingJ-=defilement;
	}
	
	public void changeMap(){
		
		
		indexMap=indexNext();
		
		
	}
	public int indexNext(){
		int ret=indexMap+1;
		if(ret>=maps.length){
			ret=0;
		}
		return ret;
	}
	
	// public void calculDesDeplacements(Bougeur b){
		// b.peuxBougerG=true;
		// b.peuxBougerD=true;
		// b.peuxBougerH=true;
		// b.coorDuSol=420;
		// b.hauteurEnLAir=480-(b.positionBonomY+b.taille);
		
	// }
	// /**
	public void calculDesDeplacements(Bougeur b){
		int x= b.positionBonomX;
		int y= b.positionBonomY;
		
		
		int index=this.indexMap;
		 
		// if(i%900<=0){
			// indexMap=this.indexMap;
			// indexMap=indexNext();
		// }
		
		
		
		int i=0;
		boolean bloque =false;
		Case c1;
		index=this.indexMap;
		// Gauche
		while(i<b.taille-2 && !bloque){
			
			if(((int)(x-1))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
				
				index=indexNext();
			}
			
			c1=maps[index].getCase((int)((x-1)%900)/30,(int)(y+i)/30);
			if(x<=0 || !c1.isFranchissable){
				b.peuxBougerG=false;
				bloque=true;
			}
			else{
				b.peuxBougerG=true;
			}
			i++;
			
		}
		// Droite
		bloque =false;
		i=0;
		index=this.indexMap;
		while(i<b.taille-2 && !bloque){
			
			if(((int)(x+b.largeur))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
				
				index=indexNext();
			}
			
			c1=maps[index].getCase((int)((x+b.largeur)%900)/30,(int)(y+i)/30);
			if( !c1.isFranchissable){ //x>maps[indexMap].largeur*30-40 ||
				b.peuxBougerD=false;
				bloque=true;
			}
			else{
				b.peuxBougerD=true;
			}
			i++;
			
		}
		
		
		
		
		// Haut
		bloque =false;
		i=0;
		index=this.indexMap;
		
		
		while(i<b.largeur && !bloque){
			
			if(((int)(x+i))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
			
			index=indexNext();
		}
			
			c1=maps[index].getCase((int)((x+i)%900)/30,(int)(y)/30);
			if(!c1.isFranchissable || c1.isPlatform){
				b.peuxBougerH=false;
				bloque=true;
			}
			else{
				b.peuxBougerH=true;
			}
			i++;
			
		}
		
		// Evenements et autres
		i=0;
		int j=0;
		index=this.indexMap;
			
		
		while(i<b.taille){
			j=0;
			
			if(((int)(x+j))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
				
				index=indexNext();
			}
			
			while(j<b.largeur){
				c1=maps[index].getCase((int)((x+j)%900)/30,(int)(y+i)/30);
				
				if(mec.tXPress){
					maps[index].bouton(c1);
					mec.tXPress=false;
				}
				if(c1.faitBobo){
					b.bobo();
				}
				
				j+=29;
			}
			i+=29;
		}
		
		// Gravite
		i=-1;
		bloque =false;
		j=0;
		
			
		index=this.indexMap;
		while(i<maps[index].hauteur*30-40 && !bloque){
			j=0;
			
			
			
			while(j<b.largeur && !bloque){
				index=this.indexMap;
				if(((int)(x+j))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
				
					index=indexNext();
				}
				c1=maps[index].getCase((int)((x+j)%900)/30,(int)(y+b.taille+i+1)/30);
				
				
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
		
			
		index=this.indexMap;
		while(i<b.largeur && !bloque){
			
			if(((int)(x+i))%900<b.largeur && b.positionBonomX%900>900-b.largeur){
				
				index=indexNext();
			}
			
			c1=maps[index].getCase((int)((x+i)%900)/30,(int)(y+b.taille+1)/30);
			
			
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
	// **/
	//Calcul des collisions
	
	public boolean seRentreDedans(Bougeur b1, Bougeur b2){
		
		Rectangle r1 = new Rectangle(b1.positionBonomX, b1.positionBonomY, b1.largeur, b1.taille);
		Rectangle r2 = new Rectangle(b2.positionBonomX, b2.positionBonomY, b2.largeur, b2.taille);
		
		boolean ret = r1.intersects(r2);
		
		return ret;
	}
}