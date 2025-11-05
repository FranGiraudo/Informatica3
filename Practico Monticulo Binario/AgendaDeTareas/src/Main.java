public class Main{
    public static void main(String[] args){
        MinHeapTareas agenda=new MinHeapTareas(10);

        agenda.add(new Tarea("Preparar informe",2));
        agenda.add(new Tarea("Enviar mail al cliente",1));
        agenda.add(new Tarea("Revisar código",3));
        agenda.add(new Tarea("Actualizar repositorio",2));
        agenda.add(new Tarea("Llamar a proveedor",1));

        System.out.println("\nPróxima tarea urgente:\n"+agenda.peek());

        System.out.println("\nTarea completada:\n");
        agenda.poll();

        System.out.println("\nTareas pendientes (por prioridad):");
        agenda.printOrdenado();
    }
}
