public class RBTree<K extends Comparable<K>, V> {

    enum Color { RED, BLACK }

    public final RBNode NIL;

    private RBNode root;

    public RBTree() {

        NIL = new RBNode(null, null, Color.BLACK, /*isNil*/ true);
        NIL.left = NIL.right = NIL.parent = NIL;


        this.root = NIL;
    }

    public boolean isEmpty() { return root == NIL; }
    public RBNode getRoot()  { return root; }


    public RBNode newNode(K key, V val) {
        return new RBNode(key, val, Color.RED, /*isNil*/ false);
    }


    public final class RBNode {
        K key;
        V val;
        Color color;
        RBNode left, right, parent;

        RBNode(K key, V val, Color color, boolean isNil) {
            this.key   = key;
            this.val   = val;
            this.color = isNil ? Color.BLACK : color;
            this.left = this.right = this.parent = NIL;
        }

        public boolean isRed()   { return this.color == Color.RED; }
        public boolean isBlack() { return this.color == Color.BLACK; }
    }
    //ejercicio 2 -------------------------------------------------------------------
    public void rotateLeft(RBNode x) {
        if (x == NIL || x.right == NIL) return;

        RBNode y = x.right;         // y sube
        x.right = y.left;           // el subárbol izq de y pasa a ser der de x
        if (y.left != NIL) {
            y.left.parent = x;
        }

        // enlazar y con el padre de x
        y.parent = x.parent;
        if (x.parent == NIL) {
            // x era la raíz
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }


        y.left = x;
        x.parent = y;
    }
    void setRootForTest(RBNode z) {
        this.root = z;
        z.parent = NIL;
    }
    void linkLeft(RBNode p, RBNode c) {
        p.left = c;
        c.parent = p;
    }
    void linkRight(RBNode p, RBNode c) {
        p.right = c;
        c.parent = p;
    }
    //ejercicio 3-----------------------------------------------
    public void rotateRight(RBNode y) {
        if (y == NIL || y.left == NIL) return;

        RBNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }

        x.parent = y.parent;
        if (y.parent == NIL) {

            this.root = x;
        } else if (y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }


        x.right = y;
        y.parent = x;
    }
    // ejercicio 4--------------------------------------------------------------
    public RBNode insertBST(K key, V val) {
        RBNode z = newNode(key, val);
        RBNode y = NIL;
        RBNode x = root;

        while (x != NIL) {
            y = x;
            if (key.compareTo(x.key) < 0) {
                x = x.left;
            } else {

                x = x.right;
            }
        }


        z.parent = y;
        if (y == NIL) {

            root = z;
        } else if (key.compareTo(y.key) < 0) {
            y.left = z;
        } else {
            y.right = z;
        }

        return z;
    }
    // 5------------------------------------------------------
    public enum Caso { TIO_ROJO, LL, RR, LR, RL }

    public Caso clasificar(RBNode z) {
        RBNode p = z.parent;
        RBNode g = p.parent;
        if (z == NIL || p == NIL || g == NIL) {

            return null;
        }

        RBNode tio = (p == g.left) ? g.right : g.left;


        if (tio != NIL && tio.isRed()) {
            return Caso.TIO_ROJO;
        }

        if (p == g.left) {

            if (z == p.left) return Caso.LL;
            else             return Caso.LR;
        } else {

            if (z == p.right) return Caso.RR;
            else              return Caso.RL;
        }
    }
    // ejercicio 6 y 7------------------------------------------------------------

    public void fixInsert(RBNode z) {
        while (z.parent != NIL && z.parent.isRed()) {
            RBNode p = z.parent;
            RBNode g = p.parent;
            if (g == NIL) break;

            RBNode tio = (p == g.left) ? g.right : g.left;

            // 1. Caso TÍO ROJO (Ejercicio 6)
            if (tio != NIL && tio.isRed()) {
                p.color = Color.BLACK;
                tio.color = Color.BLACK;
                g.color = Color.RED;
                z = g;
                continue;
            }

            // 2. Casos de Rotación (Ejercicio 7 + simétrico)
            if (p == g.left) { // Rama izquierda: LL, LR
                if (z == p.right) { // LR
                    z = p;
                    rotateLeft(z);
                }
                // Ahora es LL
                z.parent.color = Color.BLACK;
                g.color = Color.RED;
                rotateRight(g);
            } else { // Rama derecha: RR, RL (Simétrico al Ejercicio 7)
                if (z == p.left) { // RL
                    z = p;
                    rotateRight(z);
                }
                // Ahora es RR
                z.parent.color = Color.BLACK;
                g.color = Color.RED;
                rotateLeft(g);
            }
        }

        if (this.root != NIL) this.root.color = Color.BLACK;
    }
    //ejercicio 8----------------------------------------------------
    private RBNode minimum(RBNode x) {
        if (x == NIL) return NIL;
        while (x.left != NIL) x = x.left;
        return x;
    }


    private RBNode maximum(RBNode x) {
        if (x == NIL) return NIL;
        while (x.right != NIL) x = x.right;
        return x;
    }

    public RBNode successor(RBNode x) {
        if (x == NIL) return NIL;


        if (x.right != NIL) return minimum(x.right);


        RBNode p = x.parent;
        while (p != NIL && x == p.right) {
            x = p;
            p = p.parent;
        }
        return p;
    }

    public RBNode predecessor(RBNode x) {
        if (x == NIL) return NIL;


        if (x.left != NIL) return maximum(x.left);


        RBNode p = x.parent;
        while (p != NIL && x == p.left) {
            x = p;
            p = p.parent;
        }
        return p;
    }
    //ejrcicio9------------------------------------------------------------------------------

    public java.util.List<K> keysInRange(K a, K b) {
        java.util.List<K> out = new java.util.ArrayList<>();
        inOrderRange(this.root, a, b, out);
        return out;
    }

    public java.util.List<java.util.AbstractMap.SimpleEntry<K,V>> entriesInRange(K a, K b) {
        java.util.List<java.util.AbstractMap.SimpleEntry<K,V>> out = new java.util.ArrayList<>();
        inOrderRangeEntries(this.root, a, b, out);
        return out;
    }

    private void inOrderRange(RBNode x, K a, K b, java.util.List<K> out) {
        if (x == NIL) return;

        if (x.key.compareTo(a) > 0) {
            inOrderRange(x.left, a, b, out);
        }

        if (x.key.compareTo(a) >= 0 && x.key.compareTo(b) <= 0) {
            out.add(x.key);
        }

        if (x.key.compareTo(b) < 0) {
            inOrderRange(x.right, a, b, out);
        }
    }

    private void inOrderRangeEntries(RBNode x, K a, K b, java.util.List<java.util.AbstractMap.SimpleEntry<K,V>> out) {
        if (x == NIL) return;

        if (x.key.compareTo(a) > 0) {
            inOrderRangeEntries(x.left, a, b, out);
        }
        if (x.key.compareTo(a) >= 0 && x.key.compareTo(b) <= 0) {
            out.add(new java.util.AbstractMap.SimpleEntry<>(x.key, x.val));
        }
        if (x.key.compareTo(b) < 0) {
            inOrderRangeEntries(x.right, a, b, out);
        }
    }

    //-------------------------10--------------------------------------
    public boolean raizNegra() {
        return this.root == NIL || this.root.isBlack();
    }


    public boolean sinRojoRojo() {
        return checkSinRojoRojo(this.root);
    }

    private boolean checkSinRojoRojo(RBNode x) {
        if (x == NIL) return true;
        if (x.isRed()) {
            if (x.left != NIL && x.left.isRed())  return false;
            if (x.right != NIL && x.right.isRed()) return false;
        }
        return checkSinRojoRojo(x.left) && checkSinRojoRojo(x.right);
    }

    public int alturaNegra() {
        return blackHeightOrNeg1(this.root);
    }

    private int blackHeightOrNeg1(RBNode x) {
        if (x == NIL) return 1;

        int hl = blackHeightOrNeg1(x.left);
        if (hl == -1) return -1;

        int hr = blackHeightOrNeg1(x.right);
        if (hr == -1) return -1;

        if (hl != hr) return -1;


        return hl + (x.isBlack() ? 1 : 0);
    }

}
