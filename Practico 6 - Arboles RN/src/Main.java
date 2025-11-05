import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SimpleRBTree arbolRN = new SimpleRBTree();
  
        SimpleRBTree.RBNode z1 = arbolRN.insertBST(10, "Diez");
        arbolRN.fixInsert(z1);
        
 
        SimpleRBTree.RBNode z2 = arbolRN.insertBST(18, "Dieciocho");
        arbolRN.fixInsert(z2);
        
  
        SimpleRBTree.RBNode z3 = arbolRN.insertBST(7, "Siete");
        arbolRN.fixInsert(z3);
        
        SimpleRBTree.RBNode z4 = arbolRN.insertBST(15, "Quince");
        arbolRN.fixInsert(z4);
        
   
        SimpleRBTree.RBNode z5 = arbolRN.insertBST(16, "Dieciséis");
        arbolRN.fixInsert(z5);
        


        System.out.println("--- Verificación de Árbol Rojo-Negro ---");
        System.out.println("Raíz es Negra: " + arbolRN.raizNegra());
        System.out.println("No hay Rojo-Rojo: " + arbolRN.sinRojoRojo());
        
        int alturaNegra = arbolRN.alturaNegra();
        if (alturaNegra != -1) {
            System.out.println("Altura Negra Consistente: " + alturaNegra);
        } else {
            System.out.println("Altura Negra Inconsistente (Error AVL/R-N)");
        }
    }
}
