package macdronalds;

/**
 * Representa un ítem que se puede agregar a un pedido.
 * Un ítem puede ser un producto suelto (como una hamburguesa)
 * o un combo armado. Nunca los dos al mismo tiempo.
 */
public class Item {

    Producto producto;
    Combo combo;

    /**
     * Crea un ítem con un producto suelto.
     * @param producto producto que se quiere agregar al pedido
     */
    public Item(Producto producto) {
        this.producto = producto;
        this.combo = null;
    }

    /**
     * Crea un ítem con un combo.
     * @param combo combo que se quiere agregar al pedido
     */
    public Item(Combo combo) {
        this.combo = combo;
        this.producto = null;
    }

    /**
     * Devuelve el precio del ítem.
     * Si es un producto, devuelve su precio final.
     * Si es un combo, devuelve el precio del combo.
     * @return precio del ítem
     */
    public double getPrecio() {
        double precio = 0.0;
        if (producto != null) {
            precio = producto.getPrecioFinal();
        } else if (combo != null) {
            precio = combo.getPrecio();
        }
        return precio;
    }

    /**
     * Devuelve una descripción del ítem (producto o combo).
     * @return texto con el tipo y nombre del ítem
     */
    @Override
    public String toString() {
        if (producto != null) {
            return "Producto: " + producto.getClass().getSimpleName();
        } else if (combo != null) {
            return "Combo: " + combo.getNombre();
        } else {
            return "Item vacío";
        }
    }
}
