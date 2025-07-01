package macdronalds;

/**
 * Interfaz que define el menú del rol de vendedor.
 * El vendedor puede crear pedidos, agregar o modificar productos, y confirmar la venta.
 */
public interface MenuVendedor extends Menu {

    /**
     * Crea un nuevo pedido vacío.
     * @return el pedido recién creado
     */
    Pedido crearPedido();

    /**
     * Agrega un producto al pedido actual, según su ID.
     * @param idProducto identificador del producto a agregar
     */
    void agregarProducto(int idProducto);

    /**
     * Modifica un producto del pedido actual.
     * Puede agregar o quitar ingredientes, según el flag `esAgregar`.
     * @param idProducto ID del producto a modificar
     * @param idIngrediente ID del ingrediente a modificar
     * @param esAgregar true si se quiere agregar, false si se quiere quitar
     * @param cantidad cantidad de ingredientes a modificar
     */
    void modificarProducto(int idProducto, int idIngrediente, boolean esAgregar, int cantidad);

    /**
     * Confirma el pedido actual y lo envía a la cocina.
     */
    void confirmarPedido();
}
