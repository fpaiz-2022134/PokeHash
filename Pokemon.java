/**
 * Representa un Pokémon con nombre, tipo y nivel.
 * Es la entidad que se almacenará en el HashMap personalizado.
 */
public class Pokemon {

    private String nombre;
    private String tipo;
    private int nivel;

    public Pokemon(String nombre, String tipo, int nivel) {
        this.nombre = nombre;
        this.tipo   = tipo;
        this.nivel  = nivel;
    }

    public String getNombre() { return nombre; }
    public String getTipo()   { return tipo; }
    public int    getNivel()  { return nivel; }

    @Override
    public String toString() {
        return "Pokemon{nombre='" + nombre + "', tipo='" + tipo + "', nivel=" + nivel + "}";
    }
}
