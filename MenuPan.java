import java.awt.*;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class MenuPan extends Panneau {
	
	private Image fond;
	private TestPlatformer tp;
	private Image boutonJouer;
	private Image boutonJouerHover;
	private JButton jouer;
	
	public MenuPan(TestPlatformer tp){
		try {
			this.fond= ImageIO.read(new File("platformer/MenuPrincipal.png"));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		try {
			this.boutonJouer= ImageIO.read(new File("platformer/BoutonJouer.png"));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		try {
			this.boutonJouerHover= ImageIO.read(new File("platformer/BoutonJouerHover.png"));
		 
		} 
		catch (IOException e) {
		  e.printStackTrace();
		}
		jouer = new JButton(new ImageIcon(boutonJouer));
		this.tp=tp;
		setLayout(null);
		jouer.setBounds(450,375,150,50);
		jouer.setBorderPainted(false);
		jouer.setContentAreaFilled(false);
		jouer.setFocusable(false);
		jouer.setRolloverIcon(new ImageIcon(boutonJouerHover));
		add(jouer);
		
		m=new Music("zicmu/TwinShot.wav");
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(fond,0,0,null);
		
	}
	
	
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_R){
		tp.setFenetreActive(tp.JEU);
		
		}
		else{
			tp.setFenetreActive(tp.BOSS);
		}
	}
	
	/**
	*Lorsqu'une touche est relâchée
	*@Author LAY
	*/
	public void keyReleased(KeyEvent e){
		
	}
  }
