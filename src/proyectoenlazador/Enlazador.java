package proyectoenlazador;

import java.awt.*;
import javax.swing.*;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class Enlazador {
  boolean packFrame = false;

  //Construir la aplicación
  public Enlazador() {
      FormEnlazador frame = new FormEnlazador();
      //Validar marcos que tienen tamaños preestablecidos
      //Empaquetar marcos que cuentan con información de tamaño preferente útil. Ej. de su diseño.
      if (packFrame) {
        frame.pack();
      }
      else {
        frame.validate();
      }
      //Centrar la ventana
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Dimension frameSize = frame.getSize();
      if (frameSize.height > screenSize.height) {
        frameSize.height = screenSize.height;
      }
      if (frameSize.width > screenSize.width) {
        frameSize.width = screenSize.width;
      }
      frame.setLocation( (screenSize.width - frameSize.width) / 2,
                        (screenSize.height - frameSize.height) / 2);
      frame.setVisible(true);
  }

  //Método Main
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    new Enlazador();
  }
}
