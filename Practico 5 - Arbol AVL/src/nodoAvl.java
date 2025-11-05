class nodoAvl{
    int dato;
    nodoAvl izq;
    nodoAvl der;
    int altura;

    public nodoAvl(int dato){
        this.dato=dato;
        this.izq=null;
        this.der=null;
        this.altura=1;
    }
}