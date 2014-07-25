package fuentes.gestor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import fuentes.constantes.Constante;
import fuentes.descarga.*;
import proyectoenlazador.FormEnlazador;
import javax.swing.table.DefaultTableModel;
import fuentes.utiles.DescHref;



/**
 *
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */
public class GestorDescarga extends Thread{

  private static final long serialVersionUID = 1L;
  private boolean stopped = false;
  private boolean isSeleccionEnlaces = false;
  FormEnlazador s_formEnlazador;

  /**
   *
   */
  public void interrupt() {
    this.stopped = true;
  }

  /**
   *
   * @return boolean
   */
  public boolean isStopped() {
    return stopped;
  }

  public GestorDescarga()
  {
    super();
  }

  /**
   *
   * @param var_formEnlazador FormEnlazador
   * @param var_isSeleccionEnlaces boolean
   * @throws HeadlessException
   */
  public GestorDescarga(FormEnlazador var_formEnlazador, boolean var_isSeleccionEnlaces) throws HeadlessException
  {
		super();
                this.s_formEnlazador = var_formEnlazador;
                this.isSeleccionEnlaces = var_isSeleccionEnlaces;
  }

  /**
   *
   */
  public void run()
       {

          try
          {
            this.stopped = false;
            if (!this.isSeleccionEnlaces)
            {
              descargaURL(this.s_formEnlazador.getDireccionText());
            }
            else
            {

              ArrayList TempListaURL = convertirAArrayListDescHREF(this.s_formEnlazador.getModeloTablaURLS());
              System.out.println("Recogemos urls: "+TempListaURL);

              ArrayList TempListaIMG = convertirAArrayList(this.s_formEnlazador.getModeloTablaIMGS());
              System.out.println("Recogemos TempListaIMG: "+TempListaIMG);

              descargaImagenes(TempListaURL, TempListaIMG);
              System.out.println("Descargamos direcciones");
            }
          }
          catch (Exception ex)
          {
            ex.printStackTrace();
            System.out.println("Interrumpimos el thread. Causa: "+ex.getMessage());
            this.interrupt();
          }
       }

       /**
        * Metodo que sirve para descargar la url introducida en la barra de url's
        *
        * @param URL String
        */
       public void descargaURL(String URL)
    {
       //GestorDescarga f = new GestorDescarga(this.s_formEnlazador, this.isSeleccionEnlaces);
       try
       {
         ArrayList arrTemp = new ArrayList();
         ArrayList listaURLHijas = new ArrayList();

           FetchLinks textoHTML = new FetchLinks(URL,this.s_formEnlazador);
           // Al llamar a conectarURL se recoge una lista que contiene a su vez dos listas
           // con las imágenes de la pagina en la que se conecta y cada uno de los enlaces que aparecen en ella.
           arrTemp = (ArrayList)textoHTML.conectarURL();
           this.s_formEnlazador.setTitle(URL);


           this.actualizarTablaImagenes((ArrayList) arrTemp.get(0));
           this.actualizarTablaEnlaces((ArrayList) arrTemp.get(1));

           String string1 = "Sólo imágenes";
           String string2 = "Todo";
           String string3 = "Cancelar";
           Object[] options = {string1, string2, string3};

           int n = JOptionPane.showOptionDialog(this.s_formEnlazador,"¿Deseas descargar todos los enlaces e imágenes?","Total enlaces: "+((ArrayList)arrTemp.get(1)).size()+" - Total imágenes: "+((ArrayList)arrTemp.get(0)).size(),JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null,options, string2);

           if (n == JOptionPane.YES_OPTION)
           {
               this.descargaImagenes(new ArrayList(), (ArrayList)arrTemp.get(0));
               System.out.println("Solo se descargan las imagenes");
           }
           else if (n == JOptionPane.NO_OPTION)
           {
                this.descargaImagenes((ArrayList)arrTemp.get(1), (ArrayList)arrTemp.get(0));
                //System.out.println("La lista de hijas es: "+"("+listaURLHijas.size()+")" +listaURLHijas);
           }
      }
       catch (Exception e)
       {
         System.out.println("Excepcion en el GestorDescarga.descargaURL: "+e.getMessage());
         e.printStackTrace();
         fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
         miTraza.println("Excepcion en el GestorDescarga.descargaURL >>> URL ERROR: "+URL);
         miTraza.println("Excepcion en el GestorDescarga.descargaURL: "+e.getMessage());
       }
    }

    /**
     *
     * @param listaEnlaces ArrayList
     */
    public void actualizarTablaEnlaces(ArrayList listaEnlaces)
    {
      this.s_formEnlazador.getModeloTablaURLS().setColumnCount(0);
      this.s_formEnlazador.getModeloTablaURLS().addColumn("Enlace");
      this.s_formEnlazador.getModeloTablaURLS().addColumn("Descripcion");
      this.s_formEnlazador.getModeloTablaURLS().setRowCount(listaEnlaces.size());

      //Pintamos los enlaces de descarga que aparecen en la lista de enlaces
      for (int i = 0; i < listaEnlaces.size(); i++)
      {
        this.s_formEnlazador.getModeloTablaURLS().setValueAt(((DescHref)listaEnlaces.get(i)).getUrl(), i, 0);
        this.s_formEnlazador.getModeloTablaURLS().setValueAt(((DescHref)listaEnlaces.get(i)).getDesc(), i, 1);
      }
    }

    /**
     *
     * @param listaImagenes ArrayList
     */
    public void actualizarTablaImagenes(ArrayList listaImagenes)
    {
      this.s_formEnlazador.getModeloTablaIMGS().setColumnCount(0);
      this.s_formEnlazador.getModeloTablaIMGS().addColumn("Imágenes");
      this.s_formEnlazador.getModeloTablaIMGS().setRowCount(listaImagenes.size());

      //Pintamos los enlaces de descarga que aparecen en la lista de enlaces
      for (int i = 0; i < listaImagenes.size(); i++)
      {
        this.s_formEnlazador.getModeloTablaIMGS().setValueAt(listaImagenes.get(i), i, 0);
      }
    }

    /**
     * Metodo que sirve para descargar las imágenes y luego las urls de la lista de
     * url's
     *
     * Puede hacerse la búsqueda con 2 niveles de urls
     *
     * @param varArrayEnlaces ArrayList
     * @param varArrayImgs ArrayList
     * @throws Exception
     */
    public void descargaImagenes(ArrayList varArrayEnlaces, ArrayList varArrayImgs) throws java.lang.Exception
    {
      try
      {
        //Empezamos a descargar las imagenes
        ArrayList listaImg = null;
        ArrayList listaUrlNivel2 = null;
        int k=0;
        String referer = this.s_formEnlazador.getDireccionText();
        String urlDescarga = "";

        FetchLinks urlGetter = new FetchLinks();

        //Descargamos las imagenes de la lista de imagenes
        if (varArrayImgs != null && varArrayImgs.size() > 0)
        {
           actualizarTablaImagenes(varArrayImgs);
           this.descargarListaImagenes(varArrayImgs,referer);
        }

        while (k < varArrayEnlaces.size() && !this.isStopped())
        {
          //Nos vamos conectando a las URL's de la lista. De esta conexión obtenemos
          //Las imagenes a descargar en el siguiente paso del bucle
            updateTableSelection(k, this.s_formEnlazador.getTablaURLS());
            Constante.traza("Antes de obtener links: " +(k));
            urlDescarga = ( (DescHref) varArrayEnlaces.get(k)).getUrl();
            Constante.traza("url Descarga: "+urlDescarga);

            this.s_formEnlazador.getstatusBar().setText("Conectando...: " +( (DescHref) varArrayEnlaces.get(k)).getUrl());
            this.s_formEnlazador.setTitle(urlDescarga);

            ArrayList enlacesPagina = (urlGetter.conectarURL(urlDescarga));

            if (enlacesPagina != null)
            {
              Constante.traza("EnlacesPagina: " + enlacesPagina.size());

              listaImg = (ArrayList) enlacesPagina.get(0);
              listaUrlNivel2 = (ArrayList) enlacesPagina.get(1);
              referer = (String) enlacesPagina.get(2);

              //Recogemos los enlaces de nivel2
              if (Constante.getNIVEL_DESCARGA() == 2)
              {
                Constante.traza("Recogemos enlaces de nivel 2");
                for (int cNivel2 = 0; cNivel2 < listaUrlNivel2.size(); cNivel2++)
                {
                  urlDescarga = ( (DescHref) listaUrlNivel2.get(cNivel2)).getUrl();
                  this.s_formEnlazador.getstatusBar().setText("Nivel2_Conectando...: " +urlDescarga);
                  ArrayList listaConexionNivel2 = urlGetter.conectarURL(urlDescarga);
                  ArrayList imgNivel2 = (ArrayList) listaConexionNivel2.get(0);
                  Constante.traza("Añadimos imagenes de nivel 2: " + imgNivel2);

                  for (int cImg2 = 0; cImg2 < imgNivel2.size(); cImg2++)
                  {
                    if (varArrayImgs.indexOf( (String) imgNivel2.get(cImg2)) == -1)
                    {
                      listaImg.add( (String) imgNivel2.get(cImg2));
                    }
                  }
                }
              }
              // Fin de recogida de enlaces de nivel2
              if (listaImg != null && listaImg.size() > 0)
              {
                actualizarTablaImagenes(listaImg);
                this.descargarListaImagenes(listaImg, referer);
              }
            }
          k++;
          Constante.traza("k = " + k);
        } //Fin del while

        //JOptionPane.showMessageDialog(this.s_formEnlazador, "Fin de la descarga");
         this.s_formEnlazador.getstatusBar().setText("DESCARGA FINALIZADA");
         this.s_formEnlazador.setTitle("DESCARGA FINALIZADA");
      }
      catch (Exception ex) {
        System.out.println("Exception en descargaImagenes: "+ex.getMessage());
        ex.printStackTrace();
//        JOptionPane.showMessageDialog(this.s_formEnlazador, "Fin de la descarga");
         this.s_formEnlazador.getstatusBar().setText("DESCARGA FINALIZADA");
         this.s_formEnlazador.setTitle("DESCARGA FINALIZADA");
        ex.printStackTrace();
      }
    }

    /**
     * Dada una lista de imágenes, las descarga en secuencia
     * @param listaImagen ArrayList
     */
    public void descargarListaImagenes(ArrayList listaImagen, String referer) throws Exception
    {
         int i=0;

         // Iniciamos el proceso de descarga de imágenes
         while (i < listaImagen.size() && !this.isStopped())
         {
           Thread t2 = new FetchImagen( (String) listaImagen.get(i), this.s_formEnlazador, (i + 1),listaImagen.size(),referer);
           t2.start();
           this.s_formEnlazador.getstatusBar2().setText(" Imagen " + (i + 1) + " de " + listaImagen.size());

           updateTableSelection(i,this.s_formEnlazador.getTablaIMGS());

           if (Constante.getMAX_THREAD() <= 0)
           {
             t2.join();
           }
           else if ((i+1) % (Constante.getMAX_THREAD()+1) == 0 && i != 0)
           {
               t2.join();
           }
           i++;
         }
    }

    /**
     *
     * @param i int
     * @param tabla JTable
     */
    public void updateTableSelection(final int i,final JTable tabla) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          // Here, we can safely update the GUI
          // because we'll be called from the
          // event dispatch thread
          tabla.changeSelection(i, 0, false, false);
        }
      });
    }

    /**
     *
     * @param Listado JList
     * @return ArrayList
     */
    public ArrayList convertirAArrayList(JList Listado)
    {
      ArrayList lista = new ArrayList();

      try
      {
          ListModel modelo = Listado.getModel();
          for (int i = 0; i < modelo.getSize(); i++)
          {
             lista.add(modelo.getElementAt(i));
          }
      }
      catch (Exception ex) {
        System.out.println("Error en  convertirAArrayList: "+ex.getMessage());
      }
      //System.out.println("devolvemos: "+lista);
      return lista;
    }

    /**
     *
     * @param var_modeloDatos DefaultTableModel
     * @return ArrayList
     */
    public ArrayList convertirAArrayList(DefaultTableModel var_modeloDatos)
    {
      ArrayList lista = new ArrayList();

      try {

        int a=var_modeloDatos.getRowCount();
        for (int i =0; i < a; i++) {
          lista.add((String)var_modeloDatos.getValueAt(i,0));
        }
      }
      catch (Exception ex) {
        System.out.println("Error en  convertirAArrayList: " + ex.getMessage());
      }
      return lista;
    }

    /**
     *
     * @param var_modeloDatos DefaultTableModel
     * @return ArrayList
     */
    public ArrayList convertirAArrayListDescHREF(DefaultTableModel var_modeloDatos)
    {
      ArrayList lista = new ArrayList();

      try {

        int a=var_modeloDatos.getRowCount();
        for (int i =0; i < a; i++) {
          lista.add(new DescHref((String)var_modeloDatos.getValueAt(i,0),""));
        }
      }
      catch (Exception ex) {
        System.out.println("Error en  convertirAArrayList: " + ex.getMessage());
      }
      return lista;
    }


}
