package proyectoenlazador;

import javax.swing.table.*;
import java.beans.*;
import java.awt.datatransfer.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import fuentes.gestor.*;
import fuentes.utiles.*;
import fuentes.constantes.Constante;
import javax.swing.border.*;
import java.util.ArrayList;
import java.io.*;
import javax.swing.event.*;
import java.util.HashMap;
import javax.swing.tree.TreePath;
import fuentes.bbdd.Jdbc;
import javax.swing.table.DefaultTableModel;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class FormEnlazador
    extends JFrame {

  private Thread t1 = null;

  // Image1;
  boolean descargaAbortada = false;
  boolean pulsacion = false;
  JPanel contentPane;
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenu jMenuFile = new JMenu();
  JMenuItem jMenuFileExit = new JMenuItem();
  JMenu jMenuHelp = new JMenu();
  JMenuItem jMenuHelpAbout = new JMenuItem();
  JToolBar jToolBar = new JToolBar();
  JButton jButton2 = new JButton();
  ImageIcon image1;
  ImageIcon image2;
  ImageIcon image3;
  ImageIcon image4;
  ImageIcon image5;
  ImageIcon image6;
  ImageIcon image7;
  ImageIcon image8;
  ImageIcon imgCopiar;
  ImageIcon imgPegar;
  ImageIcon imgDelete;
  ImageIcon imgDeleteAll;
  JLabel statusBar = new JLabel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton jButton4 = new JButton();
  public JTextField DireccionText = new JTextField();
  JButton jButton5 = new JButton();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem1 = new JMenuItem();
  JTabbedPane jTabbedPane1 = new JTabbedPane();
  PnlFondo pnlFondo = new PnlFondo();
  PnlFondo pnlDescargados = new PnlFondo();
  PnlFondo pnlProcesoDescarga = new PnlFondo();
  JPanel jPanel3 = new JPanel();
  JSplitPane jSplitPane1 = new JSplitPane();
  JPanel jPanel4 = new JPanel();
  JTree jTree1 = new JTree();
  JPanel jPanel5 = new JPanel();
  JToolBar jToolBar1 = new JToolBar();
  JButton jButton6 = new JButton();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton jButton7 = new JButton();
  JSplitPane jSplitPane2 = new JSplitPane();
  BorderLayout borderLayout3 = new BorderLayout();
  TitledBorder titledBorder1;
  TitledBorder titledBorder2;
  BorderLayout borderLayout4 = new BorderLayout();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel8 = new JPanel();

  //Variable para añadir o eliminar datos de la lista
  JLabel jLabel1 = new JLabel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JLabel jLabel2 = new JLabel();
  BorderLayout borderLayout7 = new BorderLayout();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel9 = new JPanel();
  //Variable para añadir o eliminar datos de la lista
  public DefaultListModel m_ListaImagenes = new DefaultListModel();
  public JScrollPane jScrollPane3 = new JScrollPane();
  JMenuItem jMenuItem2 = new JMenuItem();
  JPopupMenu popup = new JPopupMenu();
  JMenuItem AddEnlace = new JMenuItem();
  JMenuItem RemEnlace = new JMenuItem();
  JPanel pnlListado = new JPanel();
  JSplitPane jSplitPane3 = new JSplitPane();
  JScrollPane jScrollPane4 = new JScrollPane();
  CardLayout cardLayout7 = new CardLayout();
  JScrollPane jScrollPane5 = new JScrollPane();
  JPanel jPanel12 = new JPanel();
  JLabel statusBar1 = new JLabel();
  JLabel statusBar2 = new JLabel();
  JPopupMenu popupListados = new JPopupMenu();
  JMenuItem EliminarListados = new JMenuItem();
  JPopupMenu popupImagenes = new JPopupMenu();
  JMenuItem eliminarImagenes = new JMenuItem();
  JButton GoogleButton = new JButton();
  JMenuItem jMenuItem3 = new JMenuItem();
  BorderLayout borderLayout5 = new BorderLayout();
  BorderLayout borderLayout9 = new BorderLayout();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JSplitPane jSplitPane4 = new JSplitPane();
  BorderLayout borderLayout10 = new BorderLayout();
  BorderLayout borderLayout11 = new BorderLayout();
  BorderLayout borderLayout12 = new BorderLayout();
  TitledBorder titledBorder3;
  TitledBorder titledBorder4;
  BorderLayout borderLayout13 = new BorderLayout();
  JMenu jMenu2 = new JMenu();
  JCheckBoxMenuItem jCheckBoxDetalleDescarga = new JCheckBoxMenuItem();
  JPanel jPanel14 = new JPanel();
  BorderLayout borderLayout14 = new BorderLayout();
  BorderLayout borderLayout15 = new BorderLayout();
  BorderLayout borderLayout16 = new BorderLayout();
  BorderLayout borderLayout17 = new BorderLayout();
  BorderLayout borderLayout18 = new BorderLayout();
  GridLayout gridLayout1 = new GridLayout();
  JPanel jPanel15 = new JPanel();
  JList listaDetalle = new JList();
  BorderLayout borderLayout19 = new BorderLayout();
  JMenuItem EliminarTodoListados = new JMenuItem();
  JMenuItem EliminarTodoImagenes = new JMenuItem();
  DefaultTableModel modeloDatosTablaURLS = new DefaultTableModel();
  DefaultTableModel modeloDatosTablaIMGS = new DefaultTableModel();
  DefaultTableModel modeloDatosTablaHIST = new DefaultTableModel();

  /*public JTable jTable1 = new JTable(modeloDatos){
     public boolean isCellEditable(int rowIndex, int colIndex) {
       return false;
     }
   };*/

  //JTable tablaURLS = new JTable(modeloDatosTablaURLS);
  //JTable tablaIMGS = new JTable(modeloDatosTablaIMGS);
  //JTable tablaHistorial = new JTable(modeloDatosTablaHIST);
  JScrollPane jScrollPane1 = new JScrollPane();
  BorderLayout borderLayout6 = new BorderLayout();
  BorderLayout borderLayout8 = new BorderLayout();

  public boolean isDescargaAbortada()
  {
    return descargaAbortada;
  }

  public void setDescargaAbortada(boolean dato)
  {
    descargaAbortada = dato;
  }

  public JTable tablaHistorial = new JTable(modeloDatosTablaHIST){
    public boolean isCellEditable(int rowIndex, int colIndex) {
      return false;
    }
  };

  public JTable tablaIMGS = new JTable(modeloDatosTablaIMGS){
    public boolean isCellEditable(int rowIndex, int colIndex) {
      return false;
    }
  };

  public JTable tablaURLS = new JTable(modeloDatosTablaURLS){
    public boolean isCellEditable(int rowIndex, int colIndex) {
      return false;
    }
  };



  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenuItem popupImgCopiar = new JMenuItem();
  JMenuItem popupImgPegar = new JMenuItem();
  JCheckBoxMenuItem jCheckBoxMenuItem1 = new JCheckBoxMenuItem();

  public DefaultTableModel getModeloTablaHIST()
  {
    return modeloDatosTablaHIST;
  }


  public JTable getTablaURLS()
  {
    return tablaURLS;
  }

  public DefaultTableModel getModeloTablaURLS()
  {
    return modeloDatosTablaURLS;
  }

  public JTable getTablaIMGS()
  {
    return tablaIMGS;
  }

  public DefaultTableModel getModeloTablaIMGS()
  {
    return modeloDatosTablaIMGS;
  }


  public void clearTextoDetalle()
  {
    listaDetalle.setModel(new DefaultListModel());
  }

  public void setTextoDetalle(String s_texto,int s_pos)
  {


    ListModel modelo = listaDetalle.getModel();
    DefaultListModel lista = new DefaultListModel();

    for (int i = 0; i < Constante.getMAX_THREAD()+1; i++)
    {
        lista.addElement("");
    }

    for (int i = 0; i < modelo.getSize(); i++)
    {
      lista.setElementAt(modelo.getElementAt(i),i);
    }

    if (s_pos >= 0)
    {
      lista.setElementAt(s_texto,(s_pos-1)%(Constante.getMAX_THREAD()+1));
    }

    listaDetalle.setModel(lista);

  }


  public void setPulsacion(boolean estado)
  {
      this.pulsacion = estado;
  }

  public boolean getPulsacion()
  {
      return this.pulsacion;
  }


  public PnlFondo getpnlFondo()
  {
    int tabPos = this.jTabbedPane1.getSelectedIndex();
    //Primera posicion de la pestaña -> detalle de descarga
    if (tabPos == 0)
    {
      return this.pnlProcesoDescarga;
    }
    else
    {
      return pnlFondo;
    }
  }

  public JLabel getstatusBar()
  {
    return statusBar;
  }

  public JLabel getstatusBar1()
  {
    return statusBar1;
  }

  public JLabel getstatusBar2()
  {
    return statusBar2;
  }

  public String getDireccionText ()
  {
    return DireccionText.getText();
  }

  //Construir el marco
  public FormEnlazador() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
      Constante.cargaConfiguracion();
      DefaultTreeModel modelo = (DefaultTreeModel) jTree1.getModel();
      DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Favoritos");
      modelo.setRoot(raiz);
      cargarFavoritos();

      tablaURLS.setDefaultRenderer(Object.class, new MyCellRenderer());
      this.getModeloTablaURLS().setColumnCount(0);
      this.getModeloTablaURLS().addColumn("Enlace");
      this.getModeloTablaURLS().addColumn("Descripcion");
      tablaURLS.setAlignmentX( (float) 0.5);
      tablaURLS.setAutoscrolls(true);
      tablaURLS.setCellSelectionEnabled(true);
      tablaURLS.setColumnSelectionAllowed(false);


      tablaIMGS.setDefaultRenderer(Object.class, new MyCellRenderer());
      this.getModeloTablaIMGS().setColumnCount(0);
      this.getModeloTablaIMGS().addColumn("Imágenes");
      tablaIMGS.setAlignmentX( (float) 0.5);
      tablaIMGS.setAutoscrolls(true);
      tablaIMGS.setCellSelectionEnabled(true);

      tablaHistorial.setDefaultRenderer(Object.class, new MyCellRenderer());
      this.getModeloTablaHIST().setColumnCount(0);
      this.getModeloTablaHIST().addColumn("Descargados");
      tablaHistorial.setAlignmentX( (float) 0.5);
      tablaHistorial.setAutoscrolls(true);
      tablaHistorial.setCellSelectionEnabled(true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    image1 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "openFile.png"));
    image2 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "closeFile.png"));
    image3 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "help.png"));
    image4 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "action_stop.gif"));
    image5 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "action_go.gif"));
    image6 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "bullet_wrench.png"));
    image7 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "comment_edit.png"));
    image8 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "google.gif"));

    imgCopiar = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource("page_copy.png"));
    imgPegar = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource("paste_plain.png"));
    imgDelete = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource("delete.png"));
    imgDeleteAll = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource("deleteAll.png"));


    contentPane = (JPanel)this.getContentPane();
    titledBorder1 = new TitledBorder("");
    titledBorder2 = new TitledBorder("");
    titledBorder3 = new TitledBorder("");
    titledBorder4 = new TitledBorder("");
    contentPane.setLayout(borderLayout1);
    this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    this.setForeground(Color.lightGray);
    this.setLocale(java.util.Locale.getDefault());
    this.setSize(new Dimension(800, 600));
    this.setTitle("Enlazador");
    this.addWindowListener(new FormEnlazador_this_windowAdapter(this));
    this.addPropertyChangeListener(new FormEnlazador_this_propertyChangeAdapter(this));
    this.addMouseListener(new FormEnlazador_this_mouseAdapter(this));
    statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar.setMaximumSize(new Dimension(4, 4));
    statusBar.setMinimumSize(new Dimension(4, 4));
    statusBar.setPreferredSize(new Dimension(4, 4));
    statusBar.setText(" ");
    statusBar.addContainerListener(new FormEnlazador_statusBar_containerAdapter(this));
    statusBar.addComponentListener(new FormEnlazador_statusBar_componentAdapter(this));
    jMenuFile.setText("Archivo");
    jMenu1.setText("Herramientas");
    jMenuItem1.setText("Opciones");
    jMenuItem1.addActionListener(new FormEnlazador_jMenuItem1_actionAdapter(this));
    jMenuItem1.addMouseListener(new FormEnlazador_jMenuItem1_mouseAdapter(this));
    jMenuFileExit.setText("Salir");
    jMenuFileExit.addActionListener(new
                                    FormEnlazador_jMenuFileExit_ActionAdapter(this));
    jMenuHelp.setText("Ayuda");
    jMenuHelpAbout.setText("Acerca de");
    jMenuHelpAbout.addActionListener(new
                                     FormEnlazador_jMenuHelpAbout_ActionAdapter(this));
    jButton2.setIcon(image6);
    jButton2.addActionListener(new FormEnlazador_jButton2_actionAdapter(this));
    jButton2.setToolTipText("Opciones");
    jButton4.setToolTipText("Abortar");
    jButton4.setHorizontalTextPosition(SwingConstants.TRAILING);
    jButton4.setIcon(image4);
    jButton4.setMnemonic('0');
    jButton4.addActionListener(new FormEnlazador_jButton4_actionAdapter(this));
    jButton5.setToolTipText("Descargar url");
    jButton5.setIcon(image5);
    jButton5.addActionListener(new FormEnlazador_jButton5_actionAdapter(this));
    jButton5.addMouseListener(new FormEnlazador_jButton5_mouseAdapter(this));
    DireccionText.addKeyListener(new FormEnlazador_DireccionText_keyAdapter(this));
    jPanel1.setLayout(borderLayout18);
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel3.setLayout(borderLayout3);
    jPanel4.setMaximumSize(new Dimension(32767, 32767));
    jPanel4.setMinimumSize(new Dimension(160, 160));
    jPanel4.setLayout(borderLayout10);
    jButton6.setIcon(image5);
    jButton6.addActionListener(new FormEnlazador_jButton6_actionAdapter(this));
    jButton6.setToolTipText("Descargar url\'s seleccionadas");
    jPanel5.setLayout(borderLayout2);
    jButton7.setToolTipText("Generar Patron Url");
    jButton7.setIcon(image7);
    jButton7.addActionListener(new FormEnlazador_jButton7_actionAdapter(this));
    jSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jPanel5.setPreferredSize(new Dimension(20, 20));
    jPanel5.addComponentListener(new FormEnlazador_jPanel5_componentAdapter(this));
    jPanel6.setLayout(borderLayout4);
    jPanel6.setMinimumSize(new Dimension(0, 0));
    jPanel6.setPreferredSize(new Dimension(0, 0));
    jPanel8.setLayout(borderLayout9);
    jPanel8.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel8.setMinimumSize(new Dimension(0, 0));
    jPanel8.setPreferredSize(new Dimension(15, 15));
    jLabel1.setBackground(Color.orange);
    jLabel1.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel1.setOpaque(true);
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("Lista de URL\'s");
    jPanel7.setLayout(borderLayout8);
    jPanel10.setLayout(borderLayout6);
    jLabel2.setBackground(Color.orange);
    jLabel2.setFont(new java.awt.Font("Dialog", 1, 11));
    jLabel2.setOpaque(true);
    jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel2.setText("Lista de Imágenes");
    jPanel11.setLayout(borderLayout5);
    jPanel11.setBorder(BorderFactory.createRaisedBevelBorder());
    jPanel11.setPreferredSize(new Dimension(15, 15));
    jPanel9.setLayout(borderLayout7);
    jMenuItem2.setText("Generador de URL\'s");
    jMenuItem2.addActionListener(new FormEnlazador_jMenuItem2_actionAdapter(this));
    popup.setOpaque(true);
    popup.setRequestFocusEnabled(true);
    popup.setToolTipText("");
    jTree1.addMouseListener(new FormEnlazador_jTree1_mouseAdapter(this));
    RemEnlace.setText("Borrar enlace");
    RemEnlace.addActionListener(new FormEnlazador_RemEnlace_actionAdapter(this));
    AddEnlace.setText("Añadir Enlace");
    AddEnlace.addActionListener(new FormEnlazador_AddEnlace_actionAdapter(this));
    jTree1.setFont(new java.awt.Font("Dialog", 0, 12));
    jTree1.setEditable(true);
    pnlDescargados.setBackground(Color.gray);
    pnlDescargados.setForeground(Color.gray);
    pnlDescargados.setLayout(borderLayout17);
    pnlProcesoDescarga.setForeground(Color.gray);
    pnlProcesoDescarga.setBorder(BorderFactory.createLoweredBevelBorder());
    pnlProcesoDescarga.setOpaque(true);
    pnlProcesoDescarga.setPreferredSize(new Dimension(80, 80));
    pnlProcesoDescarga.setLayout(borderLayout13);
    pnlListado.setLayout(borderLayout16);
    jSplitPane3.setBackground(SystemColor.control);
    jSplitPane3.setForeground(SystemColor.control);
    pnlFondo.setBackground(Color.lightGray);
    pnlFondo.setForeground(Color.lightGray);
    pnlFondo.setBorder(BorderFactory.createEtchedBorder());
    pnlFondo.setLayout(borderLayout15);
    jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane3.setColumnHeader(null);
    jScrollPane3.getViewport().setBackground(Color.white);
    jScrollPane3.setAutoscrolls(true);
    jScrollPane3.setToolTipText("Lista de URL\'s que van a descargarse");
    jScrollPane3.addMouseListener(new FormEnlazador_jScrollPane3_mouseAdapter(this));
    jScrollPane3.addKeyListener(new FormEnlazador_jScrollPane3_keyAdapter(this));
    jToolBar.setFloatable(false);
    jPanel12.setMinimumSize(new Dimension(20, 20));
    jPanel12.setPreferredSize(new Dimension(20, 20));
    jPanel12.setLayout(gridLayout1);
    statusBar1.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar1.setText("");
    statusBar1.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
    statusBar2.setBorder(BorderFactory.createLoweredBevelBorder());
    statusBar2.setHorizontalAlignment(SwingConstants.LEFT);
    statusBar2.setHorizontalTextPosition(SwingConstants.LEFT);
    statusBar2.setText("");
    EliminarListados.setActionCommand("Eliminar");
    EliminarListados.setIcon(imgDelete);
    EliminarListados.setSelectedIcon(imgDelete);
    EliminarListados.setText("Eliminar selección");
    EliminarListados.addActionListener(new FormEnlazador_EliminarListados_actionAdapter(this));
    eliminarImagenes.setIcon(imgDelete);
    eliminarImagenes.setText("Eliminar selección");
    eliminarImagenes.addActionListener(new FormEnlazador_eliminarImagenes_actionAdapter(this));
    DireccionText.setFont(new java.awt.Font("Arial", 0, 14));
    DireccionText.setToolTipText("");
    GoogleButton.setToolTipText("Buscar en Google");
    GoogleButton.setIcon(image8);
    GoogleButton.addActionListener(new FormEnlazador_GoogleButton_actionAdapter(this));
    jMenuItem3.setText("Descarga de Google");
    jMenuItem3.addActionListener(new FormEnlazador_jMenuItem3_actionAdapter(this));
    jSplitPane4.setOrientation(JSplitPane.VERTICAL_SPLIT);
    jSplitPane4.setBackground(Color.black);
    jSplitPane4.setOpaque(true);
    jSplitPane4.setLastDividerLocation(-1);
    jSplitPane4.setOneTouchExpandable(false);
    jPanel13.setLayout(borderLayout11);
    jPanel2.setLayout(borderLayout12);
    jMenu2.setText("Ver");
    jCheckBoxDetalleDescarga.setText("Progreso de descarga");
    jCheckBoxDetalleDescarga.setSelected(false);
    jCheckBoxDetalleDescarga.addActionListener(new FormEnlazador_jCheckBoxDetalleDescarga_actionAdapter(this));
    jPanel14.setBorder(BorderFactory.createEtchedBorder());
    jPanel14.setDebugGraphicsOptions(0);
    jPanel14.setMinimumSize(new Dimension(80, 80));
    jPanel14.setPreferredSize(new Dimension(110, 110));
    jPanel14.setLayout(borderLayout14);
    listaDetalle.setBackground(SystemColor.activeCaptionBorder);
    listaDetalle.setEnabled(true);
    listaDetalle.setFont(new java.awt.Font("Dialog", 0, 9));
    listaDetalle.setOpaque(true);
    listaDetalle.setSelectionForeground(Color.white);
    jPanel15.setLayout(borderLayout19);
    jPanel2.setForeground(Color.gray);
    jSplitPane1.setBackground(Color.black);
    pnlListado.setEnabled(true);
    EliminarTodoListados.setIcon(imgDeleteAll);
    EliminarTodoListados.setText("Eliminar todo");
    EliminarTodoListados.addActionListener(new FormEnlazador_EliminarTodoListados_actionAdapter(this));
    EliminarTodoImagenes.setHorizontalTextPosition(SwingConstants.TRAILING);
    EliminarTodoImagenes.setIcon(imgDeleteAll);
    EliminarTodoImagenes.setText("Eliminar todo");
    EliminarTodoImagenes.addActionListener(new FormEnlazador_EliminarTodoImagenes_actionAdapter(this));
    tablaURLS.setBackground(Color.white);
    tablaURLS.setAutoscrolls(true);
    tablaURLS.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
    tablaURLS.setColumnSelectionAllowed(true);
    tablaURLS.setRowSelectionAllowed(false);
    tablaURLS.addKeyListener(new FormEnlazador_tablaURLS_keyAdapter(this));
    tablaURLS.addMouseListener(new FormEnlazador_tablaURLS_mouseAdapter(this));
    jScrollPane1.addMouseListener(new FormEnlazador_jScrollPane1_mouseAdapter(this));
    tablaIMGS.addMouseListener(new FormEnlazador_tablaIMGS_mouseAdapter(this));
    tablaIMGS.addKeyListener(new FormEnlazador_tablaIMGS_keyAdapter(this));
    jScrollPane1.getViewport().setBackground(Color.white);
    tablaIMGS.setRowHeight(16);
    tablaHistorial.addKeyListener(new FormEnlazador_tablaHistorial_keyAdapter(this));
    tablaHistorial.addMouseListener(new FormEnlazador_tablaHistorial_mouseAdapter(this));
    jMenuItem4.setIcon(imgCopiar);
    jMenuItem4.setText("Copiar");
    jMenuItem4.addActionListener(new FormEnlazador_jMenuItem4_actionAdapter(this));
    image1.setDescription("vfs:///file:///C:/JBuilderX/Projects/ProyectoEnlazador/classes/proyectoenlazador/openFile.png");
    jMenuItem5.setIcon(imgPegar);
    jMenuItem5.setText("Pegar");
    jMenuItem5.addActionListener(new FormEnlazador_jMenuItem5_actionAdapter(this));
    popupImgCopiar.setIcon(imgCopiar);
    popupImgCopiar.setText("Copiar");
    popupImgCopiar.addActionListener(new FormEnlazador_popupImgCopiar_actionAdapter(this));
    popupImgPegar.setIcon(imgPegar);
    popupImgPegar.setText("Pegar");
    popupImgPegar.addActionListener(new FormEnlazador_popupImgPegar_actionAdapter(this));
    jCheckBoxMenuItem1.setText("Descarga auto. Clipboard");
    jCheckBoxMenuItem1.addActionListener(new FormEnlazador_jCheckBoxMenuItem1_actionAdapter(this));
    jToolBar.add(DireccionText, null);
    jToolBar.add(jButton5, null);
    jToolBar.add(jButton4, null);
    jToolBar.add(jButton2);
    jMenuFile.add(jMenuFileExit);
    jMenuHelp.add(jMenuHelpAbout);
    jMenuBar1.add(jMenuFile);
    jMenuBar1.add(jMenu2);
    jMenuBar1.add(jMenu1);
    jMenuBar1.add(jMenuHelp);
    this.setJMenuBar(jMenuBar1);
    contentPane.add(jToolBar,  BorderLayout.NORTH);
    contentPane.add(jPanel1, BorderLayout.CENTER);
    jTabbedPane1.add(jPanel3, "Descarga");
    jTabbedPane1.add(pnlFondo, "Previsualización");

    //No mostramos el panel del historial
    jTabbedPane1.add(pnlListado,   "Historial");

    pnlListado.add(jSplitPane3, BorderLayout.CENTER);
    jPanel3.add(jSplitPane1, BorderLayout.CENTER);
    jSplitPane1.add(jPanel4, JSplitPane.LEFT);
    jPanel4.add(jSplitPane4, BorderLayout.CENTER);
    jSplitPane4.add(jPanel13, JSplitPane.TOP);
    jPanel13.add(jScrollPane5, BorderLayout.CENTER);
    jSplitPane4.add(jPanel2, JSplitPane.BOTTOM);

    jScrollPane5.getViewport().add(jTree1, null);
    jSplitPane1.add(jPanel5, JSplitPane.RIGHT);
    jPanel5.add(jToolBar1, BorderLayout.NORTH);
    jToolBar1.add(jButton6, null);
    jToolBar1.add(jButton7, null);
    jToolBar1.add(GoogleButton, null);
    jPanel5.add(jSplitPane2, BorderLayout.CENTER);
    jSplitPane2.add(jPanel9, JSplitPane.BOTTOM);
    jPanel6.add(jPanel8, BorderLayout.NORTH);
    jPanel8.add(jLabel1, BorderLayout.CENTER);
    jPanel6.add(jPanel7, BorderLayout.CENTER);
    jSplitPane2.add(jPanel6, JSplitPane.TOP);
    jPanel9.add(jPanel11, BorderLayout.NORTH);
    jPanel11.add(jLabel2, BorderLayout.CENTER);
    jPanel9.add(jPanel10, BorderLayout.CENTER);
    jPanel10.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(tablaIMGS, null);
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu1.add(jMenuItem3);
    jMenu1.add(jCheckBoxMenuItem1);
    popup.add(AddEnlace);
    popup.addSeparator();
    popup.add(RemEnlace);
    jSplitPane3.add(pnlDescargados, JSplitPane.RIGHT);
    jSplitPane3.add(jScrollPane4, JSplitPane.LEFT);
    jScrollPane4.getViewport().add(tablaHistorial, null);
    jPanel12.add(statusBar, null);
    jPanel12.add(statusBar1, null);
    jPanel12.add(statusBar2, null);
    jPanel14.add(jPanel15,  BorderLayout.CENTER);
    jPanel15.add(listaDetalle, BorderLayout.NORTH);
    contentPane.add(jPanel14,  BorderLayout.SOUTH);
    jPanel14.add(jPanel12,  BorderLayout.SOUTH);
    popupListados.add(EliminarListados);
    popupListados.add(EliminarTodoListados);
    popupListados.add(jMenuItem4);
    popupListados.add(jMenuItem5);
    popupImagenes.add(eliminarImagenes);
    popupImagenes.add(EliminarTodoImagenes);
    popupImagenes.add(popupImgCopiar);
    popupImagenes.add(popupImgPegar);
    jMenu2.add(jCheckBoxDetalleDescarga);
    jPanel2.add(pnlProcesoDescarga, BorderLayout.CENTER);
    jPanel1.add(jTabbedPane1, BorderLayout.CENTER);
    jPanel7.add(jScrollPane3, BorderLayout.CENTER);
    jScrollPane3.getViewport().add(tablaURLS, null);
    jSplitPane1.setDividerLocation(200);
    jSplitPane2.setDividerLocation(140);
    jSplitPane3.setDividerLocation(160);
    jSplitPane4.setDividerLocation(170);
    jCheckBoxDetalleDescarga_actionPerformed(null);
  }

  /**
  * The cell renderer.
  */
  private class MyCellRenderer extends DefaultTableCellRenderer
  {
  private Color whiteColor = new Color(254, 254, 254);
  private Color alternateColor = new Color(237, 243, 254);
  private Color selectedColor = new Color(61, 128, 223);
  private Color descColor = new Color(0,64,128);

  public Component getTableCellRendererComponent(JTable table,
  Object value, boolean selected, boolean focused,
  int row, int column)
  {
  super.getTableCellRendererComponent(table, value,
  selected, focused, row, column);

// Set the background color
  Color bg;
  if (!selected)
  bg = (row % 2 == 0 ? alternateColor : whiteColor);
  else
  bg = selectedColor;
  setBackground(bg);

// Set the foreground to white when selected
  Color fg;
  if (selected)
  {
    fg = Color.white;
   // fg = (column == 1 ? descColor : Color.white);
  }
  else
  {
    //fg = Color.black;
    fg = (column == 1 ? descColor : Color.black);
  }



  setForeground(fg);

//   table.getColumn("Nombre").setCellRenderer(tr);

  return this;
  }
  }


  //Realizar Archivo | Salir
  public void jMenuFileExit_actionPerformed(ActionEvent e)
  {
    //Guardamos la lista de favoritos antes de salir
    DefaultTreeModel modelo =(DefaultTreeModel)jTree1.getModel();
    DefaultMutableTreeNode raiz = (DefaultMutableTreeNode)modelo.getRoot();
    ArrayList listaArbol = obtenerArbol(raiz,"");
    guardaListaArbol(listaArbol);

    System.exit(0);
  }

  //Realizar Ayuda | Acerca de
  public void jMenuHelpAbout_actionPerformed(ActionEvent e) {
    FormEnlazador_AcercaDe dlg = new FormEnlazador_AcercaDe(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  //Modificado para poder salir cuando se cierra la ventana
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      jMenuFileExit_actionPerformed(null);
    }
  }


  void jButton4_actionPerformed(ActionEvent e) {
    try {
      setDescargaAbortada(true);
      if (t1 != null) {
        t1.interrupt();
//        t1.setStopped(true);
        this.statusBar.setText("DESCARGA ABORTADA\n");
      }
    }
    catch (Exception ex) {
      setDescargaAbortada(true);
      ex.printStackTrace();
    }

  }

  void jButton2_actionPerformed(ActionEvent e) {
    FormEnlazador_Configuracion dlg = new FormEnlazador_Configuracion(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  void jMenuItem1_mouseClicked(MouseEvent e) {
  }

  void jMenuItem1_mousePressed(MouseEvent e) {

  }

  void jMenuItem1_actionPerformed(ActionEvent e) {
    FormEnlazador_Configuracion dlg = new FormEnlazador_Configuracion(this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  void jButton5_actionPerformed(ActionEvent e) {
    repaint();
    setDescargaAbortada(false);
    jButton5.setEnabled(false);
    //t1 = new GestorDescarga(pnlFondo, statusBar, DireccionText.getText(), this, false);
    t1 = new GestorDescarga(this, false);
    t1.start();
    jButton5.setEnabled(true);
  }

  void statusBar_componentShown(ComponentEvent e) {
  }

  void statusBar_componentAdded(ContainerEvent e) {
  }

  void this_propertyChange(PropertyChangeEvent e) {
  }

  public void actualizarTablaEnlaces(ArrayList listaEnlaces)
  {
    this.getModeloTablaURLS().setRowCount(listaEnlaces.size());

    //Pintamos los enlaces de descarga que aparecen en la lista de enlaces
    for (int i = 0; i < listaEnlaces.size(); i++)
    {
      //this.s_formEnlazador.getModeloTablaURLS().setValueAt(((DescHref)listaEnlaces.get(i)).getUrl(), i, 0);
      //this.s_formEnlazador.getModeloTablaURLS().setValueAt(((DescHref)listaEnlaces.get(i)).getDesc(), i, 1);
    }
  }


  public void insertarPortaPapelesTabla1()
  {

   Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
   Transferable transferable = clipboard.getContents( null );

   if( transferable.isDataFlavorSupported( DataFlavor.stringFlavor ) )
   {
     try
       {
          String texto = (String)transferable.getTransferData(DataFlavor.stringFlavor );
          if (Constante.debug) System.out.println( "texto recogido: "+texto );
          //StringBuffer entrada = new StringBuffer(texto);

          String datos[] = texto.split("\n");

          for (int i = 0; i < datos.length;i++)
          {
            //Object[] dat = {"Jose", new Integer(5), new Boolean(true)};
            Object[] dat = {datos[i],""};
            getModeloTablaURLS().addRow(dat);
          }
       }
       catch( Exception exception )
       {
          System.out.println(exception.getMessage() );
       }
    }
    else
    {
          //System.out.println( "No se ha podido recoger el texto");
    }
  }

  void insertarPortaPapelesTablaImgs()
  {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
   Transferable transferable = clipboard.getContents( null );
   if( transferable.isDataFlavorSupported( DataFlavor.stringFlavor ) )
   {

     try
       {
          String texto = (String)transferable.getTransferData(DataFlavor.stringFlavor );
          if (Constante.debug) System.out.println( "texto recogido: "+texto );
          //StringBuffer entrada = new StringBuffer(texto);

          String datos[] = texto.split("\n");

          for (int i = 0; i < datos.length;i++)
          {
            //Object[] dat = {"Jose", new Integer(5), new Boolean(true)};
            Object[] dat = {datos[i]};
            getModeloTablaIMGS().addRow(dat);
          }
       }
       catch( Exception exception )
       {
          System.out.println(exception.getMessage() );
       }
    }
    else
    {
          //System.out.println( "No se ha podido recoger el texto");
    }
  }


  void insertarPortaPapeles(JList myList)
  {
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    Transferable transferable = clipboard.getContents( null );
    if( transferable.isDataFlavorSupported( DataFlavor.stringFlavor ) )
    {

      try
        {
           String texto = (String)transferable.getTransferData(DataFlavor.stringFlavor );
           if (Constante.debug) System.out.println( "texto recogido: "+texto );
           //StringBuffer entrada = new StringBuffer(texto);

           String datos[] = texto.split("\n");
           ListModel modelo = myList.getModel();
           DefaultListModel lista = new DefaultListModel();

           for (int i = 0; i < modelo.getSize(); i++)
           {
             lista.addElement(modelo.getElementAt(i));
           }

           for (int i = 0; i < datos.length;i++)
           {
               lista.addElement(datos[i]);
           }
           myList.setModel(lista);
        }
        catch( Exception exception )
        {
           System.out.println(exception.getMessage() );
        }
     }
     else
     {
           //System.out.println( "No se ha podido recoger el texto");
     }


  }


    public  void eliminarListaArchivosTabla1()
   {
     int numSel = this.tablaURLS.getSelectedRowCount();
     int indices[] = this.tablaURLS.getSelectedRows();

     for (int i = 0; i < indices.length; i++)
     {
       eliminarListaArchivosTabla1(indices[indices.length - i - 1]);
     }

   }

   public  void eliminarListaArchivosTabla1(int pos)
   {
       getModeloTablaURLS().removeRow(pos);
   }


   void eliminarListaArchivosTablaIMGS()
    {
      int numSel = this.tablaIMGS.getSelectedRowCount();
      int indices[] = this.tablaIMGS.getSelectedRows();
      //System.out.println("Seleccionados: "+indices);
      //System.out.println("N selec: "+numSel);

      for (int i = 0; i < indices.length; i++)
      {
        //System.out.println(indices[indices.length - i - 1]);
        getModeloTablaIMGS().removeRow(indices[indices.length - i - 1]);
      }

    }


  void jScrollPane1_componentShown(ComponentEvent e) {
  }

  void jButton6_actionPerformed(ActionEvent e) {
    setDescargaAbortada(false);
    t1 = new GestorDescarga(this, true);
    t1.start();
  }

  void jPanel5_componentResized(ComponentEvent e) {

      this.jSplitPane2.setDividerLocation( (this.jPanel5.getHeight() -
                                            this.jToolBar1.getHeight()) / 2);

      this.jSplitPane4.setDividerLocation( (this.jPanel5.getHeight() -
                                            this.jToolBar1.getHeight()) / 2 +
                                          28);
  }

  void DireccionText_keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      jButton5_actionPerformed(null);
    }
  }

  void jButton7_actionPerformed(ActionEvent e) {
    FormEnlazador_URLPlantilla dlg = new FormEnlazador_URLPlantilla(this, this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    jButton7_actionPerformed(null);
  }

  private void maybeShowPopup(MouseEvent e) {
    if (e.isPopupTrigger()) {
      this.popup.show(e.getComponent(), e.getX(), e.getY());
    }
  }


  private void maybeShowPopupListadosJtable1(MouseEvent e) {
    if (e.isPopupTrigger()) {
      this.popupListados.show(e.getComponent(), e.getX(), e.getY());
    }
  }

  private void maybeShowPopupListadosJtable2(MouseEvent e) {
    if (e.isPopupTrigger()) {
      this.popupImagenes.show(e.getComponent(), e.getX(), e.getY());
    }
  }


  void jTree1_mousePressed(MouseEvent e) {
    maybeShowPopup(e);

    int selRow = jTree1.getRowForLocation(e.getX(), e.getY());
         TreePath selPath = jTree1.getPathForLocation(e.getX(), e.getY());
         if(selRow != -1) {
             if(e.getClickCount() == 1)
             {
                 jTree1.setSelectionRow(selRow);
             }
             else if(e.getClickCount() == 2)
             {
                 if (jTree1.getModel().isLeaf(jTree1.getLastSelectedPathComponent()))
                 {
                   this.DireccionText.setText(selPath.getLastPathComponent().toString());
                 }
             }
         }
  }

  void jTree1_mouseReleased(MouseEvent e) {
    maybeShowPopup(e);
  }


  /**
   * Metodo para eliminar items de los favoritos
   *
   * @param e ActionEvent
   */
  void RemEnlace_actionPerformed(ActionEvent e)
  {
    try {
      DefaultTreeModel modelo = (DefaultTreeModel) jTree1.getModel();
      DefaultMutableTreeNode seleccion = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();

      if (seleccion.getParent() != null) {
        modelo.removeNodeFromParent(seleccion);
      }
    }
    catch (Exception ex) {
      System.out.println("Excepcion en jMenuItem5_actionPerformed: "+ex.getMessage());
    }
  }

  /**
   * Metodo para añadir items a los favoritos
   *
   * @param e ActionEvent
   */
  void AddEnlace_actionPerformed(ActionEvent e) {
    DefaultMutableTreeNode r;
    DefaultTreeModel modelo =(DefaultTreeModel)jTree1.getModel();

    String s_query = (String)JOptionPane.showInputDialog(this,"Introduce el nombre de la carpeta o enlace\n","Dialogo",JOptionPane.PLAIN_MESSAGE,null,null,"Nueva carpeta");

    if (s_query != null && s_query.length() > 0)
    {
      r = new DefaultMutableTreeNode( s_query );

      DefaultMutableTreeNode seleccion = (DefaultMutableTreeNode)jTree1.getLastSelectedPathComponent();
      modelo.insertNodeInto( r,seleccion , seleccion.getChildCount() );

      DefaultMutableTreeNode raiz = (DefaultMutableTreeNode)modelo.getRoot();
      ArrayList listaArbol = obtenerArbol(raiz,"");
      guardaListaArbol(listaArbol);
    }

  }

  /**
   * Se guarda el ArrayList en el fichero Favoritos.ini en el disco duro
   *
   * @param var_array String
   */
  public void guardaListaArbol(ArrayList var_array)  {

    try
        {
          String s2 = "";
          String formatLista = "";

          for (int i = 0; i < var_array.size(); i++)
          {
            formatLista += var_array.get(i) + "\n";
          }

          BufferedReader in4 = new BufferedReader(new StringReader(formatLista));
          PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("Favoritos.ini")));
          while ( (s2 = in4.readLine()) != null)
          {
            out1.println(s2);
          }
          out1.close();
        }
    catch (IOException ex)
        {
             System.err.println("Error en guardaListaArbol: "+ex.getMessage());
        }

  }

  /**
   * Se obtiene la estructura que posee el arbol de forma recursiva y se guarda
   * en una lista.
   *
   * @param var_raiz DefaultMutableTreeNode
   * @return ArrayList
   */

  public ArrayList obtenerArbol(DefaultMutableTreeNode var_raiz, String posicion)
  {
     ArrayList estructura = new ArrayList();
     ArrayList recogido;

     //estructura.add((String)var_raiz.getUserObject());

     DefaultMutableTreeNode nodo = null;

     for (int i=0;i<var_raiz.getChildCount();i++)
     {
       nodo = (DefaultMutableTreeNode)var_raiz.getChildAt(i);
       estructura.add(posicion + "." +i + ":" + (String) nodo.getUserObject());
     }

     for (int i=0;i<var_raiz.getChildCount();i++)
     {
       nodo = (DefaultMutableTreeNode)var_raiz.getChildAt(i);
       recogido = obtenerArbol(nodo, posicion + "." + i);
       if (recogido.size() > 0)
       {
         for (int j=0;j < recogido.size();j++)
         {
           estructura.add(recogido.get(j));
         }
       }
     }
     return (estructura);
  }



  public void cargarFavoritos()
  {
    DefaultTreeModel modelo =(DefaultTreeModel)jTree1.getModel();
    DefaultMutableTreeNode raiz = (DefaultMutableTreeNode)modelo.getRoot();
    pintarListaArbol(".", raiz);
    modelo.reload();
  }

  public void pintarListaArbol(String raiz, DefaultMutableTreeNode nodoRaiz)
  {
    HashMap listaArbol = cargarListaArbol();
    DefaultTreeModel modelo =(DefaultTreeModel)jTree1.getModel();

    int i = 0;
    String rama = "";
    String valor = "";

    if (listaArbol != null && listaArbol.size() > 0)
    {
      rama = raiz + i;
      while ( (valor = (String) listaArbol.get(rama)) != null )
      {
        modelo.insertNodeInto(new DefaultMutableTreeNode(valor), nodoRaiz, nodoRaiz.getChildCount());
        //Llamamos de forma recursiva para pintar los hijos
        pintarListaArbol(rama+".", (DefaultMutableTreeNode) nodoRaiz.getChildAt(i));
        i++;
        rama = raiz + i;
      }
    }

  }

  public HashMap cargarListaArbol()  {


    HashMap lista = new HashMap();
    String [] separador;

    try {
      BufferedReader in = new BufferedReader(new FileReader("Favoritos.ini"));
      String s = new String();
      while ( (s = in.readLine()) != null) {
        //separador = s.split(":");
        separador = separaDosPuntos(s);
        lista.put(separador[0],separador[1]);
      }
      in.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Ecepcion en cargarListaArbol: "+ex.getMessage());
    }
    catch (IOException ex) {
      System.out.println("Ecepcion en cargarListaArbol: "+ex.getMessage());
    }
    return lista;
  }

  String [] separaDosPuntos (String datos)
  {
    String salida[] = {"",""};

    if (datos != null && datos.indexOf(":") != -1)
    {
      salida[0] = datos.substring(0,datos.indexOf(":"));
      salida[1] = datos.substring(datos.indexOf(":")+1,datos.length());
    }

    return salida;
  }


  public void actualizarListadoSinBorrar (ArrayList arrayEnlaces, DefaultTableModel ListaDatos)
  {
    //Pintamos los enlaces en la lista del panel
         String dato = "";

         for (int c=0;c<arrayEnlaces.size();c++)
         {
           dato = (String)arrayEnlaces.get(c);
            //if (dato != null && (dato).length() > 0 && modeloDatos.indexOf(dato) == -1)
            /**
             * Deberia comparar si ya existe el dato que añadimos
             */
            if (dato != null && (dato).length() > 0)
            {
              Object[] dat = {dato};
              getModeloTablaURLS().addRow(dat);
            }
         }
  }

  void EliminarListados_actionPerformed(ActionEvent e) {
    eliminarListaArchivosTabla1();
  }

  void eliminarImagenes_actionPerformed(ActionEvent e) {
   eliminarListaArchivosTablaIMGS();
  }

  void GoogleButton_actionPerformed(ActionEvent e) {

    FormGoogleSearch dlg = new FormGoogleSearch(this,"Buscar con google",true,this);
    Dimension dlgSize = dlg.getPreferredSize();
    Dimension frmSize = getSize();
    Point loc = getLocation();
    dlg.setLocation( (frmSize.width - dlgSize.width) / 2 + loc.x,
                    (frmSize.height - dlgSize.height) / 2 + loc.y);
    dlg.setModal(true);
    dlg.pack();
    dlg.show();
  }

  void jMenuItem3_actionPerformed(ActionEvent e) {
    GoogleButton_actionPerformed(null);
  }

  void this_windowClosed(WindowEvent e) {
  }

  void this_windowClosing(WindowEvent e) {
    System.setProperty("http.proxyHost", "");
    System.setProperty("http.proxyPort", "");
    if (Constante.isVaciarHistorial()) Jdbc.clearHash();
  }

  void jCheckBoxDetalleDescarga_actionPerformed(ActionEvent e) {

       if (!jCheckBoxDetalleDescarga.isSelected())
       {
         this.jPanel15.setVisible(false);
         Dimension tamanio = new Dimension(25,25);
         this.jPanel14.setPreferredSize(tamanio);
       }
       else
       {
           Dimension tamanio = new Dimension(110,110);
           this.jPanel14.setPreferredSize(tamanio);
           this.jPanel15.setVisible(true);
       }

       jPanel5_componentResized(null);
  }


  void this_windowActivated(WindowEvent e) {

  }

  void this_windowOpened(WindowEvent e) {
    //Definimos el contenedor de los datos
    Contenedor c = new Contenedor ();
    //Este es el thread productor de datos
    Thread t2 = new fuentes.utiles.PollClipboard(this,c);
    //Este es el thread consumidor de datos
    Thread t3 = new fuentes.utiles.FetchClipboard(this,c);

    t2.start();
    t3.start();
  }


  void EliminarTodoListados_actionPerformed(ActionEvent e) {

    int a =getModeloTablaURLS().getRowCount()-1;
    for(int i=a; i>=0;i--){
      getModeloTablaURLS().removeRow(i );
    }

  }

  void EliminarTodoImagenes_actionPerformed(ActionEvent e) {

    eliminarTablaImgs();
  }

  public void eliminarTablaImgs()
  {
    int a = getModeloTablaIMGS().getRowCount() - 1;
    for (int i = a; i >= 0; i--) {
      getModeloTablaIMGS().removeRow(i);
    }
  }

  void jScrollPane3_mousePressed(MouseEvent e) {
    tablaURLS.setFocusable(true);
    tablaURLS.setFocusCycleRoot(true);
    maybeShowPopupListadosJtable1(e);
  }

  void jScrollPane3_mouseReleased(MouseEvent e) {
    maybeShowPopupListadosJtable1(e);
  }

  void jScrollPane1_mousePressed(MouseEvent e) {
    maybeShowPopupListadosJtable2(e);
  }

  void jScrollPane1_mouseReleased(MouseEvent e) {
    maybeShowPopupListadosJtable2(e);
  }

  void tablaIMGS_mousePressed(MouseEvent e) {
    maybeShowPopupListadosJtable2(e);
  }

  void tablaIMGS_mouseReleased(MouseEvent e) {
    maybeShowPopupListadosJtable2(e);
  }

  void tablaIMGS_keyPressed(KeyEvent e) {
    if (!getPulsacion())
    {
      if (e.getKeyCode() == KeyEvent.VK_DELETE) {
        eliminarListaArchivosTablaIMGS();
      }
      else if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_V)) {
        insertarPortaPapelesTablaImgs();
      }
      setPulsacion(!getPulsacion());
    }
    else
    {
      setPulsacion(!getPulsacion());
    }
  }


  void tablaHistorial_mousePressed(MouseEvent e) {
    int indices[] = this.tablaHistorial.getSelectedRows();
    if (indices.length > 0) {
      String selecionado = (String)this.tablaHistorial.getValueAt(indices[indices.length-1],0);
      this.pnlDescargados.setFondo(selecionado);
    }

  }

  void tablaHistorial_keyReleased(KeyEvent e) {
    int indices[] = this.tablaHistorial.getSelectedRows();
    if (indices.length > 0) {
      String selecionado = (String)this.tablaHistorial.getValueAt(indices[0],0);
      this.pnlDescargados.setFondo(selecionado);
    }
  }

  void tablaURLS_mousePressed(MouseEvent e) {
    tablaURLS.setFocusable(true);
    tablaURLS.setFocusCycleRoot(true);
    maybeShowPopupListadosJtable1(e);
  }

  void tablaURLS_mouseReleased(MouseEvent e) {
    maybeShowPopupListadosJtable1(e);
  }

  void jScrollPane3_keyPressed(KeyEvent e) {
    tablaURLS_keyPressed(e);
  }

  void tablaURLS_keyPressed(KeyEvent e) {
    if (!getPulsacion())
            {
              if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                eliminarListaArchivosTabla1();
              }
              else if (e.isControlDown() && (e.getKeyCode() == KeyEvent.VK_V)) {
                insertarPortaPapelesTabla1();
              }
              setPulsacion(!getPulsacion());
            }
            else
            {
              setPulsacion(!getPulsacion());
            }

      }

  void popupImgPegar_actionPerformed(ActionEvent e) {
    insertarPortaPapelesTablaImgs();
  }

  void jMenuItem5_actionPerformed(ActionEvent e) {
    insertarPortaPapelesTabla1();
  }

  void jMenuItem4_actionPerformed(ActionEvent e) {
    //Copiar de la lista de urls
    int numSel = this.tablaURLS.getSelectedRowCount();
    int indices[] = this.tablaURLS.getSelectedRows();

    StringBuffer datos = new StringBuffer();

    for (int i = 0; i < indices.length; i++) {
      datos.append(getModeloTablaURLS().getValueAt(indices[i],0));
      datos.append("\n");
    }
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(datos.toString()), null);
  }

  void popupImgCopiar_actionPerformed(ActionEvent e) {
    //Copiar de la lista de urls
    int numSel = this.tablaIMGS.getSelectedRowCount();
    int indices[] = this.tablaIMGS.getSelectedRows();

    StringBuffer datos = new StringBuffer();

    for (int i = 0; i < indices.length; i++) {
      datos.append(getModeloTablaIMGS().getValueAt(indices[i],0));
      datos.append("\n");
    }
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(datos.toString()), null);
  }

  void jCheckBoxMenuItem1_actionPerformed(ActionEvent e) {
    Constante.setDescargaURLAutomatica(jCheckBoxMenuItem1.isSelected());
    if (Constante.debug) System.out.println("Constante.isDescarga: "+Constante.isDescargaURLAutomatica());

  }
}



class FormEnlazador_jMenuFileExit_ActionAdapter
    implements ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuFileExit_ActionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuFileExit_actionPerformed(e);
  }
}

class FormEnlazador_jMenuHelpAbout_ActionAdapter
    implements ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuHelpAbout_ActionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuHelpAbout_actionPerformed(e);
  }
}

class FormEnlazador_jButton4_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jButton4_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton4_actionPerformed(e);
  }
}

class FormEnlazador_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jButton2_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class FormEnlazador_jMenuItem1_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem1_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void mousePressed(MouseEvent e) {
    adaptee.jMenuItem1_mousePressed(e);
  }
}

class FormEnlazador_jMenuItem1_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem1_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class FormEnlazador_jButton5_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jButton5_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
}

class FormEnlazador_this_mouseAdapter
    extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_this_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

}

class FormEnlazador_jButton5_actionAdapter
    implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jButton5_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton5_actionPerformed(e);
  }
}

class FormEnlazador_statusBar_componentAdapter
    extends java.awt.event.ComponentAdapter {
  FormEnlazador adaptee;

  FormEnlazador_statusBar_componentAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void componentShown(ComponentEvent e) {
    adaptee.statusBar_componentShown(e);
  }
}

class FormEnlazador_statusBar_containerAdapter
    extends java.awt.event.ContainerAdapter {
  FormEnlazador adaptee;

  FormEnlazador_statusBar_containerAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void componentAdded(ContainerEvent e) {
    adaptee.statusBar_componentAdded(e);
  }
}

class FormEnlazador_this_propertyChangeAdapter
    implements java.beans.PropertyChangeListener {
  FormEnlazador adaptee;

  FormEnlazador_this_propertyChangeAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }

  public void propertyChange(PropertyChangeEvent e) {
    adaptee.this_propertyChange(e);
  }
}

class FormEnlazador_jScrollPane1_componentAdapter extends java.awt.event.ComponentAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jScrollPane1_componentAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void componentShown(ComponentEvent e) {
    adaptee.jScrollPane1_componentShown(e);
  }
}

class FormEnlazador_jButton6_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jButton6_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton6_actionPerformed(e);
  }
}

class FormEnlazador_jPanel5_componentAdapter extends java.awt.event.ComponentAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jPanel5_componentAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void componentResized(ComponentEvent e) {
    adaptee.jPanel5_componentResized(e);
  }
}

class FormEnlazador_DireccionText_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador adaptee;

  FormEnlazador_DireccionText_keyAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.DireccionText_keyPressed(e);
  }
}

class FormEnlazador_jButton7_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jButton7_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton7_actionPerformed(e);
  }
}

class FormEnlazador_jMenuItem2_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem2_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem2_actionPerformed(e);
  }
}


class FormEnlazador_AddEnlace_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_AddEnlace_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
}

class FormEnlazador_jTree1_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jTree1_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jTree1_mousePressed(e);
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.jTree1_mouseReleased(e);
  }
}

class FormEnlazador_RemEnlace_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_RemEnlace_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.RemEnlace_actionPerformed(e);
  }
}

class FormEnlazador_AddEnlace_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_AddEnlace_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.AddEnlace_actionPerformed(e);
  }
}

class FormEnlazador_EliminarListados_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_EliminarListados_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.EliminarListados_actionPerformed(e);
  }
}

class FormEnlazador_eliminarImagenes_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_eliminarImagenes_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.eliminarImagenes_actionPerformed(e);
  }
}

class FormEnlazador_GoogleButton_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_GoogleButton_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.GoogleButton_actionPerformed(e);
  }
}

class FormEnlazador_jMenuItem3_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem3_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem3_actionPerformed(e);
  }
}

class FormEnlazador_this_windowAdapter extends java.awt.event.WindowAdapter {
  FormEnlazador adaptee;

  FormEnlazador_this_windowAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void windowClosed(WindowEvent e) {
    adaptee.this_windowClosed(e);
  }
  public void windowClosing(WindowEvent e) {
    adaptee.this_windowClosing(e);
  }
  public void windowActivated(WindowEvent e) {
    adaptee.this_windowActivated(e);
  }
  public void windowOpened(WindowEvent e) {
    adaptee.this_windowOpened(e);
  }
}

class FormEnlazador_jCheckBoxDetalleDescarga_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jCheckBoxDetalleDescarga_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBoxDetalleDescarga_actionPerformed(e);
  }
}


class FormEnlazador_EliminarTodoListados_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_EliminarTodoListados_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.EliminarTodoListados_actionPerformed(e);
  }
}

class FormEnlazador_EliminarTodoImagenes_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_EliminarTodoImagenes_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.EliminarTodoImagenes_actionPerformed(e);
  }
}

class FormEnlazador_jScrollPane3_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jScrollPane3_keyAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jScrollPane3_keyPressed(e);
  }
}


class FormEnlazador_jScrollPane3_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jScrollPane3_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jScrollPane3_mousePressed(e);
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.jScrollPane3_mouseReleased(e);
  }
}

class FormEnlazador_jScrollPane1_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_jScrollPane1_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.jScrollPane1_mousePressed(e);
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.jScrollPane1_mouseReleased(e);
  }
}

class FormEnlazador_tablaIMGS_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaIMGS_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.tablaIMGS_mousePressed(e);
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.tablaIMGS_mouseReleased(e);
  }
}

class FormEnlazador_tablaIMGS_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaIMGS_keyAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tablaIMGS_keyPressed(e);
  }
}

class FormEnlazador_tablaHistorial_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaHistorial_keyAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void keyReleased(KeyEvent e) {
    adaptee.tablaHistorial_keyReleased(e);
  }

}

class FormEnlazador_tablaHistorial_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaHistorial_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.tablaHistorial_mousePressed(e);
  }

}

class FormEnlazador_tablaURLS_mouseAdapter extends java.awt.event.MouseAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaURLS_mouseAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void mousePressed(MouseEvent e) {
    adaptee.tablaURLS_mousePressed(e);
  }
  public void mouseReleased(MouseEvent e) {
    adaptee.tablaURLS_mouseReleased(e);
  }

}

class FormEnlazador_tablaURLS_keyAdapter extends java.awt.event.KeyAdapter {
  FormEnlazador adaptee;

  FormEnlazador_tablaURLS_keyAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.tablaURLS_keyPressed(e);
  }
}

class FormEnlazador_popupImgPegar_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_popupImgPegar_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.popupImgPegar_actionPerformed(e);
  }
}

class FormEnlazador_jMenuItem5_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem5_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem5_actionPerformed(e);
  }
}

class FormEnlazador_jMenuItem4_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jMenuItem4_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem4_actionPerformed(e);
  }
}

class FormEnlazador_popupImgCopiar_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_popupImgCopiar_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.popupImgCopiar_actionPerformed(e);
  }
}

class FormEnlazador_jCheckBoxMenuItem1_actionAdapter implements java.awt.event.ActionListener {
  FormEnlazador adaptee;

  FormEnlazador_jCheckBoxMenuItem1_actionAdapter(FormEnlazador adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jCheckBoxMenuItem1_actionPerformed(e);
  }
}


