/**
 * Nodo de la lista enlazada que vive dentro de cada balde del HashMap.
 * Guarda la llave (nombre del Pokémon), el valor (objeto Pokemon)
 * y un puntero al siguiente nodo para gestionar colisiones.
 */
public class Nodo {

    String  llave;
    Pokemon valor;
    Nodo    siguiente;

    public Nodo(String llave, Pokemon valor) {
        this.llave     = llave;
        this.valor     = valor;
        this.siguiente = null;
    }
}
