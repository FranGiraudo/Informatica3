import java.time.LocalDateTime;
import java.util.*;

public class PlanificadorQuirofanoImpl implements PlanificadorQuirofano {

    private PriorityQueue<Quirofano> heapQuirófanos;
    private Map<String, MedicoBloqueado> medicos;
    private PriorityQueue<MedicoBloqueado> topKHeap;

    public PlanificadorQuirofanoImpl(List<String> quirófanosDisponibles, List<String> matriculasMedicos){
        heapQuirófanos=new PriorityQueue<>();
        for(String id: quirófanosDisponibles) heapQuirófanos.add(new Quirofano(id));

        medicos=new HashMap<>();
        for(String m: matriculasMedicos) medicos.put(m, new MedicoBloqueado(m));

        topKHeap=new PriorityQueue<>();
    }

    @Override
    public void procesar(SolicitudCirugia s){
        Quirofano q=heapQuirófanos.poll();
        LocalDateTime inicio=q.finOcupado.isAfter(LocalDateTime.now()) ? q.finOcupado : LocalDateTime.now();
        if(inicio.plusMinutes(s.durMin).isAfter(s.deadline)){
            System.out.println("No se puede asignar cirugía " + s.id + " antes del deadline");
        } else {
            q.finOcupado=inicio.plusMinutes(s.durMin);
            MedicoBloqueado mb=medicos.get(s.matricula);
            mb.minutosBloqueados+=s.durMin;
            heapQuirófanos.add(q);

            topKHeap.remove(mb);
            topKHeap.add(mb);
        }
    }

    @Override
    public List<String> topKMedicosBloqueados(int K){
        List<MedicoBloqueado> list=new ArrayList<>(topKHeap);
        list.sort((a,b)->Integer.compare(b.minutosBloqueados,a.minutosBloqueados));
        List<String> res=new ArrayList<>();
        for(int i=0; i<Math.min(K,list.size()); i++) res.add(list.get(i).matricula + " - " + list.get(i).minutosBloqueados + " min");
        return res;
    }
}