package macdronalds;

/**
 * Clase abstracta que representa un producto del sistema Macdronalds.
 * Puede ser una hamburguesa, unas papas, una bebida, lo que sea.
 * Todos comparten precio, stock, ID y la posibilidad de tener extras.
 */
public abstract class Producto {

    private double precio;
    private double totalExtras;
    private static int contador = 100;
    private int idProducto;
    int stock;

    /**
     * Constructor que inicializa precio y stock.
     * También le asigna un ID único y lo guarda en la "base de datos".
     * @param precio precio base del producto
     * @param cantStock cuántas unidades hay disponibles
     */
    public Producto(double precio, int cantStock) {
        this.idProducto = ++contador;
        this.precio = precio;
        this.stock = cantStock;
        this.totalExtras = 0.0;
        BaseDeDatos.setProductos(this);
    }

    /**
     * Devuelve el precio final del producto (precio base + extras).
     * @return precio total
     */
    public double getPrecioFinal() {
        return precio + totalExtras;
    }

    /**
     * Devuelve el ID único de este producto.
     * @return ID del producto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Devuelve cuántas unidades hay en stock.
     * @return cantidad disponible
     */
    public int getStock() {
        return stock;
    }

    /**
     * Modifica el stock sumando o restando unidades.
     * Si se intenta restar más de lo que hay, tira un mensaje de error.
     * @param cant cantidad a modificar (puede ser negativa)
     */
    public void modificarStock(int cant) {
        if (cant < 0 && stock < -cant) {
            System.out.println("No puede quitar esa cantidad porque supera el stock actual de " + this.stock + " unidades");
        }
        this.stock += cant;
    }

    /**
     * Suma un monto al total de extras del producto.
     * @param extras valor a agregar
     */
    public void addTotalExtras(double extras) {
        this.totalExtras += extras;
    }
}
