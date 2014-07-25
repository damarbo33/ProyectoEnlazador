package proyectoenlazador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class FormEnlazador_AcercaDe
    extends JDialog
    implements ActionListener {

  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JPanel insetsPanel1 = new JPanel();
  JPanel insetsPanel2 = new JPanel();
  JPanel insetsPanel3 = new JPanel();
  JButton button1 = new JButton();
  JLabel imageLabel = new JLabel();
  JLabel label1 = new JLabel();
  JLabel label2 = new JLabel();
  JLabel label3 = new JLabel();
  JLabel label4 = new JLabel();
  ImageIcon image1 = new ImageIcon();
  BorderLayout borderLayout1 = new BorderLayout();
  BorderLayout borderLayout2 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout1 = new GridLayout();
  String product = "";
  String version = "1.0";
  String copyright = "Copyright (c) 2007";
  String comments = "";
  JTextArea jTextArea1 = new JTextArea();

  public FormEnlazador_AcercaDe(Frame parent) {
    super(parent);
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  FormEnlazador_AcercaDe() {
    this(null);
  }

  //Inicialización de componentes
  private void jbInit() throws Exception {
    image1 = new ImageIcon(proyectoenlazador.FormEnlazador.class.getResource(
        "about.png"));
    imageLabel.setIcon(image1);
    this.setModal(true);
    this.setTitle("Acerca de");
    panel1.setLayout(borderLayout1);
    panel2.setLayout(borderLayout2);
    insetsPanel1.setLayout(flowLayout1);
    insetsPanel2.setLayout(flowLayout1);
    insetsPanel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    gridLayout1.setRows(4);
    gridLayout1.setColumns(1);
    label1.setText(product);
    label2.setText("Enlazador 1.0");
    label3.setText(copyright);
    label4.setText("Pandani Software");
    insetsPanel3.setLayout(gridLayout1);
    insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 10));
    button1.setText("Aceptar");
    button1.addActionListener(this);
    jTextArea1.setBackground(SystemColor.control);
    jTextArea1.setEnabled(true);
    jTextArea1.setAlignmentY((float) 0.5);
    jTextArea1.setBorder(BorderFactory.createEtchedBorder());
    jTextArea1.setDoubleBuffered(false);
    jTextArea1.setEditable(false);
    jTextArea1.setText("Para sugerencias, alabanzas o lo que te plazca,\nenvía un email al autor:\n\nesvenco69@gmail.com\n\nSi te ha gustado el programa ¡¡Me gustará saberlo!!");
    insetsPanel2.add(imageLabel, null);
    panel2.add(insetsPanel2, BorderLayout.WEST);
    this.getContentPane().add(panel1, null);
    insetsPanel3.add(label1, null);
    insetsPanel3.add(label2, null);
    insetsPanel3.add(label3, null);
    insetsPanel3.add(label4, null);
    panel1.add(jTextArea1, BorderLayout.CENTER);
    panel2.add(insetsPanel3, BorderLayout.CENTER);
    insetsPanel1.add(button1, null);
    panel1.add(insetsPanel1, BorderLayout.SOUTH);
    panel1.add(panel2, BorderLayout.NORTH);
    setResizable(false);
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
    if (e.getSource() == button1) {
      cancel();
    }
  }
}
