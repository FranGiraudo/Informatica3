import java.time.LocalDateTime;
import java.util.Stack;
import java.util.Optional;

interface AgendaConHistorial extends AgendaMedico {
    boolean reprogramar(String idTurno, LocalDateTime nuevaFecha);
    boolean undo();
    boolean redo();
}


