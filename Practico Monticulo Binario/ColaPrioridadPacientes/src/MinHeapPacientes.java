public class MinHeapPacientes {
    private Paciente[] heap;
    private int size;

    public MinHeapPacientes(int capacidad){
        heap = new Paciente[capacidad];
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public void printArray(){
        System.out.println("\nEstado del Heap");
        for(int i = 0; i < size; i++){
            System.out.print(heap[i]+"|");
        }
        System.out.println();
    }

    public void ingresar(Paciente p){
        if(size == heap.length)throw new IllegalStateException("Cola llena");
        heap[size] = p;
        percolateUp(size);
        size++;
        System.out.println("\nIngresado: "+p);
        printArray();
    }
    public Paciente atender(){
        if (isEmpty())throw new IllegalStateException("Cola Vacia");
        Paciente min=heap[0];
        heap[0] = heap[--size];
        percolateDown(0);
        System.out.println("Atendiendo a: "+min);
        printArray();
        return min;
    }
    private void percolateUp(int i){
        while(i>0){
            int padre=(i-1)/2;
            if(heap[i].getPrioridad()<heap[padre].getPrioridad()){
                Paciente temp=heap[i];
                heap[i]=heap[padre];
                heap[padre]=temp;
                i=padre;
            }else break;
        }
    }
    private void percolateDown(int i){
        while(true){
            int izq=2*i+1;
            int der=2*i+2;
            int menor=i;

            if(izq<size && heap[izq].getPrioridad()<heap[menor].getPrioridad())menor=izq;
            if(der<size && heap[der].getPrioridad()<heap[menor].getPrioridad())menor=der;

            if(menor!=i){
                Paciente temp=heap[i];
                heap[i]=heap[menor];
                heap[menor]=temp;
                i=menor;
            }else break;
        }
    }
}
