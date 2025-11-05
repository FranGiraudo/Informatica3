public class Tarea{
    String descripcion;
    int prioridad; // menor número = más urgente

    public Tarea(String descripcion,int prioridad){
        this.descripcion=descripcion;
        this.prioridad=prioridad;
    }

    @Override
    public String toString(){
        return "Tarea[Descripcion:"+descripcion+", Prioridad:"+prioridad+"]";
    }
}
