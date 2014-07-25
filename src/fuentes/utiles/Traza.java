package fuentes.utiles;

import fuentes.constantes.Constante;

import java.io.RandomAccessFile;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Date;


public class Traza {

  String salida = "error.log";
  RandomAccessFile rf;

  public void setSalida(String var_x) {
    this.salida = var_x;
  }

  public String getSalida() {
    return salida;
  }

  public Traza() {
  }

  public void clear ()
  {
    try
    {
      PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter(salida)));
      out1.print("");
      out1.close();
    }
    catch (IOException ex)
    {
       System.out.println("Error al vaciar las trazas: " + ex.getMessage());
    }
  }

  public synchronized void  print(String input)
  {
    if (Constante.isTrazas())
    {
      abrir();
      //Pintamos las trazas
      try {
        synchronized (this) {
          rf.writeBytes(new Date() + ":####" + input);
          if (Constante.debug) System.out.println(new Date() + ":####" + input);
        }
      }
      catch (IOException ex) {
        System.out.println("Error al pintar el mensaje: " + ex.getMessage());
      }
      //Cerramos
      cerrar();
    }
  }

  public synchronized void  println(String input)
  {
    try
    {
      print(input + "\n");
      //System.out.println(input);
    }
    catch(Exception e)
    {
      System.out.println("Error al pintar las trazas: "+e.getMessage());
    }
  }


  protected void abrir()
  {
    // 6. Reading/writing random access files
      try {
        rf = new RandomAccessFile(salida, "rw");
        rf.seek(rf.length());
      }
      catch (FileNotFoundException ex) {
        System.out.println("Error al abrir el fichero: "+ex.getMessage());
      }
      catch (IOException ex) {
        System.out.println("Error al abrir el fichero: "+ex.getMessage());
      }
  }

  protected void cerrar()
  {
      try {
        rf.close();
      }
      catch (IOException ex) {
        System.out.println("Error al cerrar el fichero: "+ex.getMessage());
      }
  }


}
