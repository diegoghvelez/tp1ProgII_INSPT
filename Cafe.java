package macdronalds;

/**
 * Representa un café del menú, como espresso, cortado o con leche.
 * Además del tipo, se le puede definir el tamaño (chico, mediano, grande).
 */
public class Cafe extends Producto {

    private String tipo;
    private String tamanio;

    /**
     * Crea un café con su tipo, tamaño, precio y cantidad en stock.
     * @param tipo tipo de café (ej: espresso, cortado, etc.)
     * @param tamanio tamaño del café
     * @param precio precio del café
     * @param cantStock cantidad disponible en stock
     */
    public Cafe(String tipo, String tamanio, double precio, int cantStock) {
        super(precio, cantStock);
        this.tipo = tipo;
        this.tamanio = tamanio;
    }
}
