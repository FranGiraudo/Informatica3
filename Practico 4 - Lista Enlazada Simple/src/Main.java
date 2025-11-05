public class Main {
    public static void main(String[] args) {
        ListaSimple lista = new ListaSimple();

        System.out.println("----- 1. CONSTRUIR LISTA -----");
        lista.insertarInicio(10);
        lista.insertarInicio(20);
        lista.insertarInicio(30);
        lista.insertarFinal(40);
        lista.insertarFinal(50);
        lista.mostrar();

        System.out.println("\n----- 2. Insertar '99' en posición 3 -----");
        lista.insertarEn(3, 99);
        lista.mostrar();

        System.out.println("\n----- 3. Eliminar el valor '99' -----");
        lista.eliminar(99);
        lista.mostrar();

        System.out.println("\n----- 4. Búsqueda y Conteo -----");
        System.out.println("¿Existe el número 40? " + lista.buscar(40));
        System.out.println("¿Existe el número 500? " + lista.buscar(500));
        System.out.println("Nodos totales en la lista: " + lista.contar());

        System.out.println("\n----- 5. Invertir el orden de la lista -----");
        lista.invertir();
        lista.mostrar();

        System.out.println("\n----- 6. Eliminar Duplicados -----");
        lista.insertarFinal(40);
        lista.insertarFinal(50);
        System.out.println("Lista antes de limpiar:");
        lista.mostrar();

        lista.eliminarDuplicados();
        System.out.println("Lista después de limpiar:");
        lista.mostrar();
    }
}