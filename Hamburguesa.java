package macdronalds;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una hamburguesa armada con ingredientes.
 * Es un tipo de producto que puede tener varios ingredientes,
 * y permite agregarle o quitarle cosas al gusto del cliente.
 */
public class Hamburguesa extends Producto {

    // Lista de ingredientes que tiene esta hamburguesa
    private List<Ingrediente> ingredientes;

    /**
     * Crea una hamburguesa con una lista de ingredientes, un precio base y un stock inicial.
     * Cada ingrediente resta una unidad de stock automáticamente.
     * @param ingredientes ingredientes que componen la hamburguesa
     * @param precio precio base
     * @param cantStock cantidad de hamburguesas disponibles
     */
    public Hamburguesa(List<Ingrediente> ingredientes, double precio, int cantStock) {
        super(precio, cantStock);
        this.ingredientes = ingredientes;
        for (Ingrediente i : ingredientes) {
            i.modificarStock(-1);
        }
    }

    /**
     * Agrega un nuevo ingrediente a la hamburguesa.
     * También descuenta 1 unidad del stock de ese ingrediente
     * y suma su precio como extra.
     * @param ingrediente el nuevo ingrediente a agregar
     */
    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
        ingrediente.modificarStock(-1);
        this.addTotalExtras(ingrediente.getPrecio());
    }

    /**
     * Quita un ingrediente de la hamburguesa.
     * También devuelve 1 unidad al stock del ingrediente.
     * @param ingrediente el ingrediente a eliminar
     */
    public void quitarIngrediente(Ingrediente ingrediente) {
        ingredientes.remove(ingrediente);
        ingrediente.modificarStock(+1);
    }

    /**
     * Devuelve todos los ingredientes que tiene esta hamburguesa.
     * @return lista de ingredientes
     */
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
