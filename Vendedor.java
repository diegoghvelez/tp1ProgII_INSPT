package macdronalds;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Representa al rol de Vendedor dentro del sistema.
 * Un vendedor puede crear pedidos, agregar productos o combos,
 * modificar ítems y confirmar el pedido para enviarlo a cocina.
 */
public class Vendedor implements Rol, MenuVendedor {

    private Caja caja = new Caja();
    private static BaseDeDatos baseDeDatos;
    private Pedido pedidoActual;

    /**
     * Crea un vendedor con acceso a la base de datos.
     * @param baseDeDatos referencia a la base de datos compartida
     */
    public Vendedor(BaseDeDatos baseDeDatos) {
        this.pedidoActual = null;
        setBaseDeDatos(baseDeDatos);
    }

    /**
     * Asigna la base de datos compartida al vendedor.
     * @param baseDeDatos instancia de BaseDeDatos
     */
    public static void setBaseDeDatos(BaseDeDatos baseDeDatos) {
        Vendedor.baseDeDatos = baseDeDatos;
    }

    /**
     * Devuelve la caja del vendedor, útil para reportes y pedidos.
     * @return la caja asignada
     */
    public Caja accesoCaja() {
        return caja;
    }

    /**
     * Muestra el menú del vendedor, con opciones para crear, agregar y confirmar pedidos.
     */
    @Override
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4) {
            System.out.println("Menú Vendedor:\n1. Crear pedido\n2. Agregar producto al pedido actual\n3. Confirmar pedido\n4. Salir\nIngrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("CREA PEDIDO");
                    crearPedido();
                    System.out.println("Pedido creado. Su ID es #" + pedidoActual.getIdPedido());

                    int seguir = 1;
                    while (seguir != 0) {
                        System.out.println("Que desea agregar al pedido?");
                        System.out.println("1. Combo\n2. Producto");
                        int tipo = scanner.nextInt();
                        scanner.nextLine();

                        if (tipo == 1) {
                            System.out.println("Combos disponibles:");
                            for (Combo c : BaseDeDatos.getCombos()) {
                                System.out.println("- " + c.getNombre());
                            }
                            System.out.print("Ingrese el nombre del combo: ");
                            String nombre = scanner.nextLine();
                            Combo combo = BaseDeDatos.buscarComboPorNombre(nombre);
                            if (combo != null) {
                                pedidoActual.agregarItem(new Item(combo));
                                System.out.println("Combo agregado.");
                            } else {
                                System.out.println("Combo no encontrado.");
                            }

                        } else if (tipo == 2) {
                            System.out.println("Productos disponibles:");
                            for (Producto p : BaseDeDatos.getProductos()) {
                                System.out.printf("ID: %d - %s ($%.2f)\n", p.getIdProducto(), p.getClass().getSimpleName(), p.getPrecioFinal());
                            }

                            System.out.print("Ingrese el ID del producto ");
                            int idProd = scanner.nextInt();
                            scanner.nextLine();
                            Producto prod = BaseDeDatos.buscarProductoPorId(idProd);

                            if (prod != null) {
                                pedidoActual.agregarItem(new Item(prod));
                                System.out.println("Producto agregado.");

                                // Si es hamburguesa, permitir personalizarla
                                if (prod instanceof Hamburguesa h) {
                                    int personalizar = 1;
                                    while (personalizar != 0) {
                                        System.out.println("Desea personalizar la hamburguesa? 1=Sí 0=No: ");
                                        personalizar = scanner.nextInt();
                                        scanner.nextLine();

                                        if (personalizar == 1) {
                                            System.out.println("Ingredientes:");
                                            mostrarIngredientes(h);

                                            System.out.println("1) Agregar ingrediente\n2) Quitar ingrediente");
                                            int accion = scanner.nextInt();
                                            scanner.nextLine();

                                            System.out.println("Ingredientes:");
                                            for (Ingrediente i : BaseDeDatos.getIngredientes()) {
                                                System.out.printf("ID: %d - %s ($%.2f, stock: %d)\n",
                                                        i.getIdIngrediente(), i.getNombre(), i.getPrecio(), i.getStock());
                                            }

                                            System.out.print("Ingrese el ID del ingrediente: ");
                                            int idIng = scanner.nextInt();
                                            scanner.nextLine();
                                            Ingrediente ing = BaseDeDatos.buscarPorId(idIng);

                                            if (ing != null) {
                                                if (accion == 1) {
                                                    h.agregarIngrediente(ing);
                                                    System.out.println("Ingrediente agregado.");
                                                } else if (accion == 2) {
                                                    h.quitarIngrediente(ing);
                                                    System.out.println("Ingrediente quitado.");
                                                } else {
                                                    System.out.println("Error");
                                                }
                                            } else {
                                                System.out.println("Ingrediente no encontrado.");
                                            }

                                            System.out.println("Ingredientes:");
                                            mostrarIngredientes(h);
                                        }
                                    }
                                }
                            } else {
                                System.out.println("Error producto");
                            }

                        } else {
                            System.out.println("Opción inválida.");
                        }

                        System.out.println("¿Desea seguir agregando al pedido? (1=Sí / 0=No):");
                        seguir = scanner.nextInt();
                        scanner.nextLine();
                    }
                    break;


                case 2:
                    if (pedidoActual != null) {
                        int continuar = 1;
                        while (continuar != 0) {
                            System.out.println("Indique qué desea agregar: \n1. Combo\n2. Producto:");
                            int op = scanner.nextInt();
                            scanner.nextLine();

                            if (op == 1) {
                                System.out.println("Listado de combos disponibles:");
                                for (Combo c : BaseDeDatos.getCombos()) {
                                    System.out.println(c.getNombre());
                                }

                                System.out.println("Ingrese el nombre del combo que desea: ");
                                String nom = scanner.nextLine();

                                Combo combo = BaseDeDatos.buscarComboPorNombre(nom);
                                if (combo != null) {
                                    pedidoActual.agregarItem(new Item(combo));
                                } else {
                                    System.out.println("No se encontró un combo con ese nombre");
                                }

                            } else if (op == 2) {
                                System.out.print("Ingrese el ID del producto a agregar: ");
                                int idProducto = scanner.nextInt();
                                agregarProducto(idProducto);
                                System.out.println("Producto agregado.");
                            } else {
                                System.out.println("Opción incorrecta.");
                            }

                            System.out.println("Si ya no desea agregar más items al pedido, presione 0");
                            continuar = scanner.nextInt();
                        }
                    } else {
                        System.out.println("Primero debe crear un pedido.");
                    }
                    break;

                case 3:
                    if (pedidoActual != null) {
                        confirmarPedido();
                    } else {
                        System.out.println("No hay un pedido en proceso para confirmar.");
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del menú Vendedor...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Crea un nuevo pedido vacío.
     * @return el pedido creado
     */
    @Override
    public Pedido crearPedido() {
        this.pedidoActual = new Pedido();
        return pedidoActual;
    }

    /**
     * Agrega un producto al pedido actual usando su ID.
     * @param idProducto ID del producto
     */
    @Override
    public void agregarProducto(int idProducto) {
        Producto p = BaseDeDatos.buscarProductoPorId(idProducto);
        if (p != null) {
            pedidoActual.agregarItem(new Item(p));
        } else {
            System.out.println("No se encontró un producto con ese ID.");
        }
    }

    /**
     * Permite modificar un producto del pedido actual.
     * Se puede agregar o quitar ingredientes, o cambiar tamaños.
     */
    @Override
    public void modificarProducto(int idProducto, int idIngrediente, boolean esAgregar, int cantidad) {
        Ingrediente ing = baseDeDatos.buscarPorId(idIngrediente);
        Scanner scanner = new Scanner(System.in);

        if (ing != null) {
            for (Item item : pedidoActual.getItems()) {
                if (item.toString().contains("Hamburguesa")) {
                    Hamburguesa h = (Hamburguesa) item.producto;
                    for (int i = 0; i < cantidad; i++) {
                        if (esAgregar) {
                            h.agregarIngrediente(ing);
                        } else {
                            h.quitarIngrediente(ing);
                        }
                    }
                } else if (item.producto instanceof Papas papas) {
                    System.out.println("Ingrese el nuevo tamaño de las papas:");
                    String tamano = scanner.nextLine();
                    papas.setTamano(tamano);
                    System.out.println("Nuevo tamaño: " + papas.getTamano());
                } else if (item.producto instanceof Bebida bebida) {
                    System.out.println("Ingrese el nuevo tamaño de la bebida:");
                    String tamano = scanner.nextLine();
                    bebida.setTamano(tamano);
                    System.out.println("Nuevo tamaño: " + bebida.getTamano());
                }
            }
        }
    }

    private void mostrarIngredientes(Hamburguesa h) {
        List<Ingrediente> lista = h.getIngredientes();
        if (lista.isEmpty()) {
            System.out.println("Sin ingredientes.");
        } else {
            for (Ingrediente i : lista) {
                System.out.printf("- %s (ID: %d, $%.2f)\n", i.getNombre(), i.getIdIngrediente(), i.getPrecio());
            }
        }
    }


    /**
     * Confirma el pedido actual y lo envía a la caja.
     */
    @Override
    public void confirmarPedido() {
        // Enviar a la caja
        caja.recibirPedido(pedidoActual);


        Cocinero.getPedidosPendientes().add(pedidoActual);

        System.out.println("pedido confirmado");
        System.out.println(pedidoActual);
        pedidoActual = null;
    }


    /**
     * Devuelve el nombre del rol que representa esta clase.
     * @return "Vendedor"
     */
    @Override
    public String getNombreRol() {
        return "Vendedor";
    }
}
