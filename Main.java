public class Main {

    public static void main(String[] args) {

        Pokedexhashmap pokedex = new Pokedexhashmap(10);

        // --- Insertar ---
        System.out.println("=== Insertando ===");
        pokedex.agregar(  1, "Bulbasaur",  "Planta");
        pokedex.agregar(  4, "Charmander", "Fuego");
        pokedex.agregar(  7, "Squirtle",   "Agua");
        pokedex.agregar( 25, "Pikachu",    "Electrico");
        pokedex.agregar( 39, "Jigglypuff", "Normal");
        pokedex.agregar( 52, "Meowth",     "Normal");
        pokedex.agregar(133, "Eevee",      "Normal");
        pokedex.agregar(143, "Snorlax",    "Normal");
        pokedex.agregar(150, "Mewtwo",     "Psiquico");
        pokedex.agregar(151, "Mew",        "Psiquico");

        pokedex.mostrarTabla();

        // --- Buscar ---
        System.out.println("=== Buscando ===");
        buscar(pokedex,  25);  // existe
        buscar(pokedex, 150);  // existe
        buscar(pokedex,  99);  // no existe

        // --- Actualizar ---
        System.out.println("\n=== Actualizando Pikachu ===");
        pokedex.agregar(25, "Pikachu", "Electrico/Volador");
        buscar(pokedex, 25);

        // --- Eliminar ---
        System.out.println("\n=== Eliminando ===");
        pokedex.eliminar(  7);  // existe
        pokedex.eliminar(151);  // existe
        pokedex.eliminar( 99);  // no existe

        pokedex.mostrarTabla();

        // --- Verificar eliminados ---
        System.out.println("=== Verificando eliminados ===");
        buscar(pokedex,   7);   // debe ser null
        buscar(pokedex, 151);   // debe ser null
        buscar(pokedex,   1);   // debe existir

        System.out.println("\nTotal en tabla: " + pokedex.getTotal());
    }

    static void buscar(PokedexHashMap pokedex, int numero) {
        var resultado = pokedex.buscar(numero);
        if (resultado != null)
            System.out.println("  Encontrado: " + resultado);
        else
            System.out.println("  No existe: #" + numero);
    }
}