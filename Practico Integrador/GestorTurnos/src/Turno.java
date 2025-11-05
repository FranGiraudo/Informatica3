import java.time.LocalDateTime;

public class Turno{
    String id,dniPaciente,matriculaMedico;
    LocalDateTime fechaHora;
    int duracionMin;
    String motivo;

    public Turno(String id,String dniPaciente,String matriculaMedico,LocalDateTime fechaHora,int duracionMin,String motivo){
        this.id=id;
        this.dniPaciente=dniPaciente;
        this.matriculaMedico=matriculaMedico;
        this.fechaHora=fechaHora;
        this.duracionMin=duracionMin;
        this.motivo=motivo;
    }

    @Override
    public String toString(){
        return "Turno[id:"+id+", paciente:"+dniPaciente+", medico:"+matriculaMedico+", fecha:"+fechaHora+", duracion:"+duracionMin+", motivo:"+motivo+"]";
    }
}
