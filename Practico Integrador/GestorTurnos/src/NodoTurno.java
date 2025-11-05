import java.time.LocalDateTime;

public class NodoTurno {
    Turno turno;
    NodoTurno izquierdo, derecho;
    int altura;

    public NodoTurno(Turno turno){
        this.turno=turno;
        izquierdo=derecho=null;
        altura=1;
    }
}
