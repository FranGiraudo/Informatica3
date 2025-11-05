import java.time.LocalDateTime;

public class Recordatorio {
    private String id; // Ahora es private
    private LocalDateTime fecha; // Ahora es private
    private String dniPaciente; // Ahora es private
    private String mensaje; // Ahora es private

    public Recordatorio(String id, LocalDateTime fecha, String dniPaciente, String mensaje){
        this.id = id;
        this.fecha = fecha;
        this.dniPaciente = dniPaciente;
        this.mensaje = mensaje;
    }

    // --- Getters ---
    public String getId() {
        return id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getDniPaciente() {
        return dniPaciente;
    }

    public String getMensaje() {
        return mensaje;
    }

    // --- Setter (Necesario para reprogramar) ---
    public void setFecha(LocalDateTime nuevaFecha) {
        this.fecha = nuevaFecha;
    }

    @Override
    public String toString(){
        return "Recordatorio[ID:" + id + ", Fecha:" + fecha + ", Paciente:" + dniPaciente + ", Mensaje:" + mensaje + "]";
    }
}