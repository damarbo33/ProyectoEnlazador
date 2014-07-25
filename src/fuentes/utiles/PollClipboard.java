package fuentes.utiles;

import java.awt.datatransfer.*;
import java.util.ArrayList;
import proyectoenlazador.FormEnlazador;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import fuentes.constantes.Constante;
import fuentes.descarga.FetchLinks;




public class PollClipboard extends Thread
{
  private boolean stopped = false;
  private ArrayList urlsEncontradas = new ArrayList();
  private ArrayList urlsEncontradasSinRep = new ArrayList();
  private FormEnlazador formEnlazador;
  private Contenedor contenedor;
  int tiempoSondeo = 500;


  public PollClipboard(FormEnlazador var_formEnlazador, Contenedor var_contenedor) {
    formEnlazador=var_formEnlazador;
    contenedor = var_contenedor;
  }

  public void interrupt() {
  this.stopped = true;
}

public boolean isStopped() {
  return stopped;
}


public void run()
{

          try
          {
            FetchLinks objLinks = new FetchLinks();
            this.stopped = false;
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable transferable = null;
            while (!this.stopped)
            {
               Thread.sleep(tiempoSondeo);

              transferable = clipboard.getContents(null);
              if (transferable.isDataFlavorSupported(DataFlavor.stringFlavor))
              {

                try
                {

                  String texto = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                  String datos[] = texto.split("\n");
                  ArrayList lista = new ArrayList();
                  String lineaDatos = "";
                  String separacion = "";
                  int inihttp = 0;
                  int finhttp = 0;
                  //System.out.println("hay "+datos.length +" filas");
                  for (int i = 0; i < datos.length; i++)
                  {
                    lineaDatos = datos[i].trim();
                    if (lineaDatos != null)

                    while ((inihttp = lineaDatos.indexOf("http://")) != -1)
                    {
                      //System.out.println("Analizamos: "+lineaDatos + " "+lineaDatos.indexOf(" "));
                      if (lineaDatos.substring(inihttp).indexOf("\"") != -1 || lineaDatos.substring(inihttp).indexOf("'") != -1)
                      {
                        lineaDatos = lineaDatos.replaceAll("['\"]", " ");
                      }



                      if ((lineaDatos.substring(inihttp).indexOf(" ") )== -1 || (lineaDatos.substring(inihttp).indexOf(" ") )== 0)
                      {
                        finhttp = lineaDatos.length();
                      }
                      else
                      {
                          finhttp = lineaDatos.substring(inihttp).indexOf(" ") + inihttp;
                      }

                        /*System.out.println(inihttp + " "+finhttp);
                        System.out.println(lineaDatos);*/
                        separacion = lineaDatos.substring(inihttp,finhttp);
                        //System.out.println("Encontramos: "+separacion);
                        lista.add(separacion);
                        //System.out.println("Añadimos: "+separacion);
                        lineaDatos = lineaDatos.substring(finhttp,lineaDatos.length()).trim();
                        //System.out.println("Dejamos: "+lineaDatos);
                    }
                  }

                  boolean actualizar_lista = false;
                  //En lista tenemos la lista de urls recogidas del portapapeles
                  for (int i=0;i<lista.size();i++)
                  {
                    if ( (lista.size() == urlsEncontradas.size() ) && urlsEncontradas.size() > 0)
                    {
                      if ( !( (String) lista.get(i)).equals((String)urlsEncontradas.get(i)))
                      {
                        actualizar_lista = true;
                      }
                    }
                    else if (urlsEncontradas.size() == 0 || lista.size() != urlsEncontradas.size())
                    {
                      actualizar_lista = true;
                    }
                  }

                  if (actualizar_lista)
                  {
                    //System.out.println("DEBEMOS ACTUALIZAR");
                    urlsEncontradas.clear();
                    urlsEncontradasSinRep.clear();

                    for (int i=0;i<lista.size();i++)
                    {
                      //System.out.println("Encontramos: "+lista.get(i));
                      urlsEncontradas.add(lista.get(i));
                      if (urlsEncontradasSinRep.indexOf(lista.get(i)) == -1)
                      {
                        try
                        {
                          new URL((String)lista.get(i));
                          urlsEncontradasSinRep.add(lista.get(i));
                        }
                        catch (Exception e){
                          System.out.println("URL incorrecta: " + lista.get(i));
                        }

                      }
                    }


                    //Una vez se ha generado la lista con todas las urls, decidimos si debemos Descargar

                    if (urlsEncontradasSinRep.size() == 1)
                    {

                      this.formEnlazador.DireccionText.setText(FetchLinks.eliminarRedireccionURL((String)urlsEncontradas.get(0)));
                      if (Constante.debug) System.out.println("Productor. put: " + (String)urlsEncontradas.get(0));
                      if (Constante.isDescargaURLAutomatica())
                      {
                          if (Constante.debug) System.out.println("Lanzamos la descarga automatica");
                          this.formEnlazador.insertarPortaPapelesTabla1();
                          contenedor.put( FetchLinks.eliminarRedireccionURL((String) urlsEncontradas.get(0)));
                      }
                    }
                    else if (urlsEncontradasSinRep.size() > 1)
                    {

                      if (Constante.isDescargaURLAutomatica())
                      {
                        for (int i = 0; i < urlsEncontradasSinRep.size(); i++) {
                          Object[] dat = {urlsEncontradasSinRep.get(i)};
                          this.formEnlazador.getModeloTablaURLS().addRow(dat);
                          contenedor.put( FetchLinks.eliminarRedireccionURL((String) urlsEncontradasSinRep.get(i)));
                        }


                      }
                      else
                      {
                        String string1 = "Lista urls";
                        String string2 = "Lista imágenes";
                        String string3 = "Cancelar";
                        Object[] options = {
                            string1, string2, string3};

                        int n = JOptionPane.showOptionDialog(this.formEnlazador,
                            "¿Deseas añadir a la lista de urls o a la de imágenes?",
                            "Se han encontrado " + urlsEncontradasSinRep.size() +
                            " URL's",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, options, string2);

                        if (n == JOptionPane.YES_OPTION) {
                          for (int i = 0; i < urlsEncontradasSinRep.size(); i++) {
                            Object[] dat = {
                                urlsEncontradasSinRep.get(i)};
                            this.formEnlazador.getModeloTablaURLS().addRow(dat);
                          }
                        }
                        else if (n == JOptionPane.NO_OPTION) {
                          for (int i = 0; i < urlsEncontradasSinRep.size(); i++) {
                            Object[] dat = {
                                urlsEncontradasSinRep.get(i)};
                            this.formEnlazador.getModeloTablaIMGS().addRow(dat);
                          }
                        }
                      }
                    }
                  }
                }
                catch (Exception exception)
                {
                  System.out.println(exception.getMessage());
                }
              }
            }
          }
          catch (Exception ex)
          {
            ex.printStackTrace();
            System.out.println("Interrumpimos el thread. Causa: "+ex.getMessage());
            //this.interrupt();
          }
       }


}
