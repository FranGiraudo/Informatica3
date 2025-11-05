public class ColaCircularSobrescritora {
    private String[] datos;
    private int frente;
    private int fin;
    private int size;

    public ColaCircularSobrescritora(int capacidad) {
        datos = new String[capacidad];
        frente = 0;
        fin = -1;
        size = 0;
    }

    public boolean isEmpty() { return size == 0; }

    public boolean isFull() { return size == datos.length; }


    public void enqueue(String x) {
        if (isFull()) {

            frente = (frente + 1) % datos.length;
            fin = (fin + 1) % datos.length;
            datos[fin] = x;

        } else {
            fin = (fin + 1) % datos.length;
            datos[fin] = x;
            size++;
        }
    }

    public String dequeue() {
        if (isEmpty()) throw new IllegalStateException("Underflow: cola vacía");
        String val = datos[frente];
        frente = (frente + 1) % datos.length;
        size--;
        return val;
    }

    public String front() {
        if (isEmpty()) throw new IllegalStateException("Cola vacía");
        return datos[frente];
    }

    public void show() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Contenido (frente → fin):");
            for (int i = 0; i < size; i++) {
                int idx = (frente + i) % datos.length;
                System.out.println("[" + idx + "] → " + datos[idx]);
            }
        }
    }
}
