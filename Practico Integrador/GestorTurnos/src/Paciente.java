public class Paciente{
    String dni,nombre;

    public Paciente(String dni,String nombre){
        this.dni=dni;
        this.nombre=nombre;
    }

    @Override
    public String toString(){
        return "Paciente[dni:"+dni+", nombre:"+nombre+"]";
    }
}
