import java.time.LocalDateTime;
import java.util.Optional;

public class AVLAgenda implements AgendaMedico {
    private TurnoAVLNode raiz;

    public AVLAgenda(){
        raiz=null;
    }

    @Override
    public boolean agendar(Turno t){
        if(buscarConflicto(raiz, t)) return false;
        raiz=insertar(raiz, t);
        return true;
    }

    @Override
    public boolean cancelar(String idTurno){
        if(buscarPorId(raiz, idTurno)==null) return false;
        raiz=eliminar(raiz, idTurno);
        return true;
    }

    @Override
    public Optional<Turno> siguiente(LocalDateTime t){
        TurnoAVLNode nodo=siguienteMayor(raiz, t);
        return nodo!=null? Optional.of(nodo.turno) : Optional.empty();
    }

    private boolean buscarConflicto(TurnoAVLNode node, Turno t){
        if(node==null) return false;
        LocalDateTime start=node.turno.fechaHora;
        LocalDateTime end=start.plusMinutes(node.turno.duracionMin);
        LocalDateTime tEnd=t.fechaHora.plusMinutes(t.duracionMin);

        boolean overlap=!t.fechaHora.isAfter(end) && !tEnd.isBefore(start);
        return overlap || buscarConflicto(node.izq,t) || buscarConflicto(node.der,t);
    }

    private TurnoAVLNode buscarPorId(TurnoAVLNode node, String id){
        if(node==null) return null;
        if(node.turno.id.equals(id)) return node;
        TurnoAVLNode izqRes=buscarPorId(node.izq,id);
        return izqRes!=null? izqRes : buscarPorId(node.der,id);
    }

    // Métodos AVL: insertar, eliminar, rotaciones, altura
    private TurnoAVLNode insertar(TurnoAVLNode node, Turno t){
        if(node==null) return new TurnoAVLNode(t);
        if(t.fechaHora.isBefore(node.turno.fechaHora)) node.izq=insertar(node.izq,t);
        else node.der=insertar(node.der,t);

        node.altura=1+Math.max(altura(node.izq),altura(node.der));
        return balancear(node);
    }

    private TurnoAVLNode eliminar(TurnoAVLNode node, String id){
        if(node==null) return null;
        if(node.turno.id.equals(id)){
            if(node.izq==null) return node.der;
            if(node.der==null) return node.izq;

            TurnoAVLNode sucesor=minValueNode(node.der);
            node.turno=sucesor.turno;
            node.der=eliminar(node.der,sucesor.turno.id);
        } else if(id.compareTo(node.turno.id)<0) node.izq=eliminar(node.izq,id);
        else node.der=eliminar(node.der,id);

        node.altura=1+Math.max(altura(node.izq),altura(node.der));
        return balancear(node);
    }

    private TurnoAVLNode minValueNode(TurnoAVLNode node){
        TurnoAVLNode current=node;
        while(current.izq!=null) current=current.izq;
        return current;
    }

    private int altura(TurnoAVLNode node){
        return node==null? 0 : node.altura;
    }

    private int balanceFactor(TurnoAVLNode node){
        return node==null? 0 : altura(node.izq)-altura(node.der);
    }

    private TurnoAVLNode balancear(TurnoAVLNode node){
        int bf=balanceFactor(node);
        if(bf>1){
            if(balanceFactor(node.izq)<0) node.izq=rotacionIzq(node.izq);
            node=rotacionDer(node);
        } else if(bf<-1){
            if(balanceFactor(node.der)>0) node.der=rotacionDer(node.der);
            node=rotacionIzq(node);
        }
        return node;
    }

    private TurnoAVLNode rotacionDer(TurnoAVLNode y){
        TurnoAVLNode x=y.izq;
        TurnoAVLNode T2=x.der;

        x.der=y;
        y.izq=T2;

        y.altura=1+Math.max(altura(y.izq),altura(y.der));
        x.altura=1+Math.max(altura(x.izq),altura(x.der));

        return x;
    }

    private TurnoAVLNode rotacionIzq(TurnoAVLNode x){
        TurnoAVLNode y=x.der;
        TurnoAVLNode T2=y.izq;

        y.izq=x;
        x.der=T2;

        x.altura=1+Math.max(altura(x.izq),altura(x.der));
        y.altura=1+Math.max(altura(y.izq),altura(y.der));

        return y;
    }

    private TurnoAVLNode siguienteMayor(TurnoAVLNode node, LocalDateTime t){
        TurnoAVLNode res=null;
        while(node!=null){
            if(node.turno.fechaHora.isAfter(t)||node.turno.fechaHora.isEqual(t)){
                res=node;
                node=node.izq;
            } else node=node.der;
        }
        return res;
    }
}
