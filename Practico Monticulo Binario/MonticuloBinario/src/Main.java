
public class Main {
    public static void main(String[] args) {
        MinHeap heap=new MinHeap(10);

        int[] valores={20,5,15,3,11};
        for(int i=0;i<valores.length;i++){
            System.out.println("Agregando "+valores[i]);
            heap.add(valores[i]);

        }
        heap.printTree();

        //Heap desde arreglo
        int[] datos = {9, 4, 7, 1, 6, 2};
        MinHeap heap2 = new MinHeap(datos);

        System.out.println("HeapSort: ");
        heapsort(datos);

        System.out.println("Arreglo Ordenado: ");
        for(int i=0;i<datos.length;i++){
            System.out.println(datos[i]);
        }
        System.out.println();
       /* System.out.println("\nExtraccion en orden: ");
        while(!heap.isEmpty()){
            heap.poll();
            heap.printTree();
            //System.out.println(heap.poll());
        }*/

        //MAXHEAP
        int[] arr={10,3,15,8,6,12};
        MaxHeap maxHeap=new  MaxHeap(arr.length);

        for(int i=0;i<arr.length;i++){
            maxHeap.add(arr[i]);
        }
        maxHeap.printTree();
        System.out.println("Extraccion en orden (M2m): ");
        while(!maxHeap.isEmpty()){
            System.out.println(maxHeap.poll());
        }


    }
    public static void heapsort(int[] arr){
        MinHeap minHeap=new MinHeap(arr.length);

        for(int i=0;i<arr.length;i++){
            minHeap.add(arr[i]);
        }

        for(int i=0;i<arr.length;i++){
            arr[i]=minHeap.poll();
        }
    }
}