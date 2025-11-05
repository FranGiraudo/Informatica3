import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Pizzeria pizzeria = new Pizzeria();
        Ordenador ordenador = new Ordenador();
        int opcion;

        do {
            System.out.println("\n=== SISTEMA DE GESTION DE PEDIDOS ===");
            System.out.println("1. Agregar pedido");
            System.out.println("2. Eliminar pedido");
            System.out.println("3. Mostrar pedidos");
            System.out.println("4. Ordenar por tiempo de preparacion");
            System.out.println("5. Ordenar por precio total");
            System.out.println("6. Ordenar por nombre del cliente");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio total: ");
                    double precio = sc.nextDouble();
                    System.out.print("Tiempo de preparacion (minutos): ");
                    int tiempo = sc.nextInt();
                    sc.nextLine();
                    pizzeria.agregarPedido(new Pedido(nombre, precio, tiempo));
                    break;
                case 2:
                    System.out.print("Nombre del cliente a eliminar: ");
                    String eliminar = sc.nextLine();
                    pizzeria.eliminarPedido(eliminar);
                    break;
                case 3:
                    pizzeria.mostrarPedidos();
                    break;
                case 4:
                    ordenador.ordenarTiempo(pizzeria.getPedidos());
                    System.out.println("Pedidos ordenados por tiempo.");
                    break;
                case 5:
                    ordenador.ordenarPrecio(pizzeria.getPedidos());
                    System.out.println("Pedidos ordenados por precio.");
                    break;
                case 6:
                    ordenador.ordenarNombre(pizzeria.getPedidos(), 0, pizzeria.getPedidos().size() - 1);
                    System.out.println("Pedidos ordenados por nombre.");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }
}
