import java.util.List;

public interface PlanificadorQuirofano {
    void procesar(SolicitudCirugia s);
    List<String> topKMedicosBloqueados(int K);
}
