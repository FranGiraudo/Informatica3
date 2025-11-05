public class TurnoAVLNode {
    Turno turno;
    TurnoAVLNode izq, der;
    int altura;

    public TurnoAVLNode(Turno t){
        turno=t;
        izq=null;
        der=null;
        altura=1;
    }
}
