package proyectoenlazador;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import fuentes.constantes.Constante;
import fuentes.descarga.FetchLinks;
import java.util.*;
import fuentes.utiles.DescHref;


/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class FormGoogleSearch extends JDialog{
  public final static int max = 100;
  public final static int min = 1;
  public final static int valor = 1;
  public final static int incremento = 1;

  //Se actualiza en el metodo isFiltro
  int paginasRestantes = 0;
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
  JSpinner SpinNPag = new JSpinner(new SpinnerNumberModel( valor, min, max, incremento ));
  JSpinner SpinPag = new JSpinner(new SpinnerNumberModel( valor, min, max, incremento ));
  JSpinner SpinNResultados = new JSpinner(new SpinnerNumberModel( max, min, max, incremento ));
  JPanel jPanel4 = new JPanel();
  JButton Buscar = new JButton();
  JButton Cancelar = new JButton();
  JLabel jLabel2 = new JLabel();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  FormEnlazador s_formEnlazador;
  JCheckBox RecogerCache = new JCheckBox();
  JPanel jPanel5 = new JPanel();
  JTextField URLSite = new JTextField();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel3 = new JLabel();
  GridBagLayout gridBagLayout1 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  BorderLayout borderLayout1 = new BorderLayout();

  public FormGoogleSearch(Frame frame, String title, boolean modal, FormEnlazador s_var_form) {
    super(frame, title, modal);


    this.s_formEnlazador = s_var_form;
    try {
      jbInit();
      pack();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

/*  public FormGoogleSearch() {
    this(null, "", false);
  }
*/

  private void jbInit() throws Exception {
    this.setFont(new java.awt.Font("Dialog", 0, 12));
    this.setModal(true);
    this.setResizable(false);
    jPanel1.setLayout(borderLayout1);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setDebugGraphicsOptions(0);
    jPanel2.setLayout(gridBagLayout2);
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setLayout(gridBagLayout1);
    jRadioButton1.setText("Número de páginas");
    jRadioButton1.addActionListener(new FormGoogleSearch_jRadioButton1_actionAdapter(this));
    jRadioButton2.setText("La página: ");
    jRadioButton2.addActionListener(new FormGoogleSearch_jRadioButton2_actionAdapter(this));
    jPanel4.setBorder(BorderFactory.createEtchedBorder());
    jPanel4.setLayout(gridBagLayout3);
    Buscar.setMaximumSize(new Dimension(77, 25));
    Buscar.setMinimumSize(new Dimension(77, 25));
    Buscar.setPreferredSize(new Dimension(77, 25));
    Buscar.setSelected(true);
    Buscar.setText("Buscar");
    Buscar.addActionListener(new FormGoogleSearch_Buscar_actionAdapter(this));
    Cancelar.setText("Cancelar");
    Cancelar.addActionListener(new FormGoogleSearch_Cancelar_actionAdapter(this));
    jLabel2.setText("Número de resultados");
    SpinNPag.setMaximumSize(new Dimension(53, 20));
    SpinNPag.setMinimumSize(new Dimension(53, 20));
    SpinPag.setMaximumSize(new Dimension(53, 20));
    SpinNResultados.setMaximumSize(new Dimension(53, 20));
    RecogerCache.setText("Buscar en la Caché de Google");
    RecogerCache.addActionListener(new FormGoogleSearch_RecogerCache_actionAdapter(this));
    jPanel5.setBorder(BorderFactory.createEtchedBorder());
    jPanel5.setLayout(gridBagLayout4);
    URLSite.setToolTipText("");
    URLSite.setText("www.km77.com");
    URLSite.addKeyListener(new FormGoogleSearch_URLSite_keyAdapter(this));
    URLSite.addActionListener(new FormGoogleSearch_URLSite_actionAdapter(this));
    jLabel1.setOpaque(true);
    jLabel1.setText("DIreccion a descargar");
    jLabel3.setText("Páginas a descargar");
    jLabel3.setOpaque(true);
    this.getContentPane().add(jPanel1, BorderLayout.CENTER);
    jPanel1.add(jPanel4, BorderLayout.SOUTH);
    jPanel1.add(jPanel2, BorderLayout.CENTER);
    jPanel4.add(Cancelar,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 28, 11, 111), 0, 0));
    jPanel4.add(Buscar,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 104, 11, 0), 1, 0));
    jPanel3.add(jRadioButton1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(14, 86, 0, 0), 0, -9));
    jPanel3.add(RecogerCache,  new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(22, 86, 7, 49), 67, 0));
    jPanel3.add(SpinNResultados,  new GridBagConstraints(1, 2, 1, 2, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(16, 12, 47, 109), -2, 3));
    jPanel3.add(jLabel2,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(17, 86, 0, 13), 0, 0));
    jPanel3.add(SpinNPag,  new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(14, 12, 0, 109), -2, 3));
    jPanel3.add(SpinPag,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(17, 12, 0, 109), -2, 3));
    jPanel3.add(jRadioButton2,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(18, 86, 7, 40), 0, -10));
    jPanel2.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 23, 164, 273), 0, 0));
    jPanel2.add(jPanel3,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 9, 8, 7), 1, 2));
    jPanel2.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 22, 43, 267), 0, 0));
    jPanel2.add(jPanel5,  new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(15, 9, 0, 7), 0, 7));
    jPanel5.add(URLSite, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(11, 17, 8, 17), 239, 1));
    buttonGroup1.add(jRadioButton1);
    buttonGroup1.add(jRadioButton2);
    jRadioButton1.setSelected(true);
    jRadioButton1_actionPerformed(null);
  }

  public void descargaGoogle(String site, int num_pag, int pag_unica, int num_resultados)
  {
    String url = "http://www.google.com/search?";
    String url_compuesta = "";
    int restantes = 1;
    // la primera posicion es el numero de enlaces y la segunda el numero
    // de paginas restantes
    ArrayList numEnlaces = null;
    ArrayList listaFinal = new ArrayList();


    if (this.jRadioButton1.isSelected())
    {
      int total = ((Integer)SpinNPag.getValue()).intValue();
      System.out.println("Total: "+total+" restantes: "+restantes);
      int i = 1;
      while (i<=total)
      {
        url_compuesta = url + "num="+num_resultados+"&as_sitesearch="+site+"&start="+(i-1)*num_resultados;
        numEnlaces = descargaURLGoogle(url_compuesta,i,num_resultados);
        System.out.println("Se recogen " + numEnlaces.size() +" enlaces" );

        for (int j=0;j<numEnlaces.size();j++)
        {
          if (listaFinal.indexOf((String)numEnlaces.get(j)) == -1)
          {
            listaFinal.add(numEnlaces.get(j));
          }
        }
        i++;

        System.out.println("Total: "+total+" restantes: "+restantes);
        System.out.println("url_compuesta: "+url_compuesta);
      }

    }
    else if (this.jRadioButton2.isSelected())
    {
        //Se recoge sólo una página
        url_compuesta = url + "num="+num_resultados+"&as_sitesearch="+site+"&start="+(pag_unica-1)*num_resultados;
        numEnlaces = descargaURLGoogle(url_compuesta,1,num_resultados);
        //recogidas = Integer.valueOf( (String)numEnlaces.get(0)).intValue();

       for (int j=0;j<numEnlaces.size();j++)
       {
         if (listaFinal.indexOf((String)numEnlaces.get(j)) == -1)
         {
           listaFinal.add(numEnlaces.get(j));
         }
       }


    }

    System.out.println("Tenemos: " + listaFinal.size());
    Collections.sort(listaFinal);
    this.s_formEnlazador.actualizarListadoSinBorrar(listaFinal,this.s_formEnlazador.getModeloTablaURLS());
    JOptionPane.showMessageDialog(this.s_formEnlazador, "URL's encontradas:  "+listaFinal.size());
    System.out.println("URL Creada: "+url_compuesta);
  }


  ArrayList descargaURLGoogle(String url,int pos_pag,int num_resultados)
  {
    FetchLinks textoHTML = null;
    ArrayList arrTemp = null;
    ArrayList arrTempURLS = new ArrayList();

    try
    {
      textoHTML = new FetchLinks(url);
      // Al llamar a conectarURL se recoge una lista que contiene a su vez dos listas
      // con las imágenes de la pagina en la que se conecta y cada uno de los enlaces que aparecen en ella.
      arrTemp = (ArrayList) textoHTML.conectarURL();
      arrTempURLS = filtrarURLS((ArrayList) arrTemp.get(1),pos_pag,num_resultados);


/*      synchronized (this)
      {
        this.s_formEnlazador.actualizarListadoSinBorrar(arrTempURLS, this.s_formEnlazador.ListaArchivos);
        this.s_formEnlazador.actualizarListado( new ArrayList(), this.s_formEnlazador.m_ListaImagenes);
      }
*/
    }
    catch(Exception e)
    {
      System.out.println("Error en FormGoogleSearch.descargaURL: "+e.getMessage());
    }
    finally
    {
//        numEnlaces.add(""+arrTempURLS.size());
//        numEnlaces.add(""+this.paginasRestantes);
//        System.out.println("restantes: "+arrTempURLS.size() + ";" + this.paginasRestantes);
    }
    return arrTempURLS;

  }



/*
  public void descargaGoogle(String site, int num_pag, int pag_unica, int num_resultados)
  {
    String url = "http://www.google.com/search?";
    String url_compuesta = "";
    int recogidas = 0;
    int restantes = 1;
    // la primera posicion es el numero de enlaces y la segunda el numero
    // de paginas restantes
    ArrayList numEnlaces = null;



    if (this.jRadioButton1.isSelected())
    {
      int total = ((Integer)SpinNPag.getValue()).intValue();
      System.out.println("Total: "+total+" restantes: "+restantes);
      int i = 1;
      while (i<=total && restantes >= 0)
      {
        url_compuesta = url + "num="+num_resultados+"&as_sitesearch="+site+"&start="+(i-1)*num_resultados;
        numEnlaces = descargaURLGoogle(url_compuesta,i,num_resultados);
        recogidas += Integer.valueOf( (String) numEnlaces.get(0)).intValue();
        restantes = Integer.valueOf( (String) numEnlaces.get(1)).intValue();
        System.out.println("paso: "+i);
        System.out.println("url_compuesta: "+url_compuesta);
        System.out.println("Total: "+total+" restantes: "+restantes);
        i++;
      }
    }
    else if (this.jRadioButton2.isSelected())
    {
        //Se recoge sólo una página
        url_compuesta = url + "num="+num_resultados+"&as_sitesearch="+site+"&start="+(pag_unica-1)*num_resultados;
        numEnlaces = descargaURLGoogle(url_compuesta,1,num_resultados);
        recogidas = Integer.valueOf( (String)numEnlaces.get(0)).intValue();
    }

    JOptionPane.showMessageDialog(this.s_formEnlazador, "URL's encontradas:  "+recogidas);
    System.out.println("URL Creada: "+url_compuesta);
  }


  ArrayList descargaURLGoogle(String url,int pos_pag,int num_resultados)
  {
    FetchLinks textoHTML = null;
    ArrayList arrTemp = null;
    ArrayList arrTempURLS = new ArrayList();
    ArrayList numEnlaces = new ArrayList();

    try
    {
      textoHTML = new FetchLinks(url);
      // Al llamar a conectarURL se recoge una lista que contiene a su vez dos listas
      // con las imágenes de la pagina en la que se conecta y cada uno de los enlaces que aparecen en ella.
      arrTemp = (ArrayList) textoHTML.conectarURL();
      arrTempURLS = filtrarURLS((ArrayList) arrTemp.get(1),pos_pag,num_resultados);


      synchronized (this)
      {
        this.s_formEnlazador.actualizarListadoSinBorrar(arrTempURLS, this.s_formEnlazador.ListaArchivos);
        this.s_formEnlazador.actualizarListado( new ArrayList(), this.s_formEnlazador.m_ListaImagenes);
      }
    }
    catch(Exception e)
    {
      System.out.println("Error en FormGoogleSearch.descargaURL: "+e.getMessage());
    }
    finally
    {
        numEnlaces.add(""+arrTempURLS.size());
        numEnlaces.add(""+this.paginasRestantes);
        System.out.println("restantes: "+arrTempURLS.size() + ";" + this.paginasRestantes);
        return numEnlaces;
    }

  }
*/
  ArrayList filtrarURLS(ArrayList entrada,int pos_pag,int num_resultados)
  {
    ArrayList salida = new ArrayList();
    try
    {
      String cortado = "";
      int pos_ini = 0, pos_fin = 0;

      if (entrada != null) {
        this.paginasRestantes = 0;
        for (int i = 0; i < entrada.size(); i++) {

          //(pos_pag-1)*100 / num_resultados <= 100 y el total ha de ser menor a 1000

          cortado = ((DescHref)entrada.get(i)).getUrl();
//          System.out.println("cortado: "+cortado);
          if ( (pos_ini = cortado.indexOf("&start=")) != -1)
          {
            cortado = cortado.substring(pos_ini + "&start=".length(),cortado.length());
            if ( (pos_fin = cortado.indexOf("&")) == -1)
            {
              pos_fin = cortado.length();
            }
            cortado = cortado.substring(0, pos_fin);
            try
            {
              if (new Integer(cortado).intValue() > (pos_pag - 1) * num_resultados)
              {
                this.paginasRestantes++;
              }
            }
            catch(java.lang.NumberFormatException e)
            {
              System.out.println(e.getMessage());
            }
          }

          if (!isFiltro( ((DescHref)entrada.get(i)).getUrl() )) {
            salida.add(((DescHref)entrada.get(i)).getUrl());
          }
        }
      }
    }
    catch(Exception e)
    {
      System.out.println("Excepcion en filtrarURLS:"+e.getMessage());
    }

    return salida;
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
    String filtro[] = Constante.getTipoFichero();

    if (var_url != null)
    {
       if (filtro != null && var_url != null && var_url.length() > 0) {
        for (int i = 0; i < filtro.length; i++) {
          if (filtro[i].length() > 0 && var_url.indexOf(filtro[i]) != -1) {
            encontrado = true;
          }
        }
      }
      //Si el resultado esta cacheado comprobamos si debemos descargarlo
      if (!Constante.isDescargarDeCache()) {
        if (var_url.indexOf("q=cache") != -1) {
          encontrado = true;
        }
      }
    }

    return encontrado;
  }

  void cancel() {
    dispose();
  }

  void Buscar_actionPerformed(ActionEvent e) {
    this.Buscar.setEnabled(false);
    descargaGoogle(URLSite.getText(),((Integer)SpinNPag.getValue()).intValue(),((Integer)SpinPag.getValue()).intValue(),((Integer)SpinNResultados.getValue()).intValue());
    this.Buscar.setEnabled(true);
  }

  void Cancelar_actionPerformed(ActionEvent e) {
    cancel();
  }

  void jRadioButton2_actionPerformed(ActionEvent e) {
    if (jRadioButton2.isSelected())
    {
      this.SpinNPag.setEnabled(false);
      this.SpinPag.setEnabled(true);
    }
  }

  void jRadioButton1_actionPerformed(ActionEvent e) {
    if (jRadioButton1.isSelected())
    {
      this.SpinPag.setEnabled(false);
      this.SpinNPag.setEnabled(true);
    }

  }

  void URLSite_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      System.out.println("Pulsa intro");
      Buscar_actionPerformed(null);
    }
  }

  void RecogerCache_actionPerformed(ActionEvent e) {
    if (RecogerCache.isSelected())
    {
      Constante.setDescargarDeCache(true);
      System.out.println("ponemos descargachache a true");
    }
    else
    {
        Constante.setDescargarDeCache(false);
        System.out.println("ponemos descargachache a false");
    }
  }

  void URLSite_actionPerformed(ActionEvent e) {

  }
}

class FormGoogleSearch_Buscar_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_Buscar_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Buscar_actionPerformed(e);
  }
}

class FormGoogleSearch_Cancelar_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_Cancelar_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Cancelar_actionPerformed(e);
  }
}

class FormGoogleSearch_jRadioButton2_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_jRadioButton2_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton2_actionPerformed(e);
  }
}

class FormGoogleSearch_jRadioButton1_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_jRadioButton1_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jRadioButton1_actionPerformed(e);
  }
}

class FormGoogleSearch_RecogerCache_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_RecogerCache_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.RecogerCache_actionPerformed(e);
  }
}

class FormGoogleSearch_URLSite_actionAdapter implements java.awt.event.ActionListener {
  FormGoogleSearch adaptee;

  FormGoogleSearch_URLSite_actionAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.URLSite_actionPerformed(e);
  }
}

class FormGoogleSearch_URLSite_keyAdapter extends java.awt.event.KeyAdapter {
  FormGoogleSearch adaptee;

  FormGoogleSearch_URLSite_keyAdapter(FormGoogleSearch adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.URLSite_keyPressed(e);
  }
}
