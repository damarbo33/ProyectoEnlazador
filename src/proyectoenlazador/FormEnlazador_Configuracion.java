package proyectoenlazador;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import fuentes.constantes.*;
import com.borland.jbcl.layout.*;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class FormEnlazador_Configuracion
    extends JDialog
    implements ActionListener {

  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  String product = "";
  String version = "1.0";
  String copyright = "Copyright (c) 2007";
  String comments = "";
  JPanel JPanel1 = new JPanel();
  JButton btnCancelar = new JButton();
  JButton BtnAceptar = new JButton();
  JPanel Configuracion = new JPanel();
  JCheckBox jCheckBox1 = new JCheckBox();
  JLabel jLabel5 = new JLabel();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox3 = new JCheckBox();
  JLabel jLabel8 = new JLabel();
  JTextField jTextField4 = new JTextField();
  JButton jButton2 = new JButton();
  JFileChooser filechooser = new JFileChooser();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  GridBagLayout gridBagLayout2 = new GridBagLayout();
  JCheckBox jCheckBox4 = new JCheckBox();
  JButton jButton1 = new JButton();
  JPanel PanelFiltros = new JPanel();
  JLabel jLabel2 = new JLabel();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel4 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel3 = new JLabel();
  JPanel jPanel2 = new JPanel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JTextField edit_imgFiltradas = new JTextField();
  JTextField edit_excluidas = new JTextField();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JLabel jLabel6 = new JLabel();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField5 = new JTextField();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JPanel jPanel5 = new JPanel();
  GridBagLayout gridBagLayout4 = new GridBagLayout();
  GridBagLayout gridBagLayout7 = new GridBagLayout();
  JPanel PanelUtilidades = new JPanel();
  JPanel jPanel6 = new JPanel();
  JCheckBox jCheckGaleria = new JCheckBox();
  JLabel jLabel10 = new JLabel();
  JTextField jTextField6 = new JTextField();
  JLabel jLabel12 = new JLabel();
  JCheckBox jCheckBox5 = new JCheckBox();
  GridBagLayout gridBagLayout6 = new GridBagLayout();
  JPanel jPanel7 = new JPanel();
  JLabel jLabel14 = new JLabel();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  GridBagLayout gridBagLayout9 = new GridBagLayout();
  GridBagLayout gridBagLayout10 = new GridBagLayout();
  GridBagLayout gridBagLayout5 = new GridBagLayout();
  GridBagLayout gridBagLayout8 = new GridBagLayout();
  GridBagLayout gridBagLayout11 = new GridBagLayout();
  JCheckBox jCheckBox6 = new JCheckBox();
  JCheckBox jCheckBox7 = new JCheckBox();
  JCheckBox jCheckBox8 = new JCheckBox();
  VerticalFlowLayout verticalFlowLayout1 = new VerticalFlowLayout();
  GridBagLayout gridBagLayout1 = new GridBagLayout();



//  jCheckBox1.setSelected(Constante.isSOBREESCRIBIR());

  public FormEnlazador_Configuracion(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  FormEnlazador_Configuracion() {
    this(null);
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    jPanel4.setBorder(BorderFactory.createEtchedBorder());
    jPanel4.setMinimumSize(new Dimension(135, 30));
    jPanel4.setPreferredSize(new Dimension(188, 30));
    jPanel4.setLayout(gridBagLayout11);
    jPanel3.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setMinimumSize(new Dimension(261, 230));
    jPanel3.setPreferredSize(new Dimension(261, 230));
    jPanel3.setRequestFocusEnabled(true);
    jPanel3.setLayout(verticalFlowLayout1);
    this.setFont(new java.awt.Font("Arial", 0, 13));
    this.setModal(true);
    setResizable(false);
    this.setTitle("Configuración");
    btnCancelar.setText("Cancelar");
    btnCancelar.addActionListener(new FormEnlazador_Configuracion_btnCancelar_actionAdapter(this));
    JPanel1.setBorder(BorderFactory.createEtchedBorder());
    JPanel1.setMinimumSize(new Dimension(630, 50));
    JPanel1.setPreferredSize(new Dimension(500, 50));
//    JPanel1.setMinimumSize(new Dimension(50, 50));
//    JPanel1.setPreferredSize(new Dimension(50, 50));
    JPanel1.setRequestFocusEnabled(true);
    JPanel1.setLayout(gridBagLayout2);
    BtnAceptar.setText("Aceptar");
    BtnAceptar.addActionListener(new FormEnlazador_Configuracion_BtnAceptar_actionAdapter(this));
    Configuracion.setForeground(Color.black);
    Configuracion.setBorder(null);
    Configuracion.setDebugGraphicsOptions(0);
    Configuracion.setMinimumSize(new Dimension(486, 600));
    Configuracion.setPreferredSize(new Dimension(486, 600));
    Configuracion.setOpaque(true);
//    Configuracion.setMaximumSize(new Dimension(1000, 1000));
//    Configuracion.setMinimumSize(new Dimension(0, 0));
//    Configuracion.setPreferredSize(new Dimension(0, 0));
    Configuracion.setLayout(gridBagLayout1);
    jCheckBox1.setText("Sobreescribir imágenes existentes");
    jCheckBox1.setContentAreaFilled(false);
    jCheckBox1.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox1.setSelected(Constante.isSOBREESCRIBIR());
    jCheckBox1.addActionListener(new FormEnlazador_Configuracion_jCheckBox1_actionAdapter(this));
    jLabel5.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel5.setOpaque(true);
    jLabel5.setToolTipText("");
    jLabel5.setVerifyInputWhenFocusTarget(true);
    jLabel5.setText("Descargas");
    jCheckBox2.setText("Descargar de dominios principales .com, .es ...");
    jCheckBox2.setContentAreaFilled(false);
    jCheckBox2.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox2.setSelected(Constante.isOtrosDominios());
    jCheckBox3.setText("Descargar páginas de otros Sites");
    jCheckBox3.setToolTipText("");
    jCheckBox3.setContentAreaFilled(false);
    jCheckBox3.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox3.setSelected(Constante.isOtrosSites());
    jLabel8.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel8.setOpaque(true);
    jLabel8.setText("Directorio de descarga");
    jTextField4.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField4.setText(Constante.getDirBase());
    jButton2.setSelectedIcon(null);
    jButton2.setText("...");
    jButton2.addActionListener(new FormEnlazador_Configuracion_jButton2_actionAdapter(this));
    jButton2.addMouseListener(new FormEnlazador_Configuracion_jButton2_mouseAdapter(this));
    jButton2.addKeyListener(new FormEnlazador_Configuracion_jButton2_keyAdapter(this));
//    jTabbedPane1.setMinimumSize(new Dimension(10000, 10000));
//    jTabbedPane1.setPreferredSize(new Dimension(500, 500));
    jTabbedPane1.setMinimumSize(new Dimension(495, 400));
    jTabbedPane1.setPreferredSize(new Dimension(500, 400));
    jTabbedPane1.setRequestFocusEnabled(true);
    jCheckBox4.setText("Crear log de Errores");
    jCheckBox4.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox4.setSelected(Constante.isTrazas());
    jButton1.setText("Borrar");
    jButton1.addActionListener(new FormEnlazador_Configuracion_jButton1_actionAdapter(this));
    PanelFiltros.setLayout(gridBagLayout8);
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel2.setOpaque(true);
    jLabel2.setText("Tamaño mínimo de descarga");
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setDebugGraphicsOptions(0);
    jPanel1.setLayout(gridBagLayout6);
    jLabel4.setText("Ancho");
    jTextField2.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField2.setText(new Integer(Constante.getMIN_WIDTH()).toString());
    jTextField6.setText(new Integer(Constante.getIMAGE_TIME_OUT()).toString());
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField1.setText(new Integer(Constante.getMIN_HEIGHT()).toString());
    jLabel3.setText("Alto");
    jPanel2.setBorder(BorderFactory.createEtchedBorder());
    jPanel2.setLayout(gridBagLayout7);
    jLabel11.setText("Excluir url con las siguientes palabras");
    jLabel11.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setOpaque(true);
    jLabel1.setText("Filtro de url\'s");
    jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
    jLabel13.setToolTipText("");
    jLabel13.setText("Descargar los siguientes tipos de imágenes");
    edit_imgFiltradas.setText(Constante.getPalabrasProhibidas().toString());
    edit_imgFiltradas.setFont(new java.awt.Font("Dialog", 0, 13));
    edit_imgFiltradas.setText(Constante.getImgDescarga());
    edit_excluidas.setFont(new java.awt.Font("Dialog", 0, 13));
    edit_excluidas.setText(Constante.getPalabrasProhibidas().toString());
    jLabel6.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel6.setText("Compresión JPG (0-100)");
    jTextField3.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField3.setText(new Integer(Constante.getCalidadJPEG()).toString());
    jTextField5.setText("jTextField5");
    jTextField5.setFont(new java.awt.Font("Dialog", 0, 13));
    jTextField5.setText(new Integer(Constante.getMAX_THREAD() + 1).toString());
    jLabel9.setFont(new java.awt.Font("Dialog", 0, 11));
    jLabel9.setText("Descargas concurrentes");
    jLabel7.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel7.setOpaque(true);
    jLabel7.setText("Parámetros");
    jPanel5.setBorder(BorderFactory.createEtchedBorder());
    jPanel5.setLayout(gridBagLayout4);
    PanelUtilidades.setLayout(gridBagLayout5);
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
    jPanel6.setLayout(gridBagLayout9);
    jCheckGaleria.setText("Crear galería de imágenes en el directorio de descarga");
    this.jCheckGaleria.setSelected(Constante.isB_galeria());
    jLabel10.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel10.setOpaque(true);
    jLabel10.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel10.setText("Galería");
    jTextField6.setFont(new java.awt.Font("Dialog", 0, 13));
    jLabel12.setText("Descarga Timeout");
    jLabel12.setFont(new java.awt.Font("Dialog", 0, 11));
    jCheckBox5.setContentAreaFilled(false);
    jCheckBox5.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox5.setText("Seguir un segundo nivel de enlaces (Lento)");

    if ( Constante.getNIVEL_DESCARGA() == 2 )
    {
      jCheckBox5.setSelected(true);
    }
    else
    {
        jCheckBox5.setSelected(false);
    }

    if (Constante.isNoDescargarRepetidos())
    {
      jCheckBox7.setSelected(true);
    }
    else
    {
        jCheckBox7.setSelected(false);
    }

    if (Constante.isVaciarHistorial())
    {
      jCheckBox8.setSelected(true);
    }
    else
    {
        jCheckBox8.setSelected(false);
    }


    jPanel7.setBorder(BorderFactory.createEtchedBorder());
    jPanel7.setLayout(gridBagLayout10);
    jLabel14.setText("Dirección IP");
    jLabel15.setPreferredSize(new Dimension(40, 15));
    jLabel15.setRequestFocusEnabled(true);
    jLabel15.setText("Puerto");
    jTextField7.setText(Constante.getProxyIP());
    jTextField8.setText(Constante.getProxyPort());
    jLabel16.setText("Proxy");
    jLabel16.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel16.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel16.setOpaque(true);
    PanelUtilidades.setMaximumSize(new Dimension(2147483647, 2147483647));
    PanelUtilidades.setInputVerifier(null);
    jCheckBox6.setText("Recomprimir imágenes");
    jCheckBox6.addActionListener(new FormEnlazador_Configuracion_jCheckBox6_actionAdapter(this));
    jCheckBox6.setToolTipText("");
    jCheckBox6.setContentAreaFilled(false);
    jCheckBox6.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox6.setSelected(Constante.isRecomprimir());
    jTextField3.setEnabled(jCheckBox6.isSelected());
    jCheckBox7.setDebugGraphicsOptions(0);
    jCheckBox7.setActionCommand("");
    jCheckBox7.setContentAreaFilled(false);
    jCheckBox7.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox7.setText("No descargar imágenes ya descargadas");
    jCheckBox7.addActionListener(new FormEnlazador_Configuracion_jCheckBox7_actionAdapter(this));
    jCheckBox8.setContentAreaFilled(false);
    jCheckBox8.setMargin(new Insets(0, 0, 0, 0));
    jCheckBox8.setText("Vaciar historial de descargas al salir");
    this.getContentPane().add(JPanel1, BorderLayout.SOUTH);
    jTabbedPane1.add(Configuracion, "Descargas");
    this.getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
    JPanel1.add(BtnAceptar,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 173, 10, 0), 7, 0));
    JPanel1.add(btnCancelar,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(11, 47, 10, 185), 5, 0));
    jPanel1.add(jLabel4,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(34, 6, 0, 41), 2, 0));
    jPanel1.add(jTextField2,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 7, 38, 33), -2129, 0));
    jPanel1.add(jTextField1,    new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 24, 38, 14), -2129, 0));
    jPanel1.add(jLabel3, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(34, 23, 0, 23), 15, 0));
    PanelFiltros.add(jLabel2,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(82, 7, 0, 0), 6, 0));
    PanelFiltros.add(jLabel1,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(82, 10, 0, 215), 9, 0));
    PanelFiltros.add(jPanel2,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 90, 6), 5, -3));
    jTabbedPane1.add(PanelUtilidades,  "Utilidades");
    jPanel7.add(jTextField8,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 13, 9, 88), 103, 0));
    jPanel7.add(jTextField7,  new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(14, 13, 0, 88), 103, 0));
    jPanel7.add(jLabel14,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(14, 81, 0, 0), 0, 0));
    jPanel7.add(jLabel15,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 81, 9, 16), 0, 0));
    PanelUtilidades.add(jLabel10,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(41, 61, 0, 382), 2, -4));
    PanelUtilidades.add(jLabel16,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(28, 61, 0, 379), 12, -4));
    PanelUtilidades.add(jPanel6,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 61, 0, 73), 0, 4));
    jPanel6.add(jCheckGaleria,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 24, 17, 28), 6, 0));
    PanelUtilidades.add(jPanel7,  new GridBagConstraints(0, 3, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 61, 66, 73), 0, 4));
    jPanel2.add(edit_excluidas,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 12, 0, 48), 219, 0));
    jPanel2.add(jLabel11,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(14, 12, 0, 84), 6, 0));
    jPanel2.add(jLabel13,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(6, 12, 0, 24), 39, 0));
    jPanel2.add(edit_imgFiltradas,  new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 12, 12, 47), 166, 0));
    PanelFiltros.add(jPanel1,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 7, 90, 0), -7, -4));
    jPanel3.add(jCheckBox3, null);
    jPanel3.add(jCheckBox2, null);
    jPanel3.add(jCheckBox1, null);
    jPanel3.add(jCheckBox7, null);
    jPanel3.add(jCheckBox5, null);
    jPanel3.add(jCheckBox6, null);
    jPanel3.add(jCheckBox8, null);
    jPanel3.add(jCheckBox4, null);
    jPanel3.add(jButton1, null);
    jPanel4.add(jTextField5,  new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(12, 0, 0, 12), -2134, 0));
    jPanel4.add(jLabel9, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(14, 7, 7, 0), 3, 0));
    jPanel4.add(jLabel12, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(15, 7, 68, 0), 37, 0));
    jPanel4.add(jTextField6, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(8, 0, 68, 12), -2134, 0));
    jPanel4.add(jLabel6, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(19, 7, 0, 0), 6, 0));
    jPanel4.add(jTextField3, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(19, 0, 0, 12), -2134, 0));
    jPanel5.add(jButton2,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(24, 8, 23, 59), -17, -4));
    jPanel5.add(jTextField4,  new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(24, 58, 23, 0), 229, 0));
    Configuracion.add(jPanel4,  new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 13, 0, 13), 0, 0));
    Configuracion.add(jLabel5,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 10, 0, 201), 0, 0));
    Configuracion.add(jLabel7,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 15, 0, 136), 0, 0));
    Configuracion.add(jLabel8,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
            ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(16, 10, 0, 127), 7, 0));
    Configuracion.add(jPanel3,  new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 0), -2, 11));
    Configuracion.add(jPanel5,  new GridBagConstraints(0, 3, 2, 1, 1.0, 1.0
            ,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 11, 13), -2, 0));
    jTabbedPane1.add(PanelFiltros, "Filtros");



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
  }

  void btnCancelar_actionPerformed(ActionEvent e) {
    cancel();
  }

  void BtnAceptar_actionPerformed(ActionEvent e) {

//    System.getProperty("file.separator").toString()

    Constante.setOtrosDominios(this.jCheckBox2.isSelected());
    Constante.setSOBREESCRIBIR(this.jCheckBox1.isSelected());
    Constante.setOtrosSites(this.jCheckBox3.isSelected());
    Constante.setTrazas(this.jCheckBox4.isSelected());
    Constante.setB_galeria(this.jCheckGaleria.isSelected());
    Constante.setRecomprimir(this.jCheckBox6.isSelected());
    Constante.setNoDescargarRepetidos(this.jCheckBox7.isSelected());
    Constante.setVaciarHistorial(this.jCheckBox8.isSelected());

    if (this.jCheckBox5.isSelected())
    {
      Constante.setNIVEL_DESCARGA(2);
    }
    else
    {
        Constante.setNIVEL_DESCARGA(1);
    }




    if (this.jTextField4.getText().charAt(this.jTextField4.getText().length()-1) != System.getProperty("file.separator").toString().charAt(0) )
    {
      this.jTextField4.setText(this.jTextField4.getText() + System.getProperty("file.separator").toString());
    }

    Constante.setDirBase(this.jTextField4.getText());
    Constante.setPalabrasProhibidas(this.edit_excluidas.getText());
    Constante.setImgDescarga(this.edit_imgFiltradas.getText());

    Constante.setProxyIP(jTextField7.getText());
    Constante.setProxyPort(jTextField8.getText());

    try
    {
      Constante.setCalidadJPEG( (Integer.parseInt(this.jTextField3.getText())));
      Constante.setMIN_HEIGHT( (Integer.parseInt(this.jTextField1.getText())));
      Constante.setMIN_WIDTH( (Integer.parseInt(this.jTextField2.getText())));
      Constante.setIMAGE_TIME_OUT( (Integer.parseInt(this.jTextField6.getText())));
      Constante.setMAX_THREAD( (Integer.parseInt(this.jTextField5.getText()) - 1));
      if (Constante.getMAX_THREAD() < 0) Constante.setMAX_THREAD(0);
    }
    catch (NumberFormatException ex)
    {
      JOptionPane.showMessageDialog(null, "Error en los parametros numéricos. Se estableceran por defecto.");
      System.out.println(ex.getMessage());
      cancel();
    }

    Constante.guardaConfiguracion();
    cancel();
  }

  void jCheckBox1_actionPerformed(ActionEvent e) {

  }

  void jButton2_keyPressed(KeyEvent e) {
    filechooser.setFileSelectionMode(filechooser.DIRECTORIES_ONLY);
    int returnVal = filechooser.showSaveDialog(this);
//    int returnVal = filechooser.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = filechooser.getSelectedFile();
      this.jTextField4.setText(file.getAbsolutePath());
    }

  }

  void jButton2_mousePressed(MouseEvent e) {
    filechooser.setFileSelectionMode(filechooser.DIRECTORIES_ONLY);
    int returnVal = filechooser.showSaveDialog(this);
//    int returnVal = filechooser.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = filechooser.getSelectedFile();
      this.jTextField4.setText(file.getAbsolutePath());
    }

  }

  void jButton1_actionPerformed(ActionEvent e) {
      (new fuentes.utiles.Traza()).clear();
  }

  void jButton2_actionPerformed(ActionEvent e) {

  }

  void jCheckBox6_actionPerformed(ActionEvent e) {
    jTextField3.setEnabled(jCheckBox6.isSelected());
  }

  void jCheckBox7_actionPerformed(ActionEvent e) {

  }

}

class FormEnlazador_Configuracion_btnCancelar_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_btnCancelar_actionAdapter(FormEnlazador_Configuracion
      adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.btnCancelar_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_BtnAceptar_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_BtnAceptar_actionAdapter(
      FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.BtnAceptar_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_jCheckBox1_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jCheckBox1_actionAdapter(
      FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBox1_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_jButton2_keyAdapter
    extends java.awt.event.KeyAdapter {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jButton2_keyAdapter(FormEnlazador_Configuracion
                                                  adaptee) {
    this.adaptee = adaptee;
  }

  public void keyPressed(KeyEvent e) {
    adaptee.jButton2_keyPressed(e);
  }
}

class FormEnlazador_Configuracion_jButton2_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jButton2_mouseAdapter(FormEnlazador_Configuracion
      adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.jButton2_mousePressed(e);
  }
}

class FormEnlazador_Configuracion_jButton1_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jButton1_actionAdapter(FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_jButton2_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jButton2_actionAdapter(FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_jCheckBox6_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jCheckBox6_actionAdapter(FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBox6_actionPerformed(e);
  }
}

class FormEnlazador_Configuracion_jCheckBox7_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador_Configuracion adaptee;

  FormEnlazador_Configuracion_jCheckBox7_actionAdapter(FormEnlazador_Configuracion adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBox7_actionPerformed(e);
  }
}
