public class MapaPacientes {
    private NodoPacientes[] tabla;
    private int size;
    private int capacidad;

    public MapaPacientes(int capacidadInicial){
        capacidad=capacidadInicial;
        tabla=new NodoPacientes[capacidad];
        size=0;
    }

    private int hash(String dni){
        int h=0;
        for(int i=0;i<dni.length();i++) h=31*h+dni.charAt(i);
        return Math.abs(h)%capacidad;
    }

    public void put(String dni, Paciente p){
        int idx=hash(dni);
        NodoPacientes nodo=tabla[idx];
        while(nodo!=null){
            if(nodo.dni.equals(dni)){
                nodo.paciente=p;
                return;
            }
            nodo=nodo.next;
        }
        NodoPacientes nuevo=new NodoPacientes(dni,p);
        nuevo.next=tabla[idx];
        tabla[idx]=nuevo;
        size++;
        if((double)size/capacidad>0.75) rehash();
    }

    public Paciente get(String dni){
        int idx=hash(dni);
        NodoPacientes nodo=tabla[idx];
        while(nodo!=null){
            if(nodo.dni.equals(dni)) return nodo.paciente;
            nodo=nodo.next;
        }
        return null;
    }

    public boolean containsKey(String dni){ return get(dni)!=null; }

    public boolean remove(String dni){
        int idx=hash(dni);
        NodoPacientes nodo=tabla[idx];
        NodoPacientes prev=null;
        while(nodo!=null){
            if(nodo.dni.equals(dni)){
                if(prev==null) tabla[idx]=nodo.next;
                else prev.next=nodo.next;
                size--;
                return true;
            }
            prev=nodo;
            nodo=nodo.next;
        }
        return false;
    }

    private void rehash(){
        capacidad*=2;
        NodoPacientes[] vieja=tabla;
        tabla=new NodoPacientes[capacidad];
        size=0;

        for(int i=0;i<vieja.length;i++){
            NodoPacientes nodo=vieja[i];
            while(nodo!=null){
                put(nodo.dni,nodo.paciente);
                nodo=nodo.next;
            }
        }
    }

    public int size(){ return size; }

    public Iterable<String> keys(){
        java.util.List<String> lista=new java.util.ArrayList<>();
        for(int i=0;i<tabla.length;i++){
            NodoPacientes nodo=tabla[i];
            while(nodo!=null){
                lista.add(nodo.dni);
                nodo=nodo.next;
            }
        }
        return lista;
    }

    public void printTabla(){
        System.out.println("Estado del hash de pacientes:");
        for(int i=0;i<tabla.length;i++){
            System.out.print("Bucket "+i+": ");
            NodoPacientes nodo=tabla[i];
            while(nodo!=null){
                System.out.print("("+nodo.dni+", "+nodo.paciente.nombre+") -> ");
                nodo=nodo.next;
            }
            System.out.println("null");
        }
        System.out.println();
    }
}
