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

public abstract class Panneau extends JPanel{

	public Music m;
	public abstract void keyPressed(KeyEvent e);
	
	/**
	*Lorsqu'une touche est relâchée
	*@Author LAY
	*/
	public abstract void keyReleased(KeyEvent e);
	
	public void letsDance(){
		m.jouerEnBoucle();
	}
	
	public void tagueule(){
		m.arreter();
	}
}