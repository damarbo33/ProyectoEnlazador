package fuentes.utiles;

import java.io.IOException;
import java.io.*;

/**
 *
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */
public class Fichero extends Traza{

  /**
   *
   * @param dir String
   */
  public Fichero(String dir) {
        super();
        this.salida = dir;
        File f = new File(dir);

        if (!f.exists()) {
          try
          {
            f.createNewFile();
          }
          catch (IOException ex)
          {
          }
        }

  }

  /**
   *
   * @param input String
   */
  public synchronized void print(String input)
{
    abrir();
    //Pintamos las trazas
    try {
      synchronized (this) {
        rf.writeBytes(input);
      }
    }
    catch (IOException ex) {
      System.out.println("Error al pintar el mensaje: " + ex.getMessage());
    }
    //Cerramos
    finally
    {
      cerrar();
    }
}

  public synchronized String leer()
  {

    BufferedReader in = null;
    String s, s2 = new String();

    try
    {
      in = new BufferedReader(new FileReader(this.salida));
      while((s = in.readLine())!= null)
        s2 += s + "\n";
    }
    catch (Exception ex) {
      System.out.println("Error al abrir");
    }
    finally
    {
      try
      {
        in.close();
      }
      catch (IOException ex1)
      {
        System.out.println("Error al cerrar");
      }
    }
    return s2;
  }


}
