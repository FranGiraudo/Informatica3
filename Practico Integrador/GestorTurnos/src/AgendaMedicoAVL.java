import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

public class AgendaMedicoAVL implements AgendaMedico {
    private NodoTurno root;

    private class NodoTurno {
        Turno turno;
        NodoTurno izq;
        NodoTurno der;
        int altura;

        NodoTurno(Turno t){
            turno=t;
            izq=null;
            der=null;
            altura=1;
        }
    }

    public boolean agendar(Turno t){
        if(conflicto(t)) return false;
        root=insert(root,t);
        return true;
    }

    private NodoTurno insert(NodoTurno nodo, Turno t){
        if(nodo==null) return new NodoTurno(t);
        if(t.fechaHora.isBefore(nodo.turno.fechaHora)) nodo.izq=insert(nodo.izq,t);
        else nodo.der=insert(nodo.der,t);

        nodo.altura=1+Math.max(altura(nodo.izq),altura(nodo.der));
        return balancear(nodo);
    }

    private int altura(NodoTurno n){ return n==null?0:n.altura; }

    private NodoTurno balancear(NodoTurno n){
        int balance=altura(n.izq)-altura(n.der);
        if(balance>1){
            if(altura(n.izq.izq)>=altura(n.izq.der)) n=rotarDerecha(n);
            else{ n.izq=rotarIzquierda(n.izq); n=rotarDerecha(n);}
        } else if(balance<-1){
            if(altura(n.der.der)>=altura(n.der.izq)) n=rotarIzquierda(n);
            else{ n.der=rotarDerecha(n.der); n=rotarIzquierda(n);}
        }
        return n;
    }

    private NodoTurno rotarDerecha(NodoTurno y){
        NodoTurno x=y.izq;
        NodoTurno T2=x.der;
        x.der=y;
        y.izq=T2;
        y.altura=1+Math.max(altura(y.izq),altura(y.der));
        x.altura=1+Math.max(altura(x.izq),altura(x.der));
        return x;
    }

    private NodoTurno rotarIzquierda(NodoTurno x){
        NodoTurno y=x.der;
        NodoTurno T2=y.izq;
        y.izq=x;
        x.der=T2;
        x.altura=1+Math.max(altura(x.izq),altura(x.der));
        y.altura=1+Math.max(altura(y.izq),altura(y.der));
        return y;
    }

    public boolean cancelar(String idTurno){
        if(siguientePorId(root,idTurno)==null) return false;
        root=delete(root,idTurno);
        return true;
    }

    private NodoTurno delete(NodoTurno nodo, String id){
        if(nodo==null) return null;
        if(id.equals(nodo.turno.id)){
            if(nodo.izq==null) return nodo.der;
            else if(nodo.der==null) return nodo.izq;
            else{
                NodoTurno sucesor=minNodo(nodo.der);
                nodo.turno=sucesor.turno;
                nodo.der=delete(nodo.der,sucesor.turno.id);
            }
        } else if(id.compareTo(nodo.turno.id)<0) nodo.izq=delete(nodo.izq,id);
        else nodo.der=delete(nodo.der,id);

        nodo.altura=1+Math.max(altura(nodo.izq),altura(nodo.der));
        return balancear(nodo);
    }

    private NodoTurno minNodo(NodoTurno nodo){
        if (nodo == null) {
            return null;
        }

        NodoTurno actual=nodo;
        while(actual.izq!=null) actual=actual.izq;
        return actual;
    }

    private NodoTurno siguientePorId(NodoTurno nodo, String id){
        if(nodo==null) return null;
        if(nodo.turno.id.equals(id)) return nodo;
        NodoTurno izq=siguientePorId(nodo.izq,id);
        if(izq!=null) return izq;
        return siguientePorId(nodo.der,id);
    }

    public Optional<Turno> siguiente(LocalDateTime t){
        NodoTurno res=siguiente(root,t);
        return res==null?Optional.empty():Optional.of(res.turno);
    }

    private NodoTurno siguiente(NodoTurno nodo, LocalDateTime t){
        if (nodo == null) {
            return null;
        }
        if (nodo.turno.fechaHora.isAfter(t) || nodo.turno.fechaHora.isEqual(t)) {
            NodoTurno mejorCandidatoIzq = siguiente(nodo.izq, t);
            return mejorCandidatoIzq != null ? mejorCandidatoIzq : nodo;
        }
        else {
            return siguiente(nodo.der, t);
        }
    }

    private boolean conflicto(Turno t){
        NodoTurno n=root;
        while(n!=null){
            LocalDateTime inicio=n.turno.fechaHora;
            LocalDateTime fin=inicio.plusMinutes(n.turno.duracionMin);
            LocalDateTime tFin=t.fechaHora.plusMinutes(t.duracionMin);
            if((t.fechaHora.isBefore(fin)) && (tFin.isAfter(inicio))) return true;
            if(t.fechaHora.isBefore(n.turno.fechaHora)) n=n.izq;
            else n=n.der;
        }
        return false;
    }
    public Optional<LocalDateTime> primerHueco(LocalDateTime t0, int durMin){
        LocalDateTime candidato=t0;
        NodoTurno actual=minNodo(root); // primer turno del día
        while(actual!=null){
            LocalDateTime inicio=actual.turno.fechaHora;
            LocalDateTime fin=inicio.plusMinutes(actual.turno.duracionMin);

            if(candidato.plusMinutes(durMin).isBefore(inicio) || candidato.plusMinutes(durMin).isEqual(inicio)){
                return Optional.of(candidato); // hay hueco
            } else {
                candidato=fin; // mover candidato al final del turno actual
            }

            actual=siguiente(actual, candidato); // siguiente turno ≥ candidato
        }
        return Optional.of(candidato); // si no hay más turnos, el hueco es al final
    }
    public List<Turno> todos(){
        List<Turno> lista=new ArrayList<>();
        inorder(root, lista);
        return lista;
    }

    private void inorder(NodoTurno nodo, List<Turno> lista){
        if(nodo==null) return;
        inorder(nodo.izq, lista);
        lista.add(nodo.turno);
        inorder(nodo.der, lista);
    }
    public boolean reprogramar(String idTurno, LocalDateTime nuevaFecha){
        NodoTurno nodo=siguientePorId(root,idTurno);
        if(nodo==null) return false;

        Turno t=nodo.turno;
        // Crear turno temporal para validar solapamientos
        Turno temp=new Turno(t.id, t.dniPaciente, t.matriculaMedico, nuevaFecha, t.duracionMin, t.motivo);

        if(conflicto(temp)) return false;

        t.fechaHora=nuevaFecha;
        return true;}
}
