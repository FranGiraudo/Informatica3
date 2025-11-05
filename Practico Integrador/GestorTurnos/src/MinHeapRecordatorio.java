import java.time.LocalDateTime;

public class MinHeapRecordatorio {
    private Recordatorio[] heap;
    private int size;

    public MinHeapRecordatorio(int capacidad){
        heap = new Recordatorio[capacidad];
        size = 0;
    }

    public boolean isEmpty(){ return size == 0; }

    public void push(Recordatorio r){
        if(size == heap.length) throw new IllegalStateException("Heap lleno");
        heap[size] = r;
        int actual = size;
        size++;

        while(actual > 0){
            int padre = (actual - 1) / 2;
            // CORREGIDO: Usamos getFecha()
            if(heap[actual].getFecha().isBefore(heap[padre].getFecha())){
                Recordatorio temp = heap[actual];
                heap[actual] = heap[padre];
                heap[padre] = temp;
                actual = padre;
            } else break;
        }
    }

    public Recordatorio pop(){
        if(isEmpty()) return null;
        Recordatorio min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        percolateDown(0);
        return min;
    }

    private void percolateDown(int i){
        while(true){
            int izq = 2 * i + 1;
            int der = 2 * i + 2;
            int menor = i;

            // CORREGIDO: Usamos getFecha()
            if(izq < size && heap[izq].getFecha().isBefore(heap[menor].getFecha())) menor = izq;
            if(der < size && heap[der].getFecha().isBefore(heap[menor].getFecha())) menor = der;

            if(menor != i){
                Recordatorio temp = heap[i];
                heap[i] = heap[menor];
                heap[menor] = temp;
                i = menor;
            } else break;
        }
    }

    public void reprogramar(String id, LocalDateTime nuevaFecha){
        for(int i = 0; i < size; i++){
            // CORREGIDO: Usamos getId()
            if(heap[i].getId().equals(id)){
                // CORREGIDO: Usamos setFecha()
                heap[i].setFecha(nuevaFecha);

                // Ajustar posición en el heap (Subir o bajar si es necesario)

                // Ajuste hacia arriba (si la nueva fecha es más temprana)
                int actual = i;
                while(actual > 0){
                    int padre = (actual - 1) / 2;
                    // CORREGIDO: Usamos getFecha()
                    if(heap[actual].getFecha().isBefore(heap[padre].getFecha())){
                        Recordatorio temp = heap[actual];
                        heap[actual] = heap[padre];
                        heap[padre] = temp;
                        actual = padre;
                    } else break;
                }

                // Ajuste hacia abajo (si la nueva fecha es más tardía)
                // Es importante llamar a percolateDown desde la posición original 'i'
                percolateDown(i);

                break;
            }
        }
    }

    public int size(){ return size; }

    public void printHeap(){
        System.out.println("Estado del heap de recordatorios:");
        for(int i = 0; i < size; i++) System.out.println(heap[i]);
        System.out.println();
    }
}