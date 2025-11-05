public class Accion {
    public enum Tipo { AGENDAR, CANCELAR, REPROGRAMAR }
    public Tipo tipo;
    public Turno turnoAntes;
    public Turno turnoDespues;

    public Accion(Tipo tipo, Turno turnoAntes, Turno turnoDespues){
        this.tipo=tipo;
        this.turnoAntes=turnoAntes;
        this.turnoDespues=turnoDespues;
    }
}
