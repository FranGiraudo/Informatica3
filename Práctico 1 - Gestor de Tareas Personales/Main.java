import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        int opcion;

        do {
            // Menú
            System.out.println("\n=== GESTOR DE TAREAS ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tareas completadas");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            // Leer opción
            while (!sc.hasNextInt()) {  // Validar que sea un número
                System.out.println("⚠️ Ingrese un número válido.");
                sc.next();
            }
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la descripcion de la tarea: ");
                    String descripcion = sc.nextLine();
                    gestor.agregarTarea(descripcion);
                    break;

                case 2:
                    gestor.listarTareas();
                    break;

                case 3:
                    gestor.listarTareas();
                    System.out.print("Ingrese el numero de la tarea a marcar: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Ingrese un numero valido");
                        sc.next();
                    }
                    int num = sc.nextInt();
                    gestor.marcarCompletada(num);
                    break;

                case 4:
                    gestor.eliminarCompletadas();
                    break;

                case 5:
                    System.out.println("Programa finalizado");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 5);

        sc.close();
    }
}
