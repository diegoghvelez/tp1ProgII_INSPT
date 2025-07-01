package macdronalds;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal del sistema Macdronalds.
 * Se encarga de iniciar la app, asignar roles iniciales y manejar el login de usuarios por consola.
 */
public class Macdronalds {

    /**
     * Punto de entrada del sistema.
     * Asigna roles por defecto y permite iniciar sesión con el DNI.
     * Según el rol, muestra el menú correspondiente.
     */
    public static void main(String[] args) {
        // Inicializa la base de datos con usuarios e ingredientes
        BaseDeDatos base = new BaseDeDatos();

        // Asignación de roles por defecto
        Usuario gerenteUser = BaseDeDatos.buscarUsuarioPorDni(98765432); // Admin
        Usuario vendedorUser = BaseDeDatos.buscarUsuarioPorDni(12345678); // Juan Pérez
        Usuario cocineroUser = BaseDeDatos.buscarUsuarioPorDni(23456789); // María López

        gerenteUser.cambiarRol(new Gerente(base));
        vendedorUser.cambiarRol(new Vendedor(base));
        cocineroUser.cambiarRol(new Cocinero());

        Cocinero.setPedidosPendientes(new ArrayList<>());

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== BIENVENIDO AL SISTEMA MACDRONALDS ===");

        while (true) {
            System.out.print("\nIngrese su DNI (o 0 para salir): ");
            int dni = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            if (dni == 0) {
                System.out.println("Sesión finalizada. Gracias por usar el sistema.");
                break;
            }

            Usuario usuario = BaseDeDatos.buscarUsuarioPorDni(dni);

            if (usuario == null) {
                System.out.println("Usuario no encontrado.");
                continue;
            }

            Rol rol = usuario.getRolActual();

            if (rol == null) {
                System.out.println("El usuario no tiene rol asignado. Contacte al gerente.");
                continue;
            }

            System.out.println("Bienvenido " + usuario.getNombre() + ". Rol: " + rol.getNombreRol());
            rol.mostrarMenu(); // Ejecuta el menú correspondiente al rol
        }
    }
}
