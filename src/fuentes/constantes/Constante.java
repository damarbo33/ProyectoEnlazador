package fuentes.constantes;

import java.io.*;

/**
 * @author dmarcobo
 *
 */
public class Constante {

  //niveles de descarga url
  private static int NIVEL_DESCARGA = 1;
  //Definimos si se generan trazas de la aplicación
  public static boolean debug = false;
  //Tamaño minimo de la imagen para guardarla en disco o descargarla.
  private static int MIN_HEIGHT = 300;
  private static int  MIN_WIDTH = 300;
    // Si MAX_THREAD = 0 -> Sólo se acepta un thread
  // Si MAX_THREAD = 1 -> Se aceptan 2 Threads
  private static int MAX_THREAD = 2;
  //Indicador para saber si se sobreescribe el archivo o se crea uno nuevo
  private static boolean SOBREESCRIBIR = false;
  //Se define si se quiere recoger paginas de otros dominios. No recogera enlaces terminados en .com, .net...
  private static boolean otrosDominios = false;
  //Se define si se quiere realizar busquedas de paginas en otros dominios. No recogerá páginas que
  //esten en dominios ajenos si es false
  private static boolean otrosSites = true;
  //Número de reintentos cuando la conexión falla. No se utiliza para recoger imagenes. Solo
  //para conectarse a la URL del fichero html
  private static int MAX_TIME_OUT = 0;
  //Tiempo hasta el que debe establecerse la conexion en segundos
  private static int IMAGE_TIME_OUT = 15;
  // Define la calidad de la compresion JPEG que se guarda en disco
  private static int calidadJPEG = 80;
  // Define el directorio en el que se guardarán las imágenes
  private static String dirBase = "descargado"+System.getProperty("file.separator").toString();
  // Define si la imagen será escalada al tamaño del panel donde se visualice
  private static boolean tamAuto = true;
  // Define palabras prohibidas
  private static String v_palabrasProhibidas[]={""} ;
  //Define las imágenes que se van a descargar
  private static String s_imgDescarga = ".jpg,.jpeg";
  //Define si se pintan trazas o no
  private static boolean trazas = false;
  //Controla el numero de threads de descarga de imagenes que se encuentran activos
  private static int contadorThreads = 0;
  //Define si se crearán galerías para las imágenes descargadas
  private static boolean b_galeria = false;
  //Define los tipos de ficheros encontrados por google que no debe descargarse
  //private static String TipoFichero[]={".pdf",".txt",".swf","q=related","http://www.google.com","http://groups.google","http://images.google","http://maps.google","http://news.google","http://pack.google","http://toolbar.google","oi=translate"};
  private static String TipoFichero[]={".pdf",".txt",".swf","q=related",".google.com","oi=translate"};
  //Define si se deben descargar enlaces cacheados de Google
  private static boolean descargarDeCache=false;
  //Define la IP del proxy
  private static String proxyIP = "";
  //Define el puerto del proxy
  private static String proxyPort = "";
  //Indicador para saber si se sobreescribe el archivo o se crea uno nuevo
  private static boolean recomprimir = true;
  //Indicador que escribe el numero de fichero descargado delante del nombre del fichero
  //rellenando con el numero de ceros que se especifiquen
  private static int num_ceros = 4;
  //Especifica si el nombre del fichero se generara en base a la url o un autonumerico
  private static boolean nombFichUrl=true;
  private static boolean NoDescargarRepetidos = true;
  private static boolean vaciarHistorial = false;
  private static boolean descargaURLAutomatica = false;


  public static void cargaConfiguracion()
  {
// 1. Reading input by lines:
    System.out.println("Vamos a cargar las constantes");
    try {
      BufferedReader in = new BufferedReader(new FileReader("Enlazador.ini"));
      String s = new String();
      while ( (s = in.readLine()) != null) {
        asignarConstantes(s);
      }
      in.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("No se ha encontrado el fichero de configuracion");
    }
    catch (IOException ex) {
      System.out.println("Error al leer del fichero");
    }

  }

  public static void asignarConstantes(String var_s)
  {
    try
    {
      if (var_s != null)
      {
        String separador[] = var_s.split("=");
        if (separador != null && separador.length == 2) {
          if (separador[0].indexOf("MIN_HEIGHT") != -1) {
            setMIN_HEIGHT(Integer.parseInt(separador[1]));
          }
          if (separador[0].indexOf("MIN_WIDTH") != -1) {
            setMIN_WIDTH(Integer.parseInt(separador[1]));
          }
          if (separador[0].indexOf("MAX_THREAD") != -1) {
            setMAX_THREAD(Integer.parseInt(separador[1]));
          }
          if (separador[0].indexOf("timeout") != -1)
          {
            setIMAGE_TIME_OUT(Integer.parseInt(separador[1]));
          }
          if (separador[0].indexOf("NIVEL_DESCARGA") != -1) {
            setNIVEL_DESCARGA(Integer.parseInt(separador[1]));
          }



          if (separador[0].indexOf("SOBREESCRIBIR") != -1) {
            if ("true".equals(separador[1]))
              setSOBREESCRIBIR(true);
            else
              setSOBREESCRIBIR(false);
          }


          if (separador[0].indexOf("otrosDominios") != -1) {
            if ("true".equals(separador[1]))
              setOtrosDominios(true);
            else
              setOtrosDominios(false);
          }

          if (separador[0].indexOf("otrosSites") != -1) {
            if ("true".equals(separador[1]))
              setOtrosSites(true);
            else
              setOtrosSites(false);
          }

          if (separador[0].indexOf("vaciarHistorial") != -1) {
           if ("true".equals(separador[1]))
             setVaciarHistorial(true);
           else
             setVaciarHistorial(false);
         }

          if (separador[0].indexOf("calidadJPEG") != -1) {
            setCalidadJPEG(Integer.parseInt(separador[1]));
          }

          if (separador[0].indexOf("dirBase") != -1) {
            setDirBase(separador[1]);
          }
          if (separador[0].indexOf("v_palabrasProhibidas") != -1) {
            setPalabrasProhibidas(separador[1]);
          }
          if (separador[0].indexOf("s_imgDescarga") != -1) {
            setImgDescarga(separador[1]);
          }

          if (separador[0].indexOf("proxyIP") != -1) {
           setProxyIP(separador[1]);
         }

         if (separador[0].indexOf("proxyPort") != -1) {
          setProxyPort(separador[1]);
        }



          if (separador[0].indexOf("trazas") != -1) {
           if ("true".equals(separador[1]))
             setTrazas(true);
           else
             setTrazas(false);
         }

         if (separador[0].indexOf("recomprimir") != -1) {
          if ("true".equals(separador[1]))
            setRecomprimir(true);
          else
            setRecomprimir(false);
        }

        if (separador[0].indexOf("NoDescargarRepetidos") != -1) {
         if ("true".equals(separador[1]))
           setNoDescargarRepetidos(true);
         else
           setNoDescargarRepetidos(false);
       }

         if (separador[0].indexOf("galeria") != -1) {
           if ("true".equals(separador[1]))
             setB_galeria(true);
           else
             setB_galeria(false);
         }


        }
      }
    }
    catch (NumberFormatException ex) {
      System.out.println("Error en asignarConstantes: "+ex.getMessage());
    }
  }

  public static void guardaConfiguracion()
  {
    try
    {
      String s, s2 = new String();

      s2 = "MIN_HEIGHT=" + getMIN_HEIGHT() + "\n";
      s2 += "MIN_WIDTH=" + getMIN_WIDTH() + "\n";
      s2 += "MAX_THREAD=" + getMAX_THREAD() + "\n";
      s2 += "SOBREESCRIBIR=" + isSOBREESCRIBIR() + "\n";
      s2 += "otrosDominios=" + isOtrosDominios() + "\n";
      s2 += "otrosSites=" + isOtrosSites() + "\n";
      s2 += "calidadJPEG=" + getCalidadJPEG() + "\n";
      s2 += "dirBase=" + getDirBase() + "\n";
      s2 += "v_palabrasProhibidas=" + getPalabrasProhibidas() + "\n";
      s2 += "s_imgDescarga=" + getImgDescarga() + "\n";
      s2 += "trazas=" + isTrazas() + "\n";
      s2 += "galeria=" + isB_galeria() + "\n";
      s2 += "timeout=" + getIMAGE_TIME_OUT() + "\n";
      s2 += "NIVEL_DESCARGA=" + getNIVEL_DESCARGA() + "\n";
      s2 += "proxyIP=" + getProxyIP() + "\n";
      s2 += "proxyPort=" + getProxyPort() + "\n";
      s2 += "recomprimir=" + isRecomprimir() + "\n";
      s2 += "NoDescargarRepetidos=" + isNoDescargarRepetidos() + "\n";
      s2 += "vaciarHistorial=" + isVaciarHistorial() + "\n";

      BufferedReader in4 = new BufferedReader( new StringReader(s2));
      PrintWriter out1 = new PrintWriter( new BufferedWriter( new FileWriter("Enlazador.ini")));
      while ( (s = in4.readLine()) != null)
        out1.println(s);

      out1.close();
    }
    catch (Exception e)
    {
      System.err.println("Error en guardaConfiguracion: "+e.getMessage());
    }
  }

  public static String getImgDescarga() {
    return Constante.s_imgDescarga;
  }

  public static void setImgDescarga(String var_img) {
    Constante.s_imgDescarga = var_img;
  }


  public static String getPalabrasProhibidas()
  {
    String salida = "";

    int i;
    for (i = 0 ; i < v_palabrasProhibidas.length-1; i++)
    {
      salida += v_palabrasProhibidas[i].trim();
      salida += ",";
    }

    if (i >= 0 && i == v_palabrasProhibidas.length-1)
      salida += v_palabrasProhibidas[i].trim();

    return salida;
  }

  public static String[] getV_PalabrasProhibidas() {
    return v_palabrasProhibidas;
  }

  public static void traza(String msg)
  {
    if (debug) System.out.println(msg);
  }

  public static void setPalabrasProhibidas(String var_s) {
    if (var_s != null)
    {
      if (var_s.indexOf(",") != -1) {
        v_palabrasProhibidas = var_s.split(",");
      }
      else
      {
        v_palabrasProhibidas = (new String[] {var_s});
      }
    }

  }


  public static int getCalidadJPEG() {
    return calidadJPEG;
  }

  public static void setCalidadJPEG(int calidadJPEG) {
    Constante.calidadJPEG = calidadJPEG;
  }

  public static int getMAX_THREAD() {
    return MAX_THREAD;
  }

  public static void setMAX_THREAD(int max_thread) {
    MAX_THREAD = max_thread;
  }

  public static boolean isSOBREESCRIBIR() {
    return SOBREESCRIBIR;
  }

  public static boolean isNoDescargarRepetidos() {
  return NoDescargarRepetidos;
}

public static void setNoDescargarRepetidos(boolean descargar) {
  NoDescargarRepetidos = descargar;
}

  public static void setSOBREESCRIBIR(boolean sobreescribir) {
    SOBREESCRIBIR = sobreescribir;
  }

  public static int getMIN_HEIGHT() {
    return MIN_HEIGHT;
  }

  public static void setMIN_HEIGHT(int min_height) {
    MIN_HEIGHT = min_height;
  }

  public static int getMIN_WIDTH() {
    return MIN_WIDTH;
  }

  public static void setMIN_WIDTH(int min_width) {
    MIN_WIDTH = min_width;
  }

  public static boolean isOtrosDominios() {
    return otrosDominios;
  }

  public static void setOtrosDominios(boolean otrosDominios) {
    Constante.otrosDominios = otrosDominios;
  }

  public static int getMAX_TIME_OUT() {
    return MAX_TIME_OUT;
  }

  public static void setMAX_TIME_OUT(int max_time_out) {
    MAX_TIME_OUT = max_time_out;
  }

  public static boolean isOtrosSites() {
    return otrosSites;
  }

  public static boolean isTamAuto() {
    return tamAuto;
  }


  public static void setOtrosSites(boolean otrosSites) {
    Constante.otrosSites = otrosSites;
  }

  /**
   * @return the dirBase
   */
  public static String getDirBase() {
    return dirBase;
  }

  public static boolean isTrazas() {
    return trazas;
  }

  public static synchronized int getContadorThreads() {
    return contadorThreads;
  }

  public static boolean isB_galeria() {
    return b_galeria;
  }

  public static boolean isDescargarDeCache() {
    return descargarDeCache;
  }

  public static String getProxyPort() {
    return proxyPort;
  }

  public static String getProxyIP() {
    return proxyIP;
  }

  public static boolean isRecomprimir() {
    return recomprimir;
  }

  public static int getNum_ceros() {
    return num_ceros;
  }

  public static boolean isVaciarHistorial() {
    return vaciarHistorial;
  }

  public static boolean isDescargaURLAutomatica() {
    return descargaURLAutomatica;
  }

  public static boolean isNombFichUrl() {
    return nombFichUrl;
  }

  public static int getNIVEL_DESCARGA() {
    return NIVEL_DESCARGA;
  }

  public static int getIMAGE_TIME_OUT() {
    return IMAGE_TIME_OUT;
  }

  public static String[] getTipoFichero() {
    return TipoFichero;
  }

  /**
   * @param dirBase the dirBase to set
   */
  public static void setDirBase(String dirBase) {
    Constante.dirBase = dirBase;
  }

  public static void setTrazas(boolean in) {
    trazas = in;
  }

  public static synchronized void setContadorThreads(int var_contadorThreads) {
    contadorThreads = var_contadorThreads;
  }

  public static void setB_galeria(boolean varb_galeria) {
    b_galeria = varb_galeria;
  }

  public static void setDescargarDeCache(boolean var_descargarDeCache) {
    descargarDeCache = var_descargarDeCache;
  }

  public static void setProxyPort(String s_proxyPort) {
    proxyPort = s_proxyPort;
    System.setProperty("http.proxyPort", s_proxyPort);
  }

  public static void setProxyIP(String s_proxyIP) {
    proxyIP = s_proxyIP;
     System.setProperty("http.proxyHost", s_proxyIP);
  }

  public static void setRecomprimir(boolean b_recomprimir) {
    recomprimir = b_recomprimir;
  }

  public static void setNum_ceros(int varnum_ceros) {
    num_ceros = varnum_ceros;
  }

  public static void setVaciarHistorial(boolean var_vaciarHistorial) {
    vaciarHistorial = var_vaciarHistorial;
  }

  public static void setDescargaURLAutomatica(boolean var_descargaURLAutomatica) {
    descargaURLAutomatica = var_descargaURLAutomatica;
  }

  public static void setNombFichUrl(boolean var_nombFichUrl) {
    nombFichUrl = var_nombFichUrl;
  }

  public static void setNIVEL_DESCARGA(int var_NIVEL_DESCARGA) {
    NIVEL_DESCARGA = var_NIVEL_DESCARGA;
  }

  public static void setIMAGE_TIME_OUT(int var_IMAGE_TIME_OUT) {
    IMAGE_TIME_OUT = var_IMAGE_TIME_OUT;
  }
}
