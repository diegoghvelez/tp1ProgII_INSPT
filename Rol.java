package macdronalds;

/**
 * Interfaz que define el comportamiento básico de un rol dentro del sistema.
 * Todo rol (gerente, vendedor, cocinero...) debe poder mostrar su menú y decir cómo se llama.
 */
public interface Rol {

    /**
     * Muestra el menú correspondiente al rol que lo implemente.
     */
    void mostrarMenu();

    /**
     * Devuelve el nombre del rol, útil para mostrarlo en pantalla.
     * @return nombre del rol como texto
     */
    String getNombreRol();
}
