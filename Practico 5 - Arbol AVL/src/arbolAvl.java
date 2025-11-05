public class arbolAvl {
    private nodoAvl raiz;

    public arbolAvl(){
        raiz=null;
    }

    private int altura(nodoAvl nodo){
        return (nodo == null) ? 0 : nodo.altura;
    }

    private int factorEquilibrio(nodoAvl nodo){
        if(nodo == null) return 0;
        return altura(nodo.izq) - altura(nodo.der);
    }

    // Rotaciones
    private nodoAvl rotacionDerecha(nodoAvl y){
        nodoAvl x = y.izq;
        nodoAvl T2 = x.der;
        x.der = y;
        y.izq = T2;
        y.altura = 1 + Math.max(altura(y.izq), altura(y.der));
        x.altura = 1 + Math.max(altura(x.izq), altura(x.der));
        return x;
    }

    private nodoAvl rotacionIzquierda(nodoAvl x) {
        nodoAvl y = x.der;
        nodoAvl T2 = y.izq;
        y.izq = x;
        x.der = T2;
        x.altura = 1 + Math.max(altura(x.izq), altura(x.der));
        y.altura = 1 + Math.max(altura(y.izq), altura(y.der));
        return y;
    }

    // EJERCICIO 1 & 2: Inserción con balanceo
    public void insertar(int dato){
        raiz = insertarRec(raiz, dato);
    }

    private nodoAvl insertarRec(nodoAvl nodo, int dato){
        if (nodo == null) return new nodoAvl(dato);
        if (dato < nodo.dato){
            nodo.izq = insertarRec(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = insertarRec(nodo.der, dato);
        } else return nodo;

        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        int fe = factorEquilibrio(nodo);

        // Rotaciones (LL, RR, LR, RL)
        if (fe > 1 && dato < nodo.izq.dato) return rotacionDerecha(nodo);
        if (fe < -1 && dato > nodo.der.dato) return rotacionIzquierda(nodo);
        if (fe > 1 && dato > nodo.izq.dato) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && dato < nodo.der.dato) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // EJERCICIO 4: Eliminación con balanceo
    public void eliminar(int dato){
        raiz = eliminarRec(raiz, dato);
    }

    private nodoAvl eliminarRec(nodoAvl nodo, int dato) {
        if (nodo == null) return null;

        if (dato < nodo.dato) nodo.izq = eliminarRec(nodo.izq, dato);
        else if (dato > nodo.dato) nodo.der = eliminarRec(nodo.der, dato);
        else {
            if (nodo.izq == null || nodo.der == null) {
                nodoAvl temp = (nodo.izq != null) ? nodo.izq : nodo.der;
                nodo = temp;
            } else {
                nodoAvl sucesor = minValorNodo(nodo.der);
                nodo.dato = sucesor.dato;
                nodo.der = eliminarRec(nodo.der, sucesor.dato);
            }
        }

        if (nodo == null) return null;
        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        int fe = factorEquilibrio(nodo);

        // Rebalanceo
        if (fe > 1 && factorEquilibrio(nodo.izq) >= 0) return rotacionDerecha(nodo);
        if (fe > 1 && factorEquilibrio(nodo.izq) < 0) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
        if (fe < -1 && factorEquilibrio(nodo.der) <= 0) return rotacionIzquierda(nodo);
        if (fe < -1 && factorEquilibrio(nodo.der) > 0) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    private nodoAvl minValorNodo(nodoAvl n) {
        nodoAvl actual = n;
        while (actual.izq != null) actual = actual.izq;
        return actual;
    }

    // EJERCICIO 5: Comprobador de AVL (Devuelve un par: [esAVL, altura])
    public int[] esAVL() {
        return esAVLRec(raiz);
    }

    private int[] esAVLRec(nodoAvl nodo) {
        if (nodo == null) return new int[]{1, 0};

        int[] resIzq = esAVLRec(nodo.izq);
        int[] resDer = esAVLRec(nodo.der);

        int esAVL = resIzq[0] * resDer[0];
        int altIzq = resIzq[1];
        int altDer = resDer[1];


        if (Math.abs(altIzq - altDer) > 1) {
            esAVL = 0;
        }


        int nuevaAltura = 1 + Math.max(altIzq, altDer);



        return new int[]{esAVL, nuevaAltura};
    }

    public void imprimirEstructura() {
        System.out.println("Árbol (dato, altura, FE):");
        imprimirEstructuraRec(raiz, "", true);
    }

    private void imprimirEstructuraRec(nodoAvl nodo, String prefijo, boolean esUltimo) {
        if (nodo == null) return;
        System.out.print(prefijo);
        System.out.print(esUltimo ? "└─ " : "├─ ");
        System.out.println(nodo.dato + " (h=" + nodo.altura + ", FE=" + factorEquilibrio(nodo) + ")");
        String prefijoHijos = prefijo + (esUltimo ? "   " : "│  ");
        if (nodo.izq != null) imprimirEstructuraRec(nodo.izq, prefijoHijos, nodo.der == null);
        if (nodo.der != null) imprimirEstructuraRec(nodo.der, prefijoHijos, true);
    }
}