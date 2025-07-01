package macdronalds;

/**
 * Representa un menú que se puede mostrar en pantalla según el rol del usuario.
 * Cada clase que implemente esta interfaz va a tener su propia forma de mostrarlo.
 */
public interface Menu {

     /**
      * Muestra el menú correspondiente al rol que lo implemente.
      */
     void mostrarMenu();
}
