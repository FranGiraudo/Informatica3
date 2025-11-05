public class Pila {
    private int[] datos;
    private int tope;

    public Pila(int capacidad) {
        datos = new int[capacidad];
        tope = -1;
    }

    public boolean isEmpty() { return tope == -1; }

    public boolean isFull() { return tope == datos.length - 1; }

    public void push(int x) {
        if (isFull()) throw new IllegalStateException("Overflow: pila llena");
        datos[++tope] = x;
    }

    public int pop() {
        if (isEmpty()) throw new IllegalStateException("Underflow: pila vacía");
        return datos[tope--];
    }

    public int top() {
        if (isEmpty()) throw new IllegalStateException("Pila vacía");
        return datos[tope];
    }

    public void show(){
        if (isEmpty()){
            System.out.println("La pila esta vacia.");
        } else {
            System.out.println("Contenido de la pila (tope → base):");
            for (int i = tope; i >= 0; i--) {
                System.out.println("[" + i + "] " + datos[i]);
            }
        }
    }
}
