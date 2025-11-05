import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // ==========================================================
        // EJERCICIO 1: Inserciones y FE (LL y RR)
        // Secuencia: 30, 20, 10, 40, 50, 60
        // ==========================================================
        System.out.println("\n===== EJERCICIO 1: CASOS LL y RR =====");
        arbolAvl avl1 = new arbolAvl();
        int[] seq1 = {30, 20, 10, 40, 50, 60};

        for (int dato : seq1) {
            System.out.println("\n--- Insertando: " + dato + " ---");
            avl1.insertar(dato);
            avl1.imprimirEstructura();
        }
        System.out.println("En el paso 3 (insertar 10) ocurre LL. En el paso 6 (insertar 60) ocurre RR.");

        // ==========================================================
        // EJERCICIO 2: Inserciones con rotación doble (LR y RL)
        // Secuencia: 30, 10, 20, 40, 35, 37
        // ==========================================================
        System.out.println("\n===== EJERCICIO 2: CASOS LR y RL =====");
        arbolAvl avl2 = new arbolAvl();
        int[] seq2 = {30, 10, 20, 40, 35, 37};

        for (int dato : seq2) {
            System.out.println("\n--- Insertando: " + dato + " ---");
            avl2.insertar(dato);
            avl2.imprimirEstructura();
        }
        System.out.println("En el paso 3 (insertar 20) ocurre LR. En el paso 6 (insertar 37) ocurre RL.");

        // ==========================================================
        // EJERCICIO 4: Eliminación con rebalanceo
        // Inicial: 50, 30, 70, 20, 40, 60, 80, 65, 75. Eliminar: 20, luego 70.
        // ==========================================================
        System.out.println("\n===== EJERCICIO 4: ELIMINACIÓN Y REBALANCEO =====");
        arbolAvl avl4 = new arbolAvl();
        for (int dato : new int[]{50, 30, 70, 20, 40, 60, 80, 65, 75}) {
            avl4.insertar(dato);
        }
        System.out.println("Árbol inicial (antes de eliminar):");
        avl4.imprimirEstructura();

        System.out.println("\n--- Eliminando: 20 ---");
        avl4.eliminar(20);
        avl4.imprimirEstructura();

        System.out.println("\n--- Eliminando: 70 ---");
        avl4.eliminar(70);
        avl4.imprimirEstructura();

        // ==========================================================
        // EJERCICIO 5: Comprobador de AVL
        // ==========================================================
        System.out.println("\n===== EJERCICIO 5: COMPROBADOR DE AVL =====");

        // Árbol 1: AVL válido (el avl4 después de las eliminaciones)
        int[] resValid = avl4.esAVL();
        System.out.println("Árbol AVL (avl4 final) → Es AVL: " + (resValid[0] == 1) + ", Altura: " + resValid[1]);

        // Árbol 2: Crear un ABB desbalanceado manualmente (si se permite) para probar el fallo.
        arbolAvl avl_inv = new arbolAvl();
        avl_inv.insertar(1);
        avl_inv.insertar(2);
        avl_inv.insertar(3);

        arbolAvl avl_check = new arbolAvl();
        for (int dato : new int[]{10, 20, 30, 40}) {
            avl_check.insertar(dato);
        }
        int[] resCheck = avl_check.esAVL();
        System.out.println("Árbol Check (10,20,30,40) → Es AVL: " + (resCheck[0] == 1) + ", Altura: " + resCheck[1]);

    }
}