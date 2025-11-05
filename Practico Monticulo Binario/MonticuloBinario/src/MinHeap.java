import java.util.NoSuchElementException;

public class MinHeap {
    private int[] heap;
    private int size;

    public MinHeap(int capacidad){
        heap = new int[capacidad];
        size = 0;
    }

    public MinHeap(int[] datos){
        heap = datos.clone();
        size= datos.length;

        for(int i=size/2-1; i>=0; i--){
            percolateDown(i);
        }
        System.out.println("Heap desde arreglo: ");
        printArray();
        printTree();

    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int peek(){
        if (isEmpty()) throw new NoSuchElementException("Heap vacio");
        return heap[0];
    }
    public void printArray(){
        System.out.println("Heap:");
        for(int i=0;i<size;i++){
            System.out.print(heap[i]+" ");
        }
        System.out.println();
    }

    public void add(int valor) {
        if (size == heap.length) throw new IllegalStateException("Heap lleno");

        heap[size] = valor;
        int actual = size;
        size++;

        while (actual>0) {
            int padre=(actual-1)/2;
            if (heap[actual]<heap[padre]) {
                System.out.println("Intercambio: "+heap[actual]+" <-> "+heap[padre]);
                int temp=heap[actual];
                heap[actual]=heap[padre];
                heap[padre]=temp;
                actual=padre;
            } else break;
        }
        printArray();
    }


    public int poll(){
        if(isEmpty()) throw new IllegalStateException("Heap vacio");

        int min=heap[0];
        heap[0]=heap[size-1];
        size--;

        percolateDown(0);

        //System.out.println("Se elimino: "+min);
        //printArray();
        return min;
    }

    private void percolateDown(int i){
        while(true){
            int izq=2*i+1;
            int der=2*i+2;
            int menor=i;

            if(izq<size && heap[izq]<heap[menor])menor=izq;
            if(der<size && heap[der]<heap[menor])menor=der;

            if(menor!=i) {
                System.out.println("Intercambio: " + heap[i] + " <-> " + heap[menor]);
                int temp = heap[i];
                heap[i] = heap[menor];
                heap[menor] = temp;
                i = menor;
            }
            else break;
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
