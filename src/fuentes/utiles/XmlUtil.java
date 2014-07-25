package fuentes.utiles;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import fuentes.utiles.Traza;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;


public class XmlUtil {
  //Objeto donde almacenamos el parseo del xml
  Document documento;
  //Objeto de las trazas
  Traza miTraza;

  /**
   *
   */
  public XmlUtil() {
    miTraza = new Traza();
  }

  /**
   *
   * @param nombreFicheroXML String
   */
  public void cargarXML(String nombreFicheroXML)
  {
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
      DocumentBuilder builder = factory.newDocumentBuilder();
      documento = builder.parse(new File(nombreFicheroXML));
    }
    catch (Exception e) {
      // Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
      miTraza.println("Ha ocurrido un error: "+e.getMessage());
    }
  }

  /**
   *
   */
  public void obtenerValores()
  {
    if (documento != null)
    {
      // Nos devuelve el nodo raíz del documento XML.
      Node nodoRaiz = documento.getFirstChild();
      // Devuelve nodos hijos de un nodo dado
      NodeList listaNodosHijo = nodoRaiz.getChildNodes();


      for (int i = 0; i < listaNodosHijo.getLength(); i++)
      {
        Node unNodoHijo = listaNodosHijo.item(i);
        // Obtener los atributos de un nodo
        System.out.println("NodeName: "+unNodoHijo.getNodeName());
        System.out.println("NodeValue: "+unNodoHijo.getNodeValue());


        //NamedNodeMap atributos = unNodoHijo.getAttributes();
      }

    }
  }

}
