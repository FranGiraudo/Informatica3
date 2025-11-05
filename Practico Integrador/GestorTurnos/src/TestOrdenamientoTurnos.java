import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestOrdenamientoTurnos {

    private List<Paciente> pacientes;
    private Random rand;

    public TestOrdenamientoTurnos() {
        pacientes=new ArrayList<>();
        rand=new Random(42);
        generarPacientes(100); // Genera 100 pacientes de ejemplo
    }

    private void generarPacientes(int n) {
        for(int i=0;i<n;i++){
            String dni=String.valueOf(30000000+i);
            String nombre="Paciente"+i;
            pacientes.add(new Paciente(dni,nombre));
        }
    }

    public void ejecutarPruebas() {
        int[] tamaños={1000,10000,50000};
        for(int n:tamaños){
            System.out.println("\n=== Prueba con "+n+" turnos ===");

            Turno[] turnos=generarTurnosAleatorios(n);

            medirOrdenamientos(turnos, OrdenamientoTurnos.porHora(), "por hora");
            medirOrdenamientos(turnos, OrdenamientoTurnos.porDuracion(), "por duración");
            medirOrdenamientos(turnos, OrdenamientoTurnos.porApellidoPaciente(pacientes), "por apellido");
        }
    }

    private Turno[] generarTurnosAleatorios(int n) {
        Turno[] turnos=new Turno[n];
        for(int i=0;i<n;i++){
            String id="T-"+i;
            Paciente p=pacientes.get(rand.nextInt(pacientes.size()));
            String dni=p.dni;
            String medico="M-"+(rand.nextInt(10)+1);
            LocalDateTime fecha=LocalDateTime.of(2025,rand.nextInt(12)+1,rand.nextInt(28)+1,rand.nextInt(24),rand.nextInt(60));
            int duracion=15+rand.nextInt(90);
            String motivo="Motivo"+i;
            turnos[i]=new Turno(id,dni,medico,fecha,duracion,motivo);
        }
        return turnos;
    }

    private void medirOrdenamientos(Turno[] turnos, java.util.Comparator<Turno> cmp, String criterio){
        Turno[] arrInsertion=turnos.clone();
        Turno[] arrShell=turnos.clone();
        Turno[] arrQuick=turnos.clone();

        long t0=System.nanoTime();
        OrdenamientoTurnos.insertionSort(arrInsertion,cmp);
        long t1=System.nanoTime();
        OrdenamientoTurnos.shellSort(arrShell,cmp);
        long t2=System.nanoTime();
        OrdenamientoTurnos.quickSort(arrQuick,cmp,0,arrQuick.length-1);
        long t3=System.nanoTime();

        System.out.println("\nOrdenamiento "+criterio+":");
        System.out.println("InsertionSort: "+(t1-t0)/1_000_000+" ms");
        System.out.println("ShellSort:     "+(t2-t1)/1_000_000+" ms");
        System.out.println("QuickSort:     "+(t3-t2)/1_000_000+" ms");
    }
}
