package fuentes.utiles;

import java.awt.*;
import java.applet.*;
import java.awt.image.*;


public class ImageStatus extends Applet implements ImageObserver {

  private Image picture;
  private int ancho = 2000;
  private int alto = 2000;


  /*
  public void init() {

    String filename = "http://java.sun.com/developer/technicalArticles/javase/swingworker/images/imageselection.jpg";

    System.out.println(this.getDocumentBase().getPath());

    try
    {
    if (filename != null) {
      this.picture = this.getImage(this.getDocumentBase(), filename);
    }
    }
    catch(Exception e)
    {
    	System.out.println("Excecccpion: "+e.getMessage());
    }
  }
*/

  public void paint(Graphics g) {

    if (this.picture != null) {
      g.drawImage(this.picture, 0, 0, this);
    }
    else {
      g.drawString("Missing Picture", 20, 20);
    }

  }

  public boolean imageUpdate(Image img, int infoflags, int x, int y,
   int width, int height) {

     if ((infoflags & ImageObserver.ALLBITS) != 0) {
       showStatus("Image Complete");
       this.repaint();
       return false;
     }
     else {

       if ((infoflags & ImageObserver.HEIGHT) != 0 && (infoflags & ImageObserver.WIDTH) != 0)
       {
    	   //System.out.println("Tamaño encontrado: "+this.getWidth()+" ; "+this.getHeight());
    	   if (this.getWidth() < this.ancho || this.getHeight() < this.alto)
    	   {
    		   return false;
    	   }
       }

       showStatus("x: " + x + " y: " + y + " width: " + width
        + " height: " + height);
       this.repaint();
     }

     return true;
  }

}
