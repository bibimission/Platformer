
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class TestPlatformer extends JFrame implements KeyListener , Runnable {
	
	public static JFrame fenetre;
	private Platformer pan;
	
	private CardLayout layout;
	private JPanel content;
	private int indPan;
	
	private Panneau[] panneaux;

	
	public static final String JEU = "0";
	public static final String MENU = "1";
	public static final String BOSS = "2";
	
	public TestPlatformer(){
		this.fenetre = new JFrame();
		this.fenetre.setTitle("Sophie Dina Floch");
		this.fenetre.setSize(905, 630);
		this.fenetre.setLocationRelativeTo(null);
		this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.fenetre.setResizable(false);
		
		
		content = new JPanel();
		layout = new CardLayout();
		content.setLayout(layout);
		
		panneaux = new Panneau[5];
		panneaux[0] = new Platformer();
		panneaux[1] = new MenuPan(this);
		panneaux[2] = new ScrollPlatformer();
		indPan=1;
		
		content.add(panneaux[0],JEU);
		content.add(panneaux[1],MENU);
		content.add(panneaux[2],BOSS);
		// content.add(panneaux[3],PARTIE);
		// content.add(panneaux[4],SELECTEUR);
		
		setContentPane(content);
		setFenetreActive(MENU);
		

		// this.pan = new Platformer(); //"maps/map1/map3.png","maps/map1/arrierePlan.png",900,600,"maps/map1/map1.txt"
		this.fenetre.setContentPane(this.content);
		this.fenetre.setVisible(true);
		this.fenetre.addKeyListener(this);
		
		new Thread(this).start();
	}
	
	 public static void main(String[] args) {
		new TestPlatformer();
		
		// Music m =new Music("zicmu/TwinShot.wav");
		// m.jouerEnBoucle();
		// m.jouer();
		
		
	}
	public void setFenetreActive(String fCode) {
		System.out.println("show "+fCode);
		layout.show(content, fCode);//si la fenetre n'exite pas rien ne se passe
		panneaux[indPan].tagueule();
		indPan=Integer.parseInt(fCode);
		panneaux[indPan].letsDance();
	}
	
	
	public void keyTyped(KeyEvent e){
		// System.out.println(e.getKeyChar());
		
	}
	
	/**
	*Lorsqu'une touche est pressee
	*@Author LAY
	*/
	public void keyPressed(KeyEvent e){
		// System.out.println(e.paramString());
		
		this.panneaux[indPan].keyPressed(e);
		
		
	}
	
	/**
	*Lorsqu'une touche est relâchée
	*@Author LAY
	*/
	public void keyReleased(KeyEvent e){
		// System.out.println(e.paramString());
		
		this.panneaux[indPan].keyReleased(e);
		
		
	}
	
	/**
	*Un thread, tourne en boucle lorsque le jeu est lancé.
	*@Author LAY
	*/
	public void run() {
        long time;
        long wait;
        
        while(true) {
            time = System.nanoTime();
            this.content.repaint();
           
            
            wait = 33-((System.nanoTime()-time)/1000000);
            if(wait < 0) {
                wait = 5;
                System.out.println("trop long&quot");
            }
            
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}