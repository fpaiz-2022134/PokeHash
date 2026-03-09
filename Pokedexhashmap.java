public class Pokedexhashmap{

    // Cada nodo guarda un Pokemon y apunta al siguiente.
    private static class Nodo {
        int    numero;
        String nombre;
        String tipo;
        Nodo   siguiente;

        Nodo(int numero, String nombre, String tipo) {
            this.numero    = numero;
            this.nombre    = nombre;
            this.tipo      = tipo;
            this.siguiente = null;
        }

        public String toString() {
            return "#" + numero + " " + nombre + " [" + tipo + "]";
        }
    }

    private Nodo[] tabla;
    private int    capacidad;
    private int    total;

    public PokedexHashMap(int capacidad) {
        this.capacidad = capacidad;
        this.tabla     = new Nodo[capacidad];
        this.total     = 0;
    }

    // Convierte el numero del Pokemon en un indice valido para la tabla
    private int generarHash(int numero) {
        long h = (long) numero * 2_654_435_769L;
        h = h ^ (h >>> 16);
        int indice = (int) (h % capacidad);
        if (indice < 0) indice += capacidad;
        return indice;
    }

    // Agrega un Pokemon. Si el numero ya existe, actualiza sus datos
    public void agregar(int numero, String nombre, String tipo) {
        int  indice = generarHash(numero);
        Nodo actual = tabla[indice];

        // Buscar si ya existe
        while (actual != null) {
            if (actual.numero == numero) {
                actual.nombre = nombre;
                actual.tipo   = tipo;
                System.out.println("  Actualizado: " + actual);
                return;
            }
            actual = actual.siguiente;
        }

        // Insertar al inicio de la cadena
        Nodo nuevo = new Nodo(numero, nombre, tipo);
        nuevo.siguiente = tabla[indice];
        tabla[indice]   = nuevo;
        total++;

        if (nuevo.siguiente != null)
            System.out.println("  Colision en balde " + indice + ": " + nuevo + " -> " + nuevo.siguiente);
        else
            System.out.println("  Insertado en balde " + indice + ": " + nuevo);
    }

    // Busca un Pokemon por numero, retorna null si no existe
    public Nodo buscar(int numero) {
        int  indice = generarHash(numero);
        Nodo actual = tabla[indice];

        while (actual != null) {
            if (actual.numero == numero) return actual;
            actual = actual.siguiente;
        }
        return null;
    }

    // Elimina un Pokemon y reconecta la cadena
    public boolean eliminar(int numero) {
        int  indice   = generarHash(numero);
        Nodo actual   = tabla[indice];
        Nodo anterior = null;

        while (actual != null) {
            if (actual.numero == numero) {
                if (anterior == null)
                    tabla[indice] = actual.siguiente;   // era el primero
                else
                    anterior.siguiente = actual.siguiente; // saltarse el nodo

                total--;
                System.out.println("  Eliminado: " + actual);
                return true;
            }
            anterior = actual;
            actual   = actual.siguiente;
        }

        System.out.println("  No encontrado: #" + numero);
        return false;
    }

    // Muestra todos los baldes que tienen al menos un Pokemon
    public void mostrarTabla() {
        System.out.println("\n--- Tabla Hash (" + capacidad + " baldes, " + total + " Pokemon) ---");
        for (int i = 0; i < capacidad; i++) {
            if (tabla[i] == null) continue;

            System.out.print("  [" + i + "] ");
            Nodo actual = tabla[i];
            while (actual != null) {
                System.out.print(actual);
                if (actual.siguiente != null) System.out.print(" -> ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------\n");
    }

    public int getTotal()     { return total; }
    public int getCapacidad() { return capacidad; }
}