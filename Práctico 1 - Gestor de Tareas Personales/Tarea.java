public class Tarea {
    private String descripcion;
    private boolean estado; // false = pendiente, true = completada

    // Constructor
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.estado = false;
    }

    // Getter y setter de descripción
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion.trim();
        } else {
            System.out.println("La descripción no puede estar vacía");
        }
    }

    // Getter y setter de estado
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método específico para marcar completada
    public void marcarCompletada() {
        this.estado = true;
    }

    // Representación en texto
    @Override
    public String toString() {
        return (estado ? "[✔] " : "[X] ") + descripcion;
    }
}
