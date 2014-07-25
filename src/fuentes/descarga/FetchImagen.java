package fuentes.descarga;

// This example is from the book _Java in a Nutshell_ by David Flanagan.
// Written by David Flanagan.  Copyright (c) 1996 O'Reilly & Associates.
// You may study, use, modify, and distribute this example for any purpose.
// This example is provided WITHOUT WARRANTY either expressed or implied.


import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Random;
import fuentes.constantes.*;
import java.text.DecimalFormat;
import proyectoenlazador.FormEnlazador;
import java.awt.image.*;
import java.util.Date;
import fuentes.utiles.Galeria;
import javax.imageio.ImageIO;
import fuentes.md5.MD5;
import fuentes.bbdd.Jdbc;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;




public class FetchImagen extends Thread {//implements ImageObserver{
    // Get the contents of a URL and return it as a string.

  private String address = null;

  //Variable para controlar el numero de threads funcionando actualmente
  private static int contador = 0;

  //Variable para saber el numero de imagenes descargadas
  private static int contadorDescarga = 0;

  //Variable para medir la cantidad de megas bajados
  private static float MBDescarga = 0;

  //String que contiene los caracteres posibles para generar un nombre de fichero aleatorio
  private String s_carater = "abcdefghijklmnopqrstuvwxyz0123456789";
  FormEnlazador s_formEnlazador;

  DecimalFormat formateador = new DecimalFormat("#.##");
  DecimalFormat formateadorPercent = new DecimalFormat("#");

  int sLastId = -1;

  //Define el codigo del thread
  int idThread = 0;

  //Aqui se define la cabecera http referer
  private String referer = "";

  //Cuando estos valores sean iguales indicará que se ha bajado la última imagen
  //de la galeria
  int n_img = 0;
  int total_img = 0;


  Toolkit toolkit = Toolkit.getDefaultToolkit();

  public  FetchImagen(String dirhttp, FormEnlazador var_formEnlazador, int var_n_img, int var_total_img, String var_referer)  throws MalformedURLException, IOException
  {
    this.address = dirhttp;
    this.compruebaDirectorio();
    this.s_formEnlazador = var_formEnlazador;
    this.n_img = var_n_img;
    this.total_img = var_total_img;
    this.referer = var_referer;

  }


  public boolean imageUpdate(Image img, int infoflags, int x, int y,
   int width, int height) {

     if ((infoflags & ImageObserver.ALLBITS) != 0) {
       //System.out.println("Image Complete");
       return false;
     }
     else {
       System.out.println("x: " + x + " y: " + y + " width: " + width + " height: " + height);
     }

     return true;
  }


    public void run()
    {
      try
      {
          if ( getContador() <= Constante.getMAX_THREAD())
          {
              subeContador();
              //System.out.println("FetchImagen: Run: Subimos el contador a: "+getContador()+" max: "+(Constante.getMAX_THREAD()+1));
              fetchimage();
          }
          else
          {
            synchronized(this)
            {
              if (Constante.debug) System.out.println("FetchImagen: Run: Esperamos para la imagen " +
                                 getContador() + " max: " +
                                 (Constante.getMAX_THREAD() + 1));
              while (getContador() > Constante.getMAX_THREAD()) {
                Thread.sleep(1000 + (new Random().nextInt(5)+5) * 100);
              }
              if (Constante.debug) System.out.println("FetchImagen: Run: Antes de subir el contador a: " +
                                 getContador() + " max: " +
                                 (Constante.getMAX_THREAD() + 1));
              subeContador();
              if (Constante.debug) System.out.println("FetchImagen: Run: Contador subido: " +
                                 getContador() + " max: " +
                                 (Constante.getMAX_THREAD() + 1));
              this.fetchimage();
            }
          }
      }

/*      catch (ConnectException a)
      {
        System.out.println("No se ha podido conectar con la URL: "+a.getMessage());
        bajaContador();
      }
*/
      catch(Exception e)
      {
        System.out.println("Error en el run: "+e.getMessage());
        //bajaContador();
      }
      finally
      {
      	bajaContador();
      }
      //System.out.println("Termina thread " + getName());
}



    /**
     *
     */
        public void fetchAllInputStream()
        {
                URL theUrl=null;
                HttpURLConnection uc=null;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                BufferedInputStream in = null;
                InputStream content = null;
                BufferedOutputStream fos = null;
                fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
                //System.out.println("fetchAllInputStream");
                miTraza.println("fetchAllInputStream: "+address);

                try
                {
                  if (address != null && !"".equals(address))
                  {
                        theUrl = new URL(address);
                        uc = (HttpURLConnection)theUrl.openConnection();

                        uc.setRequestMethod("GET");
                        uc.setRequestProperty("Accept-Language","es");
                        uc.setRequestProperty("Accept-Encoding","gzip, deflate");
                        uc.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                        //uc.setRequestProperty("Keep-Alive", "timeout=" + Constante.getIMAGE_TIME_OUT());
                        uc.setRequestProperty("Connection", "Keep-Alive");
                        uc.setRequestProperty("Host", theUrl.getHost());
                        uc.setRequestProperty("Referer", this.getReferer());

                        /**Codificacion de autenticacion
                         *
                         *                         String userPassword = this.s_formEnlazador.authUser.getText() + ":" + this.s_formEnlazador.authUser.getText();
                         * String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());
                         * uc.setRequestProperty ("Authorization", "Basic " + encoding);
                         * Constante.traza("Autorizacion: "+"Basic " + encoding);
                         */



                        uc.setDoInput(true);
                        uc.setDoOutput(false);
                        uc.connect();

                        content = uc.getInputStream();

                        int fileLen = uc.getContentLength();
                        if (fileLen <= 0) fileLen = 1;//Para evitar division por 0
                        if (Constante.debug) System.out.println("fileLen: "+fileLen);
                        int contadorTam = 0;
                        //Creamos el nomnbre del fichero
                        String s_fileDir = comprobarNombreFichero(theUrl);
                        fos = new BufferedOutputStream(new FileOutputStream(s_fileDir));

                        final int bufferSize = 1024;
                        byte[] buffer = new byte[bufferSize]; // Experiment with this value
                        int bytesRead;

                        while ( (bytesRead = content.read(buffer)) != -1) {
                          fos.write(buffer, 0, bytesRead);
                          //if (fileLen > 0 && (contadorTam % bufferSize) == 0 || contadorTam == fileLen - 1){
                             this.s_formEnlazador.setTextoDetalle(formateadorPercent.format(contadorTam / (float) fileLen * 100) + "% - "+address,this.n_img);
                           //}
                           contadorTam+=bytesRead;
                        }
                        this.s_formEnlazador.setTextoDetalle("100% - "+address,this.n_img);

                        content.close();
                        if (fos != null) fos.close();
                        theUrl=null;
                        content = null;
                        fos = null;

                        if (!evitarDuplicados(s_fileDir))
                        {

                          if (comprobarTamanioImg(s_fileDir))
                          {
                            insertarEnHistorial(s_fileDir);
                            this.s_formEnlazador.getpnlFondo().setFondo(s_fileDir);
                          }
                          else
                          {
                            //this.setContadorDescarga(this.getContadorDescarga() - 1);
                            this.MBDescarga -= (new File(s_fileDir).length()) / (float) 1048576;
                            this.s_formEnlazador.getstatusBar().setText("La imagen ya existe: " +s_fileDir);
                            this.s_formEnlazador.getstatusBar1().setText(" Total: " +  this.getContadorDescarga() + "   |   " + formateador.format(MBDescarga) + "Mbytes");

                              if (Constante.debug) System.out.println("fetchAllInputStream: Borramos el fichero: "+s_fileDir);
                              File ficheroBorrar = new File(s_fileDir);
                              ficheroBorrar.delete();
                          }
                        }
                    }
                }
                catch(Exception e)
                {
                        miTraza.println("Error en el FetchImagen.fetchAllInputStream >>> ERROR: "+address);
                        miTraza.println(e.getMessage());
                }
                finally
                {

                    uc.disconnect();
                    try
                    {
                      if (out != null) out.close();
                      if (in != null) in.close();
                    }
                    catch (IOException ex) {
                    }
                }
        }

    public boolean comprobarTamanioImg(String ruta)
    {
      boolean retorno = true;

      try
      {
/*        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = Toolkit.getDefaultToolkit().getImage(ruta);
        MediaTracker tracker=new MediaTracker(this.s_formEnlazador.getstatusBar2());
        tracker.addImage(image, 0);
        try {
          tracker.waitForAll(0);
        }
        catch (InterruptedException ie) {
        }
*/
        BufferedImage image = ImageIO.read(new File(ruta));
        if (Constante.debug) System.out.println("ruta: "+ruta);
        if (Constante.debug) System.out.println("image.getWidth(null): "+image.getWidth(null));
        if (Constante.debug) System.out.println("image.getHeight(null): "+image.getHeight(null));

        if (!(image.getWidth(null) >= Constante.getMIN_WIDTH()
              && image.getHeight(null) >= Constante.getMIN_HEIGHT()
              ) && image.getHeight(null) > 0
              && image.getWidth(null) > 0)
        {
          retorno = false;
        }
        image.flush();
      }
      catch (Exception ex) {
        System.out.println("Error comprobarTamanioImg: " + ex.getMessage());
      }
      return retorno;
    }

    public boolean evitarDuplicados(String rutaFichero)
    {
      MD5 calculadorMD5 = new MD5((new File(rutaFichero)).getAbsolutePath());
      String hash=calculadorMD5.calcularMD5();
      boolean borrado = false;

      synchronized (this)
      {
        if (Jdbc.existeHash(hash) && Constante.isNoDescargarRepetidos()) {
          borrado = true;
          File ficheroBorrar = new File(rutaFichero);

          this.s_formEnlazador.getstatusBar().setText("La imagen ya existe: " +rutaFichero);
          this.setContadorDescarga(this.getContadorDescarga() - 1);
          //this.s_formEnlazador.getstatusBar1().setText(" La imagen ya existe" + "   |   " + formateador.format(MBDescarga) + "Mbytes");


          if (ficheroBorrar.delete()) {
            if (Constante.debug) System.out.println("El fichero ha sido borrado satisfactoriamente");
          }
          else {
             if (Constante.debug) System.out.println("El fichero no puede ser borrado");
          }
        }
        else {
          if (!Jdbc.existeHash(hash)) Jdbc.insertaHash(hash);
          this.MBDescarga += (new File(rutaFichero).length()) / (float) 1048576;
          this.s_formEnlazador.getstatusBar().setText(" Salvada: " + rutaFichero + "   |   " + "Threads: " + (this.getContador()));
          this.s_formEnlazador.getstatusBar1().setText(" Total: " +  this.getContadorDescarga() + "   |   " + formateador.format(MBDescarga) + "Mbytes");
        }
      }
      return borrado;
    }

    public void fetchimageInputStream()
    {
            URL theUrl=null;
            HttpURLConnection uc=null;
            InputStream content = null;
            String s_fileDir = "";
             //System.out.println("fetchimageInputStream");
             if (Constante.debug) System.out.println("fetchimageInputStream: "+address);

            try
            {
              if (address != null && !"".equals(address))
              {
                    theUrl = new URL(address);
                    uc = (HttpURLConnection)theUrl.openConnection();
                    uc.setRequestMethod("GET");
                    uc.setRequestProperty("Accept-Language","es");
                    uc.setRequestProperty("Accept-Encoding","gzip, deflate");
                    uc.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
                    //uc.setRequestProperty("Keep-Alive", "timeout=" + Constante.getIMAGE_TIME_OUT());
                    uc.setRequestProperty("Connection", "Keep-Alive");
                    uc.setRequestProperty("Host", theUrl.getHost());
                    uc.setRequestProperty("Referer", this.getReferer());
                    uc.setDoInput(true);
                    uc.setDoOutput(false);
                    uc.setUseCaches(false);
                    uc.connect();
                    //System.out.println("urlImg: "+theUrl.getPath());
                    content = new BufferedInputStream(uc.getInputStream());
                    //Creamos el nomnbre del fichero
                    //String nombreFichero = address.substring(address.lastIndexOf("/") + 1,address.length());
                    String nombreFichero = "";

                    BufferedImage image = ImageIO.read(content);
                    int width = image.getWidth();
                    int height = image.getHeight();

                    if (width >= Constante.getMIN_WIDTH() && height >= Constante.getMIN_HEIGHT() )
                    {
                      //Comprobamos que el nombre del fichero exista y si no, creamos un nombre aleatorio y lo guardamos
                      s_fileDir = comprobarNombreFichero(theUrl);
                      ImageIO.write(image, "jpeg", new File(s_fileDir));

                      boolean ficheroBorrado = this.evitarDuplicados(s_fileDir);
                      if (!ficheroBorrado)
                      {
                           insertarEnHistorial(s_fileDir);
                           this.s_formEnlazador.getpnlFondo().setFondoImg(image, height,width);
                           Galeria objGaleria = new Galeria();
                           if ( (this.n_img == this.total_img) && this.n_img != 0)
                           {
                             objGaleria.crearGalerias(s_fileDir, address, true);
                           }
                           else
                           {
                             objGaleria.crearGalerias(s_fileDir, address, false);
                           }
                      }
                    }
                    else
                    {
                        this.s_formEnlazador.getstatusBar().setText(" Rechazada por tamaño: "+s_fileDir+":"+width+":"+height+" | "+"Threads: "+(this.getContador()));
                    }
                    image.flush();

                    this.s_formEnlazador.getstatusBar1().setText(" Total: " + this.getContadorDescarga() + "   |   " + formateador.format(MBDescarga) + "Mbytes");
                    if (uc != null)
                    uc.disconnect();
                    content = null;
              }
            }
            catch(Exception e)
            {
                    //System.out.println("ImageInputStream: Error al conectar a "+address+":\r\n"+e.getMessage());
                    //e.printStackTrace();
                    this.s_formEnlazador.getstatusBar().setText("El contenido no era una imagen: "+address);
                    //System.out.println("El contenido no era una imagen: "+address + " referer: "+ this.getReferer()+" host: "+theUrl.getHost());
                    fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
                    miTraza.println("Error en el FetchImagen.fetchimageInputStream >>> IMG FORMAT ERROR: "+address + " referer: "+ this.getReferer()+" host: "+theUrl.getHost());
            }
    }

    public void insertarEnHistorial(String rutaArchivo)
    {
      Object[] dat = {rutaArchivo};
      this.s_formEnlazador.getModeloTablaHIST().addRow(dat);
    }

    // Get the contents of a URL and return it as an image
    public void fetchimage()
    {
      //Image myImg = null;
      URL url = null;
      HttpURLConnection connection = null;
      String nombreFichero = address.substring(address.lastIndexOf("/")+1, address.length());
      try
      {
          if (address != null && !"".equals(address))
          {
            url = new URL(this.address);

            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Accept-Language", "es");
            connection.setRequestProperty("Accept-Encoding", "deflate");
            connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //connection.setRequestProperty("Keep-Alive", "timeout=" + Constante.getIMAGE_TIME_OUT());
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Host", url.getHost());
            connection.setRequestProperty("Referer", this.getReferer());

            /**Autenticacion
            String userPassword = this.s_formEnlazador.authUser.getText() + ":" + this.s_formEnlazador.authUser.getText();
            String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());
            connection.setRequestProperty ("Authorization", "Basic " + encoding);

            Constante.traza("Autorizacion: "+"Basic " + encoding);
*/

            Object conexion = connection.getContent();
            Image img_descarga = null;

            //Esta parte es lo que sustituye a la de antes que esta comentada un poco mas abajo
            if (!Constante.isRecomprimir())
            {
              connection.disconnect();
              this.fetchAllInputStream();
            }
            else

            if ( ("sun.awt.image.URLImageSource".equals(conexion.getClass().getName())
                  || "sun.net.www.protocol.http.HttpURLConnection$HttpInputStream".equals(conexion.getClass().getName()))
                  && Constante.isRecomprimir() )
            {
              java.awt.image.ImageProducer contenido = (java.awt.image.ImageProducer) conexion ;
              img_descarga = Toolkit.getDefaultToolkit().createImage(contenido);

                if (nombreFichero != null && nombreFichero.length() > 0)
                {
                    this.saveimage(url, img_descarga);
                    img_descarga = null;
                }
            }


            /*
            else if (!Constante.isRecomprimir() && ("sun.awt.image.URLImageSource".equals(conexion.getClass().getName()) ||
                                                    ("sun.net.www.protocol.http.HttpURLConnection$HttpInputStream".equals(conexion.getClass().getName()) &&
                                                     (address.toLowerCase().indexOf(".jpg") >= 0 && address.toLowerCase().indexOf(".jpeg") >= 0 ) &&
                                                     (address.toLowerCase().indexOf(".gif") >= 0 && address.toLowerCase().indexOf(".bmp") >= 0 ) &&
                                                     (address.toLowerCase().indexOf(".tiff") >= 0 && address.toLowerCase().indexOf(".png") >= 0 )
                                                     ) ))
            {
              connection.disconnect();
              this.fetchimageInputStream();
            }
            else if (("sun.net.www.protocol.http.HttpURLConnection$HttpInputStream".equals(conexion.getClass().getName())
                       && (address.toLowerCase().indexOf(".jpg") == -1 && address.toLowerCase().indexOf(".jpeg") == -1 )))
            {
              connection.disconnect();
              this.fetchAllInputStream();
            }
            */

            else
            {
              this.s_formEnlazador.getstatusBar().setText("fetchimage: El contenido no era una imagen: "+address);
              System.out.println("fetchimage: El contenido no era una imagen: "+address);
              fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
              miTraza.println("Error en el FetchImagen.fetchimage >>> IMG FORMAT ERROR: "+address);
           }
        }
     }
     catch(Exception e)
     {
        //Si dio error en la ultima imagen, no se podrá crear la galeria
        //Entonces la creamos aqui
        if ((this.n_img == this.total_img ) && this.n_img != 0)
        {
             Galeria objGaleria = new Galeria();
             String s_fileDir = Constante.getDirBase()+nombreFichero;
             objGaleria.crearGalerias(s_fileDir, address, true);
        }
        fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
        miTraza.println("Excepcion en el FetchImagen.fetchimage >>> IMG ERROR: "+address+"; Message: "+e.getMessage());
      }
      finally
      {
        try
        {
                if (Constante.debug) System.out.println(((HttpURLConnection)connection).getResponseCode());
                connection.disconnect();
        }
        catch(Exception e)
        {
            System.out.println("Error al desconectar: "+e.getMessage());
        }
      }
    }


    public synchronized String comprobarNombreFichero (URL varUrl)
    {
      String salida = "";

      String fileName = "";

      String nombreFichero = varUrl.getPath();

      if (Constante.isNombFichUrl())
      {
        nombreFichero = nombreFichero.replaceAll("_", "__");
        nombreFichero = nombreFichero.replaceAll("/", "_");
      }
      else
      {
        nombreFichero = nombreFichero.substring(nombreFichero.lastIndexOf("/") + 1,nombreFichero.length());
      }

      fileName = Constante.getDirBase() + nombreFichero;

      File objFichero = new File(fileName);


      //Añadimos el numero de descarga al nombre del fichero
      String sContaDescarga = "" + this.getContadorDescarga();

      int numCeros = Constante.getNum_ceros() - sContaDescarga.length();
      sContaDescarga = "";

      while (numCeros > 0) {
        sContaDescarga += "0";
        numCeros--;
      }
      sContaDescarga += this.getContadorDescarga();
      this.setContadorDescarga(this.getContadorDescarga() + 1);

      if (!Constante.isNombFichUrl())
      {
        fileName = Constante.getDirBase() + sContaDescarga + "_" + objFichero.getName();
      }

      objFichero = new File(fileName);
      if (Constante.debug) System.out.println("Fichero: "+objFichero.getName());
      boolean existe = objFichero.exists();

      String extension = "";

      if (fileName.indexOf(".") != -1)
      {
        extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
      }

      if (existe)
      {
         Random generator = new Random();
         generator.setSeed(new Date().getTime());
         int indice = 0;
         int contadorVueltas = 0;
         do
         {
            salida = "";
            for (int i = 0; i < 19; i++)
            {
              //Devuelve un caracter aleatorio del array entre 0 y 37 para generar el nombre del fichero
              indice = generator.nextInt(s_carater.length());
              salida += s_carater.charAt(indice);
            }
            salida += extension;
            contadorVueltas++;
         }
         while ( (new File(Constante.getDirBase() + salida)).exists() && contadorVueltas < 100);
         salida = Constante.getDirBase() + sContaDescarga + "_" + salida;
      }
      else
      {
          salida = fileName;
      }

      return salida;
    }


    public void saveimage(URL fileDir,Image i)  throws Exception
    {
        String s_fileDir;
        if (Constante.debug) System.out.println("saveimage: "+address);
        MediaTracker mediaTracker = new MediaTracker(this.s_formEnlazador.getpnlFondo());
        mediaTracker.addImage(i, 0);

        mediaTracker.waitForAll(Constante.getIMAGE_TIME_OUT()*1000);

        int width=i.getWidth(null),height=i.getHeight(null);




        if (width >= Constante.getMIN_WIDTH() && height >= Constante.getMIN_HEIGHT())
        {
          synchronized (this) {
            BufferedImage tnsImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = tnsImg.createGraphics();
            graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics2D.drawImage(i, 0, 0, width, height, null);

            //Preparamos para escribir la imagen generada en el disco
            s_fileDir = comprobarNombreFichero(fileDir);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(s_fileDir));
            //creamos el "parseador" para guardar la imagen en formato JPG
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tnsImg);
            //Asignamos la calidad con la que se va a guardar la imagen de 0-100
            int calidad = Math.max(0, Math.min(Constante.getCalidadJPEG(), 100));
            param.setQuality( (float) calidad / 100.0f, false);
            encoder.setJPEGEncodeParam(param);
            encoder.encode(tnsImg);
            out.close();

            if (!evitarDuplicados(s_fileDir))
            {
              MBDescarga += (new File(s_fileDir).length())/(float)1048576;
              //this.setContadorDescarga(this.getContadorDescarga()+1);
              this.s_formEnlazador.getstatusBar().setText(" Salvada: " + s_fileDir+"   |   "+"Threads: "+this.getContador());
              this.s_formEnlazador.getstatusBar1().setText(" Total: "+this.getContadorDescarga()+"   |   "+formateador.format(MBDescarga)+"Mbytes");
              //Pintamos la imagen en el panel correspondiente
              this.s_formEnlazador.getpnlFondo().setFondoImg(i, height, width);
              //Pintamos los enlaces en la lista del panel
              insertarEnHistorial(s_fileDir);
              //Creamos las galerías
              Galeria objGaleria = new Galeria();
              if ( (this.n_img == this.total_img) && this.n_img != 0) {
                objGaleria.crearGalerias(s_fileDir, address, true);
              }
              else {
                objGaleria.crearGalerias(s_fileDir, address, false);
              }
            }
            else
            {
                this.s_formEnlazador.getstatusBar().setText("La imagen ya existe: " +s_fileDir);
            }
          }//Fin sincronizado
        }
        else
        {
          this.s_formEnlazador.getstatusBar().setText(" Rechazada por tamaño: w-"+width+":h-"+height+" | "+"Threads: "+(this.getContador()-1));
          //System.out.println("Rechazada: "+s_fileDir);
        }
        mediaTracker.removeImage(i);
        //Puede que el garbage collector lo elimine???
        mediaTracker = null;
    }

  public synchronized void compruebaDirectorio()
  {

    try {
      File f = new File(Constante.getDirBase());
      if (!f.exists()) {
        f.mkdirs();
      }
    }
    catch (Exception ex) {
      System.out.println("No se ha podido crear el directorio: "+Constante.getDirBase());
    }
  }

  public synchronized int getContador()
  {
    return contador;
  }

  public synchronized int getContadorDescarga() {
    return contadorDescarga;
  }

  public String getReferer() {
    return referer;
  }

  public synchronized void setContadorDescarga(int varcontadorDescarga) {
    this.contadorDescarga = varcontadorDescarga;
  }


  public synchronized void subeContador()
  {
          contador++;
          Constante.setContadorThreads(contador);
  }

  public synchronized void bajaContador()
  {
          if (contador > 0)
          {
              contador--;
              Constante.setContadorThreads(contador);
          }
  }
}
