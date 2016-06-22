
import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public abstract class Map{
	
	private Image fond;
	public Image arplan;
	public Case[][] monde;
	public int hauteur;
	public int largeur;
	public HashMap<Long,String> events;
	public Music zicmu;
	public Music zicmuBoss;
	
	public Platformer p;
	
	
	public Map(String nomImage,String nomArPl,int largeur, int hauteur,String nomFichier,Platformer p,String fichierEvents) throws	FileNotFoundException{
		this(nomImage,nomArPl,largeur,hauteur,nomFichier,fichierEvents);
		this.p=p;
	}
	/**
	*Constructeur de la Map a partir d'un fichier texte de 0 et de 100
	*Si 1 alors obstacle, sinon normal.
	*@Author LAY
	*/
	public Map(String nomImage,String nomArPl,int largeur, int hauteur,String nomFichier,String fichierEvents) throws	FileNotFoundException{ //
		
		this.p=p;
		this.monde = new Case[200][200];
		this.largeur = (int)(largeur/30);
		this.hauteur = (int)(hauteur/30);
		
		
		try {
			this.fond= ImageIO.read(new File(nomImage));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		
		try {
			this.arplan= ImageIO.read(new File(nomArPl));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		
		
		int k =0;
		int l =0;
		
		BufferedReader in = new BufferedReader(new FileReader(nomFichier));
		String ligne=null;
		
		while(l<this.hauteur){
				
			try{
			ligne = in.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			k=0;
			while(k<this.largeur){
				String s=ligne.substring(k,k+1);
				Case c =new Case(k,l,Integer.parseInt(s));
				this.monde[k][l] = c;
				k++;
				
			}
			l++;	
		}
		
		try{
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		this.events = analyseEvent(fichierEvents);
	}	
		//Events
		public HashMap<Long,String> analyseEvent(String fichierEvents){
			
		HashMap<Long,String> ret = new HashMap<Long,String>();
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(fichierEvents));
		}
		
		catch(IOException e){
			e.printStackTrace();
		}
		
		
		String ligne=null;
		
		
				
			try{
			ligne = in.readLine();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			
			try{
			
			while(in.ready()!=false){
				ligne = in.readLine();
				
				String[] s= new String[2];
				s=ligne.split(";");
				
				Long c = new Long(0);
				c=c.parseLong(s[0]);
				String d= s[1];
				ret.put(c,d);
				
				// System.out.println(c+" "+d);
				
			}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			
			try{
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
			
		return ret;	
	}
	
	public Image getFond(){
		Image ret = this.fond;
		return ret;
	}
	
	public Case getCase(int x, int y){
		Case ret = monde[x][y];
		return ret;
	}
	
	public boolean franchissable(int x, int y){
		boolean ret = false;
		if(monde[x][y].isFranchissable = true ){
			ret = true;
		}
		return ret;
	}
	
	// public abstract void bigEvent();
	
	public void update(){
		//Animations
	}
	
	abstract public void bouton(Case c);
}