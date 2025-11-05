public class Main {
    public static void main(String[] args) {

        System.out.println("----- E1: Pila -----");
        Pila pila = new Pila(10);
        pila.push(10);
        pila.push(20);
        pila.push(30);
        pila.push(40);
        pila.show();
        System.out.println("top = " + pila.top());
        System.out.println("pop = " + pila.pop());
        System.out.println("pop = " + pila.pop());
        pila.show();


        System.out.println("\n----- E2: Cola -----");
        Cola cola = new Cola(10);
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);
        cola.show();
        System.out.println("front = " + cola.front());
        System.out.println("dequeue = " + cola.dequeue());
        cola.show();


        System.out.println("\n----- E3: Invertir cadena -----");
        String original = "Hola";
        String invertida = DemoEjercicios.invertir(original);
        System.out.println("Original: " + original + " → Invertida: " + invertida);


        System.out.println("\n----- E4: Turnos banco -----");
        DemoEjercicios.demoTurnosBanco();


        System.out.println("\n----- E5: Palíndromo -----");
        String p1 = "radar";
        String p2 = "Anita lava la tina";
        String p3 = "hola";
        System.out.println(p1 + " ¿palíndromo? " + DemoEjercicios.esPalindromo(p1));
        System.out.println(p2 + " ¿palíndromo? " + DemoEjercicios.esPalindromo(p2));
        System.out.println(p3 + " ¿palíndromo? " + DemoEjercicios.esPalindromo(p3));


        System.out.println("\n----- E6: Deshacer/Rehacer -----");
        DemoEjercicios.demoUndoRedo();


        System.out.println("\n----- E7: Impresora -----");
        DemoEjercicios.demoImpresora();


        System.out.println("\n----- E8: Cola circular con sobrescritura -----");
        DemoEjercicios.demoColaSobrescritora();
    }
}
