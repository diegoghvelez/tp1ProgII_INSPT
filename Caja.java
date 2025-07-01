package macdronalds;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa la caja del local, donde se reciben y despachan pedidos.
 * También sirve para ver lo que se vendió en el día y calcular el total recaudado.
 */
public class Caja {

    // Lista de pedidos que todavía no fueron despachados
    private List<Pedido> pedidosPendientes;

    // Lista de pedidos que ya se mandaron a cocina
    private List<Pedido> pedidosDespachados;

    /**
     * Crea una caja con listas vacías de pedidos pendientes y despachados.
     */
    public Caja() {
        this.pedidosPendientes = new ArrayList<>();
        this.pedidosDespachados = new ArrayList<>();
    }

    /**
     * Recibe un nuevo pedido y lo agrega a la lista de pendientes.
     * @param pedido el pedido que llega a caja
     */
    public void recibirPedido(Pedido pedido) {
        pedidosPendientes.add(pedido);
    }

    /**
     * Despacha un pedido (si está en la lista de pendientes), lo mueve a despachados
     * y cambia su estado a "en cocina".
     * @param pedido el pedido a despachar
     */
    public void despacharPedidoACocina(Pedido pedido) {
        if (pedidosPendientes.remove(pedido)) {
            pedidosDespachados.add(pedido);
            pedido.setEstado("en cocina");
        }
    }

    /**
     * Devuelve la lista de pedidos que todavía no se despacharon.
     * @return pedidos pendientes
     */
    public List<Pedido> getPedidosPendientes() {
        return pedidosPendientes;
    }

    /**
     * Devuelve los pedidos que ya fueron despachados.
     * @return pedidos en cocina o ya procesados
     */
    public List<Pedido> getPedidosDespachados() {
        return pedidosDespachados;
    }

    /**
     * Devuelve todos los pedidos del día (pendientes + despachados).
     * @return lista completa de pedidos del día
     */
    public List<Pedido> obtenerPedidosDelDia() {
        List<Pedido> todos = new ArrayList<>();
        todos.addAll(pedidosPendientes);
        todos.addAll(pedidosDespachados);
        return todos;
    }

    /**
     * Calcula el total recaudado en el día a partir de los pedidos despachados.
     * @return total en pesos
     */
    public double calcularTotalDelDia() {
        double total = 0.0;
        for (Pedido p : pedidosDespachados) {
            total += p.getTotal();
        }
        return total;
    }
}
