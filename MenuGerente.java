package macdronalds;

import java.util.List;

/**
 * Interfaz que define el menú específico para el rol de gerente.
 * El gerente puede hacer tareas administrativas como cambiar roles, ver reportes,
 * consultar pedidos y modificar el stock.
 */
public interface MenuGerente extends Menu {

    /**
     * Cambia el rol de un usuario.
     * @param usuario el usuario a modificar
     * @param nuevoRol el nuevo rol que se le asigna
     */
    void cambiarRol(Usuario usuario, Rol nuevoRol);

    /**
     * Muestra los reportes de la caja del día.
     * @param caja la caja que se consulta
     */
    void verReportes(Caja caja);

    /**
     * Devuelve la lista completa de pedidos del día.
     * @param caja la caja del sistema
     * @return lista de pedidos
     */
    List<Pedido> obtenerPedidosDelDia(Caja caja);

    /**
     * Calcula el total de ventas del día desde la caja.
     * @param caja la caja a consultar
     * @return total recaudado
     */
    double calcularTotalDelDia(Caja caja);

    /**
     * Modifica el stock de un ingrediente, sumando o restando cantidad.
     * @param idIngrediente el ID del ingrediente
     * @param cantidad la cantidad a modificar
     */
    void modificarStock(int idIngrediente, int cantidad);
}
