package fuentes.utiles;

import java.awt.*;
import javax.swing.*;
import fuentes.constantes.Constante;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class PnlFondo extends javax.swing.JPanel{

  private String imgUrl = "";

  private Image img;
  private int im_alto=0,im_ancho=0;
  private float calc_alto=0,calc_ancho=0;
  private int top=0,left=0;


  public void setFondo (final String varURL){
    final MediaTracker mediaTracker = new MediaTracker(this);
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        // Here, we can safely update the GUI
        // because we'll be called from the
        // event dispatch thread
        if (varURL != null && varURL.length() > 0)
        {
          imgUrl = varURL;

          try {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image1 = toolkit.getImage(imgUrl);
            mediaTracker.addImage(image1, 0);
            mediaTracker.waitForID(0);
            im_alto = image1.getHeight(null);
            im_ancho = image1.getWidth(null);
            setFondoImg(image1, image1.getHeight(null), image1.getWidth(null));
          }
          catch (InterruptedException ex) {
            System.out.println("Descarga interrumpida: " + ex.getMessage());
          }
          catch (Exception ex) {
            System.out.println("Error en la carga de la imagen: " +
                               ex.getMessage());
          }
        }
      }
    });
  }



public void setFondoImg (final Image varImg,final int var_alto,final int var_ancho) {
  SwingUtilities.invokeLater(new Runnable() {
    public void run() {
      // Here, we can safely update the GUI
      // because we'll be called from the
      // event dispatch thread
      try
      {
        img = varImg;
        im_alto = var_alto;
        im_ancho = var_ancho;
        repaint();
      }
      catch(Exception e)
      {
        System.out.println("Error en setfondo");
      }

    }
  });
}

/*
  private void setFondoImg (Image varImg,int var_alto, int var_ancho)
  {
    try
    {
      this.img = varImg;
      this.im_alto = var_alto;
      this.im_ancho = var_ancho;
      //this.reshape(0,0,tamanio.height,tamanio.width);
      repaint();
    }
    catch(Exception e)
    {
      System.out.println("Error en setfondo");
    }
  }
*/
  public PnlFondo()
  {
    setBackground(Color.BLACK);
    setForeground(Color.BLACK);
    //repaint();
  }

  private void relacion()
  {
    Dimension tamanio = getSize();

    float relacion=1;
    if (Constante.isTamAuto())
    {
     if (this.im_ancho <= tamanio.width && this.im_alto <= tamanio.height)
     {
        calc_alto = this.im_alto;
        calc_ancho  = this.im_ancho;
     }
     else
     {
       if (this.im_ancho > 0 && this.im_alto > 0)
       {
          relacion = this.im_alto / (float)this.im_ancho;
          if (relacion > 1)
          {
                  calc_alto=tamanio.height;
                  if (calc_alto / (float)relacion < tamanio.width)
                      calc_ancho = calc_alto / (float)relacion;
                  else
                  {
                      calc_ancho = tamanio.width;
                      calc_alto = calc_ancho * (float)relacion;
                  }
          }
          else
          {
                  calc_ancho=tamanio.width;
                  if (calc_ancho * (float)relacion < tamanio.height )
                      calc_alto = calc_ancho * (float)relacion;
                  else
                  {
                      calc_alto = tamanio.height;
                      calc_ancho = calc_alto / (float)relacion;
                  }
          }
       }
     }
    }
    else
    {
          calc_alto=this.im_alto;
          calc_ancho=this.im_ancho;
    }
  }

  private void centrado()
  {
     Dimension tamanio = getSize();

     this.top=(int)(tamanio.height - this.calc_alto)/2;
     this.left=(int)(tamanio.width - this.calc_ancho)/2;
  }



public void paintComponent(Graphics g)
{
  try
  {
    if (img != null) {
      setOpaque(false);
      this.relacion();
      this.centrado();
      g.setColor(Color.black);
      g.fillRect(0, 0, getSize().width, getSize().height);
      g.drawImage(img, this.left, this.top, (int)this.calc_ancho,(int)this.calc_alto, null);
      super.paintComponent(g);
      g.dispose();
      this.img.flush();
    }
  }
catch(Exception e)
{
  System.out.println("Error en paintComponent");
}


}

}











/*
  public void paintComponent(Graphics g)
  {
        Dimension tamanio = getSize();
        Image imagenFondo = new ImageIcon(imgUrl).getImage();
        g.drawImage(imagenFondo, 0, 0, tamanio.width, tamanio.height, null);
        setOpaque(false);
        super.paintComponent(g);
  }
*/
