package macdronalds;

import java.util.List;
import java.util.Scanner;

/**
 * Representa al gerente del sistema.
 * Este rol puede cambiar roles de usuarios, ver reportes, revisar pedidos y modificar el stock.
 */
public class Gerente implements Rol, MenuGerente {

    private static BaseDeDatos baseDeDatos;

    /**
     * Crea un gerente con acceso a la base de datos del sistema.
     * @param base referencia a la base de datos
     */
    public Gerente(BaseDeDatos base) {
        setBaseDeDatos(base);
    }

    /**
     * Asigna la base de datos a nivel general para que el gerente la use.
     * @param baseDeDatos referencia a la base
     */
    public static void setBaseDeDatos(BaseDeDatos baseDeDatos) {
        Gerente.baseDeDatos = baseDeDatos;
    }

    /**
     * Muestra el menú del gerente, con distintas acciones administrativas.
     * Se puede cambiar rol, ver reportes, modificar stock, entre otros.
     */
    @Override
    public void mostrarMenu() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        while (opcion != 5) {
            System.out.println("Menu Gerente:\n1. Cambiar rol\n2. Ver reportes\n3. Ver pedidos del dia\n4. Modificar stock\n5. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            List<Usuario> usuarios = BaseDeDatos.obtenerUsuariosPredefinidos();
            Usuario userEncontrado = null;
            Rol rol = null;

            switch (opcion) {
                case 1:
                    System.out.println("=== CAMBIO DE ROL A USUARIO EXISTENTE ===");
                    System.out.print("Ingrese el DNI del usuario: ");
                    int dniBuscado = scanner.nextInt();
                    scanner.nextLine();

                    userEncontrado = BaseDeDatos.buscarUsuarioPorDni(dniBuscado);

                    if (userEncontrado == null) {
                        System.out.println("Usuario no encontrado con ese DNI.");
                        break;
                    }

                    System.out.println("Usuario encontrado: " + userEncontrado.getNombre());
                    System.out.println("Seleccione el nuevo rol:\n1. Vendedor\n2. Cocinero");

                    int nuevoRol = scanner.nextInt();
                    scanner.nextLine();

                    Rol rolAsignado = switch (nuevoRol) {
                        case 1 -> new Vendedor(baseDeDatos);
                        case 2 -> new Cocinero();
                        default -> null;
                    };

                    if (rolAsignado != null) {
                        cambiarRol(userEncontrado, rolAsignado);
                        System.out.println("Rol actualizado correctamente para " + userEncontrado.getNombre());
                    } else {
                        System.out.println("Opción inválida. No se cambió el rol.");
                    }
                    break;

                case 2:
                    System.out.println("=== VER REPORTES DE VENDEDOR ===");
                    mostrarUsuariosConRoles(usuarios);

                    System.out.print("\nIngrese el DNI del vendedor: ");
                    int dniVendedor = scanner.nextInt();
                    scanner.nextLine();

                    userEncontrado = BaseDeDatos.buscarUsuarioPorDni(dniVendedor);
                    if (userEncontrado == null || !(userEncontrado.getRolActual() instanceof Vendedor vendedor)) {
                        System.out.println("No se encontró un vendedor con ese DNI.");
                        break;
                    }

                    verReportes(vendedor.accesoCaja());
                    break;

                case 3:
                    System.out.println("=== USUARIOS DISPONIBLES ===");
                    mostrarUsuariosConRoles(usuarios);

                    System.out.print("\nIngrese el DNI del vendedor: ");
                    int dniVerPedidos = scanner.nextInt();
                    scanner.nextLine();

                    userEncontrado = BaseDeDatos.buscarUsuarioPorDni(dniVerPedidos);
                    if (userEncontrado == null || !(userEncontrado.getRolActual() instanceof Vendedor vendedorPedidos)) {
                        System.out.println("No se encontró un vendedor con ese DNI.");
                        break;
                    }

                    for (Pedido p : obtenerPedidosDelDia(vendedorPedidos.accesoCaja())) {
                        System.out.println(p);
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el ID del ingrediente a modificar el stock:");
                    int idIng = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la cantidad a agregar:");
                    int cant = scanner.nextInt();
                    scanner.nextLine();
                    modificarStock(idIng, cant);
                    break;

                case 5:
                    System.out.println("Saliendo del menú Gerente...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void mostrarUsuariosConRoles(List<Usuario> usuarios) {
        for (Usuario u : usuarios) {
            String rolNombre = (u.getRolActual() != null) ? u.getRolActual().getClass().getSimpleName() : "Sin rol";
            System.out.println("Nombre: " + u.getNombre() + " | DNI: " + u.getDni() + " | Rol: " + rolNombre);
        }
    }

    /**
     * Accede a la caja de un vendedor.
     * @param vendedor el vendedor
     * @return la caja asignada
     */
    public Caja accesoCajaVendedor(Vendedor vendedor) {
        return vendedor.accesoCaja();
    }

    /**
     * Cambia el rol asignado a un usuario.
     * @param usuario el usuario a modificar
     * @param nuevoRol el nuevo rol que se le asigna
     */
    @Override
    public void cambiarRol(Usuario usuario, Rol nuevoRol) {
        usuario.cambiarRol(nuevoRol);
    }

    /**
     * Muestra los reportes de ventas del día para una caja.
     * @param caja la caja a consultar
     */
    @Override
    public void verReportes(Caja caja) {
        System.out.println("=== Reporte de Ventas del Día ===");
        List<Pedido> pedidos = caja.obtenerPedidosDelDia();
        for (Pedido p : pedidos) {
            System.out.println(p);
            System.out.println("-----------------------------");
        }
        System.out.printf("TOTAL RECAUDADO: $%.2f\n", caja.calcularTotalDelDia());
    }

    /**
     * Devuelve los pedidos del día desde una caja.
     * @param caja la caja a consultar
     * @return lista de pedidos
     */
    @Override
    public List<Pedido> obtenerPedidosDelDia(Caja caja) {
        return caja.obtenerPedidosDelDia();
    }

    /**
     * Modifica el stock de un ingrediente por ID.
     * @param idIngrediente el ID del ingrediente
     * @param cantidad la cantidad a agregar
     */
    @Override
    public void modificarStock(int idIngrediente, int cantidad) {
        Ingrediente ingrediente = BaseDeDatos.buscarPorId(idIngrediente);

        if (ingrediente == null) {
            System.out.println("No se encontró un ingrediente con ese ID.");
            return;
        }

        int stockAnterior = ingrediente.getStock();
        ingrediente.setStock(stockAnterior + cantidad);

        System.out.println("Stock actualizado correctamente.");
        System.out.printf("Ingrediente: %s\nStock anterior: %d\nCantidad agregada: %d\nNuevo stock: %d\n",
                ingrediente.getNombre(), stockAnterior, cantidad, ingrediente.getStock());
    }

    /**
     * Devuelve el nombre del rol.
     * @return el string "Gerente"
     */
    @Override
    public String getNombreRol() {
        return "Gerente";
    }

    /**
     * Calcula el total recaudado en el día desde la caja.
     * @param caja la caja a consultar
     * @return total en pesos
     */
    @Override
    public double calcularTotalDelDia(Caja caja) {
        return caja.calcularTotalDelDia();
    }
}
