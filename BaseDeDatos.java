package macdronalds;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase simula una \"base de datos\" del sistema Macdronalds.
 * Acá se guardan todos los datos importantes como los usuarios, ingredientes,
 * productos y combos. Todo en memoria, sin usar base de datos posta.
 */
public class BaseDeDatos {

    // Lista de ingredientes disponibles (ej: pan, carne, lechuga...)
    private static List<Ingrediente> ingredientes = new ArrayList<>();

    // Usuarios cargados en el sistema (clientes, gerente, vendedor, etc.)
    private static List<Usuario> usuarios = new ArrayList<>();

    // Productos individuales como hamburguesas, papas, bebidas, etc.
    private static List<Producto> productos = new ArrayList<>();

    // Combos armados que juntan varios productos en una oferta
    private static List<Combo> combos = new ArrayList<>();

    /**
     * Constructor. Cuando se crea una instancia, se cargan algunos datos básicos
     * (solo si todavía no hay usuarios cargados).
     * Agrega ingredientes y usuarios por defecto para arrancar el sistema.
     */
    public BaseDeDatos() {
        if (usuarios.isEmpty()) {
            ingredientes.add(new Ingrediente("Pan", 100, 50));
            ingredientes.add(new Ingrediente("Carne", 300, 30));
            ingredientes.add(new Ingrediente("Queso", 150, 40));
            ingredientes.add(new Ingrediente("Lechuga", 80, 60));

            usuarios.add(new Usuario(12345678, "Juan Pepe", null));
            usuarios.add(new Usuario(23456789, "Maria santa", null));
            usuarios.add(new Usuario(98765432, "Supremo", new Gerente(this)));

            Hamburguesa hSimple = new Hamburguesa(new ArrayList<>(List.of(
                    ingredientes.get(0), // Pan
                    ingredientes.get(1)  // Carne
            )), 1000, 20);

            // Hamburguesa 2: Pan + Queso + Lechuga
            Hamburguesa hClasica = new Hamburguesa(new ArrayList<>(List.of(
                    ingredientes.get(0), // Pan
                    ingredientes.get(2), // Queso
                    ingredientes.get(3)  // Lechuga
            )), 900, 15);

            // Papas y bebidas
            Papas papasMedianas = new Papas("medianas", 500, 20);
            Papas papasChicas = new Papas("chicas", 400, 15);
            Bebida cocaGrande = new Bebida("gaseosa", "coca", 600, 20, "grande");
            Bebida aguaMediana = new Bebida("agua", "villavicencio", 300, 15, "mediana");

            productos.add(hSimple);
            productos.add(hClasica);
            productos.add(papasMedianas);
            productos.add(papasChicas);
            productos.add(cocaGrande);
            productos.add(aguaMediana);

            Combo comboClasico = new Combo("Combo clasico", 15000);
            comboClasico.agregarProducto(hSimple);
            comboClasico.agregarProducto(papasMedianas);
            comboClasico.agregarProducto(cocaGrande);

            combos.add(comboClasico);

        }

    }

    /**
     * Devuelve la lista completa de productos cargados.
     * @return todos los productos disponibles.
     */
    public static List<Producto> getProductos() {
        return productos;
    }

    /**
     * Agrega un producto nuevo a la lista.
     * @param producto el producto a guardar.
     */
    public static void setProductos(Producto producto) {
        productos.add(producto);
    }

    /**
     * Devuelve la lista de combos cargados en el sistema.
     * @return todos los combos.
     */
    public static List<Combo> getCombos() {
        return combos;
    }

    /**
     * Agrega un combo nuevo a la lista de combos.
     * @param combo el combo a guardar.
     */
    public static void setCombos(Combo combo) {
        combos.add(combo);
    }

    /**
     * Devuelve todos los usuarios que ya están cargados (los \"predefinidos\").
     * @return lista de usuarios.
     */
    public static List<Usuario> obtenerUsuariosPredefinidos() {
        return usuarios;
    }

    /**
     * Devuelve todos los ingredientes cargados.
     * @return lista de ingredientes.
     */
    public static List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    /**
     * Busca un ingrediente por su ID.
     * @param idIngrediente identificador del ingrediente.
     * @return el ingrediente si lo encuentra, sino null.
     */
    public static Ingrediente buscarPorId(int idIngrediente) {
        for (Ingrediente i : ingredientes) {
            if (i.getIdIngrediente() == idIngrediente) {
                return i;
            }
        }
        return null;
    }

    /**
     * Busca un producto por su ID.
     * @param idProducto identificador del producto.
     * @return el producto si lo encuentra, sino null.
     */
    public static Producto buscarProductoPorId(int idProducto) {
        for (Producto p : productos) {
            if (p.getIdProducto() == idProducto) {
                return p;
            }
        }
        return null;
    }

    /**
     * Busca un combo por nombre exacto.
     * @param nombreCombo el nombre del combo a buscar.
     * @return el combo si lo encuentra, sino null.
     */
    public static Combo buscarComboPorNombre(String nombreCombo) {
        for (Combo c : combos) {
            if (nombreCombo.equals(c.getNombre())) {
                return c;
            }
        }
        return null;
    }

    /**
     * Busca un usuario por su DNI.
     * @param dni el número de documento del usuario.
     * @return el usuario si lo encuentra, sino null.
     */
    public static Usuario buscarUsuarioPorDni(int dni) {
        for (Usuario u : usuarios) {
            if (u.getDni() == dni) {
                return u;
            }
        }
        return null;
    }
}
