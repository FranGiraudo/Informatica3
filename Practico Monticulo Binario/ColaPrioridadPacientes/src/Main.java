//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MinHeapPacientes cola=new MinHeapPacientes(10);

        cola.ingresar(new Paciente("Fran", 2));
        cola.ingresar(new Paciente("Fabri", 3));
        cola.ingresar(new Paciente("Ale", 1));
        cola.ingresar(new Paciente("Manu", 2));
        cola.ingresar(new Paciente("Paciente", 1));

        System.out.println("\n Orden de atencion: ");
        while(!cola.isEmpty()){
            cola.atender();
        }
    }
}