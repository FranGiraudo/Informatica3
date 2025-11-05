import java.time.LocalDateTime;

class Quirofano implements Comparable<Quirofano> {
    String id;
    LocalDateTime finOcupado;

    public Quirofano(String id){
        this.id = id;
        this.finOcupado = LocalDateTime.now(); // libre desde ahora
    }

    @Override
    public int compareTo(Quirofano o){
        return this.finOcupado.compareTo(o.finOcupado);
    }
}
