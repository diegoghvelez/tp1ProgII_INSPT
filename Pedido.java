package macdronalds;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido dentro del sistema.
 * Cada pedido tiene una lista de ítems, un total, un estado (pendiente, listo, etc.) y un ID único.
 */
public class Pedido {

    private List<Item> items;
    private static int contador = 1000;
    private int idPedido;
    private double total;
    private String estado;

    /**
     * Constructor. Crea un nuevo pedido vacío, con estado "pendiente" y le asigna un ID único.
     */
    public Pedido() {
        this.idPedido = ++contador;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.estado = "pendiente";
    }

    /**
     * Devuelve el número de pedido.
     * @return ID del pedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Agrega un ítem al pedido y actualiza el total.
     * @param item ítem a agregar
     */
    public void agregarItem(Item item) {
        items.add(item);
        total += item.getPrecio();
    }

    /**
     * Devuelve la lista de ítems del pedido.
     * @return lista de ítems
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Devuelve el total acumulado del pedido.
     * @return total en pesos
     */
    public double getTotal() {
        return total;
    }

    /**
     * Devuelve el estado actual del pedido.
     * @return estado como texto
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Cambia el estado del pedido.
     * @param estado nuevo estado (por ejemplo: "listo")
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve un resumen del pedido en formato texto, con sus ítems y el total.
     * @return string representando el pedido
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(idPedido).append("\n");
        for (Item i : items) {
            sb.append("- ").append(i.toString()).append("\n");
        }
        sb.append("Total: $").append(total);
        return sb.toString();
    }
}
