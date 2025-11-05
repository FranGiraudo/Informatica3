public class DemoEjercicios {


    public static String invertir(String s) {
        Pila pila = new Pila(s.length());
        for (int i = 0; i < s.length(); i++) {
            pila.push(s.charAt(i)); // char a int
        }
        StringBuilder sb = new StringBuilder();
        while (!pila.isEmpty()) {
            sb.append((char) pila.pop());
        }
        return sb.toString();
    }


    public static boolean esPalindromo(String s) {
        // Normalización mínima: solo letras/dígitos, a minúsculas
        StringBuilder limpio = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) limpio.append(Character.toLowerCase(c));
        }
        String t = limpio.toString();
        int n = t.length();
        Pila pila = new Pila(n);
        Cola cola = new Cola(n);
        for (int i = 0; i < n; i++) {
            int code = t.charAt(i);
            pila.push(code);
            cola.enqueue(code);
        }
        while (!pila.isEmpty() && !cola.isEmpty()) {
            if (pila.pop() != cola.dequeue()) return false;
        }
        return true;
    }


    public static void demoUndoRedo() {
        PilaString undo = new PilaString(20);
        PilaString redo = new PilaString(20);


        undo.push("Escribir: 'Hola'");
        undo.push("Escribir: ' Mundo'");
        undo.push("Borrar: 'o'");
        undo.push("Copiar");
        undo.push("Pegar");

        System.out.println("Estado inicial UNDO:");
        undo.show();
        System.out.println("REDO:");
        redo.show();


        System.out.println("\nDeshacer 2:");
        for (int i = 0; i < 2; i++) {
            String act = undo.pop();
            redo.push(act);
            System.out.println("Deshecho → " + act);
        }

        System.out.println("\nUNDO tras deshacer:");
        undo.show();
        System.out.println("REDO tras deshacer:");
        redo.show();

        // Rehacer 1
        System.out.println("\nRehacer 1:");
        String reh = redo.pop();
        undo.push(reh);
        System.out.println("Rehecho → " + reh);

        System.out.println("\nUNDO final:");
        undo.show();
        System.out.println("REDO final:");
        redo.show();
    }


    public static void demoTurnosBanco() {
        ColaString cola = new ColaString(10);
        cola.enqueue("Ana");
        cola.enqueue("Luis");
        cola.enqueue("Marta");
        cola.enqueue("Pedro");

        System.out.println("Cola antes de atender:");
        cola.show();

        System.out.println("Atendiendo: " + cola.dequeue());
        System.out.println("Atendiendo: " + cola.dequeue());

        System.out.println("Cola después de atender 2:");
        cola.show();
    }


    public static void demoImpresora() {
        ColaString cola = new ColaString(10);
        cola.enqueue("Doc1");
        cola.enqueue("Doc2");
        cola.enqueue("Doc3");
        cola.enqueue("Doc4");
        cola.enqueue("Doc5");

        System.out.println("Cola de impresión (inicio):");
        cola.show();

        System.out.println("Imprimir: " + cola.dequeue());
        System.out.println("Imprimir: " + cola.dequeue());
        System.out.println("Imprimir: " + cola.dequeue());

        System.out.println("Pendientes:");
        cola.show();
    }


    public static void demoColaSobrescritora() {
        ColaCircularSobrescritora q = new ColaCircularSobrescritora(5);
        for (int i = 1; i <= 8; i++) {
            q.enqueue("Llamada" + i);
        }
        System.out.println("Estado final (capacidad 5, llegaron 8 → quedan las 5 más recientes):");
        q.show();
    }
}
