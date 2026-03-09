/**
 * Clase principal que demuestra el funcionamiento de la Pokédex personalizada.
 * Prueba las operaciones de agregar, buscar, eliminar y mostrar la tabla.
 */
public class Main {

    public static void main(String[] args) {

        PokedexHashMap pokedex = new PokedexHashMap();

        // Agregar Pokémon de distintos tipos y niveles
        pokedex.agregar("Pikachu",    new Pokemon("Pikachu",    "Eléctrico", 35));
        pokedex.agregar("Charmander", new Pokemon("Charmander", "Fuego",     20));
        pokedex.agregar("Squirtle",   new Pokemon("Squirtle",   "Agua",      18));
        pokedex.agregar("Bulbasaur",  new Pokemon("Bulbasaur",  "Planta",    22));
        pokedex.agregar("Mewtwo",     new Pokemon("Mewtwo",     "Psíquico",  70));
        pokedex.agregar("Mew",        new Pokemon("Mew",        "Psíquico",  50));
        pokedex.agregar("Gengar",     new Pokemon("Gengar",     "Fantasma",  42));
        pokedex.agregar("Eevee",      new Pokemon("Eevee",      "Normal",    25));

        // Mostrar toda la tabla para ver distribución y colisiones
        pokedex.mostrarTabla();

        // Buscar un Pokémon existente
        System.out.println("--- Búsqueda ---");
        Pokemon encontrado = pokedex.buscar("Pikachu", "Eléctrico", 35);
        System.out.println("Buscando Pikachu: " + (encontrado != null ? encontrado : "No encontrado"));

        // Buscar uno que no existe
        Pokemon noExiste = pokedex.buscar("Charizard", "Fuego", 50);
        System.out.println("Buscando Charizard: " + (noExiste != null ? noExiste : "No encontrado"));

        // Eliminar un Pokémon
        System.out.println("\n--- Eliminación ---");
        boolean eliminado = pokedex.eliminar("Gengar", "Fantasma", 42);
        System.out.println("Eliminando Gengar: " + (eliminado ? "Éxito" : "No encontrado"));

        // Verificar que ya no existe
        Pokemon despuesDeEliminar = pokedex.buscar("Gengar", "Fantasma", 42);
        System.out.println("Buscando Gengar tras eliminarlo: " + (despuesDeEliminar != null ? despuesDeEliminar : "No encontrado"));

        // Actualizar un Pokémon existente (subir de nivel)
        System.out.println("\n--- Actualización ---");
        pokedex.agregar("Pikachu", new Pokemon("Pikachu", "Eléctrico", 60));
        Pokemon actualizado = pokedex.buscar("Pikachu", "Eléctrico", 60);
        System.out.println("Pikachu actualizado: " + actualizado);

        // Estado final de la tabla
        pokedex.mostrarTabla();
    }
}
