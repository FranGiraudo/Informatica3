import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Stack;


public class AgendaConHistorialImpl implements AgendaConHistorial {
    private AgendaMedicoAVL agenda;
    private Stack<Accion> pilaUndo;
    private Stack<Accion> pilaRedo;

    public AgendaConHistorialImpl(AgendaMedicoAVL agendaBase){
        this.agenda=agendaBase;
        pilaUndo=new Stack<>();
        pilaRedo=new Stack<>();
    }

    @Override
    public boolean agendar(Turno t){
        boolean ok=agenda.agendar(t);
        if(ok){
            pilaUndo.push(new Accion(Accion.Tipo.AGENDAR,null,t));
            pilaRedo.clear();
        }
        return ok;
    }

    @Override
    public boolean cancelar(String idTurno){
        Optional<Turno> tOpt=agenda.todos().stream().filter(x->x.id.equals(idTurno)).findFirst();
        if(tOpt.isEmpty()) return false;
        Turno t=tOpt.get();
        boolean ok=agenda.cancelar(idTurno);
        if(ok){
            pilaUndo.push(new Accion(Accion.Tipo.CANCELAR,t,null));
            pilaRedo.clear();
        }
        return ok;
    }

    @Override
    public boolean reprogramar(String idTurno, LocalDateTime nuevaFecha){
        Optional<Turno> tOpt=agenda.todos().stream().filter(x->x.id.equals(idTurno)).findFirst();
        if(tOpt.isEmpty()) return false;
        Turno tAnt=tOpt.get();
        Turno tNuevo=new Turno(tAnt.id,tAnt.dniPaciente,tAnt.matriculaMedico,nuevaFecha,tAnt.duracionMin,tAnt.motivo);
        boolean ok=agenda.reprogramar(idTurno,nuevaFecha);
        if(ok){
            pilaUndo.push(new Accion(Accion.Tipo.REPROGRAMAR,tAnt,tNuevo));
            pilaRedo.clear();
        }
        return ok;
    }

    @Override
    public boolean undo(){
        if(pilaUndo.isEmpty()) return false;
        Accion a=pilaUndo.pop();
        switch(a.tipo){
            case AGENDAR:
                agenda.cancelar(a.turnoDespues.id);
                break;
            case CANCELAR:
                agenda.agendar(a.turnoAntes);
                break;
            case REPROGRAMAR:
                agenda.reprogramar(a.turnoDespues.id,a.turnoAntes.fechaHora);
                break;
        }
        pilaRedo.push(a);
        return true;
    }

    @Override
    public boolean redo(){
        if(pilaRedo.isEmpty()) return false;
        Accion a=pilaRedo.pop();
        switch(a.tipo){
            case AGENDAR:
                agenda.agendar(a.turnoDespues);
                break;
            case CANCELAR:
                agenda.cancelar(a.turnoAntes.id);
                break;
            case REPROGRAMAR:
                agenda.reprogramar(a.turnoAntes.id,a.turnoDespues.fechaHora);
                break;
        }
        pilaUndo.push(a);
        return true;
    }

    @Override
    public Optional<Turno> siguiente(LocalDateTime t){
        return agenda.siguiente(t);
    }
}
