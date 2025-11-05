public class SalaEspera {
    private String[] cola;
    private int capacidad;
    private int front;
    private int rear;
    private int size;

    public SalaEspera(int capacidad){
        this.capacidad=capacidad;
        cola=new String[capacidad];
        front=0;
        rear=-1;
        size=0;
    }

    public void llega(String dni){
        if(size==capacidad){ // sala llena, pisar al más antiguo
            System.out.println("Sala llena. Paciente "+cola[front]+" removido");
            front=(front+1)%capacidad;
            size--;
        }
        rear=(rear+1)%capacidad;
        cola[rear]=dni;
        size++;
        System.out.println("Paciente "+dni+" ingresó a la sala");
    }

    public String atiende(){
        if(size==0) return null;
        String paciente=cola[front];
        front=(front+1)%capacidad;
        size--;
        return paciente;
    }

    public String peek(){
        if(size==0) return null;
        return cola[front];
    }

    public int size(){
        return size;
    }

    public void printEstado(){
        System.out.print("FRONT → [");
        for(int i=0;i<size;i++){
            int idx=(front+i)%capacidad;
            System.out.print(cola[idx]);
            if(i<size-1) System.out.print(", ");
        }
        System.out.println("] ← REAR");
    }
}
