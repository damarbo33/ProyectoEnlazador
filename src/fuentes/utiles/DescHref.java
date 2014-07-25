package fuentes.utiles;

import java.util.*;

public class DescHref implements Comparable
{
  private String url;
  private String descripcion;
  private ArrayList listaURL = new ArrayList();
  private ArrayList listaIMG = new ArrayList();


  public DescHref(String var_url, String var_descripcion)
  {
    url = var_url;
    descripcion = var_descripcion;
  }

  public String getUrl()
  {
    return url;
  }

  public String getDesc()
  {
    return descripcion;
  }

  public int compareTo(Object o) {
    DescHref otroDescHref = (DescHref) o;
    //podemos hacer esto porque String implementa Comparable
    return url.compareTo(otroDescHref.getUrl());
  }
}
