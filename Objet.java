import java.awt.*;
import java.util.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Objet{

	public String nom;
	public Image[] sprites;
	public BufferedImage tronche;
	public String type;	//Arme contondante ou fusil
	public int posx;
	public int posy;
	public double numImg;
	//Tronches
	public Image currentTronche;
	public Image holo;
	public Bonom b;
	public ArrayList<Projectile> balle;
	
	//calcul des coups. faitBobo si l'arme touche les gens. coordDuBobox1 et coordDuBobox2 sont le debut et la fin de la zone de degats
	public boolean faitBobo;
	public int coordDuBobox1;
	public int coordDuBobox2;
	
	//timer pour les balles
	private int timer;
	
	/**
	*Constructeur
	*@Author LAY
	*/
	public Objet(String nomFichier, Bonom b,String type){
		faitBobo = false;
		coordDuBobox1 = b.positionBonomX;
		coordDuBobox2 = b.positionBonomX;
		posx = b.positionBonomX;
		posy = b.positionBonomY;
		this.b =b;
		
		try {
			this.tronche= ImageIO.read(new File(nomFichier));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		
		
		this.sprites = new Image[60];
		int x =0;
		int y =0;
		int i =0;
		while(x<700){
			this.sprites[i] = this.tronche.getSubimage(x,y,50,60);
			x =x+50;
			i++;
		}
		this.numImg =7;			//Image 10 est l'image lorsque le bonom est immobile et regarde a droite
		
		this.currentTronche = sprites[(int)numImg];
		
		this.type=type;
		this.balle=new ArrayList<Projectile>();
		if(type=="fusil"){
			
			
			try {
			this.holo= ImageIO.read(new File("platformer/holoLanceRoquette.png"));

			} 
			catch (IOException e) {
			e.printStackTrace();
			}
		}
		else{
			try {
			this.holo= ImageIO.read(new File("platformer/holopelle.png"));

			} 
			catch (IOException e) {
			e.printStackTrace();
			}
		}
		timer=0;
	}
	
	/**
	*Met a jour les infos
	*@Author LAY
	*/
	public void update(){
		if(b.sens == "droite"){
		posx = b.positionBonomX;
		posy = b.positionBonomY;
		}
		else{
			posx = b.positionBonomX -20;
			posy = b.positionBonomY;
		}
		this.currentTronche = sprites[(int)numImg];
		
		if(type =="fusil"){
			Projectile aSupprimer=null;
			for(Projectile pan : balle){
				
				if(pan.pete){
					aSupprimer=pan;
				}
				else{
					pan.update();
				}
			}
			if(aSupprimer!=null){
				balle.remove(aSupprimer);
			}
		}
		if(timer>0){
			timer--;
		}
	}
	
	/**
	*Fait le mouvement et modifie coordDuBobo pour donner l'abscisse à partir duquel les enemis sont touchés.
	*@Author LAY
	*/
	public void taper(){
		if(b.sens=="droite"){
			if(numImg<7 || numImg >12){
				numImg =7;
			}
			else{
				numImg+=0.5;
			}
			coordDuBobox1= posx;
			coordDuBobox2= posx +50;
			
			if(type=="fusil" && timer==0){
				balle.add(new Projectile(posx,posy+20,posx+500,posy,"platformer/balle.png"));
				timer=10;
			}
		}
		if(b.sens=="gauche" ){
			if(numImg>6 || numImg <1){
				numImg =6;
			}
			else{
				numImg-=0.5;
			}
			
			coordDuBobox1= posx-20;
			coordDuBobox2= posx+30;
			if(type=="fusil" && timer==0){
				balle.add(new Projectile(posx,posy+20,posx-500,posy,"platformer/balle.png"));
				timer=10;
			}
		}
		faitBobo =true;
		
		
		
	}
	
	/**
	*Stoppe l'animation et reset les infos de collision.
	*@Author LAY
	*/
	public void arret(){
		if(b.sens == "droite"){
			this.numImg=7;
		}
		if(b.sens == "gauche"){
			this.numImg=6;
		}
		faitBobo =false;
		coordDuBobox1=posx;
		coordDuBobox2=posx;
	}
	
}