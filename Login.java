package macdronalds;

import java.util.Scanner;

/**
 * Punto de entrada del sistema Macdronalds.
 * Permite que un usuario se loguee con su DNI y acceda a su menú según el rol que tenga asignado.
 */
public class Login {

    /**
     * Método principal del sistema.
     * Pide el DNI del usuario, busca su rol y lo deja interactuar con el sistema.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE LOGIN ===");
        System.out.print("Ingrese su DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        Usuario usuario = BaseDeDatos.buscarUsuarioPorDni(dni);

        if (usuario == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        Rol rol = usuario.getRolActual();

        if (rol == null) {
            System.out.println("El usuario no tiene un rol asignado. Contacte a un gerente.");
            return;
        }

        System.out.println("Bienvenido, " + usuario.getNombre() + ". Rol: " + rol.getClass().getSimpleName());
        usuario.mostrarMenu(); // esto llama al menú correspondiente al rol
    }
}
