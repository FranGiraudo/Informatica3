import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaUtils {

    public static List<Turno> mergeAgendas(List<Turno> agenda1, List<Turno> agenda2){
        List<Turno> resultado=new ArrayList<>();
        int i=0, j=0;

        while(i<agenda1.size() && j<agenda2.size()){
            Turno t1=agenda1.get(i);
            Turno t2=agenda2.get(j);

            // Evitar duplicados por ID
            if(existeEnLista(resultado, t1.id)){i++; continue;}
            if(existeEnLista(resultado, t2.id)){j++; continue;}

            // Choques exactos de horario para mismo médico
            if(t1.matriculaMedico.equals(t2.matriculaMedico) && t1.fechaHora.equals(t2.fechaHora)){
                System.out.println("Conflicto de horario para médico "+t1.matriculaMedico+" en "+t1.fechaHora+" (ID: "+t1.id+" vs "+t2.id+")");
                // se mantiene t1
                j++;
                continue;
            }

            // Elegir el turno con fecha más temprana
            if(t1.fechaHora.isBefore(t2.fechaHora)){
                resultado.add(t1);
                i++;
            }else{
                resultado.add(t2);
                j++;
            }
        }

        // Agregar lo que quede de agenda1
        while(i<agenda1.size()){
            Turno t=agenda1.get(i++);
            if(!existeEnLista(resultado,t.id))resultado.add(t);
        }

        // Agregar lo que quede de agenda2
        while(j<agenda2.size()){
            Turno t=agenda2.get(j++);
            if(!existeEnLista(resultado,t.id))resultado.add(t);
        }

        return resultado;
    }

    private static boolean existeEnLista(List<Turno> lista, String id){
        for(Turno t:lista)if(t.id.equals(id))return true;
        return false;
    }
}
