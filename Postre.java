package macdronalds;

/**
 * Representa un postre del menú (ej: helado, flan, torta, etc.).
 * Puede ser congelado o no, y tiene su nombre propio.
 */
public class Postre extends Producto {

    private String nombre;
    private boolean congelado;

    /**
     * Crea un postre con nombre, estado de congelación, precio y stock.
     * @param nombre nombre del postre
     * @param congelado true si está congelado, false si no
     * @param precio precio del postre
     * @param cantStock cantidad disponible
     */
    public Postre(String nombre, boolean congelado, double precio, int cantStock) {
        super(precio, cantStock);
        this.nombre = nombre;
        this.congelado = congelado;
    }
}
