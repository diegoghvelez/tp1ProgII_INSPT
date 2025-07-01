package macdronalds;

/**
 * Representa una bebida que se puede vender en el local.
 * Tiene tipo (gaseosa, jugo, etc.), marca (Coca, Pepsi, etc.) y tamaño (chico, mediano, grande).
 */
public class Bebida extends Producto {

    private String tipo;
    private String marca;
    private String tamano;

    /**
     * Crea una bebida con tipo, marca, precio, stock y tamaño.
     * @param tipo tipo de bebida (gaseosa, jugo, agua, etc.)
     * @param marca marca de la bebida (ej: "Coca-Cola")
     * @param precio precio unitario
     * @param cantStock cantidad en stock
     * @param tamano tamaño (chico, mediano, grande)
     */
    public Bebida(String tipo, String marca, double precio, int cantStock, String tamano) {
        super(precio, cantStock);
        this.tipo = tipo;
        this.marca = marca;
        this.tamano = tamano; // esta línea faltaba
    }

    /**
     * Devuelve el tamaño de la bebida.
     * @return tamaño como texto
     */
    public String getTamano() {
        return tamano;
    }

    /**
     * Permite modificar el tamaño de la bebida.
     * @param tamano nuevo tamaño (chico, mediano, grande)
     */
    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
