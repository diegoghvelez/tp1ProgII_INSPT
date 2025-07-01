package macdronalds;

/**
 * Representa a un usuario dentro del sistema Macdronalds.
 * Cada usuario tiene un DNI, un nombre y un rol asignado (como gerente, vendedor o cocinero).
 */
public class Usuario implements Menu {

    private int dni;
    private String nombre;
    private Rol rolActual;

    /**
     * Crea un usuario con su DNI, nombre y rol inicial (puede ser null si no tiene).
     * @param dni documento del usuario
     * @param nombre nombre completo
     * @param rolInicial rol asignado (puede ser null)
     */
    public Usuario(int dni, String nombre, Rol rolInicial) {
        this.dni = dni;
        this.nombre = nombre;
        this.rolActual = rolInicial;
    }

    /**
     * Devuelve el DNI del usuario.
     * @return el número de documento
     */
    public int getDni() {
        return dni;
    }

    /**
     * Devuelve el rol actual del usuario.
     * @return el rol (puede ser null)
     */
    public Rol getRolActual() {
        return rolActual;
    }

    /**
     * Devuelve el nombre del usuario.
     * @return nombre como texto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el rol asignado al usuario.
     * @param nuevoRol el nuevo rol que va a tener
     */
    public void cambiarRol(Rol nuevoRol) {
        this.rolActual = nuevoRol;
    }

    /**
     * Ejecuta el menú del rol actual.
     * Es una forma de activar las acciones propias del usuario.
     */
    public void ejecutarRol() {
        rolActual.mostrarMenu();
    }

    /**
     * Implementación del menú. Llama al menú del rol asignado.
     */
    @Override
    public void mostrarMenu() {
        rolActual.mostrarMenu();
    }
}
