package macdronalds;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un combo del menú, que junta varios productos por un precio fijo.
 * Por ejemplo: "Combo Clásico" con hamburguesa, papas y bebida.
 */
public class Combo {

    // Nombre del combo (ej: "Combo Doble Queso")
    private String nombre;

    // Lista de productos que incluye el combo
    private List<Producto> productos;

    // Precio total del combo (puede tener descuento respecto al precio individual)
    private double precio;

    /**
     * Crea un combo con nombre, precio y lista vacía de productos.
     * @param nombre nombre del combo
     * @param precio precio final del combo
     */
    public Combo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.productos = new ArrayList<>();
    }

    /**
     * Agrega un producto a la lista del combo.
     * @param producto el producto que se quiere incluir
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Devuelve el nombre del combo.
     * @return nombre como texto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio total del combo.
     * @return precio del combo
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve todos los productos que componen el combo.
     * @return lista de productos
     */
    public List<Producto> getProductos() {
        return productos;
    }
}
