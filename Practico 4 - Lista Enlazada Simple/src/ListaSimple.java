public class ListaSimple {

    static class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    private Nodo cabeza;
    private int size;

    public ListaSimple() {
        cabeza = null;
        size = 0;
    }

    // EJERCICIO 2 – Insertar al inicio
    public void insertarInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = cabeza;
        cabeza = nuevo;
        size++;
    }

    // EJERCICIO 3 – Insertar al final
    public void insertarFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        size++;
    }

    // EJERCICIO 8 – Insertar en posición
    public void insertarEn(int posicion, int dato) {
        if (posicion < 0 || posicion > size) {
            System.out.println("Fuera de rango");
            return;
        }
        if (posicion == 0) {
            insertarInicio(dato);
            return;
        }
        Nodo nuevo = new Nodo(dato);
        Nodo actual = cabeza;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.siguiente;
        }
        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;
        size++;
    }

    // EJERCICIO 4 – Eliminar por valor
    public boolean eliminar(int valor) {
        if (cabeza == null) {
            return false;
        }

        if (cabeza.dato == valor) {
            cabeza = cabeza.siguiente;
            size--;
            return true;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && actual.siguiente.dato != valor) {
            actual = actual.siguiente;
        }

        if (actual.siguiente == null) {
            return false;
        }

        actual.siguiente = actual.siguiente.siguiente;
        size--;
        return true;
    }

    // EJERCICIO 5 – Buscar un valor
    public boolean buscar(int valor) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.dato == valor) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    // EJERCICIO 6 – Contar elementos
    public int contar() {
        return size;
    }

    // EJERCICIO 7 – Invertir la lista
    public void invertir() {
        Nodo anterior = null;
        Nodo actual = cabeza;
        Nodo siguiente = null;

        while (actual != null) {
            siguiente = actual.siguiente;
            actual.siguiente = anterior;
            anterior = actual;
            actual = siguiente;
        }
        cabeza = anterior;
    }

    // EJERCICIO 9 – Eliminar duplicados
    public void eliminarDuplicados() {
        if (cabeza == null) return;

        Nodo actual = cabeza;

        while (actual != null) {
            Nodo corredor = actual;

            while (corredor.siguiente != null) {
                if (corredor.siguiente.dato == actual.dato) {
                    corredor.siguiente = corredor.siguiente.siguiente;
                    size--;
                } else {
                    corredor = corredor.siguiente;
                }
            }
            actual = actual.siguiente;
        }
    }

    // Método para mostrar la lista
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("Lista: [Vacia]");
            return;
        }
        System.out.print("Lista: ");
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null (Size: " + size + ")");
    }
}