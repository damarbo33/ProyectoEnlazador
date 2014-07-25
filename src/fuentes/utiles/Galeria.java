package fuentes.utiles;

import java.net.*;
import java.io.*;
import fuentes.constantes.Constante;

public class Galeria {

  public Galeria()
  {

  }

  /**
   *
   * @param s_fileDir String - Directorio en el que se ha guardado la imagen
   * @param s_address String - direccion http de la imagen
   */
  public void crearGalerias(String s_fileDir, String s_address, boolean crearIndex)
  {

    if (Constante.isB_galeria())
    {
      String dominio = "error";
      String urlDominio = "";
      try
      {
        urlDominio = new URL(s_address).getHost() + new URL(s_address).getPath();
        dominio = eliminarBarras(urlDominio);
      }
      catch (MalformedURLException ex)
      {
        System.out.println("Error al recuperar el dominio: " + ex.getMessage());
      }

      //Si hemos llegado a la ultima imagen a descargar, primero añadimos la
      //imagen en la galeria de forma recursiva y despues se añade el enlace
      //en el index.html siguiendo la ejecución
      if (crearIndex)
      {
        crearGalerias(s_fileDir, s_address, false);
      }

      String barraDir = System.getProperty("file.separator").toString();
      String rutaSalida = "";
      String tag_href = "";
      String tag_img = "";


      try
      {
        if (s_fileDir != null)
        {

          //Si s_address es index, crearemos la pagina index.html
          if (crearIndex)
          {
            tag_img = ".." + s_fileDir.substring(s_fileDir.lastIndexOf(barraDir), s_fileDir.length());
            tag_href = dominio + ".html";
          }
          else
          {
            tag_img = ".." + s_fileDir.substring(s_fileDir.lastIndexOf(barraDir), s_fileDir.length());
            tag_href = ".." + s_fileDir.substring(s_fileDir.lastIndexOf(barraDir), s_fileDir.length());
          }

          String ruta = s_fileDir.substring(0, s_fileDir.lastIndexOf(barraDir));
          rutaSalida = ruta + barraDir + "galeria";
          File path = new File(rutaSalida);
          if (!path.exists())
          {
            path.mkdirs();
          }
        } //Fin  if (s_fileDir != null)


        //Este fichero define donde se añaden las galerias. En la galeria o en
        //el index.html
        Fichero salidaIndex;

        if (crearIndex)
        {
          //Añadiremos el enlace con la nueva imagen en la pagina index.html
          salidaIndex = new Fichero(rutaSalida + barraDir + "index.html");
        }
        else
        {
            //Añadiremos el enlace con la imagen en la galería que corresponda
            salidaIndex = new Fichero(rutaSalida + barraDir + dominio + ".html");
        }

        String contenido = salidaIndex.leer();
        int nDiv = analizarDiv(contenido);
        int max_cols = 6;
        String s2 = "";

        if (nDiv == 0)
        {
          s2 += ("<html>			\n");
          s2 += ("	                                \n");
          s2 += ("	<style type='text/css'>         \n");
          s2 += ("		.contenedor {margin-top:45px;}\n");
          s2 += ("		img {height:100;width:100}                                                                         \n");


          //cambiamos los colores dependiendo de la pagina a pintar
          if (crearIndex)
          {
            s2 += ("		.filas {width:650px;border-style: solid;border-width:1px; color: skyblue;background-color: #0080c0}\n");
            s2 += ("		body {background-color: #004080}                                                                   \n");
            s2 += ("            .titulo {font-size: 20pt;font-family: arial;font-weight: bold;text-decoration: underline;color: #0080c0;}\n") ;
          }
          else
          {
            s2 += ("		.filas {width:650px;border-style: solid;border-width:1px; color: #FF9BCD;background-color: #FF178B}\n");
            s2 += ("		body {background-color: #910048}                                                                   \n");
            s2 += ("           .titulo {font-size: 20pt;font-family: arial;font-weight: bold;text-decoration: underline;color: #FF178B;}\n") ;
          }

          s2 += ("	</style>                        \n");
          s2 += ("	                                \n");
          s2 += ("	<body>                          \n");
          s2 += ("		<center>                      \n");
          s2 += ("			<div class='contenedor'>    \n");

          if (crearIndex)
          {
            s2 += ("                         <div class='titulo'>Indice de Im&aacute;genes</div>\n");
          }
          else
          {
            s2 += ("                         <div class='titulo'>Galer&iacute;a "+dominio+"</div>\n");
          }

           s2 += ("<br>\n");

        }

        if (nDiv % max_cols == 0) {
          s2 += ("				<div class='filas'>\n");
          s2 += "					<a href='" + tag_href + "'><img src='" + tag_img +
              "'></a>\n";
        }
        else if ( (nDiv + 1) % max_cols == 0) {
          s2 += "					<a href='" + tag_href + "'><img src='" + tag_img +
              "'></a>\n";
          s2 += "				</div>\n";
        }
        else {
          s2 += "					<a href='" + tag_href + "'><img src='" + tag_img +
              "'></a>\n";
        }

        //Añadimos la imagen al fichero abierto
        salidaIndex.println(s2);
      }
      catch (Exception e)
      {
        fuentes.utiles.Traza miTraza = new fuentes.utiles.Traza();
        miTraza.println("Excepcion en el FetchImagen.crearGalerias: " + e.getMessage());
      }
    }
}
/**
 *
 * @param s_var String
 */
public int analizarDiv(String s_var)
{
  int nDiv = 0;

  String [] separador = s_var.split("<a");
  nDiv = separador.length;
  nDiv -= 1;

  return nDiv;
}

/**
 * Reemplaza las barras de una URL:
 * 127.0.0.1/descarga/ejemplo/ -> 127.0.0.1.descarga.ejemplo.
 * @param s_var String
 * @return String
 */
public String eliminarBarras(String s_var)
{
  String salida = "";

  //Eliminamos primero el nombre del fichero a descargar
  if (s_var != null && s_var.length() > 0)
  {
        s_var = s_var.trim();
    int posExtension = s_var.lastIndexOf(".");
    if (posExtension != -1)
    {
      int ultimaBarra = s_var.lastIndexOf("/",posExtension);
      if (ultimaBarra != -1)
      {
              s_var = s_var.substring(0,ultimaBarra);
      }
    }
    //Ahora convertimos las barras a puntos
    salida = s_var.replaceAll("/", ".");
  }
  return salida;
}

public void cerrarPaginas(String s_fileDir, String s_address, boolean crearIndex, String dominio)
{
  String barraDir = System.getProperty("file.separator").toString();
  String ruta = s_fileDir.substring(0, s_fileDir.lastIndexOf(barraDir));
  String rutaSalida = ruta + barraDir + "galeria";

  //Este fichero define donde se cerrarán las galerias. En la galeria o en
  //el index.html
  Fichero salidaIndex;

  if (crearIndex)
  {
    //Añadiremos el enlace con la nueva imagen en la pagina index.html
    salidaIndex = new Fichero(rutaSalida + barraDir + "index.html");
  }
  else
  {
      //Añadiremos el enlace con la imagen en la galería que corresponda
      salidaIndex = new Fichero(rutaSalida + barraDir + dominio + ".html");
  }

   String contenido = "";

   contenido += "</div>\n";
   contenido += "</div>\n";
   contenido += "</center>\n";
   contenido += "</body>\n";

   salidaIndex.println(contenido);

}


}
