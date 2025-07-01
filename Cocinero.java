package macdronalds;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Representa al cocinero del sistema. Este rol se encarga de ver los pedidos pendientes
 * y marcarlos como preparados una vez que los cocina.
 */
public class Cocinero implements Rol, MenuCocinero {

    // Lista compartida de pedidos pendientes (estática)
    private static List<Pedido> pedidosPendientes;

    /**
     * Crea un cocinero con una lista de pedidos vacía.
     */
    public Cocinero() {
        this.setPedidosPendientes(new ArrayList<>());
    }

    /**
     * Muestra el menú del cocinero. Permite ver pedidos, preparar uno o salir.
     */
    @Override
    public void mostrarMenu() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        List<Pedido> p = getPedidosPendientes();
        while (opcion != 3) {
            System.out.println("Menú Cocinero:\n1. Ver pedidos\n2. Preparar pedido\n3. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            switch (opcion) {
                case 1:
                    verPedidosPendientes(p);
                    break;
                case 2:
                    System.out.println("Ingrese el ID del pedido a preparar: ");
                    int idPedido = scanner.nextInt();
                    scanner.nextLine(); // limpiar buffer
                    prepararPedido(idPedido);
                    break;
                case 3:
                    System.out.println("Saliendo del menú Cocinero...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Muestra por pantalla los pedidos pendientes.
     * @param pedidosPendientes lista de pedidos para mostrar
     */
    @Override
    public void verPedidosPendientes(List<Pedido> pedidosPendientes) {
        System.out.println("=== PEDIDOS PENDIENTES ===");
        for (Pedido p : pedidosPendientes) {
            System.out.println(p.toString());
        }
    }

    /**
     * Marca como preparado un pedido según su ID y lo saca de la lista de pendientes.
     * @param idPedido ID del pedido a preparar
     */
    @Override
    public void prepararPedido(int idPedido) {
        System.out.println("=== PREPARANDO PEDIDO #" + idPedido + " ===");
        boolean encontrado = false;
        Iterator<Pedido> it = pedidosPendientes.iterator();
        while (it.hasNext() && !encontrado) {
            Pedido p = it.next();
            if (p.getIdPedido() == idPedido) {
                cocinar(p.getItems());
                p.setEstado("listo");
                it.remove();
                System.out.println("Pedido #" + idPedido + " preparado.");
                encontrado = true;
            }
        }
    }

    /**
     * Simula la preparación de cada ítem del pedido.
     * @param items lista de ítems a preparar
     */
    public void cocinar(List<Item> items) {
        System.out.println("Cocinando...");
        for (Item i : items) {
            System.out.println(i.toString());
        }
    }

    /**
     * Devuelve la lista compartida de pedidos pendientes.
     * @return pedidos pendientes
     */
    public static List<Pedido> getPedidosPendientes() {
        return pedidosPendientes;
    }

    /**
     * Asigna una nueva lista de pedidos pendientes.
     * @param pedidosPendientes la lista a asignar
     */
    public static void setPedidosPendientes(List<Pedido> pedidosPendientes) {
        Cocinero.pedidosPendientes = pedidosPendientes;
    }

    /**
     * Agrega un nuevo pedido a la lista de pendientes.
     * @param pedido el pedido que llega a cocina
     */
    public void recibirPedido(Pedido pedido) {
        List<Pedido> p = getPedidosPendientes();
        p.add(pedido);
        setPedidosPendientes(p);
    }

    /**
     * Devuelve el nombre del rol, útil para mostrar en pantalla o en menús.
     * @return el string "Cocinero"
     */
    @Override
    public String getNombreRol() {
        return "Cocinero";
    }
}
