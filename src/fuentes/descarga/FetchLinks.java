package fuentes.descarga;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;



import java.io.*;
import java.net.*;
import java.util.*;
import fuentes.constantes.*;
import proyectoenlazador.FormEnlazador;
import fuentes.utiles.DescHref;
import fuentes.utiles.XmlUtil;

/**
 * @author dmarcobo
 *
 */
public class FetchLinks{

  private String s_url = null;
  private String listaDominios = ".com,.net,.org,.info,.biz,.edu,.es,.tk,.tv";
  //private String listaImagenes = ".jpg,.jpeg,.gif,.bmp";
  private String listaImagenes = ".jpg,.jpeg,.gif,.bmp" + "," +Constante.getImgDescarga() ;
  private String s_dominio = "";
  private String urlRelativa = "";
  private static int reconexiones = 0;
  FormEnlazador s_formEnlazador;
  private XmlUtil xmlUtil;

  public String getUrlRelativa() {
    return urlRelativa;
  }

  public String getDominio() {
    return s_dominio;
  }


  public FetchLinks() {
      super();
  }


  /**
   *
   * @param url String
   */
  public FetchLinks(String url) {
    super();
    this.s_url = url;
    this.asignarDominioRelatividad(url);
  }


  public FetchLinks(String url, FormEnlazador var_formEnlazador) {
    super();
    this.s_url = url;
    this.asignarDominioRelatividad(url);
    this.s_formEnlazador = var_formEnlazador;
  }

  /**
   *
   * @param url String
   * @return java.util.ArrayList
   * @throws Exception
   */
  public ArrayList conectarURL (String url) throws Exception
  {
    this.s_url = url;
    this.asignarDominioRelatividad(url);
    return conectarURL();
  }

  public void mensajeBarraPrincipal(String mensaje)
  {
    if (this.s_formEnlazador!=null)
        this.s_formEnlazador.getstatusBar().setText(mensaje);
  }



  public void asignarDominioRelatividad(String var_url)
  {
    this.s_dominio = obtenDominio(var_url);
    this.urlRelativa = comprobarRelatividad(var_url);
  }



  /**
   *
   * @return java.util.ArrayList
   * @throws Exception
   */
  public ArrayList conectarURL ()
  {
    XmlUtil xmlUtil = new XmlUtil();
    xmlUtil.cargarXML("C:\\naruto.xml");
    xmlUtil.obtenerValores();


    ArrayList listaIMGS = new ArrayList();
    ArrayList listaENLACES = new ArrayList();

    ArrayList listaENLACES_site = new ArrayList();
    ArrayList listaENLACES_nosite = new ArrayList();




    ArrayList listaSALIDA = new ArrayList();
    HttpURLConnection connection = null;
    String location = this.s_url;

    try
    {
      if (this.s_url != null && !"".equals(this.s_url))
      {
        URL url = new URL(this.s_url);
        if (Constante.debug) System.out.println("Vamos a abrir la conexion a: " + this.s_url);
        mensajeBarraPrincipal("Conectando a: "+this.s_url);
        connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","*/*");
        connection.setRequestProperty("Accept-Language","es-us");
        connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
        connection.setRequestProperty("Connection", "Keep-Alive");
        //connection.setRequestProperty("Keep-Alive", "timeout=" + "3");

        mensajeBarraPrincipal("Conexión establecida");

        //Al obtener la conexion podemos recoger el campo Location que nos indica si
        //hay alguna redireccion a otra pagina. Siempre se abre una nueva conexion a esa nueva url.

        if (Constante.debug) System.out.println("Recogemos el Location");
        connection.getHeaderField("Location");
        if (Constante.debug) System.out.println("Location recogido");

        location = connection.getURL().toString();
        //Nos conectamos una segunda vez para seguir las redirecciones si el location es distinto
        if (!this.s_url.equals(location))
        {
          location = formatearURL(location);
          if (Constante.debug) System.out.println("Location Distinto: " + location);
          mensajeBarraPrincipal("Cambiando location: "+location);
          url = new URL(location);
          if (Constante.debug) System.out.println("Conexion");
          String location_sinParms = eliminarParametros(location);
          //this.s_dominio = location_sinParms;
          this.asignarDominioRelatividad(location_sinParms);
          if (Constante.debug) System.out.println("antes de conectar");
          connection = (HttpURLConnection) url.openConnection();
          if (Constante.debug) System.out.println("despues de conectar");
        }

        if (Constante.debug) System.out.println("Recogido");

        //BufferedReader d = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        InputStream html = connection.getInputStream();
        HtmlCleaner cleaner = new HtmlCleaner();
        //Se añaden las imagenes de las etiquetas img
        TagNode nodeHtml = cleaner.clean(html);
        if (Constante.debug) System.out.println("Desconectamos");
        connection.disconnect();


        if (Constante.debug) System.out.println("Obtenemos el contenido");

//        String s = new String();

        String imagenPagina = "";
        String enlacePagina = "";
        boolean enlaceSite = false;
        boolean isSoloDominio = false;  //Indica si la URL es solo un dominio. No apunta a una página en concreto
        String temp = "";
        ArrayList arrImagenPagina = new ArrayList();
        ArrayList arrEnlaces = new ArrayList();
        //ArrayList arrFrames = new ArrayList();
        ArrayList arrCustomEnlaces;

        mensajeBarraPrincipal("Obteniendo el contenido de la página...");

        if (Constante.debug) System.out.println("Analizamos pagina obtenida");

        if ( (arrImagenPagina = analizarImagenesHTML(nodeHtml)).size() > 0 )
        {
          for (int i=0;i<arrImagenPagina.size();i++)
          {
            imagenPagina = (String)arrImagenPagina.get(i);
            temp = imagenPagina.toLowerCase();

            //Solo se descargarán imágenes que se definan en este condicional y se recogen de la clase Constante
            if (listaIMGS.indexOf(imagenPagina) == -1 && isImagen(imagenPagina.toLowerCase(),Constante.getImgDescarga()))
            {
                listaIMGS.add(imagenPagina);
            }
          }
        }

//              Buscamos todos los enlaces de la pagina
        if ( ( arrEnlaces = ((analizarEnlacesHTML(nodeHtml))) ).size() > 0 )
        {
          if (Constante.debug) System.out.println("arrEnlaces = "+arrEnlaces);

          for (int i=0;i<arrEnlaces.size();i++)
          {
            if (Constante.debug) System.out.println("Antes del lower");
            enlacePagina=formatearURL(((DescHref)arrEnlaces.get(i)).getUrl());


          if (Constante.debug) System.out.println("Despues del Dominio: "+enlacePagina);

          //Miramos si se hace referencia solo al dominio.
          isSoloDominio = isFinDominio(enlacePagina);

          if (enlacePagina.indexOf(this.s_dominio) != -1)
          {
            enlaceSite = true;
          }
          else
          {
            enlaceSite = false;
          }



          if ( !isFiltro(enlacePagina)) //No quiero añadir enlaces que coincidan con el filtro de palabras prohibidas
          if ( !isImagen(enlacePagina.toLowerCase(),listaImagenes)) //No quiero que aparezcan imagenes
          if ( !(isSoloDominio && !Constante.isOtrosDominios())) //Si no quiero que aparezcan dominios principales
          if ( enlaceSite || Constante.isOtrosSites()) //Si no quiero que aparezcan otros sites
          {

            if (Constante.debug) System.out.println("despues del filtro: "+enlacePagina);
              if (enlaceSite)
              {
                if ( listaENLACES_site.indexOf(enlacePagina) == -1){
                  //listaENLACES_site.add(enlacePagina);
                  listaENLACES_site.add(new DescHref(enlacePagina,((DescHref)arrEnlaces.get(i)).getDesc()));
                }

              }
              else
              {
                  if ( listaENLACES_nosite.indexOf(enlacePagina) == -1){
                    //listaENLACES_nosite.add(enlacePagina);
                    listaENLACES_nosite.add(new DescHref(enlacePagina,((DescHref)arrEnlaces.get(i)).getDesc()));
                  }
              }
            }
          } //Fin for (arrEnlaces)
       } //Fin if arrEnlaces

       if ( ( arrCustomEnlaces = ((analizarCustomHTML(nodeHtml))) ).size() > 0 )
       {
           String customUrl;
           for (int i=0;i < arrCustomEnlaces.size();i++)
           {
               //customUrl = ((DescHref) arrCustomEnlaces.get(i)).getUrl();
               //Anyadimos los enlaces encontrados a la lista de enlaces del site.
               //if ( listaENLACES_site.indexOf(enlacePagina) == -1){
                 //listaENLACES_site.add(enlacePagina);
                 if ( ((DescHref)arrCustomEnlaces.get(i)).getUrl().indexOf("html") > -1
                      || ((DescHref)arrCustomEnlaces.get(i)).getUrl().indexOf("htm") > -1)
                 {
                   listaENLACES_site.add(new DescHref( ( (DescHref) arrCustomEnlaces.get(i)).getUrl(),( (DescHref) arrCustomEnlaces.get(i)).getDesc()));
                 }
               //}
           }

       }

/*
            if ((arrEnlaces = analizarIFRAMEHTML(s.trim())).size() > 0)
            {
                String LocationActual = location;
                ArrayList arrTempFramesSalidaImgs = new ArrayList();
                ArrayList arrTempFramesSalidaUrls = new ArrayList();

                for (int i=0;i<arrEnlaces.size();i++)
                {
                  arrFrames = this.conectarURL( (String) arrEnlaces.get(i));
                  arrTempFramesSalidaImgs = (ArrayList)arrFrames.get(0);
                  arrTempFramesSalidaUrls = (ArrayList)arrFrames.get(1);

                  for (int j=0;j<arrTempFramesSalidaImgs.size();j++)
                  {
                    listaIMGS.add((DescHref)arrTempFramesSalidaUrls.get(j));
                  }

                  for (int j=0;j<arrTempFramesSalidaUrls.size();j++)
                  {
                    if (Constante.debug) System.out.println("arrFrame: "+((DescHref)arrTempFramesSalidaUrls.get(j)).getUrl());
                    listaENLACES_site.add((DescHref)arrTempFramesSalidaUrls.get(j));
                  }
                  arrFrames.clear();
                }

                location = LocationActual;
            }
*/

        //}//Fin del if(s!=null)










        //Ordenamos las listas
        Collections.sort(listaENLACES_site);
        Collections.sort(listaENLACES_nosite);

        //Añadimos las listas con los enlaces ordenados
        //Primero van los enlaces del site seguidas de las restantes
        for (int i=0; i < listaENLACES_site.size(); i++){
          if (listaENLACES.indexOf(listaENLACES_site.get(i)) == -1){
            listaENLACES.add(listaENLACES_site.get(i));
          }
        }

        for (int i=0; i < listaENLACES_nosite.size(); i++){
          if (listaENLACES.indexOf(listaENLACES_nosite.get(i)) == -1){
            listaENLACES.add(listaENLACES_nosite.get(i));
          }
        }
      } // Fin if this.s_url
    }
    catch(ConnectException e)
    {
      if (Constante.debug) System.out.println("TimeOut en FetchLinks "+ "("+reconexiones+")" +": " +e.getMessage());
      if (reconexiones < Constante.getMAX_TIME_OUT())
      {
        reconexiones++;
        connection.disconnect();
        conectarURL();
      }
    }
    catch (java.io.IOException e)
    {
      mensajeBarraPrincipal("Fallo en la conexión");
      if (Constante.debug) System.out.println("URL NO ENCONTRADA: FetchLinks >>> URL ERROR: "+this.s_url +" :"+ e.getMessage());
      connection.disconnect();

      /*
      //Normalmente esto pasa cuando hay puntos en la URL que indican que se ha de volver a un directorio anterior
      this.s_url =  eliminarDirAnt(this.s_url);
      //Volvemos a conectarnos con la url nueva
      fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
      miTraza.println("URL NO ENCONTRADA: FetchLinks >>> Volvemos a conectarnos: "+this.s_url);

      ArrayList listaRecursiva = new ArrayList();

      if (this.s_url != null)
      {
        listaRecursiva = conectarURL();
        listaIMGS = (ArrayList)listaRecursiva.get(0);
        listaENLACES = (ArrayList)listaRecursiva.get(1);
      }
*/

    }
    catch (Exception e)
    {
      mensajeBarraPrincipal("Fallo en la conexión");
      if (Constante.debug) System.out.println("Excepcion en FetchLinks.conectarURL "+e.getMessage());
      e.printStackTrace();
      connection.disconnect();
      fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
      miTraza.println("Excepcion en el FetchLinks.conectarURL >>> URL ERROR: "+this.s_url+" >>>LOCATION: "+location);
      return null;
    }

//    finally
//    {

      if (Constante.debug)
      {
        if (Constante.debug) System.out.println("Las " + listaIMGS.size() + " imagenes son: " + listaIMGS.toString());
        System.out.println("Los " + listaENLACES.size() + " enlaces son: " + listaENLACES.toString());
      }

      listaSALIDA.add(listaIMGS); //Añadimos la lista de imágenes
      listaSALIDA.add(listaENLACES); //Añadimos la lista de enlaces
      listaSALIDA.add(location); //Añadimos la url desde la que nos hemos conectado. En location estará hasta la redireccion
      mensajeBarraPrincipal("Url analizada con éxito");
      return listaSALIDA;
//    }
  }


  /**
   *
   * @param s_HTML String
   * @throws Exception
   * @return ArrayList
   */
  public ArrayList analizarIFRAMEHTML (final String s_HTML) throws Exception
  {
    final ArrayList arrSalida = new ArrayList();

    int corte_ini=0,corte_fin=0;
    String frameSRC = "";
    String salida = "";
    String linea = "";
    String str_url = this.s_url;


    String temp = s_HTML;

    temp = s_HTML.toLowerCase();


    try
    {

      if (s_HTML != null && s_HTML.length() > 0) {

        while (corte_fin != -1 &&
               (corte_ini = temp.indexOf("frame", corte_fin)) != -1) {

          corte_fin = s_HTML.indexOf(">", corte_ini);

          if (corte_fin != -1) {
            if (Constante.debug) System.out.println(corte_ini + "," + corte_fin);
            linea = s_HTML.substring(corte_ini, corte_fin);

            frameSRC = buscarPropiedad(linea, "src");

            if (!"".equals(frameSRC))
            {
              if (frameSRC.indexOf("http:") == -1) {
                //str_url = comprobarRelatividad(this.s_dominio);

                str_url = this.s_dominio;

                //Si la imagen tiene una barra, se le debe concatenar el dominio
                if (frameSRC.length() > 0 && frameSRC.trim().charAt(0) == '/') {
                  str_url = this.obtenDominio(this.s_dominio);
                }

                if (str_url.length() > 0 &&
                    ('/' == str_url.charAt(str_url.length() - 1) &&
                     (frameSRC.length() > 0 && '/' == frameSRC.charAt(0)))) {
                  frameSRC = frameSRC.substring(1, frameSRC.length());
                }

                if (str_url.length() > 0 &&
                    ('/' == str_url.charAt(str_url.length() - 1) ||
                     (frameSRC.length() > 0 && '/' == frameSRC.charAt(0))))
                  salida = str_url + frameSRC;
                else
                  salida = str_url + "/" + frameSRC;
              }
              else {
                salida = frameSRC;
              }

              if (!isFiltro(salida)) {

                if (Constante.debug) System.out.println("URL de salida: " +
                    salida);
                //salida = URLEncoder.encode(salida);
                //if (Constante.debug) System.out.println("urlEncode: "+salida);
                arrSalida.add(salida);
              }
            }

            frameSRC = "";
          }
        } //Fin while
      }
    }
    catch (final Exception e) {
      System.out.println("Error en analizarImagenesHTML: " + e.getMessage());
      e.printStackTrace();
    }

    return arrSalida;
  }

  /**
   *
   * @param s_HTML TagNode
   * @return boolean
   */
  public boolean buscarBaseHref(TagNode s_HTML)
  {
    boolean encontrado = false;
    try
    {
      ArrayList salida = new ArrayList();
      String str_url = this.s_url;
      int i_alto = -1, i_ancho = -1;


      String tempURL = "";

      //Se añaden las imagenes de las etiquetas img
      List listaNodos;

      listaNodos = s_HTML.getElementListByName("base", true);

      TagNode nodo;
      String base = "";

      if (listaNodos.size()>0)
      {
        nodo = (TagNode) listaNodos.get(0);
        base = nodo.getAttributeByName("href");
        if (base != null && !"".equals(base))
        {
          this.urlRelativa = base;
          this.s_dominio = obtenDominio(base);
          if (Constante.debug) System.out.println("Se ha encontrado la base");
          encontrado = true;
        }
      }
    }
    catch(Exception e)
    {
      System.out.println("Error al buscar la base Href");
    }
    return encontrado;
  }

  /**
   *
   * @param s_HTMLNode TagNode
   * @throws Exception
   * @return ArrayList
   */
  public ArrayList analizarCustomHTML(TagNode s_HTMLNode) throws Exception
 {
    ArrayList salida = new ArrayList();
    String str_url;
    String uriRelleno = "naruto/";

    //Se añaden las imagenes de las etiquetas img
    List listaNodosTag;
    listaNodosTag = s_HTMLNode.getElementListByName("option", true);
    String attr;
    TagNode nodoAttr;

    for (int i=0;i<listaNodosTag.size();i++)
    {
      nodoAttr = (TagNode) listaNodosTag.get(i);
      attr = nodoAttr.getAttributeByName("value");
      if (attr != null) attr = attr.trim();
      //Si la imagen empieza por barra, se le debe concatenar el dominio
      if (attr.length() > 0 && attr.charAt(0) == '/')
      {
          str_url = this.s_dominio + attr;
      }
      else
      {
          str_url = this.urlRelativa + uriRelleno + attr;
      }

      salida.add(new DescHref(str_url, nodoAttr.getText().toString()));
    }
    return salida;
 }


 public ArrayList analizarImagenesHTML (TagNode s_HTMLNode) throws Exception
 {
   ArrayList salida = new ArrayList();
   String str_url = this.s_url;
   int i_alto=-1,i_ancho=-1;
   String tempURL = "";


   //Se añaden las imagenes de las etiquetas img
   List listaNodosImg;
   listaNodosImg = s_HTMLNode.getElementListByName("img", true);

   TagNode nodoImg;
   String imagen = "";
   String width = "";
   String height = "";

   for (int i=0;i<listaNodosImg.size();i++)
   {
     nodoImg = (TagNode) listaNodosImg.get(i);
     imagen = nodoImg.getAttributeByName("src");
     width = nodoImg.getAttributeByName("width");
     height = nodoImg.getAttributeByName("height");

     if (Constante.debug) System.out.println("imagen: "+imagen);
     if (Constante.debug) System.out.println("width: "+width);
     if (Constante.debug) System.out.println("height: "+height);

     try
     {
       i_alto = Integer.parseInt(height);
       i_ancho = Integer.parseInt(width);
     }
     catch(final NumberFormatException e)
     {
       i_alto = -1;
       i_ancho = -1;
     }


     if (imagen.toLowerCase().indexOf("http:") == -1)
     {
       str_url = this.getUrlRelativa();
       if (buscarBaseHref(s_HTMLNode))
       {
         //Si la imagen empieza por barra, se le debe concatenar el dominio
         if (imagen.length() > 0 && imagen.trim().charAt(0) == '/')
         {
             str_url = this.getDominio();
         }
       }

       //Si las dos partes terminan y empiezan por / habra que quitarsela a
       //imagen
       if (str_url.length() > 0 && ('/' == str_url.charAt(str_url.length() - 1)
           && (imagen.length() > 0 && '/' == imagen.charAt(0))))
       {
         imagen = imagen.substring(1, imagen.length());
       }

       if (str_url.length() > 0 &&
           ('/' == str_url.charAt(str_url.length() - 1) ||
            (imagen.length() > 0 && '/' == imagen.charAt(0))))
         tempURL = str_url + imagen;
       else
         tempURL = str_url + "/" + imagen;
     }
     else
     {
       tempURL = imagen;
       tempURL = eliminarRedireccionURL(tempURL);
     }

     if (!isFiltro(tempURL) && ((i_alto > Constante.getMIN_HEIGHT() && i_ancho > Constante.getMIN_WIDTH()) || (i_alto == -1 || i_ancho == -1)) )
     {
       //System.out.println("Imagen de salida: " +tempURL);
       if (salida.indexOf(tempURL)==-1)
       {
         salida.add(tempURL);
       }

     }

   }
   //SE AÑADEN LAS IMAGENES DEL HREF

   ArrayList listaEnlacesIMG = analizarEnlacesHTML(s_HTMLNode);



   for (int i=0;i<listaEnlacesIMG.size();i++)
   {
     imagen = ((DescHref)listaEnlacesIMG.get(i)).getUrl();

     if (Constante.debug) System.out.println("imagen: "+imagen);
     //De internet solo me interesan las imagenes .jpg
     if (isImagen(imagen.toLowerCase(),this.listaImagenes))
     {
       if (salida.indexOf(imagen)==-1)
         salida.add(imagen);
     }
   }



   return salida;
 }

   public ArrayList analizarEnlacesHTML (TagNode s_HTMLNode) throws Exception
   {

    ArrayList salida = new ArrayList();
    String str_url = this.s_url;
    ArrayList duplicados = new ArrayList();
    String tempURL = "";

   buscarBaseHref(s_HTMLNode);
   //Se añaden las imagenes de las etiquetas img
   List listaNodosHref;
   listaNodosHref = s_HTMLNode.getElementListByName("a", true);

   TagNode nodoHref;

   for (int i=0;i<listaNodosHref.size();i++)
   {
      nodoHref = (TagNode) listaNodosHref.get(i);
      String href = nodoHref.getAttributeByName("href");
      StringBuffer texto = nodoHref.getText();


      if (Constante.debug) System.out.println("href: "+href);
      if (Constante.debug) System.out.println("texto: "+texto);

        //URL baseURL = new URL(this.s_url);
        URL baseURL=null;
        URL relativaURL = null;
        try
        {
          baseURL = new URL(this.getUrlRelativa());
          relativaURL = new URL(baseURL, href);
        }catch(Exception e){};

        if (relativaURL != null)
        {
          if (Constante.debug) System.out.println("File: " + relativaURL.toString());
          tempURL = relativaURL.toString();
          tempURL = eliminarRedireccionURL(tempURL);

          tempURL = formatearURL(tempURL);
          if (duplicados.indexOf(tempURL) == -1)
          {
            if (Constante.debug) System.out.println("URL Creada: " + tempURL);
            salida.add(new DescHref(tempURL, texto.toString()));
            duplicados.add(tempURL);
          }
        }

   }


    return salida;
  }
  /**
   * private static String comprobarRelatividad(String s_HTML) throws Exception
   * Si la URL termina con .jsp, .html, etc. cortamos la url para obtener el
   * dominio en el que se encuentra esta url
   * Entrada: String - http://www.dominio.com/site1/index.html
   * Salida: String -  http://www.dominio.com/site1
   *
   * @param s_HTML String
   * @throws Exception
   * @return String
   */
  private static String comprobarRelatividad(String s_HTML)
  {
    try
    {
      if (s_HTML != null) {
        s_HTML = eliminarParametros(s_HTML);
        String temp = s_HTML.toLowerCase();
        int indice_ini = 0;
        int indice_fin = 0;

        ArrayList extensiones = new ArrayList();
        extensiones.add(".jsp");
        extensiones.add(".htm");
        extensiones.add(".php");
        extensiones.add(".shtm");

        String urlBase = s_HTML;
        if (Constante.debug) System.out.println(
            "comprobarRelatividad Cortado: " + urlBase);
        int lastBar = urlBase.trim().lastIndexOf("/");
        if (lastBar != -1) {
          String findir = urlBase.substring(lastBar, urlBase.length());
          if (findir.length() > 0 && findir.indexOf(".") == -1) {
            if (Constante.debug) System.out.println(
                "comprobarRelatividad Cortado: " +
                urlBase.substring(0, lastBar + 1));
            urlBase = urlBase.substring(0, lastBar + 1);
             if (Constante.debug) System.out.println("Relatividad: urlBase: "+urlBase);
            return urlBase;
          }
        }


        for (int i = 0; i < extensiones.size(); i++) {
          if ( (indice_ini = temp.lastIndexOf( (String) extensiones.get(i))) !=
              -1) {
            if ( (indice_fin = temp.lastIndexOf("/", indice_ini)) != -1) {
              s_HTML = s_HTML.substring(0, indice_fin+1);
            }
          }
        }
      }
      if (Constante.debug) System.out.println("Relatividad: "+s_HTML);

    }
    catch(Exception e)
    {
      System.out.println("Error en: comprobarRelatividad: "+e.getMessage());
    }

    return s_HTML;
  }

  /**
   * private static String buscarPropiedad(String s_HTML, String tag_HTML)
   * throws Exception Busca en el parametro s_HTML el valor de la propiedad que
   * aparezca en la variable tag_HTML y devuelve dicho valor.
   *
   * @param s_HTML String
   * @param tag_HTML String
   * @throws Exception
   * @return String
   */
/*  private static String buscarPropiedad(String s_HTML, String tag_HTML) throws Exception
  {
    String dato="";
    String temp="";

    try
    {
      StringTokenizer strPipe = new StringTokenizer(s_HTML, " ");

      String s_dato="";
      String s_valor="";

      while (strPipe.hasMoreTokens())
      {
            temp = (String)strPipe.nextToken();

            System.out.println("######## Token: "+temp);

            if ( temp.indexOf("=") != -1)
            {
              s_dato = temp.substring(0, temp.indexOf("="));
              s_valor = temp.substring(temp.indexOf("=")+1, temp.length());
            }

            s_dato = s_dato.toLowerCase().trim();
            tag_HTML = tag_HTML.toLowerCase().trim();

            if (s_dato.equals(tag_HTML))
            {
              dato = quitarComillas(s_valor);
            }
          }
    }
    catch(Exception e)
    {
      System.out.println("Error en buscarPropiedad: "+e.getMessage());
      e.printStackTrace();
    }
    if (dato == null) dato = "";
    return dato;
  }
*/

private static String buscarPropiedad(String s_HTML, String tag_HTML)
{
  String dato="";

  String lowerHtml = s_HTML.toLowerCase();

  try
  {
    if (Constante.debug) System.out.println("######## linea: "+s_HTML);
    //String strPipe[] = s_HTML.split(tag_HTML);
    String strPipe2[];

    String s_valor="";

    ArrayList listaPipe = new ArrayList();
    int inicio = 0;
    while ((inicio = lowerHtml.lastIndexOf(tag_HTML.toLowerCase())) > -1)
    {
      listaPipe.add(s_HTML.substring(inicio,s_HTML.length()));
      lowerHtml = lowerHtml.substring(0,inicio);
    }

    String texto = "";
    //if (strPipe.length > 1)
    for (int i = 0; i < listaPipe.size(); i++)
    {
            texto = (String)listaPipe.get(i);
            if (Constante.debug) System.out.println("corte: "+listaPipe.get(i));
            if ( texto.indexOf("=") > -1)
            {
                    s_valor = texto.substring(texto.indexOf("=")+1,texto.length()).trim();
                    if (Constante.debug) System.out.println("s_valor: "+s_valor);
                    //No se para que estaban estas lineas pero no hacen falta
                    strPipe2 = s_valor.split(" ");
                    dato = strPipe2[0];
                    dato = quitarComillas(dato);
                    if (Constante.debug) System.out.println("Quitar comillas: "+dato);
                    //dato = URLEncoder.encode(dato);
                    //if (Constante.debug) System.out.println("URLEncoder: "+dato);
            }
    }
  }
  catch(Exception e)
  {
    System.out.println("Error en buscarPropiedad: "+e.getMessage());
    e.printStackTrace();
  }
  if (dato == null) dato = "";
  {
    if (Constante.debug) System.out.println("Devolvemos: "+dato);
    return dato;

  }
}


  /**
   * private static String quitarComillas(String s_HTML) throws Exception
   * Elimina comillas simples y dobles de una url
   *
   * @param s_HTML String
   * @throws Exception
   * @return String
   */
  private static String quitarComillas(String s_HTML) throws Exception
  {
//    try
//    {
      if (s_HTML != null && s_HTML.length() > 0)
      {
/*        if (s_HTML.charAt(0) == '\'')
        {
          s_HTML = s_HTML.substring(1,s_HTML.length());
          if (s_HTML.length() > 0 && s_HTML.charAt(s_HTML.length() - 1) == '\'')
          {
            s_HTML = s_HTML.substring(0,s_HTML.length()-1);
          }
        }
        else if (s_HTML.charAt(0) == '"')
        {
          s_HTML = s_HTML.substring(1,s_HTML.length());
          if (s_HTML.length() > 0 && s_HTML.charAt(s_HTML.length()-1) == '"')
          {
            s_HTML = s_HTML.substring(0,s_HTML.length()-1);
          }
        }
*/
        s_HTML = s_HTML.replaceAll("['\"]", "");
      }
/*    }
    catch(Exception e)
    {
      System.out.println("Error en quitarComillas: "+e.getMessage());
    }
 */
    return s_HTML;
  }

  /**
   * public boolean isFinDominio (String s_HTML) throws Exception Si la url no
   * tiene referencia a ninguna pagina y solo aparece el dominio de un site,
   * devuelve true. Entrada String - http://www.google.com o
   * http://www.google.com/ Salida boolean - true Entrada String -
   * http://www.google.com/images Salida boolean - false
   *
   * @param s_HTML String
   * @throws Exception
   * @return boolean
   */
  public boolean isFinDominio (String s_HTML) throws Exception
  {
    boolean encontrado = false;
    int posicion = 0;
    String temp = listaDominios;

    try
    {
        StringTokenizer tokens = new StringTokenizer (temp, ",");
        String dominio = "";

          if (s_HTML != null && s_HTML.length() > 0)
          {
            s_HTML = s_HTML.toLowerCase();

            if (Constante.debug) System.out.print("FinDominio: "+s_HTML);

            for (int i = 0; tokens.hasMoreTokens (); i++)
            {
              dominio = tokens.nextToken ().trim ().toLowerCase();

              if ( (posicion = s_HTML.lastIndexOf(dominio)) != -1 )
              {
                if (s_HTML.charAt(s_HTML.length()-1)=='/')
                  posicion++;

                if (posicion == s_HTML.length()-dominio.length())
                {
                  if (Constante.debug)  System.out.print(" ;Devolvemos true");
                  encontrado = true;
                }
              }
            }
            //System.out.println("");
          }
    }
    catch (Exception e)
    {
      System.out.println("Error en otroDominio: "+e.getMessage() );
    }
    return encontrado;
  }

  /**
   * public String obtenDominio (String s_domURL) Se obtiene el dominio de una
   * URL Entrada: String - http://www.ejemplo.com/dir1/index.html Salida: String
   * - http://www.ejemplo.com
   *
   * @param s_domURL String
   * @return String
   */
  public String obtenDominio (String s_domURL)
  {

    int contHTPP = 0;
    int contFirstBarra = 0;
    String temp = "";

    try
    {
      if (s_domURL != null && s_domURL.length() > 0)
      {
        temp = s_domURL.toLowerCase();
        contHTPP = temp.indexOf(("http://"));
        if (contHTPP != -1)
        {
          contFirstBarra = temp.indexOf('/', contHTPP+"http://".length());
          if (contFirstBarra != -1)
          {
            s_domURL = s_domURL.substring(0,contFirstBarra);
          }
        }
      }
    }
    catch(Exception e)
    {
      System.out.println("Error en obtenDominio: "+e.getMessage());
    }
    return s_domURL;
  }

  /**
   * public String eliminarParametros (String s_URLParms) Se eliminan los
   * parametros de una URL Entrada: String -
   * http://www.ejemplo.com?parm1=1&parm2=3 Salida: String -
   * http://www.ejemplo.com
   *
   * @param s_URLParms String
   * @return String
   */
  public static String eliminarParametros (String s_URLParms)
  {

	  if (s_URLParms != null)
	  {
		  if (s_URLParms.indexOf("?") != -1)
		  {
			  s_URLParms = s_URLParms.substring(0,s_URLParms.indexOf("?"));
		  }

	  }
	  return s_URLParms;
  }


  public static String eliminarRetornos (String s_datos)
  {
    if (s_datos != null)
      return s_datos.replaceAll("[\r\n]", "");
    else
      return "";
  }


  public static String eliminarDirAnt (String s_datos)
  {
     String salida = null;
     int posCorte = 0;
     int posPuntos = 0;
     String cortado = "";

     if (s_datos != null)
     {
             if ((posPuntos = s_datos.indexOf("/..")) != -1)
             {
                     cortado = s_datos.substring(0,posPuntos);
                     if ((posCorte = cortado.lastIndexOf("/")) != -1)
                     {
                             cortado = cortado.substring(0,posCorte);
                             salida = cortado + s_datos.substring(posPuntos,s_datos.length());
                     }
             }
     }
     return salida;
   }


  /**
   * public String formatearURL(String URL) Se da formato a la url en el caso de
   * que venga con codigos hexadecimales Entrada: String -
   * http://www.ejemplo.com%30parm1%161%20parm2%163 Salida: String -
   * http://www.ejemplo.com?parm1=1&parm2=3
   *
   * @param URL String
   * @return String
   */
  public static String formatearURL(String URL)
  {
		int fin=0;
		String t_URL = URL;
		String separador = "";

                try
                {
                  if (URL != null) {
                    while ( (fin = URL.indexOf("%")) != -1) {
                      t_URL = URL.substring(0, fin);
                      if (fin + 3 < URL.length()) {
                        separador = URL.substring(fin, fin + 3);
                        separador = separador.substring(1, separador.length());
                        int valor = Integer.parseInt(separador, 16); //Conversion de hexadecimal a decimal
                        t_URL += (char) valor;
                        t_URL += URL.substring(fin + 3, URL.length());
                      }
                      URL = t_URL;
                    }
                  }
                }
                catch(Exception e)
                {
                  System.out.println("Excepcion en formatearURL: "+e.getMessage());
                  return URL;
                }

		return t_URL;
  }

  /**
   * public boolean isFiltro (String var_url)
   * Si la url coincide con el filtro definido en la clase Constante, devuelve true
   *
   * @param var_url String
   * @return boolean
   */
  public boolean isFiltro (String var_url)
  {
    boolean encontrado = false;
    String filtro[] = Constante.getV_PalabrasProhibidas();

    if (filtro != null && var_url != null && var_url.length() > 0){
      for (int i = 0; i < filtro.length; i++) {
        if (filtro[i].length() > 0 && var_url.indexOf(filtro[i]) != -1) {
          encontrado = true;
        }
      }
    }
    return encontrado;
  }

  /**
   * public boolean isImagen (String var_url, String var_lImag
   * Si la url coincide con alguna de las palabras definidas en var_lImag, devuelve true
   *
   * @param var_url String
   * @param var_lImag String
   * @return boolean
   */
  public boolean isImagen (String var_url, String var_lImag)
  {
    boolean encontrado = false;
    String filtro[] = var_lImag.split(",");

    if (filtro != null && filtro.length == 1 && filtro[0].equals(var_lImag))
    {
      filtro = var_lImag.split(";");
    }

    if (filtro != null && var_url != null){
      for (int i = 0; i < filtro.length; i++) {
        if (filtro[i] != null && var_url.indexOf(filtro[i].trim()) != -1) {
          encontrado = true;
        }
      }
    }
    return encontrado;
  }

  //Eliminamos redirecciones y
  //validamos la url. No pueden haber parámetros en una url si no está la interrogacion ?
  //Esto es necesario por la sentencia anterior. Si nos quedamos con la URL del final
  //no necesitamos los parámetros que se le pasaban a la URL que está antes.

  public static String eliminarRedireccionURL(String var_URL)
 {
  try
  {
    Constante.traza("eliminarRedireccionURL: "+var_URL);
    var_URL = formatearURL(var_URL);
    if (var_URL.toLowerCase().indexOf("http:") != -1) {
      var_URL = var_URL.substring(var_URL.toLowerCase().lastIndexOf("http:"),
                                  var_URL.length());

      if (var_URL.indexOf("&") >= 0 && var_URL.indexOf("?") < 0)
      {

          if (var_URL.indexOf("&") >= 0 && var_URL.indexOf("?") < 0 && (var_URL.indexOf("&")+1) < var_URL.length())
          {
            //en lugar de eso transformamos el primer & en una ?
            var_URL = var_URL.substring(0,var_URL.indexOf("&"))+"?"+var_URL.substring(var_URL.indexOf("&")+1,var_URL.length());
          }

      }
    }
    Constante.traza("eliminarRedireccionURL:End: "+var_URL);
  }
  catch (Exception e)
  {
    System.out.println("eliminarRedireccionURL: Error: "+e.getMessage());
  }
  return var_URL;

 }

}
