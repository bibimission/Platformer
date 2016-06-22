import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Projectile{

	public int x;
	public int y;
	
	private int xa;
	private int ya;
	
	public int largeur=4;
	public int taille=4;
	
	public boolean pete;
	
	public Image tronche;
	
	public Projectile(int xDepart,int yDepart, int xArrivee,int yArrivee, String nomFichier){
		this.x=xDepart;
		this.y=yDepart;
		this.xa=xArrivee;
		this.ya=yArrivee;
		
		try {
			this.tronche= ImageIO.read(new File(nomFichier));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		
		pete=false;
	}
	public void update(){
		
		if(x!=xa){
			x+=((x-xa)/Math.abs(x-xa))*(-5);
		}
		else{
			// System.out.println("Boum");
			pete=true;
		}
	}
}