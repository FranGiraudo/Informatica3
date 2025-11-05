public class MinHeapTareas{
    private Tarea[] heap;
    private int size;

    public MinHeapTareas(int capacidad){
        heap=new Tarea[capacidad];
        size=0;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(Tarea t){
        if(size==heap.length)throw new IllegalStateException("Heap lleno");
        heap[size]=t;
        int actual=size;
        size++;

        while(actual>0){
            int padre=(actual-1)/2;
            if(heap[actual].prioridad<heap[padre].prioridad){
                Tarea temp=heap[actual];
                heap[actual]=heap[padre];
                heap[padre]=temp;
                actual=padre;
            }else break;
        }

        printArray();
    }

    public Tarea peek(){
        if(isEmpty())throw new IllegalStateException("Heap vacio");
        return heap[0];
    }

    public Tarea poll(){
        if(isEmpty())throw new IllegalStateException("Heap vacio");
        Tarea min=heap[0];
        heap[0]=heap[size-1];
        size--;
        percolateDown(0);

        System.out.println("Tarea completada: "+min+"\n");
        printArray();
        return min;
    }

    private void percolateDown(int i){
        while(true){
            int izq=2*i+1;
            int der=2*i+2;
            int menor=i;

            if(izq<size && heap[izq].prioridad<heap[menor].prioridad)menor=izq;
            if(der<size && heap[der].prioridad<heap[menor].prioridad)menor=der;

            if(menor!=i){
                Tarea temp=heap[i];
                heap[i]=heap[menor];
                heap[menor]=temp;
                i=menor;
            }else break;
        }
    }

    public void printArray(){
        System.out.println("Estado del heap:");
        for(int i=0;i<size;i++){
            System.out.println(heap[i]);
        }
        System.out.println();
    }

    public void printOrdenado(){
        MinHeapTareas copia=new MinHeapTareas(size);
        for(int i=0;i<size;i++)copia.add(heap[i]);

        while(!copia.isEmpty()){
            System.out.println(copia.poll());
        }
    }
}
