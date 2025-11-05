class MedicoBloqueado implements Comparable<MedicoBloqueado> {
    String matricula;
    int minutosBloqueados;

    public MedicoBloqueado(String matricula){
        this.matricula = matricula;
        this.minutosBloqueados = 0;
    }

    @Override
    public int compareTo(MedicoBloqueado o){
        return Integer.compare(this.minutosBloqueados, o.minutosBloqueados);
    }
}
