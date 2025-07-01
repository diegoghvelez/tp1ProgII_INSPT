package macdronalds;

import java.util.List;

/**
 * Interfaz que define el comportamiento del menú para el rol de cocinero.
 * Extiende de Menu, así que también incluye mostrar el menú.
 */
public interface MenuCocinero extends Menu {

    /**
     * Muestra la lista de pedidos pendientes para preparar.
     * @param pedidosPendientes lista de pedidos aún no preparados
     */
    void verPedidosPendientes(List<Pedido> pedidosPendientes);

    /**
     * Marca un pedido como preparado según su ID.
     * @param idPedido el identificador del pedido a preparar
     */
    void prepararPedido(int idPedido);
}
