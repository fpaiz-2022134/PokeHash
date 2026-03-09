
public class PokedexHashMap {

    private static final int CAPACIDAD = 31;

    // Arreglo de baldes; cada balde es la cabeza de una lista enlazada
    private Nodo[] tabla;

    // Cantidad de entradas almacenadas
    private int tamanio;

    public PokedexHashMap() {
        tabla    = new Nodo[CAPACIDAD];
        tamanio  = 0;
    }

    // ----------------------------------------------------------------
    // Función hash principal
    // ----------------------------------------------------------------

    /**
     * Convierte los atributos de un Pokémon en un índice válido para el arreglo.
     *
     * Paso 1 – Suma ponderada del nombre:
     *   Cada carácter del nombre se multiplica por su posición (base 1).
     *   Esto hace que "MEW" y "WEM" produzcan valores distintos.
     *
     * Paso 2 – Factor del tipo:
     *   El resultado anterior se multiplica por la longitud del tipo.
     *   Pokémon del mismo nombre pero diferente tipo quedan en baldes distintos.
     *
     * Paso 3 – Integración del nivel:
     *   El nivel se mezcla en una posición "aleatoria" multiplicándolo por
     *   31^(mitad de la longitud del nombre). El primo 31 asegura una buena
     *   dispersión sin que valores bajos de nivel agrupen todo en los primeros índices.
     *
     * Paso 4 – Módulo:
     *   Se aplica % CAPACIDAD para obtener un índice dentro del rango [0, 30].
     *   Math.abs evita índices negativos por posible overflow.
     */
    private int generarHash(Pokemon pokemon) {

        String nombre = pokemon.getNombre().toLowerCase();
        String tipo   = pokemon.getTipo().toLowerCase();
        int    nivel  = pokemon.getNivel();

        // Paso 1: suma ponderada del nombre
        int sumaPonderada = 0;
        for (int i = 0; i < nombre.length(); i++) {
            sumaPonderada += nombre.charAt(i) * (i + 1);
        }

        // Paso 2: multiplicar por la longitud del tipo
        int hashBase = sumaPonderada * tipo.length();

        // Paso 3: insertar el nivel en una posición dispersa
        int posicionNivel = (int) Math.pow(31, nombre.length() / 2.0);
        int hashFinal     = hashBase + (nivel * posicionNivel);

        // Paso 4: reducir al rango del arreglo
        return Math.abs(hashFinal % CAPACIDAD);
    }

    // ----------------------------------------------------------------
    // Operaciones del HashMap
    // ----------------------------------------------------------------

    /**
     * Agrega o actualiza un Pokémon en la tabla.
     * Si ya existe un Pokémon con la misma llave, actualiza su valor.
     * Si hay colisión con otra llave, encadena el nodo al final de la lista.
     *
     * @param llave   nombre que se usará para buscar el Pokémon
     * @param pokemon objeto a almacenar
     */
    public void agregar(String llave, Pokemon pokemon) {
        int indice = generarHash(pokemon);
        Nodo nodoActual = tabla[indice];

        // Recorrer la lista para ver si la llave ya existe
        while (nodoActual != null) {
            if (nodoActual.llave.equals(llave)) {
                nodoActual.valor = pokemon; // actualizar si ya existe
                return;
            }
            nodoActual = nodoActual.siguiente;
        }

        // La llave no existe → crear nodo nuevo al inicio de la lista
        Nodo nuevoNodo = new Nodo(llave, pokemon);
        nuevoNodo.siguiente = tabla[indice];
        tabla[indice]       = nuevoNodo;
        tamanio++;
    }

    /**
     * Busca un Pokémon por su llave.
     * Recorre la lista del balde hasta encontrar la coincidencia exacta.
     *
     * @param llave   nombre con el que fue registrado el Pokémon
     * @param tipo    tipo del Pokémon (necesario para recalcular el hash)
     * @param nivel   nivel del Pokémon (necesario para recalcular el hash)
     * @return el objeto Pokemon, o null si no existe
     */
    public Pokemon buscar(String llave, String tipo, int nivel) {
        // Reconstruimos un Pokemon temporal solo para calcular el índice
        Pokemon temporal = new Pokemon(llave, tipo, nivel);
        int  indice      = generarHash(temporal);
        Nodo nodoActual  = tabla[indice];

        while (nodoActual != null) {
            if (nodoActual.llave.equals(llave)) {
                return nodoActual.valor;
            }
            nodoActual = nodoActual.siguiente;
        }

        return null; // no encontrado
    }

    /**
     * Elimina un Pokémon de la tabla usando su llave.
     *
     * @return true si fue eliminado, false si no existía
     */
    public boolean eliminar(String llave, String tipo, int nivel) {
        Pokemon temporal = new Pokemon(llave, tipo, nivel);
        int  indice      = generarHash(temporal);
        Nodo actual      = tabla[indice];
        Nodo anterior    = null;

        while (actual != null) {
            if (actual.llave.equals(llave)) {
                if (anterior == null) {
                    tabla[indice] = actual.siguiente; // era el primer nodo
                } else {
                    anterior.siguiente = actual.siguiente; // saltar el nodo
                }
                tamanio--;
                return true;
            }
            anterior = actual;
            actual   = actual.siguiente;
        }

        return false; // no encontrado
    }

    /**
     * Muestra todos los baldes de la tabla con su contenido.
     * Útil para visualizar la distribución y detectar colisiones.
     */
    public void mostrarTabla() {
        System.out.println("\n===== Estado de la Pokédex =====");
        for (int i = 0; i < CAPACIDAD; i++) {
            if (tabla[i] != null) {
                System.out.print("Balde [" + i + "]: ");
                Nodo actual = tabla[i];
                while (actual != null) {
                    System.out.print("[" + actual.llave + " → " + actual.valor + "]");
                    if (actual.siguiente != null) System.out.print(" → ");
                    actual = actual.siguiente;
                }
                System.out.println();
            }
        }
        System.out.println("Total de entradas: " + tamanio);
        System.out.println("================================\n");
    }

    public int getTamanio() { return tamanio; }
}
