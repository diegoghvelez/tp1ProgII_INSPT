package macdronalds;

/**
 * Representa un ingrediente que se usa para armar productos.
 * Por ejemplo: pan, carne, queso, etc. Cada uno tiene precio, stock y un ID único.
 */
public class Ingrediente {

    private double precio;
    private String nombre;
    private static int contador = 0;
    private int idIngrediente;
    private int stock;

    /**
     * Crea un nuevo ingrediente con su nombre, precio y stock inicial.
     * @param nombre el nombre del ingrediente
     * @param precio el precio unitario
     * @param stock la cantidad que hay en inventario
     */
    public Ingrediente(String nombre, double precio, int stock) {
        this.idIngrediente = ++contador;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Permite modificar directamente el stock (sobrescribe el valor anterior).
     * @param stock nuevo valor para el stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Devuelve el ID único de este ingrediente.
     * @return el ID
     */
    public int getIdIngrediente() {
        return idIngrediente;
    }

    /**
     * Devuelve el nombre del ingrediente.
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio por unidad del ingrediente.
     * @return el precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve cuántas unidades hay disponibles del ingrediente.
     * @return el stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Modifica el stock sumando o restando unidades.
     * Si se quiere restar más de lo que hay, tira un mensaje por consola.
     * @param cantidad cantidad a modificar (puede ser negativa)
     */
    public void modificarStock(int cantidad) {
        if (cantidad < 0 && stock < -cantidad) {
            System.out.println("El stock no puede modificarse porque supera las unidades disponibles. Quedan " + this.stock + " unidades de " + this.nombre);
        } else {
            this.stock += cantidad;
        }
    }

    /**
     * Muestra los datos del ingrediente como texto.
     * @return representación tipo "Lechuga (ID: 3, $ 80.0, stock: 50)"
     */
    @Override
    public String toString() {
        return nombre + " (ID: " + idIngrediente + ", $ " + precio + ", stock: " + stock + ")";
    }
}
