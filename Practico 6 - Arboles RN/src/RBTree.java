public class SimpleRBTree {

    // Define los dos posibles colores de un nodo
    enum Color { RED, BLACK }

    // El nodo NIL Siempre es NEGRO.
    public final RBNode NIL;

    private RBNode root;

    public SimpleRBTree() {
        // Inicializa el nodo NIL
        NIL = new RBNode(null, null, Color.BLACK, true);
        // La raíz del árbol vacío es NIL
        this.root = NIL;
    }

    // Clase interna para la estructura de cada nodo
    public final class RBNode {
        Integer key;
        String val;
        Color color;
        RBNode left, right, parent;

        RBNode(Integer key, String val, Color color, boolean isNil) {
            this.key   = key;
            this.val   = val;
            this.color = isNil ? Color.BLACK : color;
            // Todos los punteros de un nodo nuevo apuntan inicialmente a NIL
            this.left = this.right = this.parent = NIL;
        }

        public boolean isRed()   { return this.color == Color.RED; }
        public boolean isBlack() { return this.color == Color.BLACK; }
    }

    // Crea y retorna un nuevo nodo (por defecto, ROJO)
    public RBNode newNode(int key, String val) {
        return new RBNode(key, val, Color.RED, false);
    }
    
    
    public boolean isEmpty() { return root == NIL; }
    public RBNode getRoot()  { return root; }
    
    

    // Rotación a la Izquierda (el hijo derecho 'y' sube)
    public void rotateLeft(RBNode x) {
        if (x == NIL || x.right == NIL) return;

        RBNode y = x.right;                
        x.right = y.left;                  
        
        if (y.left != NIL) {
            y.left.parent = x;
        }

        // Enlazar y con el padre de x
        y.parent = x.parent;
        if (x.parent == NIL) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }


        y.left = x;
        x.parent = y;
    }


    public void rotateRight(RBNode y) {
        if (y == NIL || y.left == NIL) return;

        RBNode x = y.left;                  
        y.left = x.right;                  
        
        if (x.right != NIL) {
            x.right.parent = y;
        }

        // Enlazar x con el padre de y
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

    
    public RBNode insertBST(int key, String val) {
        RBNode z = newNode(key, val);
        RBNode y = NIL; 
        RBNode x = root;

        
        while (x != NIL) {
            y = x;
            if (key < x.key) { 
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // Enlace del nuevo nodo z (hoja)
        z.parent = y;
        if (y == NIL) {
            root = z;
        } else if (key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }

        return z;
    }

    
    public void fixInsert(RBNode z) {
      
        while (z.parent != NIL && z.parent.isRed()) {
            RBNode p = z.parent;
            RBNode g = p.parent;
            if (g == NIL) break; 
            
         
            RBNode tio = (p == g.left) ? g.right : g.left;

           
            if (tio != NIL && tio.isRed()) {
                p.color = Color.BLACK;
                tio.color = Color.BLACK;
                g.color = Color.RED;
                z = g; 
                continue;
            }

            
            if (p == g.left) { 
                if (z == p.right) { 
                    z = p;
                    rotateLeft(z); 
                }
                
                z.parent.color = Color.BLACK; 
                g.color = Color.RED;         
                rotateRight(g);             
            } else { 
                if (z == p.left) { 
                    z = p;
                    rotateRight(z); 
                }
                z.parent.color = Color.BLACK;
                g.color = Color.RED;
                rotateLeft(g);
            }
        }

        
        if (this.root != NIL) this.root.color = Color.BLACK;
    }

    
    
    
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
        int hr = blackHeightOrNeg1(x.right);

        // Si hay desbalance o error, retorna -1
        if (hl == -1 || hr == -1 || hl != hr) return -1; 

        // Retorna la altura del hijo + 1 si el nodo es Negro
        return hl + (x.isBlack() ? 1 : 0);
    }
}
