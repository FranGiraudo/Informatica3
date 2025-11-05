public class MainAlumnos {
    public static void main(String[] args) {
        ListaAlumnos registro = new ListaAlumnos();

        registro.agregarAlumno("Ana García", 101);
        registro.agregarAlumno("Bruno Díaz", 205);
        registro.agregarAlumno("Carla Pérez", 150);
        registro.imprimir();


        System.out.println("\n--- Prueba de Búsqueda ---");
        var alumnoBuscado = registro.buscarAlumno(205);
        System.out.println("Buscar 205: " + (alumnoBuscado != null ? alumnoBuscado : "No encontrado"));

        alumnoBuscado = registro.buscarAlumno(999);
        System.out.println("Buscar 999: " + (alumnoBuscado != null ? alumnoBuscado : "No encontrado"));


        System.out.println("\n--- Prueba de Eliminación ---");

        boolean elim = registro.eliminarAlumno(101);
        System.out.println("Eliminar 101: " + (elim ? "OK" : "Falló/No estaba"));
        registro.imprimir();

        elim = registro.eliminarAlumno(999);
        System.out.println("Eliminar 999: " + (elim ? "OK" : "Falló/No estaba"));

        elim = registro.eliminarAlumno(150);
        System.out.println("Eliminar 150: " + (elim ? "OK" : "Falló/No estaba"));
        registro.imprimir();

        elim = registro.eliminarAlumno(205);
        System.out.println("Eliminar 205: " + (elim ? "OK" : "Falló/No estaba"));
        registro.imprimir();
    }
}