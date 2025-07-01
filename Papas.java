package macdronalds;

/**
 * Representa una porción de papas fritas.
 * Se puede especificar el tamaño (chicas, medianas, grandes) y su precio.
 */
public class Papas extends Producto {

    private String tamano;

    /**
     * Crea una porción de papas con tamaño, precio y cantidad en stock.
     * @param tamano puede ser "chicas", "medianas" o "grandes"
     * @param precio precio de esta porción
     * @param cantStock cuántas hay disponibles
     */
    public Papas(String tamano, double precio, int cantStock) {
        super(precio, cantStock);
        this.tamano = tamano;
    }

    /**
     * Devuelve el tamaño de las papas.
     * @return texto con el tamaño
     */
    public String getTamano() {
        return tamano;
    }

    /**
     * Permite cambiar el tamaño de las papas.
     * @param tamano nuevo tamaño (chicas, medianas, grandes...)
     */
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
