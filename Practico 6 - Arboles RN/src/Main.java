public class Main {

    public static void main(String[] args) {
        System.out.println("=== EJ.2: rotateLeft ===");
        testEj2_rotateLeft();

        System.out.println("\n=== EJ.3: rotateRight ===");
        testEj3_rotateRight();

        System.out.println("\n=== EJ.4: insertBST (sin balance) ===");
        testEj4_insertBST();

        System.out.println("\n=== EJ.6+7: fixInsert (tío rojo + rama izquierda LL/LR) ===");
        testEj6y7_fixInsert_izquierda();

        System.out.println("\n=== EJ.8: successor / predecessor ===");
        testEj8_succ_pred();

        System.out.println("\n=== EJ.9: keysInRange [a,b] ===");
        testEj9_rango();

        System.out.println("\n=== EJ.0: verificadores de invariantes ===");
        testEj0_invariantes();
    }


    private static void testEj2_rotateLeft() {
        RBTree<Integer, String> t = new RBTree<>();

        RBTree<Integer,String>.RBNode n10 = t.newNode(10, "A");
        RBTree<Integer,String>.RBNode n20 = t.newNode(20, "B");

        // 10 \ 20
        t.setRootForTest(n10);
        t.linkRight(n10, n20);

        System.out.println("Antes de rotar a la izquierda:");
        System.out.println("Raíz = " + t.getRoot().key);                       // 10
        System.out.println("Derecho de raíz = " + t.getRoot().right.key);      // 20

        t.rotateLeft(n10);

        System.out.println("Después de rotar a la izquierda:");
        System.out.println("Raíz = " + t.getRoot().key);                       // 20
        System.out.println("Izquierdo de raíz = " + t.getRoot().left.key);     // 10
        System.out.println("La raíz no tiene padre (NIL): " + (t.getRoot().parent == t.NIL));
        System.out.println("El derecho de 10 es NIL: " + (t.getRoot().left.right == t.NIL));
    }


    private static void testEj3_rotateRight() {
        RBTree<Integer, String> t = new RBTree<>();

        RBTree<Integer,String>.RBNode n20 = t.newNode(20, "B");
        RBTree<Integer,String>.RBNode n10 = t.newNode(10, "A");


        t.setRootForTest(n20);
        t.linkLeft(n20, n10);

        System.out.println("Antes de rotar a la derecha:");
        System.out.println("Raíz = " + t.getRoot().key);
        System.out.println("Izquierdo de raíz = " + t.getRoot().left.key);

        t.rotateRight(n20);

        System.out.println("Después de rotar a la derecha:");
        System.out.println("Raíz = " + t.getRoot().key);
        System.out.println("Derecho de raíz = " + t.getRoot().right.key);
        System.out.println("La raíz no tiene padre (NIL): " + (t.getRoot().parent == t.NIL));
        System.out.println("El izquierdo de 20 es NIL: " + (t.getRoot().right.left == t.NIL));
    }


    private static void testEj4_insertBST() {
        RBTree<Integer, String> t = new RBTree<>();

        RBTree<Integer,String>.RBNode n10 = t.insertBST(10, "A");
        RBTree<Integer,String>.RBNode n20 = t.insertBST(20, "B");
        RBTree<Integer,String>.RBNode n5  = t.insertBST(5,  "C");

        System.out.println("Raíz = " + t.getRoot().key);
        System.out.println("Izquierdo de raíz = " + t.getRoot().left.key);
        System.out.println("Derecho de raíz = " + t.getRoot().right.key);
        System.out.println("Padre de 5 = " + n5.parent.key);
        System.out.println("Padre de 20 = " + n20.parent.key);
        System.out.println("n5.left == NIL: " + (n5.left == t.NIL));
        System.out.println("n20.right == NIL: " + (n20.right == t.NIL));
    }


    private static void testEj6y7_fixInsert_izquierda() {
        RBTree<Integer, String> t = new RBTree<>();


        RBTree<Integer,String>.RBNode n30 = t.insertBST(30, "30");
        t.fixInsert(n30);
        RBTree<Integer,String>.RBNode n20 = t.insertBST(20, "20");
        t.fixInsert(n20);
        RBTree<Integer,String>.RBNode n25 = t.insertBST(25, "25");
        t.fixInsert(n25); // rotateLeft(20) + rotateRight(30) + recoloreo

        System.out.println("Raíz = " + t.getRoot().key);                       // 25
        System.out.println("Color raíz negra: " + t.getRoot().isBlack());
        System.out.println("Hijo izq = " + t.getRoot().left.key);              // 20
        System.out.println("Hijo der = " + t.getRoot().right.key);             // 30
    }


    private static void testEj8_succ_pred() {
        RBTree<Integer, String> t = new RBTree<>();

        RBTree<Integer,String>.RBNode n10 = t.insertBST(10, "diez");
        RBTree<Integer,String>.RBNode n5  = t.insertBST(5,  "cinco");
        RBTree<Integer,String>.RBNode n15 = t.insertBST(15, "quince");

        RBTree<Integer,String>.RBNode s5  = t.successor(n5);
        RBTree<Integer,String>.RBNode s10 = t.successor(n10);
        RBTree<Integer,String>.RBNode s15 = t.successor(n15);

        RBTree<Integer,String>.RBNode p5  = t.predecessor(n5);
        RBTree<Integer,String>.RBNode p10 = t.predecessor(n10);
        RBTree<Integer,String>.RBNode p15 = t.predecessor(n15);

        System.out.println("successor(5)  = " + (s5  != t.NIL ? s5.key  : "NIL"));
        System.out.println("successor(10) = " + (s10 != t.NIL ? s10.key : "NIL"));
        System.out.println("successor(15) = " + (s15 != t.NIL ? s15.key : "NIL"));

        System.out.println("predecessor(5)  = " + (p5  != t.NIL ? p5.key  : "NIL"));
        System.out.println("predecessor(10) = " + (p10 != t.NIL ? p10.key : "NIL"));
        System.out.println("predecessor(15) = " + (p15 != t.NIL ? p15.key : "NIL"));
    }


    private static void testEj9_rango() {
        RBTree<Integer, String> t = new RBTree<>();
        t.insertBST(10, "diez");
        t.insertBST(5,  "cinco");
        t.insertBST(15, "quince");

        System.out.println("Rango [5,15]  -> " + t.keysInRange(5, 15));
        System.out.println("Rango [6,14]  -> " + t.keysInRange(6, 14));
        System.out.println("Rango [0,4]   -> " + t.keysInRange(0, 4));
        System.out.println("Rango [5,10]  -> " + t.keysInRange(5, 10));
    }

    private static void testEj0_invariantes() {
        // Caso LR (evitamos RR/RL hasta completarlos)
        RBTree<Integer, String> t = new RBTree<>();
        RBTree<Integer,String>.RBNode n30 = t.insertBST(30, "30"); t.fixInsert(n30);
        RBTree<Integer,String>.RBNode n20 = t.insertBST(20, "20"); t.fixInsert(n20);
        RBTree<Integer,String>.RBNode n25 = t.insertBST(25, "25"); t.fixInsert(n25);

        System.out.println("[LR] raíz negra: " + t.raizNegra());
        System.out.println("[LR] sin rojo-rojo: " + t.sinRojoRojo());
        System.out.println("[LR] altura negra: " + t.alturaNegra());


        RBTree<Integer, String> t2 = new RBTree<>();
        RBTree<Integer,String>.RBNode a = t2.insertBST(10, "10"); t2.fixInsert(a);
        RBTree<Integer,String>.RBNode b = t2.insertBST(5,  "5");  t2.fixInsert(b);
        RBTree<Integer,String>.RBNode c = t2.insertBST(15, "15"); t2.fixInsert(c);
        RBTree<Integer,String>.RBNode d = t2.insertBST(1,  "1");  t2.fixInsert(d);

        System.out.println("[TÍO ROJO] raíz negra: " + t2.raizNegra());
        System.out.println("[TÍO ROJO] sin rojo-rojo: " + t2.sinRojoRojo());
        System.out.println("[TÍO ROJO] altura negra: " + t2.alturaNegra());
    }
}
