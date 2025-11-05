public class PilaString {
    private String[] datos;
    private int tope;

    public PilaString(int capacidad) {
        datos = new String[capacidad];
        tope = -1;
    }

    public boolean isEmpty() { return tope == -1; }
    public boolean isFull() { return tope == datos.length - 1; }

    public void push(String x) {
        if (isFull()) throw new IllegalStateException("Overflow: pila llena");
        datos[++tope] = x;
    }

    public String pop() {
        if (isEmpty()) throw new IllegalStateException("Underflow: pila vacía");
        return datos[tope--];
    }

    public String top() {
        if (isEmpty()) throw new IllegalStateException("Pila vacía");
        return datos[tope];
    }

    public void show() {
        if (isEmpty()){
            System.out.println("PilaString vacía.");
        } else {
            System.out.println("PilaString (tope → base):");
            for (int i = tope; i >= 0; i--) {
                System.out.println("[" + i + "] " + datos[i]);
            }
        }
    }
}
