// Fix: validación de descripciones corregida


import java.util.ArrayList;
import java.util.Iterator;

public class GestorTareas {
    private ArrayList<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    // Agregar tarea
    public void agregarTarea(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("La descripcion no puede estar vacia");
            return;
        }
        tareas.add(new Tarea(descripcion.trim())); // ✅ trim para limpiar espacios
        System.out.println("Tarea agregada correctamente");
    }

    // Listar tareas
    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No tiene tareas");
            return;
        }
        System.out.println("Lista de Tareas");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    // Marcar tarea como completada
    public void marcarCompletada(int indice) {
        if (indice < 1 || indice > tareas.size()) {
            System.out.println("Numero de tarea invalido");
            return;
        }
        tareas.get(indice - 1).marcarCompletada();
        System.out.println("Tarea marcada como completada");
    }

    // Eliminar tareas completadas
    public void eliminarCompletadas() {
        Iterator<Tarea> it = tareas.iterator();
        boolean eliminado = false;

        while (it.hasNext()) {
            Tarea t = it.next();
            if (t.getEstado()) {
                it.remove();
                eliminado = true;
            }
        }

        if (eliminado) {
            System.out.println("Tareas eliminadas");
        } else {
            System.out.println("No tiene tareas para eliminar");
        }
    }
}
