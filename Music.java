import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
 
public class Music extends Thread
{
    private URL url;
    private AudioClip sound;
  
    public Music(String nomMusic)
    {
        url = this.getClass().getClassLoader().getResource(nomMusic);
		System.out.println(url);
        sound = Applet.newAudioClip(url);
    }
     
    public void jouer()
    {
        sound.play();
    }
     
    public void jouerEnBoucle()
    {
        sound.loop();
    }
     
    public void arreter()
    {
        sound.stop();
    }
	
	
}