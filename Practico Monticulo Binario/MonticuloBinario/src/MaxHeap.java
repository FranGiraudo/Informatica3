public class MaxHeap {
    private int[] heap;
    private int size;

    public MaxHeap(int capacidad){
        heap = new int[capacidad];
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(int valor){
        if(size == heap.length)throw new IllegalStateException("Heap lleno");
        heap[size]=valor;
        int actual=size;
        size++;

        while(actual>0){
            int padre=(actual-1)/2;
            if(heap[actual]>heap[padre]){
                int temp=heap[actual];
                heap[actual]=heap[padre];
                heap[padre]=temp;
                actual=padre;
            }else break;

        }
    }
    public int poll(){
        if(isEmpty()) throw new IllegalStateException("Heap vacio");
        int max=heap[0];
        heap[0]=heap[size-1];
        size--;
        percolateDown(0);
        return max;
    }

    private void percolateDown(int i){
        while(true){
            int izq=2*i+1;
            int der=2*i+2;
            int mayor=i;
            if(izq<size&&heap[izq]>heap[mayor])mayor=izq;
            if(der<size&&heap[der]>heap[mayor])mayor=der;

            if(mayor!=i){
                int temp=heap[i];
                heap[i]=heap[mayor];
                heap[mayor]=temp;
                i=mayor;
            }else break;
        }
    }
    public void printTree(){
        System.out.println("Heap (Arbol): ");
        int nivel=0;
        int elementosNivel=1;

        for(int i=0;i<size;i++){
            System.out.print(heap[i]+" ");
            if(i==elementosNivel-1){
                System.out.println();
                nivel++;
                elementosNivel=(int)Math.pow(2,nivel+1)-1;
            }
        }
        System.out.println();
    }
}

