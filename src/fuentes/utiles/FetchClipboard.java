package fuentes.utiles;

import java.util.ArrayList;
import proyectoenlazador.FormEnlazador;
import fuentes.gestor.GestorDescarga;
import fuentes.utiles.DescHref;



public class FetchClipboard extends Thread
{
  private boolean stopped = false;
  private FormEnlazador formEnlazador;
  private Contenedor contenedor;
  private int tiempoSondeo = 500;
  private Thread t1 = null;
  private GestorDescarga gestor = null;

  public FetchClipboard(FormEnlazador var_formEnlazador, Contenedor var_c) {
    formEnlazador = var_formEnlazador;
    contenedor = var_c;
    gestor = new GestorDescarga(this.formEnlazador, false);
  }

  public void interrupt() {
    this.stopped = true;
  }

  public boolean isStopped() {
    return stopped;
  }

  public void run() {

    try
    {
           this.stopped = false;
           String value = "";
           while (!this.stopped)
           {
             value = contenedor.get();
             gestor.updateTableSelection(0,this.formEnlazador.getTablaURLS());

             if (!"".equals(value))
             {

               this.formEnlazador.setDescargaAbortada(false);
               ArrayList urlDescarga = new ArrayList();
               DescHref classLinks = new DescHref(value,"");
               urlDescarga.add(classLinks);
               gestor.descargaImagenes(urlDescarga, null);
               this.formEnlazador.eliminarListaArchivosTabla1(0);
               this.formEnlazador.eliminarTablaImgs();
             }

             if (this.formEnlazador.isDescargaAbortada())
             {
               contenedor.clear();
             }
            Thread.sleep(tiempoSondeo);
           }

    }
    catch(Exception e)
    {
      System.out.println("ERROR: "+e.getMessage());
    }



  }
}
