package proyectoenlazador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import proyectoenlazador.FormEnlazador;
import javax.swing.table.DefaultTableModel;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class FormEnlazador_URLPlantilla extends JDialog implements ActionListener
{
  FormEnlazador formEnlazador;

//  DefaultListModel listaGlobal = new DefaultListModel();

  JPanel panel1 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JButton Aceptar = new JButton();
  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton Cerrar = new JButton();
  JPanel jPanel2 = new JPanel();
  JTextField textFin = new JTextField();
  JLabel jLabel4 = new JLabel();
  JTextField textInicio = new JTextField();
  JLabel jLabel3 = new JLabel();
  JCheckBox checkRellenar = new JCheckBox();
  JCheckBox checkRango = new JCheckBox();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JRadioButton radioImagenes = new JRadioButton();
  JRadioButton radioURL = new JRadioButton();
  ButtonGroup buttonGroupListas = new ButtonGroup();
  JLabel jLabel5 = new JLabel();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  GridBagLayout gridBagLayout3 = new GridBagLayout();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JTextField editURL = new JTextField();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  FlowLayout flowLayout1 = new FlowLayout();


  public FormEnlazador_URLPlantilla(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public FormEnlazador_URLPlantilla(Frame parent, FormEnlazador var_formEnlazador) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    this.formEnlazador = var_formEnlazador;
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  FormEnlazador_URLPlantilla() {
    this(null);
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    image1 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "about.png"));
    this.setModal(true);
    this.setTitle("Generador de URL's");
    panel1.setLayout(borderLayout1);
    Aceptar.setBounds(new Rectangle(100, 12, 73, 25));
    Aceptar.setText("Insertar");
    Aceptar.addActionListener(new FormEnlazador_URLPlantilla_Aceptar_actionAdapter(this));
    jPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setMinimumSize(new Dimension(400, 265));
    jPanel1.setPreferredSize(new Dimension(400, 265));
    jPanel1.setLayout(flowLayout1);
    Cerrar.setBounds(new Rectangle(217, 12, 77, 25));
    Cerrar.setText("Cerrar");
    Cerrar.addActionListener(new FormEnlazador_URLPlantilla_Cerrar_actionAdapter(this));
    Cerrar.addActionListener(new FormEnlazador_URLPlantilla_Cerrar_actionAdapter(this));
    insetsPanel1.setAlignmentX((float) 0.5);
    insetsPanel1.setBorder(BorderFactory.createRaisedBevelBorder());
    insetsPanel1.setDebugGraphicsOptions(0);
    insetsPanel1.setMinimumSize(new Dimension(30, 50));
    insetsPanel1.setPreferredSize(new Dimension(30, 50));
    insetsPanel1.setLayout(null);
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setMinimumSize(new Dimension(380, 78));
    jPanel2.setPreferredSize(new Dimension(386, 78));
    jPanel2.setLayout(gridBagLayout3);
    textFin.setEnabled(false);
    textFin.setText("");
    jLabel4.setEnabled(false);
    jLabel4.setText("Fin");
    textInicio.setEnabled(false);
    textInicio.setText("");
    jLabel3.setEnabled(false);
    jLabel3.setText("Inicio");
    checkRellenar.setToolTipText("");
    checkRellenar.setSelected(true);
    checkRellenar.setText("Rellenar números con ceros");
    checkRango.setEnabled(true);
    checkRango.setToolTipText("");
    checkRango.setText("Especificar rango");
    checkRango.addActionListener(new FormEnlazador_URLPlantilla_checkRango_actionAdapter(this));
    checkRango.addActionListener(new FormEnlazador_URLPlantilla_checkRango_actionAdapter(this));
//    checkRango.addActionListener(new FormEnlazador_URLPlantilla_checkRango_actionAdapter(this));
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setPreferredSize(new Dimension(386, 94));
    jPanel3.setLayout(gridBagLayout4);
    jPanel4.setBorder(BorderFactory.createEtchedBorder());
    jPanel4.setMinimumSize(new Dimension(381, 54));
    jPanel4.setPreferredSize(new Dimension(386, 54));
    jPanel4.setLayout(gridBagLayout2);
    radioImagenes.setAlignmentX((float) 0.5);
    radioImagenes.setText("Listado de imágenes");
    radioURL.setAlignmentX((float) 0.5);
    radioURL.setSelected(true);
    radioURL.setText("Listado de URL\'s");
    jLabel5.setText("Añadir a:");
    jLabel2.setText("* Usar #s para especificar posiciones usadas por un enumerador");
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setText("URL a generar");
    editURL.setVerifyInputWhenFocusTarget(true);
    editURL.setText("http://www.ejemplo.com/dominio##/index.htm");
    editURL.addKeyListener(new FormEnlazador_URLPlantilla_editURL_keyAdapter(this));
    this.getContentPane().add(panel1, null);
    panel1.add(jPanel1,  BorderLayout.CENTER);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    jPanel1.add(jPanel3, null);
    jPanel1.add(jPanel2, null);
    jPanel2.add(checkRellenar,  new GridBagConstraints(0, 0, 2, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(6, 9, 0, 0), 14, 0));
    jPanel2.add(checkRango,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(9, 9, 13, 0), 9, 0));
    jPanel2.add(textFin,  new GridBagConstraints(4, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(9, 0, 13, 48), 51, 0));
    jPanel2.add(jLabel3,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(13, 26, 13, 0), 3, 0));
    jPanel2.add(textInicio,  new GridBagConstraints(2, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 13, 0), 51, 0));
    jPanel2.add(jLabel4,  new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(12, 11, 13, 0), 5, 0));
    jPanel3.add(jLabel2,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(11, 2, 10, 36), 26, 0));
    jPanel3.add(jLabel1,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(8, 2, 0, 254), 45, 0));
    jPanel3.add(editURL,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(10, 2, 0, 2), 150, 0));
    jPanel1.add(jPanel4, null);
    jPanel4.add(jLabel5,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(7, 16, 0, 0), 13, 0));
    jPanel4.add(radioURL,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 0), 0, 0));
    jPanel4.add(radioImagenes,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 5, 72), 0, 0));
    insetsPanel1.add(Aceptar, null);
    insetsPanel1.add(Cerrar, null);
    setResizable(false);
    buttonGroupListas.add(radioURL);
    buttonGroupListas.add(radioImagenes);
  }

  public void crearURLPlantilla (String URL)
  {
    try {

      DefaultListModel listadoURLS = new DefaultListModel();

      int ini = 0, fin = 0;
      boolean rellenar0 = this.checkRellenar.isSelected();
      int inicioContador = 0;



      if (this.radioImagenes.isSelected()){
        this.obtenerURLLista(this.formEnlazador.getModeloTablaIMGS(),listadoURLS);
      } else {
          this.obtenerURLLista(this.formEnlazador.getModeloTablaURLS(),listadoURLS);
      }

      ini = URL.indexOf("#");

      if (ini != -1)
      {

        fin = ini;
        do
        {
          fin++;
        }
        while ( (fin < URL.length()) && ('#' == URL.charAt(fin)));

        System.out.println("ini: " + ini);
        System.out.println("fin: " + fin);

        int rango = fin - ini;
        double totalNumeros = Math.pow(10, rango);
        String resultado = "";
        int relleno = 0;
        String numPlantilla = "";


        try {
          int i_inicio = 0;
          i_inicio = Integer.parseInt(this.textInicio.getText());
          inicioContador = i_inicio;
        }
        catch (final NumberFormatException e) {
          System.out.println("Error en el numero inicial");
        }

        try {
          int i_final = 0;
          i_final = Integer.parseInt(this.textFin.getText());
          totalNumeros = i_final;
        }
        catch (final NumberFormatException e) {
          System.out.println("Error en el numero final");
        }



        if (totalNumeros < inicioContador) inicioContador = 0;

        for (int i = inicioContador; i < totalNumeros; i++) {
          numPlantilla = "";
          relleno = rango - String.valueOf(i).length();
          if (rellenar0) {
            for (int j = 0; j < relleno; j++) {
              numPlantilla += "0";
            }
          }
          numPlantilla += String.valueOf(i);
          resultado = URL.substring(0, ini);
          resultado += numPlantilla;
          resultado += URL.substring(fin, URL.length());
          System.out.println("resultado: " + resultado);
          this.aniadirURLLista(resultado, listadoURLS);
        }

        JOptionPane.showMessageDialog(this, "Se han añadido correctamente "+(int)(totalNumeros - inicioContador)+" enlaces");

      } else if (URL != null && URL.length() > 0) {
        this.aniadirURLLista(URL, listadoURLS);
        JOptionPane.showMessageDialog(this, "Se ha añadido la URL:\n"+URL);
      }



      if (this.radioImagenes.isSelected()){
        this.actualizarURLLista(this.formEnlazador.getModeloTablaIMGS(),listadoURLS);
      } else {
          this.actualizarURLLista(this.formEnlazador.getModeloTablaURLS(),listadoURLS);
      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

/*
*  Pintado de la lista de url
*/
  protected void obtenerURLLista(JList myList, DefaultListModel var_listadoURLS) {
    ListModel modelo = myList.getModel();
    var_listadoURLS.clear();
  //Copiamos los valores de la lista en un objeto DefaultListModel
    for (int i = 0; i < modelo.getSize(); i++) {
      var_listadoURLS.addElement(modelo.getElementAt(i));
    }
  }



      /*
*  Pintado de la lista de url
*/
  protected void obtenerURLLista(DefaultTableModel myList, DefaultListModel var_listadoURLS) {
    var_listadoURLS.clear();
  //Copiamos los valores de la lista en un objeto DefaultListModel
    for (int i = 0; i < myList.getRowCount(); i++) {
      var_listadoURLS.addElement(myList.getValueAt(i,0));
    }
  }


  protected void aniadirURLLista(String URL, DefaultListModel var_listadoURLS) {
    //Copiamos los valores de la lista en un objeto DefaultListModel
    if (URL != null && URL.length() > 0)
      var_listadoURLS.addElement(URL);
  }

  protected void actualizarURLLista(JList myList,DefaultListModel var_listadoURLS) {
    //Pintamos la lista con los elementos aniadidos
    myList.setModel(var_listadoURLS);
  }

  protected void actualizarURLLista(DefaultTableModel myList,DefaultListModel var_listadoURLS) {
    //Pintamos la lista con los elementos aniadidos
    for (int i=0;i<var_listadoURLS.getSize();i++)
    {
      Object[] dat = {var_listadoURLS.get(i)};
      myList.addRow(dat);
    }
  }


  //Modificado para poder salir cuando se cierra la ventana
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      cancel();
    }
    super.processWindowEvent(e);
  }

  //Cerrar el cuadro de diálogo
  void cancel() {
    dispose();
  }

  //Cerrar el cuadro de diálogo tras un suceso de un botón
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == Cerrar) {
      cancel();
    }
  }

  void checkRango_actionPerformed(ActionEvent e) {
      jLabel3.setEnabled(this.checkRango.isSelected());
      jLabel4.setEnabled(this.checkRango.isSelected());
      textInicio.setEnabled(this.checkRango.isSelected());
      textFin.setEnabled(this.checkRango.isSelected());
  }

  void Cerrar_actionPerformed(ActionEvent e) {
    cancel();
  }


class FormEnlazador_URLPlantilla_Cerrar_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_Cerrar_actionAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Cerrar_actionPerformed(e);
  }
}


class FormEnlazador_URLPlantilla_checkRango_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_checkRango_actionAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.checkRango_actionPerformed(e);
  }
}

  void editURL_keyPressed(KeyEvent e) {
    if (e.getSource() == editURL && e.getKeyCode() == KeyEvent.VK_ENTER)
    {
      Aceptar_actionPerformed(null);
    }

  }

  void Aceptar_actionPerformed(ActionEvent e) {
    this.crearURLPlantilla(this.editURL.getText());
//    cancel();
  }

}

class FormEnlazador_URLPlantilla_editURL_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_editURL_keyAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.editURL_keyPressed(e);
  }
}

class FormEnlazador_URLPlantilla_Aceptar_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_Aceptar_actionAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Aceptar_actionPerformed(e);
  }
}

class FormEnlazador_URLPlantilla_Cerrar_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_Cerrar_actionAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.Cerrar_actionPerformed(e);
  }
}

class FormEnlazador_URLPlantilla_checkRango_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_URLPlantilla adaptee;

  FormEnlazador_URLPlantilla_checkRango_actionAdapter(FormEnlazador_URLPlantilla adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.checkRango_actionPerformed(e);
  }
}


