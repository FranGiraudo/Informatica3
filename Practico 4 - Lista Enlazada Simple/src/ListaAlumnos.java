public class ListaAlumnos {

    public static class Alumno {
        private String nombre;
        private int legajo;

        public Alumno(String nombre, int legajo) {
            this.nombre = nombre;
            this.legajo = legajo;
        }

        public String getNombre() { return nombre; }
        public int getLegajo() { return legajo; }

        @Override
        public String toString() {
            return "(" + legajo + ") " + nombre;
        }
    }

    private static class Nodo {
        Alumno dato;
        Nodo siguiente;

        Nodo(Alumno dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cabeza;
    private int size;

    public ListaAlumnos() {
        this.cabeza = null;
        this.size = 0;
    }

    public boolean isEmpty() { return cabeza == null; }
    public int size() { return size; }

    public void agregarAlumno(String nombre, int legajo) {
        Alumno nuevoAlumno = new Alumno(nombre, legajo);
        Nodo nuevo = new Nodo(nuevoAlumno);

        if (this.cabeza == null) {
            this.cabeza = nuevo;
        } else {
            Nodo actual = this.cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        this.size++;
    }

    public Alumno buscarAlumno(int legajo) {
        Nodo actual = this.cabeza;
        while (actual != null) {
            if (actual.dato.getLegajo() == legajo) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean eliminarAlumno(int legajo) {
        if (isEmpty()) return false;

        if (this.cabeza.dato.getLegajo() == legajo) {
            this.cabeza = this.cabeza.siguiente;
            this.size--;
            return true;
        }

        Nodo actual = this.cabeza;
        while (actual.siguiente != null &&
                actual.siguiente.dato.getLegajo() != legajo) {
            actual = actual.siguiente;
        }

        if (actual.siguiente == null) {
            return false;
        }

        actual.siguiente = actual.siguiente.siguiente;
        this.size--;
        return true;
    }

    public void imprimir() {
        if (isEmpty()) {
            System.out.println("[Lista vacía]");
            return;
        }

        System.out.print("Registro de Alumnos (Total: " + this.size + "): ");
        Nodo actual = this.cabeza;
        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }
        System.out.println("null");
    }
}