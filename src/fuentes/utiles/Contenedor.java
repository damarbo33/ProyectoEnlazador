package fuentes.utiles;

import java.util.Stack;
import fuentes.constantes.Constante;

//Definimos la clase Contenedor que será la que almacene los datos compartidos
//entre los threads productor y consumidor
public class Contenedor {
        private String dato;
        private boolean hayDato = false;
        private Stack pila = new Stack();

/*        public synchronized String get() {
                while (hayDato == false) {
                        try {
                                // espera a que el productor coloque un valor
                                wait();
                        }
                        catch (InterruptedException e) { }
                }
                hayDato = false;
                // notificar que el valor ha sido consumido
                notifyAll();
                return dato;
        }

        public synchronized void put(String valor) {
                while (hayDato == true) {
                        try {
                                // espera a que se consuma el dato
                                wait();
                        } catch (InterruptedException e) { }
                }
                dato = valor;
//                pila.push(valor);
                hayDato = true;
                // notificar que ya hay dato.
                notifyAll();
        }
      */

     public synchronized String get() {
        while (pila.empty()) {
                try {
                        // espera a que el productor coloque un valor
                        wait();
                }
                catch (InterruptedException e) { }
        }
        if (Constante.debug) System.out.println("La pila no esta vacia");
        hayDato = false;
        // notificar que el valor ha sido consumido
        //notifyAll();
        return (String)pila.pop();
}

public synchronized void put(String valor) {
        if (Constante.debug) System.out.println("Metemos un valor");
        pila.push(valor);
        hayDato = true;
        // notificar que ya hay dato.
        notifyAll();
}

public synchronized void clear() {
        if (Constante.debug) System.out.println("Vaciamos la pila");
        pila.clear();
        hayDato = false;
}


}
